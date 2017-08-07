package practice.webgameproject.strategy.engine;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import practice.webgameproject.strategy.engine.child.Army;
import practice.webgameproject.strategy.engine.child.BattleLogMaker;
import practice.webgameproject.strategy.engine.child.BattleLogMaker.Round;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelBuilding;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelCastleTroop;
import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelHeroTroop;
import practice.webgameproject.strategy.model.ModelMembers;
import practice.webgameproject.strategy.model.ModelOutResource;
import practice.webgameproject.strategy.model.ModelSlot;
import practice.webgameproject.strategy.model.ModelStructures;
import practice.webgameproject.strategy.model.ModelUnit;
import practice.webgameproject.strategy.model.ModelUnitBuild;
import practice.webgameproject.strategy.model.ModelWaitList_Building;
import practice.webgameproject.strategy.model.ModelWaitList_Unit;
import practice.webgameproject.strategy.model.ModelXYval;
import practice.webgameproject.strategy.service.ServiceGame;

public class Engine {
	
	private ServiceGame service;
	
	private static final int STARTING_USER_RESOURCE_AMOUNT = 0;// 신규유저 시작자원량
	private static final int BASIC_BUILDING_LEVEL = 1;// 신규 건물 초기레벨

	private static final int MAX_MARCH_PER_CASTLE = 3;// 한 성에서 최대로 전투보낼 수 있는 영웅 수
	private static final int MAX_CONSTRUCT_PER_CASTLE = 3;// 한 성에서 최대로 건설할 수 있는 수
	private static final int MAXHEROS_FOR_TAVERN = 3;//여관 최대 영웅수.
	private static final int MAX_SLOT_PER_HERO = 3;// 한 영웅이 최대로 들 수 있는 영웅 수
	
	private static final double SPECIALTY_BONUS = 1.2;// 특기병 능력치 보너스
	
	private int numberOfUnitKind;

	private List<ThreadHolder> threadsHolder;
	private List<ModelUnit> units;
	private List<List<ModelUnit>> unitTierList;
	
	//엔진 초기화
	public Engine(ServiceGame service){
		//서비스를 가져옴
		this.service = service;
		//쓰레드 홀더 초기화
		threadsHolder = new ArrayList<ThreadHolder>();
		
		//나은 제조시간을 가져옴
		List<Object> producions = this.service.getProducingList();
		for(int i=0 ; i < producions.size(); i++){
			Object target = producions.get(i);
			
			//건물인 경우
			if(target instanceof ModelWaitList_Building){
				ProductThread tr = new ProductThread();
				tr.setFinish_time(((ModelWaitList_Building) target).getWaitTime().getTime());
				tr.setTarget(target);
				tr.start();
				threadsHolder.add(new ThreadHolder(((ModelWaitList_Building) target).getLocationID(), tr));
			}
			//유닛인 경우
			if(target instanceof ModelWaitList_Unit){
				ProductThread tr = new ProductThread();
				tr.setFinish_time(((ModelWaitList_Unit) target).getWaitTime().getTime());
				tr.setTarget(target);
				tr.start();
				threadsHolder.add(new ThreadHolder(((ModelWaitList_Unit) target).getLocationID(), tr));
			}
		}
		
		//유닛 티어분류
		// TODO : 유닛이 많아지면 티어를 기억하는 테이블이 하나 더 필요하겠다. 그리고 자동화할것...손코딩 말고...
		unitTierList = new ArrayList<List<ModelUnit>>();
		units = service.getUnitInformationList();
		numberOfUnitKind = units.size();

		// TODO : 유닛을 티어별로 자동정리하려면 유닛 테이블에 티어를 입력해줘야하지 않을까
		List<ModelUnit> tier1Units = new ArrayList<ModelUnit>();
		tier1Units.add(service.getUnitInformation(IServices.UNIT_TYPE_LV1));
		unitTierList.add(tier1Units);
		
		List<ModelUnit> tier2Units = new ArrayList<ModelUnit>();
		tier2Units.add(service.getUnitInformation(IServices.UNIT_TYPE_LV2));
		unitTierList.add(tier2Units);
		
		List<ModelUnit> tier3Units = new ArrayList<ModelUnit>();
		tier3Units.add(service.getUnitInformation(IServices.UNIT_TYPE_LV3));
		unitTierList.add(tier3Units);
		
	}
	
	public boolean shutDown(){
		
		//기존 큐 테이블 제거, 생성 
		service.deleteWaitList_Building();
		service.deleteWaitList_Unit();
		

		//큐 테이블 재작성
		List<ModelWaitList_Building> buildQueue = new ArrayList<ModelWaitList_Building>();
		List<ModelWaitList_Unit> unitQueue = new ArrayList<ModelWaitList_Unit>();
		for( int i=0; i < threadsHolder.size() ; i++){
			Thread tr = threadsHolder.get(i).thread;
			if(((ProductThread)tr).target instanceof ModelWaitList_Building){
				buildQueue.add((ModelWaitList_Building)(((ProductThread)tr).getTarget()));
			}else{
				unitQueue.add((ModelWaitList_Unit)(((ProductThread)tr).getTarget()));
			}
		}
		
		service.mInsertWaitList_Building(buildQueue);
		service.mInsertWaitList_Unit(unitQueue);
		
		return true;
	}
	
