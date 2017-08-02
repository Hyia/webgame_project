package practice.webgameproject.strategy.engine;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelBuilding;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelCastleTroop;
import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelHeroTroop;
import practice.webgameproject.strategy.model.ModelMembers;
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

	private List<ThreadHolder> threadsHolder;
	
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
		int result = service.insertMapObject(location,service.LOCATION_TYPE_CASTLE);//위치랑 타입을 가지고 맵 오브젝트를 만들어줄 메서드. 이걸로 외부자원지도 만듬.
		return result;
	}
	
	/**
	 * 회원가입 -> 뉴비에게 성을 준다.
	 * @param member
	 * @return
	 */
	public int registerMember(ModelMembers member){
		int result = service.ERROR_UNHANDLED_EXCEPTION;
		
		result = service.registerMember(member);
		if(result == service.SUCCESS){
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
		 * 반영해야하는 것
		 * 		자원 변화
		 * 		공격/회군/건설 남은시간 표기
		 */
		
		return -1;
	}
	
	
	public int productUnit(ModelMembers who, Integer locationID, int kind, int amount){
		ModelUnitBuild unit_build_info = service.getUnitBuild(kind);

		return productUnit(who,locationID,unit_build_info,amount);
		
	}
	public int productUnit(ModelMembers who, Integer locationID, ModelUnit unit, int amount){
		return productUnit(who,locationID,unit.getUnitID(),amount);
	}
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
		for(int i=0; i<herosUnits.size();i++){
			//TODO : 나중에 이거 간소화좀 시켜봐
			speed_sum += service.getUnitInformation(service.getSlot(herosUnits.get(i).getSlotID()).getSlotUID()).getSPD().intValue();
		}
		average_unitmove_speed = speed_sum / herosUnits.size();
		int heroAGI = hero.getAGI();
		
		travelTime = (long) (travelTime	/average_unitmove_speed);
		
		
		//쓰레드 시작
		//보내는 놈의 유저정보 조회
		ModelMembers owner = new ModelMembers(hero.getOwner(), null, null, null);
		if(hasAddableMarch(hero.getLacationID())){
			//병력을 보낼 수 있으면 쓰레드를 붙여주고 성공 리턴
			/**
			 * 필요사항
			 * ㅁ.쓰레드를 누군가 잡고있어야함 - holder 필요
			 * ㅁ.여기서 start까지 해야함. 왜냐하면 메서드 이름이 goBattle이니까.
			 */
			MarchThread march = new MarchThread();
			march.setHero(hero);
			march.setFinish_time(travelTime + (new Date()).getTime());//이동시간에 현재시간을 더해서 이동완료시간으로 변환
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
		
		public MarchThread() {
			super();
			this.order_return = false;
			this.status_isAttacking = false;
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
		
		
		public boolean getIsAttacking(){
			return status_isAttacking;
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
			//wait until time elapsed or quick done
			while(timeleft >= 0 && !order_return){
				currentTime = (new Date()).getTime();
				timeleft = currentTime - finish_time; 
			}
			
			//arrived war location
			if(!order_return){
				//뒤돌아가는 상태가 아니면
				// TODO 1.전투 쾅
				
				// TODO 2.로그파일 쓰기
				
				// TODO 2.로그정보를 DB에 넣기
				
			}
			
			status_isAttacking = false;			
			//회군명령 또는 전투쾅 후 회군중
			//지나갔던 시간만큼 되돌아오기.
			long 가던시간 = currentTime - startTime + (new Date()).getTime();
			while(timeleft >= 0 && !order_return){
				currentTime = (new Date()).getTime();
				timeleft = currentTime - 가던시간;
			}
			//도착은 페이지 새로고침될 때 이 쓰레드를 isAlive()를 호출함으로 알 수 있을걸?
			//end of method
		}
	}


}
