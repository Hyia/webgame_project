package practice.webgameproject.strategy.engine;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelBuilding;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelCastleTroop;
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

	//엔진 초기화
	public Engine(ServiceGame service){
		//서비스를 가져옴
		this.service = service;
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
			}
			//유닛인 경우
			if(target instanceof ModelWaitList_Unit){
				ProductThread tr = new ProductThread();
				tr.setFinish_time(((ModelWaitList_Unit) target).getWaitTime().getTime());
				tr.setTarget(target);
				tr.start();
			}
		}
	}
	
	/**
	 * 로그인시 사용, 이 유저가 등록된 유저놈이었는지를 판단한다.
	 * @param member
	 * @return
	 */
	public boolean IsValidLogin(ModelMembers member){
		ModelMembers result = null;
		result = service.getMember(member);
		if(result == null){
			//이거 없는 유저야!
			return false;
		}
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
			
			return IServices.SUCCESS;
		}else{
			//
			return IServices.ERROR_INVAILD_ACCESS;
		}
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
			
			return IServices.SUCCESS;
		}else{
			return IServices.ERROR_INVAILD_ACCESS;
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
			while((new Date()).getTime() - finish_time >= 0 || !quickdone);
			
			//건물인 경우
			if(target instanceof ModelWaitList_Building){
				int locationID = ((ModelWaitList_Building) target).getLocationID().intValue();
				int roomNumber = ((ModelWaitList_Building) target).getRoomNumber();
				ModelBuilding building = service.getBuilding(locationID, roomNumber);
				//레벨업
				building.setLevel(building.getLevel()+1);
				service.updateBuilding(building);
			}
			//유닛인 경우
			if(target instanceof ModelWaitList_Unit){
				int locationID = ((ModelWaitList_Unit) target).getLocationID().intValue();
				int amount = ((ModelWaitList_Unit) target).getAmount().intValue();
				List<ModelCastleTroop> troops = service.getCastleTroops(locationID);
				boolean isAdded = false;
				
				for(int i=0; i< troops.size();i++){
					ModelSlot slot = service.getSlot(troops.get(i).getSlotID());
					if(slot.getSoltUID().intValue() == ((ModelWaitList_Unit) target).getUnitID().intValue()){
						//수량 증가시키고 나감
						slot.setSoltAmount(slot.getSoltAmount() + ((ModelWaitList_Unit) target).getAmount());
						isAdded = true;
						break;
					}
				}
				
				if(!isAdded){
					//해당 유닛이 성에 전혀 없던 경우
					
				}
			}
		
		}
	}
	

}