	/**
	 * 로그인시 사용, 이 유저가 등록된 유저놈이었는지를 판단한다.
	 * @param member
	 * @return
	 */
	public boolean isValidLogin(ModelMembers member){
		ModelMembers result = null;
		result = service.getMember(member);
		if(result == null){
			//이거 없는 유저야!
			return false;
		}
		
		//있는 경우 패스워드 비교
		if(!member.getUserPW().equals(result.getUserPW())){
			//패스워드가 달라!
			return false;
		}
		
		//정상 로그인
		return true;
	}
	
	/**
	 * 새 유저가 로그인을 하면, 해당 유저를 위한 기본영토 등등을 만든다.
	 * @param newbie
	 * @return 에로코드 혹은 성공코드.
	 */
	private int newUserInitialize(ModelMembers newbie){
		//초기자원 설정
		newbie.setSaveProduction(STARTING_USER_RESOURCE_AMOUNT);
		//TODO: 초기 성이 완전랜덤? 어느정도 랜덤?
		ModelXYval location = service.getEmptyField();//'공터'상태인 맵 어딘가의 위치를 반납하줄 메서드.
		int result = service.insertMapObject(location,IServices.LOCATION_TYPE_CASTLE);//위치랑 타입을 가지고 맵 오브젝트를 만들어줄 메서드. 이걸로 외부자원지도 만듬.
		return result;
	}
	
	/**
	 * 회원가입 -> 뉴비에게 성을 준다.
	 * @param member
	 * @return
	 */
	public int registerMember(ModelMembers member){
		int result = IServices.ERROR_UNHANDLED_EXCEPTION;
		
		result = service.registerMember(member);
		if(result == IServices.SUCCESS){
			result = newUserInitialize(member);
		}
		return result;
	}

	/**
	 * 에러메세지가 뭔지 모르겠으면 얘한테 물어본다.
	 * @param errorCode
	 * @return
	 */
	public String getErrorMsg(int errorCode){
		return service.getErrorMsg(errorCode);
	}

	/**
	 * 새 건물 건설용 메서드.
	 * @param targetCastle
	 * @param kind
	 * @param roomNumber
	 * @return IServices.SUCCESS or error code
	 */
	public int buildStucture(ModelCastle targetCastle,int kind,int roomNumber){
		return buildStucture(targetCastle.getLocationID(), kind, roomNumber);
	}
	/**
	 * 새 건물 건설용 메서드.
	 * @param locationID
	 * @param kind
	 * @param roomNumber
	 * @return IServices.SUCCESS or error code
	 */
	public int buildStucture(int locationID,int kind,int roomNumber){
		ModelBuilding building = new ModelBuilding(locationID,kind,BASIC_BUILDING_LEVEL,roomNumber);
		int result = service.insertBuilding(building);
		return result;
	}
	
	/**
	 * 건물 업그레이드. 놈의 자원을 빼먹어라
	 * @param who
	 * @param building
	 * @return
	 */
	public int upgradeStructure(ModelMembers who, ModelBuilding building){
		return upgradeStructure(who,building.getLocationID(),building.getRoomNumber());
	}
	
	/**
	 * 건물 업그레이드. 놈의 자원을 빼먹어라
	 * @param who
	 * @param roomNumber
	 * @return
	 */
	public int upgradeStructure(ModelMembers who, Integer locationID,int roomNumber){

		int stocked_resource = who.getSaveProduction();
		ModelBuilding target = service.getBuilding(locationID,roomNumber);//
		ModelStructures structure = service.getSturcture(target.getKind());
		
		int require_resource = service.getUpgradeValue(target, structure.getValues().intValue());
		
		if(stocked_resource >= require_resource){
			//뭔가 업그레이드 시행

			who.setSaveProduction( stocked_resource - require_resource);
			//자원은 즉시 빠져야함
			service.updateMemberResource(who);
			
			//쓰레드 시작하기
			long buildTime = (new Date()).getTime() + structure.getTime().intValue();
			ModelWaitList_Building queueBuilding = new ModelWaitList_Building(new Date(buildTime), locationID, structure.getKind().intValue(), roomNumber);
			ProductThread tr = new ProductThread();
			tr.setTarget(queueBuilding);
			tr.setFinish_time(queueBuilding.getWaitTime().getTime());
			tr.start();
			threadsHolder.add(new ThreadHolder(queueBuilding.getLocationID(), tr));
			
			return IServices.SUCCESS;
		}else{
			//
			return IServices.ERROR_INVAILD_ACCESS;
		}
	}
	
	public int refreshResource(){
		/**
		 * TODO 안만들었음
		 * 반영해야하는 것
		 * 		자원 변화
		 * 		공격/회군/건설 남은시간 표기
		 */
		
		return -1;
	}
	
	/**
	 * 누가who 어디서locationID 뭘kind 얼마나amount 만들었는가
	 * @param who 
	 * @param locationID
	 * @param kind
	 * @param amount
	 * @return
	 */
	public int productUnit(ModelMembers who, Integer locationID, int kind, int amount){
		ModelUnitBuild unit_build_info = service.getUnitBuild(kind);

		return productUnit(who,locationID,unit_build_info,amount);
		
	}

	/**
	 * 누가who 어디서locationID 뭘unit 얼마나amount 만들었는가
	 * @param who
	 * @param locationID
	 * @param unit
	 * @param amount
	 * @return
	 */
	public int productUnit(ModelMembers who, Integer locationID, ModelUnit unit, int amount){
		return productUnit(who,locationID,unit.getUnitID(),amount);
	}

	/**
	 * 누가who 어디서locationID 뭘unitType 얼마나amount 만들었는가
	 * @param who
	 * @param locationID
	 * @param unitType
	 * @param amount
	 * @return
	 */
	public int productUnit(ModelMembers who, Integer locationID, ModelUnitBuild unitType, int amount){
		int stocked_resource = who.getSaveProduction();
		int require_resource = service.getUnitBuild(unitType.getUnitID()).getValues() * amount;

		if(stocked_resource >= require_resource){
			long buildTime = (new Date()).getTime() + unitType.getBuildTime().getTime();
			ModelWaitList_Unit queueUnit = new ModelWaitList_Unit(new Date(buildTime), unitType.getUnitID(), locationID, amount);
			
			ProductThread tr = new ProductThread();
			tr.setTarget(queueUnit);
			tr.setFinish_time(queueUnit.getWaitTime().getTime());
			tr.start();
			threadsHolder.add(new ThreadHolder(locationID, tr));
			return IServices.SUCCESS;
		}else{
			return IServices.ERROR_INVAILD_ACCESS;
		}
	}
	private ModelUnit getUnit(int unitID){
		ModelUnit unit = new ModelUnit(unitID, null, null, null, null);
		int index = units.indexOf(unit);
		if(index != -1){
			return units.get(index);
		}
		System.out.println("INVALID UNIT INDEX");
		return null;
	}
	
	//전투이동 명령
	public int goBattle(ModelHeroTable hero, ModelXYval targetLocation){
		return goBattle(hero, targetLocation.getLocationID());
	}
	public int goBattle(ModelHeroTable hero, int x, int y){
		ModelXYval xy = service.getModelXYval(x,y);
		return goBattle(hero,xy);
	}
	public int goBattle(ModelHeroTable hero, Integer locationID){
		//지금 좌표랑 공격목표 좌표랑 이동시간 계산
		ModelXYval startPoint = service.getModelXYval(hero.getLacationID());
		int startX = startPoint.getCastleX();
		int startY = startPoint.getCastleY();
		
		ModelXYval endPoint = service.getModelXYval(locationID);
		int endX = endPoint.getCastleX();
		int endY = endPoint.getCastleY();
		
		//HELP ME PYTHAGORAS!!!
		double travelLength = Math.sqrt(Math.pow((startX-endX), 2)-Math.pow((startY-endY), 2));
		long travelTime = (long) (travelLength * IServices.TRAVEL_UNIT_TIME);
		
		//유닛 이동속도 보정
		List<ModelHeroTroop> herosUnits = service.getHeroTroop_SlotList(hero);
		double average_unitmove_speed = 0;
		int speed_sum = 0;
		//보정 들어간 단순합
		for(int i=0; i<herosUnits.size();i++){
			// TODO : 나중에 이거 간소화좀 시켜봐
			ModelUnit unit = getUnit(service.getSlot(herosUnits.get(i).getSlotID()).getSlotUID());
			int unitSPD = unit.getSPD().intValue();
			speed_sum += correction(unitSPD, hero.getAGI(), (hero.getSpecialty().intValue() == unit.getUnitID().intValue()));
		}
		//나눔
		average_unitmove_speed = speed_sum / herosUnits.size();
		
		travelTime = (long) ((travelTime*1000)	/average_unitmove_speed);//millisecond
		
		
		//쓰레드 시작
		if(hasAddableMarch(hero.getLacationID())){
			//병력을 보낼 수 있으면 쓰레드를 붙여주고 성공 리턴
			/**
			 * 필요사항
			 * ㅁ.쓰레드를 누군가 잡고있어야함 - holder 필요
			 * ㅁ.여기서 start까지 해야함. 왜냐하면 메서드 이름이 goBattle이니까.
			 */
			MarchThread march = new MarchThread();
			march.setHero(hero);
			march.setDestination(locationID);
			march.setFinish_time(travelTime + (new Date()).getTime());//이동시간에 현재시간을 더해서 이동완료시간으로 변환
			march.setTravelLength((long)travelLength);
			march.start();
			threadsHolder.add(new ThreadHolder(hero.getLacationID(), march));
			return IServices.SUCCESS;
		}
		
		//공격 도착은 리플레시할 때 되도록 만들것. 이 메서드는 전투를 보내는 것 까지만.
		// TODO 에러 종류 "더 보낼 수 없는 상태"를 IServices에 추가.
		return IServices.ERROR_UNHANDLED_EXCEPTION;
	}

	/**
	 * 해당 성에서 추가적으로 병력을 파견할 수 있는지를 리턴.
	 * @param locationID
	 * @return
	 */
	private boolean hasAddableMarch(Integer locationID) {
		int counter = 0;
		for(int i=0; i< threadsHolder.size(); i++){
			ThreadHolder holder =threadsHolder.get(i); 
			if(holder.thread instanceof MarchThread){
				if( ((MarchThread)holder.thread).target.getLacationID() == locationID){
					counter++;
				}
			}
		}
		
		if(counter < MAX_MARCH_PER_CASTLE){
			return true;
		}
		
		return false;
	}
	
	private boolean isAliance(String owner, String userID) {
		// TODO 동맹 미구현.
		return false;
	}
	
	/**
	 * 특기 유닛에 대한 능력치 보정
	 * @param basicUnitStat
	 * @param heroStat
	 * @param isSpecialty
	 * @return
	 */
	private int correction(int basicUnitStat, int heroStat, boolean isSpecialty){
		double specialty = 1;
		if(isSpecialty){
			specialty = SPECIALTY_BONUS;
		}
		return (int)(basicUnitStat * (((double)heroStat/100) + specialty));
	}
	
	//전투 및 로그작성
	private String fight(ModelHeroTable attacker, Integer destination){
		List<ModelHeroTable> attackerOne = new ArrayList<ModelHeroTable>();
		attackerOne.add(attacker);
		return fight(attackerOne,destination);
	}

	//전투 및 로그작성
	private String fight(List<ModelHeroTable> attacker, Integer destination){
		//방어자측 정보+보정
		List<Army> defenderArms = new ArrayList<Army>();
		//방어측에 영웅이 있는지 확인
		List<ModelHeroTable> defHeros = service.getHeroList_InCastle(new ModelCastle(null, null, null, destination, null));
		Army heroArmy;
		//영웅 있는 경우
		if(defHeros != null && defHeros.size() != 0){
			//영웅 루프
			for(int i=0; i<defHeros.size(); i++){
				//영웅 한 기의 슬롯 전체를 돌며 보정 후 영웅 한 기의 휘하병력상태를 저장
				ModelHeroTable hero = defHeros.get(i);
				List<ModelHeroTroop> heroUnits = service.getHeroTroop_SlotList(hero);
				heroArmy = new Army(hero.getHeroID());
				for(int j=0; j< heroUnits.size(); j++){
					ModelSlot slot = service.getSlot(heroUnits.get(j).getSlotID());
					ModelUnit unitInfo = service.getUnitInformation(slot.getSlotUID());
					ModelUnit unit = new ModelUnit(unitInfo);
					int amount = slot.getSlotAmount();
					heroArmy.addUnit(unit, amount);
					
				}
				defenderArms.add(heroArmy);
			}
		}
		//영웅이 배정되지 않은 병력들.
		List<ModelSlot> defenders =  service.getLocalArmySlotList(destination);
		Army localArmy = new Army(IServices.HEROID_NO_HERO_TROOPS);
		for(int i=0; i<defenders.size();i++){
			ModelSlot slot = service.getSlot(defenders.get(i).getSlotID());
			ModelUnit unit = service.getUnitInformation(slot.getSlotUID());
			
			int amount = slot.getSlotAmount();
			localArmy.addUnit(unit, amount);

		}
		defenderArms.add(localArmy);
		
		return fight(attacker,defenderArms);
	}
	
	private String fight(List<ModelHeroTable> attacker, List<Army> localArmy){
		//로그 생성 준비
		BattleLogMaker logMaker = new BattleLogMaker();
		
		//공격자측 정보+보정
		List<Army> attackerArms = new ArrayList<Army>();
		int myAtkSum = 0;
		int myHpSum = 0;
		for(int i=0; i<attacker.size(); i++){
			//영웅 한 기의 슬롯 전체를 돌며 보정 후 영웅 한 기의 휘하병력상태를 저장
			ModelHeroTable hero = attacker.get(i);
			List<ModelHeroTroop> heroUnits = service.getHeroTroop_SlotList(hero);
			Army heroArmy = new Army(attacker.get(i).getHeroID());
			for(int j=0; j< heroUnits.size(); j++){
				ModelSlot slot = service.getSlot(heroUnits.get(j).getSlotID());
				ModelUnit unitInfo = service.getUnitInformation(slot.getSlotUID());
				ModelUnit unit = new ModelUnit(unitInfo);
				int amount = slot.getSlotAmount();
				int atk = correction(unit.getATK(), hero.getSTR().intValue(), (hero.getSpecialty().intValue() == slot.getSlotUID().intValue()));
				int hp = correction(unit.getHP(), hero.getCON().intValue(), (hero.getSpecialty().intValue() == slot.getSlotUID().intValue()));

				unit.setATK(atk);
				unit.setHP(hp);
				heroArmy.addUnit(unit, amount);
				
				myAtkSum += (atk*amount);
				myHpSum += (hp*amount);
				
			}
			attackerArms.add(heroArmy);
		}
		//전투 직전의 공격자 영웅들 상태를 저장
		logMaker.setAttacker(attacker);
		//전투 직전 공격자 영웅들 휘하병력 상태를 저장.
		logMaker.setAttackerArmy(attackerArms);
		
		//방어자측 정보+보정
		logMaker.setDefender(null);
		int defAtkSum = 0;
		int defHpSum = 0;
		//방어측에 영웅이 있는지 확인
		List<ModelHeroTable> defHeros = new ArrayList<ModelHeroTable>();
		for(int i=0; i<localArmy.size();i++){
			Army oneArmy = localArmy.get(i);
			Integer HeroID = oneArmy.getHeroID();

			List<ModelUnit> units = oneArmy.getUnits();
			List<Integer> unitAmounts = oneArmy.getUnitAmountList();
			if(HeroID == null || HeroID.intValue() == IServices.HEROID_NO_HERO_TROOPS){
				//영웅 없는 병력
				for(int j=0; j< units.size(); j++){
					ModelUnit unit = units.get(j);
					int amount = unitAmounts.get(j);

					defAtkSum += (unit.getATK()*amount);
					defHpSum += (unit.getHP()*amount);
				}
			}else{
				//영웅 있는 병력
				//영웅 한 기의 슬롯 전체를 돌며 보정 후 영웅 한 기의 휘하병력상태를 저장				
				ModelHeroTable hero = service.getHero(new ModelHeroTable(HeroID, null, null, null, null, null, null, null, null, null));
				for(int j=0; j< units.size(); j++){
					ModelUnit unit = units.get(j);
					int amount = unitAmounts.get(j);
					int atk = correction(unit.getATK(), hero.getSTR().intValue(), (hero.getSpecialty().intValue() == unit.getUnitID().intValue()));
					int hp = correction(unit.getHP(), hero.getCON().intValue(), (hero.getSpecialty().intValue() == unit.getUnitID().intValue()));

					unit.setATK(atk);
					unit.setHP(hp);
					defAtkSum += (atk*amount);
					defHpSum += (hp*amount);
				}
				defHeros.add(hero);
			}
		}
//		logMaker.setDefender(defHeros.size()==0? null: defHeros);
		logMaker.setDefender(defHeros);
		logMaker.setDefenderArmy(localArmy);
		//정보저장 끝

		//이제 전투
		/**
		 * attackerArms : 공격 가는 영웅들의 슬롯정보. List<Army>
		 * defenderArms : 방어자 슬롯정보 List.  List<Army>
		 * 
		 * myAtkSum: 공격자 병력의 총공격력
		 * myHpSum: 공격자 병력의 총체력
		 * defAtkSum: 방어자 병력의 총공격력
		 * defHpSum : 방어자 병력의 총체력
		 * 
		 * 각 회합당 총 공격력을 통하여 서로에게 데미지를 주고, 해당 데미지를 각자의 모든 슬롯에서 나눠받는다.
		 * 최대 회합수가 넘거나 어느 한 쪽의 총체력이 0 이하가 되면 전투종료.
		 * 
		 */
		boolean isContinusBattle = false;
		do{
			//라운드가 진행되면서 점차 깎일 공격력
			int tempMyAtk = myAtkSum;
			int tempDefAtk = defAtkSum;
			//일단 공격자측 피해입음
			/**
			 * 이 루프를 다 돌게되었을 때 생길 수 있는 경우
			 * 모든 제대를 다 돌지 못한 채 잔여딜 소멸(딜부족)	-	루프를 빠져나간다.
			 * 모든 제대를 다 돌고도 상대 공격력이 여전히 양수인 경우(오버딜) - 루프를 빠져나가고 패배
			 */
			for(int i=0; i<attackerArms.size(); i++){
				Army arm = attackerArms.get(i);
				List<ModelUnit> units = arm.getUnits();
				List<Integer> unitAmount = arm.getUnitAmountList();
				
				if(tempDefAtk <= 0){
					//딜부족이면 루프를 더이상 돌 필요가 없음
					break;
				}
				//공격력이 남으면 다음 슬롯을 돌게됨
				/**
				 * 이 루프를 다 돌게되었을 때 생길 수 있는 경우
				 * 영웅 슬롯을 다 돌지 못한 채 잔여딜 소멸(딜부족)	-	루프를 빠져나간다.
				 * 영웅의 모든 슬롯을 돌았지만 상대 공격력이 여전히 양수인 경우(모든 줄 1개씩 까고도 오버딜) - 다시 슬롯 처음부터 루프를 돈다
				 * 전멸하고도 오버딜인 경우 - 다음 영웅이 피해를 입는다.
				 * 						다음 영웅이 없는 경우 - 패배하는거지 뭐
				 */
				for(int j=0; j < units.size(); j++){
					ModelUnit unit = units.get(j);
					int amount = unitAmount.get(j);
					//저념ㄹ한 경우 다음 슬롯을 본다.
					if(amount <= 0 ) continue;
					
					int unithp = unit.getHP().intValue();
					
					//딜 다 받아냈고, 잔여딜은 무효화.
					if(unithp > tempDefAtk){
						tempDefAtk = tempDefAtk - unithp;//음수로 만들어서 루프를 빠져나가도록 함.
						break;
					}

					//유닛이 죽어야하는 경우
					//죽은 유닛 체력만큼 상대 공격력을 깎고 유닛수를 1기 줄임.
					tempDefAtk = tempDefAtk - unithp;
					myHpSum = myHpSum - unithp;
					myAtkSum = myAtkSum - unit.getATK().intValue();
					unitAmount.set(j, amount-1);
					
					//한줄이 모두 1씩 감소했는데 여전히 오버딜을 받은 상태인 경우
					if(tempDefAtk > 0 && j == units.size()-1){
						//다시 첫번째 슬롯부터 딜을 받는다.
						//이 경우 이 영웅네가 전멸인 경우랑 그렇지 않은 경우가 있는데
						if(arm.isDefeat()){
							//전멸인 경우 루프를 나가야함
							break;
						}else{
							//전멸이 아닌 경우 다시 첫 슬롯부터 깜.
							j=0;
						}
					}
				}//한 영웅이 쳐맞는 딜.
			}//방어측 공격종료
				
			//방어자측 피해입음
			/**
			 * 이 루프를 다 돌게되었을 때 생길 수 있는 경우
			 * 모든 제대를 다 돌지 못한 채 잔여딜 소멸(딜부족)	-	루프를 빠져나간다.
			 * 모든 제대를 다 돌고도 상대 공격력이 여전히 양수인 경우(오버딜) - 루프를 빠져나가고 패배
			 */
			for(int i=0; i<localArmy.size(); i++){
				Army arm = localArmy.get(i);
				List<ModelUnit> units = arm.getUnits();
				List<Integer> unitAmount = arm.getUnitAmountList();
				
				if(tempMyAtk <= 0){
					//딜부족이면 루프를 더이상 돌 필요가 없음
					break;
				}
				//공격력이 남으면 다음 슬롯을 돌게됨
				/**
				 * 이 루프를 다 돌게되었을 때 생길 수 있는 경우
				 * 영웅 슬롯을 다 돌지 못한 채 잔여딜 소멸(딜부족)	-	루프를 빠져나간다.
				 * 영웅의 모든 슬롯을 돌았지만 상대 공격력이 여전히 양수인 경우(모든 줄 1개씩 까고도 오버딜) - 다시 슬롯 처음부터 루프를 돈다
				 * 전멸하고도 오버딜인 경우 - 다음 영웅이 피해를 입는다.
				 * 						다음 영웅이 없는 경우 - 패배하는거지 뭐
				 */
				for(int j=0; j < units.size(); j++){
					ModelUnit unit = units.get(j);
					int amount = unitAmount.get(j);
					//저념ㄹ한 경우 다음 슬롯을 본다.
					if(amount <= 0 ) continue;
					
					int unithp = unit.getHP().intValue();
					
					//딜 다 받아냈고, 잔여딜은 무효화.
					if(unithp > tempMyAtk){
						tempMyAtk = tempMyAtk - unithp;//음수로 만들어서 루프를 빠져나가도록 함.
						break;
					}

					//유닛이 죽어야하는 경우
					//죽은 유닛 체력만큼 상대 공격력을 깎고 유닛수를 1기 줄임.
					tempMyAtk = tempMyAtk - unithp;
					defHpSum = defHpSum - unithp;
					defAtkSum = defAtkSum - unit.getATK().intValue();
					unitAmount.set(j, amount-1);
					
					//한줄이 모두 1씩 감소했는데 여전히 오버딜을 받은 상태인 경우
					if(tempMyAtk > 0 && j == units.size()-1){
						//다시 첫번째 슬롯부터 딜을 받는다.
						//이 경우 이 영웅네가 전멸인 경우랑 그렇지 않은 경우가 있는데
						if(arm.isDefeat()){
							//전멸인 경우 루프를 나가야함
							break;
						}else{
							//전멸이 아닌 경우 다시 첫 슬롯부터 깜.
							j=0;
						}
					}
				}//한 영웅이 쳐맞는 딜.
			}//공격측 공격종료
			
			//라운드 종료. 저장할것.
			//addRound는 라운드 추가에 성공시 TRUE를 리턴
			isContinusBattle =  logMaker.addRound(attackerArms, localArmy);
		}while(myHpSum>0 && defHpSum>0 && isContinusBattle);
		
		/**
		 * 양측 전멸
		 * 양측 모두 딜부족으로 무승부
		 * 공격자만 전멸
		 * 방어자만 전멸
		 * 
		 * 그런데 어느 쪽이든 병력 소모된 양을 구해서 DB갱신하면 되잖아?
		 * 어차피 승패는 로그보면 암;
		 */
		
		//로그파일 쓰기
		logMaker.writeLog();
		// TODO 2.로그정보를 DB에 넣기
//		service.inser
		
		
		
		//로그 이름을 반환하여 찾을 수 있도록 함
		return logMaker.getLogName();
	}
	
	public List<ModelHeroTable> makeHero(){
		List<ModelHeroTable> heros = new ArrayList<ModelHeroTable>();

		for(int i=0; i< MAXHEROS_FOR_TAVERN ; i++){
			
		Integer str = (int)(Math.random()*10)+1;
		Integer agi = (int)(Math.random()*10)+1;
		Integer con = 10 - (str + agi);
		Integer specialty = (int)(Math.random()*(numberOfUnitKind-1))+1;
		Integer potrait = null;//TODO 준비된 랜덤포트릿을 집어넣는다.
		boolean sex = (int)(Math.random()*1)==0?true:false;
		
		ModelHeroTable hero = new ModelHeroTable(null, str, agi, con, null, null, null, specialty, potrait, sex);
		heros.add(hero);
		
		}
		
		return heros;
	}
	
	public int recruitHero(ModelMembers user, List<ModelHeroTable> list, int index){
		ModelHeroTable who = null;
		try{

			who = list.get(index);
			who.setOwner(user.getUserID());
			service.insertHerotable(who);
			
			
		}catch (ArrayIndexOutOfBoundsException e) {
			return IServices.ERROR_INVAILD_ACCESS;
		}
		
		return IServices.SUCCESS;
	}
	
	public int fireHero(ModelMembers usr, Integer HeroID){
		ModelHeroTable targetHero = new ModelHeroTable(HeroID, null, null, null, null, null, null, null, null, null);
		targetHero = service.getHero(targetHero);
		if(usr.getUserID().equals(targetHero.getOwner())){
			//삭제하려고 함

			List<ModelHeroTroop> slots = service.getHeroTroop_SlotList(targetHero);
			for(int i=0; i< slots.size() ; i++){
				service.deleteSlot(slots.get(i).getSlotID());
			}
			service.deleteHeroTroop(HeroID);
			service.deleteHeroTable(HeroID);
			
			
			return IServices.SUCCESS;
		}else{
			//지 영웅이 아닌데 삭제시도함
			return IServices.ERROR_INVAILD_ACCESS;
		}
	}
	
	
	//진격지 도착
	private void makeBattle(ModelHeroTable target, Integer targetLocationID, long distance) {
		// 도착한 곳은 어떤곳인가
		ModelXYval xy =  service.getModelXYval(targetLocationID);
		int targetLocationKind = xy.getKind();
		
		switch(targetLocationKind){
		case IServices.LOCATION_TYPE_CASTLE://성
			ModelCastle targetCastle =service.getCastleOne(targetLocationID);
			if(target.getOwner().equals(targetCastle.getUserID())){
				//자신의 성에서 자신의 성으로
				
			}else if(isAliance(target.getOwner(), targetCastle.getUserID())){
				//동맹 성으로 보낸 경우
				//(동맹 미구현)
				target.setStatuus(targetLocationID);
				
			}else{
				//적 성. 전투개시
				fight(target, targetLocationID);
			}
			
			break;
		case IServices.LOCATION_TYPE_NORMAL://맨땅
			//개척선택 (미구현)
			
			//필드전투
			//일반화
			List<ModelHeroTable> attacker = new ArrayList<ModelHeroTable>();
			attacker.add(target);
			
			//잡몹 생성
			List<Army> localArmy = makeCreeps(distance);
			fight(attacker, localArmy);
			break;
		case IServices.LOCATION_TYPE_EXTERNALRESOURCE://야외자원지
			ModelOutResource outResource = service.getOutResource(targetLocationID);
			if(target.getOwner().equals(outResource.getUserID())){
				//자신의 성에서 자신의 자원지로
				target.setStatuus(targetLocationID);
				
			}else if(isAliance(target.getOwner(), outResource.getUserID())){
				//동맹 자원지로 보낸 경우
				//(동맹 미구현)
			}else{
				//적이거나 중립. 전투개시.
				//일반화
				attacker = new ArrayList<ModelHeroTable>();
				attacker.add(target);
				
				//잡몹 생성
				localArmy = makeCreeps(distance);
				fight(attacker, localArmy);
			}
			break;
		}
	}
	

	/**
	 * 중립병력을 만들어주는 메서드
	 * @param distance
	 * @return
	 */
	private List<Army> makeCreeps(double distance){
		return makeCreeps(distance,false);
	}
	private List<Army> makeCreeps(double distance,boolean isNeturalCastle){
		List<Army> creeps = new ArrayList<Army>();
		final int creepLevelCut = 2;//every 2 distance
		int creepLevel = (int) Math.ceil(distance)/creepLevelCut;

		// TODO 영웅이 출몰하는 경우가 있다면 이 메서드를 새로 만들 필요가 있다.
		// 지휘하는 영웅이 없는 부대 생성.
		Army army = new Army(IServices.HEROID_NO_HERO_TROOPS);
		int slotAmount = (int)Math.random()*(MAX_SLOT_PER_HERO-1) + 1;//최소 1개슬롯 보장
		for(int i=0; i< slotAmount; i++){
			if(creepLevel < Math.pow(creepLevelCut, creepLevelCut)){
				// 1 단계 이하의 유닛을 랜덤하게 가져와서 level+1개만큼 뿌려주자!
				ModelUnit unit = getRandomUnitAtTier(1);
				int amount = (int)Math.random()*(creepLevel+1)+5;//랜덤이되 최소치 보장
				army.addUnit(unit, amount);
			}else if(creepLevel < Math.pow(creepLevelCut, creepLevelCut+1)){
				// 2 단계 이하의 유닛을 랜덤하게 가져와서 level+1개만큼 뿌려주자!
				int tier = (int)Math.random()*(2 - 1) + 1;
				ModelUnit unit = getRandomUnitAtTier(tier);
				int amount = (int)Math.random()*(creepLevel+1)+10;
				army.addUnit(unit, amount);
			}else{
				// 3 단계 이하의 유닛을 랜덤하게 가져와서 level+1개만큼 뿌려주자!
				int tier = (int)Math.random()*(3 - 1) + 1;
				ModelUnit unit = getRandomUnitAtTier(tier);
				int amount = (int)Math.random()*(creepLevel+1)+10;
				army.addUnit(unit, amount);
			}
		}
		
		return creeps;
	}
	
	private ModelUnit getRandomUnitAtTier(int tier){
		int index = (int)(Math.random()*unitTierList.get(tier).size());
		return unitTierList.get(tier).get(index);
	}


	private class ThreadHolder{
		Integer locationID;
		Thread thread;
		
		public ThreadHolder(Integer locationID, Thread thread) {
			this.locationID = locationID;
			this.thread = thread;
		}
		public ThreadHolder(Integer locationID) {
			this.locationID = locationID;
		}
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof ThreadHolder){
				ThreadHolder target = (ThreadHolder) obj;
				if(locationID.intValue() == target.locationID.intValue()){
						return target.equals(thread);
				}else{
					return false;
				}
			}
			
			return false;
		}
	}


	private class ProductThread extends Thread{
		private long finish_time = -1;
		private boolean quickdone = false;
		private Object target = null;
		
		public void setFinish_time(long finish_time) {
			this.finish_time = finish_time;
		}
		
		public void setTarget(Object target){
			this.target = target;
		}
		
		public Object getTarget() {
			return target;
		}
		
		@Override
		public void run() {
			//wait until time elapsed or quick done
			while((new Date()).getTime() - finish_time >= 0 && !quickdone);
			
			//건물인 경우
			if(target instanceof ModelWaitList_Building){
				int locationID = ((ModelWaitList_Building) target).getLocationID().intValue();
				int roomNumber = ((ModelWaitList_Building) target).getRoomNumber().intValue();
				ModelBuilding building = service.getBuilding(locationID, roomNumber);
				//레벨업
				building.setLevel(building.getLevel()+1);
				service.updateBuilding(building);
				return;
			}
			//유닛인 경우
			if(target instanceof ModelWaitList_Unit){
				int locationID = ((ModelWaitList_Unit) target).getLocationID().intValue();
				int amount = ((ModelWaitList_Unit) target).getAmount().intValue();
				List<ModelCastleTroop> troops = service.getCastleTroops(locationID);
				boolean isAdded = false;
				
				//이미 해당 유닛종류가 성에 있는 경우.
				for(int i=0; i< troops.size();i++){
					ModelSlot slot = service.getSlot(troops.get(i).getSlotID());
					if(slot.getSlotUID().intValue() == ((ModelWaitList_Unit) target).getUnitID().intValue()){
						//수량 증가시키고 나감
						slot.setSlotAmount(slot.getSlotAmount() + ((ModelWaitList_Unit) target).getAmount());
						isAdded = true;
						break;
					}
				}
				
				if(!isAdded){
					//해당 유닛이 성에 전혀 없던 경우. 슬롯을 만들어서 해당 성에 박아놓기
					ModelSlot slot = new ModelSlot(((ModelWaitList_Unit) target).getUnitID(), amount);
					service.insertSlotToCastle(locationID, slot);
				}
				return;
			}
		}
		@Override
		public boolean equals(Object obj) {
			//로케이션 아이디만 주거나 프로덕트 쓰레드로 주면
			//로케이션 아이디가 같으면 같은 쓰레드로 취급?
			//한 성에서 여러 건물을 짓는 경우가 발생할 수 있기때문에 건물의 경우 룸넘버도 같아야함
			//한 성에서 여러 유닛을 만드는 경우가 발생할 수 있기때문에 
			if(obj instanceof Integer){
				Integer integer = (Integer) obj;
				if(target instanceof ModelWaitList_Building){
					return ((ModelWaitList_Building) target).getLocationID().intValue() == integer.intValue();
				}
				if(target instanceof ModelWaitList_Unit){
					return ((ModelWaitList_Unit) target).getLocationID().intValue() == integer.intValue();
				}
			}
			if(obj instanceof ProductThread){
				ProductThread thread = (ProductThread) obj;
				if(thread.target instanceof ModelWaitList_Building){
					int intvalue = ((ModelWaitList_Building) thread.target).getLocationID();
					if( intvalue != ((ModelWaitList_Building) this.target).getLocationID()){
						return false;
					}
					intvalue = ((ModelWaitList_Building) thread.target).getRoomNumber();
					if( intvalue != ((ModelWaitList_Building) this.target).getRoomNumber()){
						return false;
					}
					return true;
				}
				if(target instanceof ModelWaitList_Unit){
					int intvalue = ((ModelWaitList_Unit) thread.target).getLocationID();
					if( intvalue != ((ModelWaitList_Unit) this.target).getLocationID()){
						return false;
					}
					intvalue = ((ModelWaitList_Unit) thread.target).getRoomNumber();
					intvalue = ((ModelWaitList_Unit) thread.target).getRoomNumber();
					if( intvalue != ((ModelWaitList_Unit) this.target).getRoomNumber()){
						return false;
					}
					return true;
					
				}
			}
			return super.equals(obj);
		}
	}
	
	private class MarchThread extends Thread{
		private long finish_time = -1;
		private boolean order_return = false;
		private boolean status_isAttacking = false;
		private ModelHeroTable target = null;
		private long timeleft = 999999999; // 남은시간 반환
		private Integer targetLocationID;
		private long travelLength;
		

		public MarchThread() {
			super();
			this.order_return = false;
			this.status_isAttacking = true;
		}

		public long getTravelLength() {
			return travelLength;
		}
		public void setTravelLength(long travelLength) {
			this.travelLength = travelLength;
		}



		public void setFinish_time(long finish_time) {
			this.finish_time = finish_time;
		}
		
		public void setHero(ModelHeroTable target){
			this.target = target;
		}
		
		public ModelHeroTable getHero(){
			return target;
		}
		
		public long getTimeLeft(){
			return timeleft;
		}
		
		public boolean getIsAttacking(){
			return status_isAttacking;
		}
		public void setIsAttacking(boolean isAttacking){
			this.status_isAttacking = isAttacking;
		}
		
		
		public void setDestination(Integer locationID){
			targetLocationID = locationID;
		}
		public Integer getDestination(){
			return targetLocationID;
		}
		
		/**
		 * order hero to return base
		 */
		public void returnOrderToHero(){
			this.order_return = true;
		}
		
		@Override
		public void run() {
			long startTime = (new Date()).getTime();
			long currentTime = startTime;

			status_isAttacking = true;			
			timeleft = currentTime - finish_time; 
			//wait until time elapsed or quick done
			while(timeleft >= 0 && !order_return){
				currentTime = (new Date()).getTime();
				timeleft = currentTime - finish_time; 
			}
			
			//arrived war location
			if(!order_return && status_isAttacking){
				//뒤돌아가는 상태가 아니면
				// 전투 쾅
				makeBattle(getHero(), getDestination(), getTravelLength());
				
			}
			status_isAttacking = false;			
			//회군명령 또는 전투쾅 후 회군중
			//지나갔던 시간만큼 되돌아오기.
			long 가던시간 = currentTime - startTime + (new Date()).getTime();
			//이 order_return이 다시 true가 되면 즉시회군(과금아이템?).
			order_return = false;
			while(timeleft >= 0 && !order_return){
				currentTime = (new Date()).getTime();
				timeleft = currentTime - 가던시간;
			}
			//도착은 페이지 새로고침될 때 이 쓰레드를 isAlive()를 호출함으로 알 수 있을걸?
			//end of method
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof ModelHeroTable){
				ModelHeroTable model = (ModelHeroTable) obj;
				return model.equals(target);
			}
			if(obj instanceof MarchThread){
				MarchThread marchThread = (MarchThread) obj;
				return this.getHero().equals(marchThread.getHero());
			}
			if(obj instanceof Integer){
				int heroID = (int) obj;
				return this.getHero().getHeroID().intValue() == heroID;
			}
			return false;
		}

	}


}
