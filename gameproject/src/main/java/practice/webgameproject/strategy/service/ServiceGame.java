package practice.webgameproject.strategy.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import practice.webgameproject.strategy.dao.DaoGame;
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

@Service("serviceGame")
public class ServiceGame extends DaoGame{
	
	@Autowired
    @Qualifier("daogame")
	DaoGame dao;
	
	 private static Logger logger = LoggerFactory.getLogger(ServiceGame.class);

	@Override
	public ModelMembers getMember(ModelMembers targetMember) {
        ModelMembers result=new ModelMembers();
        
        try {
        	result = dao.getMember(targetMember);
        } catch (Exception e) {
            logger.error("getMember " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public Integer getMembersLogin(ModelMembers targetMember) {
        
		Integer result=-1;
        
        try {
        	result = dao.getMembersLogin(targetMember);
        } catch (Exception e) {
            logger.error("getMembersLogin " + e.getMessage() );
        }
        
        return result;
	}


	@Override
	public Integer insertMembers(ModelMembers newMember) {
		
		int result=-1;
        
        try {
        	result = dao.insertMembers(newMember);
        } catch (Exception e) {
            logger.error("insertMembers " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer updateMembers(ModelMembers updateMembers, ModelMembers searchMembers) {
		// TODO Auto-generated method stub
		return super.updateMembers(updateMembers, searchMembers);
	}

	@Override
	public Integer deleteMembers(ModelMembers members) {
		// TODO Auto-generated method stub
		return super.deleteMembers(members);
	}

	@Override
	public ModelUnit getUnitInformation(Integer UnitID) {
		// TODO Auto-generated method stub
		return super.getUnitInformation(UnitID);
	}

	@Override
	public List<ModelUnitBuild> getUnitBuild(ModelUnitBuild targetUnitBuild) {
		// TODO Auto-generated method stub
		return super.getUnitBuild(targetUnitBuild);
	}

	@Override
	public List<ModelStructures> getStructures(ModelStructures targetStructures) {
		// TODO Auto-generated method stub
		return super.getStructures(targetStructures);
	}

	@Override
	public List<ModelXYval> getAllXYval() {
		// TODO Auto-generated method stub
		return super.getAllXYval();
	}

	@Override
	public Integer updateSlot(ModelSlot updateSlot, ModelSlot serchSlot) {
		// TODO Auto-generated method stub
		return super.updateSlot(updateSlot, serchSlot);
	}

	@Override
	public Integer deleteXYval(Integer locationID) {
		// TODO Auto-generated method stub
		return super.deleteXYval(locationID);
	}

	@Override
	public Integer deleteSlot(Integer slotID) {
		// TODO Auto-generated method stub
		return super.deleteSlot(slotID);
	}

	@Override
	public List<ModelCastle> getCastleList(ModelMembers targetUser) {
		// TODO Auto-generated method stub
		return super.getCastleList(targetUser);
	}

	@Override
	public ModelCastle getCastleOne(Integer locationID) {
		// TODO Auto-generated method stub
		return super.getCastleOne(locationID);
	}

	@Override
	public List<ModelHeroTable> getHeroList_InCastle(ModelCastle targetCastle) {
		// TODO Auto-generated method stub
		return super.getHeroList_InCastle(targetCastle);
	}

	@Override
	public List<ModelSlot> getCastleTroop_SlotList(ModelCastle targetTroop) {
		// TODO Auto-generated method stub
		return super.getCastleTroop_SlotList(targetTroop);
	}

	@Override
	public ModelXYval getXYval_LocationID(Integer locationID) {
		// TODO Auto-generated method stub
		return super.getXYval_LocationID(locationID);
	}

	@Override
	public ModelXYval getXYval_XY(ModelXYval targetXYval) {
		// TODO Auto-generated method stub
		return super.getXYval_XY(targetXYval);
	}

	@Override
	public List<ModelWaitList_Building> getWaitList_Building(ModelCastle targetWitList) {
		// TODO Auto-generated method stub
		return super.getWaitList_Building(targetWitList);
	}

	@Override
	public List<ModelWaitList_Unit> getWaitList_Unit(ModelCastle targetWitList) {
		// TODO Auto-generated method stub
		return super.getWaitList_Unit(targetWitList);
	}

	@Override
	public List<ModelBuilding> getBuilding(ModelCastle targetBuilding) {
		// TODO Auto-generated method stub
		return super.getBuilding(targetBuilding);
	}

	@Override
	public Integer getAllProduction(ModelCastle targetCastleProduction) {
		// TODO Auto-generated method stub
		return super.getAllProduction(targetCastleProduction);
	}

	@Override
	public ModelOutResource getOutResource(Integer locationID) {
		// TODO Auto-generated method stub
		return super.getOutResource(locationID);
	}

	@Override
	public Integer insertCastle(ModelCastle newCastle) {
		// TODO Auto-generated method stub
		return super.insertCastle(newCastle);
	}

	@Override
	public Integer insertXYval(ModelXYval newXYval) {
		// TODO Auto-generated method stub
		return super.insertXYval(newXYval);
	}

	@Override
	public Integer insertWaitBuildingList(ModelWaitList_Building newBuildingList) {
		// TODO Auto-generated method stub
		return super.insertWaitBuildingList(newBuildingList);
	}

	@Override
	public Integer insertWaitUnitgList(ModelWaitList_Unit newUnitList) {
		// TODO Auto-generated method stub
		return super.insertWaitUnitgList(newUnitList);
	}

	@Override
	public Integer insertCastletroop(ModelCastleTroop newCastleTroop) {
		// TODO Auto-generated method stub
		return super.insertCastletroop(newCastleTroop);
	}

	@Override
	public Integer insertSlot(ModelSlot newSlot) {
		// TODO Auto-generated method stub
		return super.insertSlot(newSlot);
	}

	@Override
	public Integer insertOutResource(ModelOutResource newOutResource) {
		// TODO Auto-generated method stub
		return super.insertOutResource(newOutResource);
	}

	@Override
	public Integer mInsertWaitList_Building(List<ModelWaitList_Building> list) {
		// TODO Auto-generated method stub
		return super.mInsertWaitList_Building(list);
	}

	@Override
	public Integer mInsertWaitList_Unit(List<ModelWaitList_Unit> list) {
		// TODO Auto-generated method stub
		return super.mInsertWaitList_Unit(list);
	}

	@Override
	public Integer updateCastle(ModelCastle updateCastle, ModelCastle serchCastle) {
		// TODO Auto-generated method stub
		return super.updateCastle(updateCastle, serchCastle);
	}

	@Override
	public Integer updateBuilding(ModelBuilding updateBuilding, ModelBuilding serchBuilding) {
		// TODO Auto-generated method stub
		return super.updateBuilding(updateBuilding, serchBuilding);
	}

	@Override
	public Integer updateOutResource(ModelOutResource updateOutResource, ModelOutResource serchOutResource) {
		// TODO Auto-generated method stub
		return super.updateOutResource(updateOutResource, serchOutResource);
	}

	@Override
	public Integer updateWaitList_Building(ModelWaitList_Building updateBudingList,
			ModelWaitList_Building serchBudingList) {
		// TODO Auto-generated method stub
		return super.updateWaitList_Building(updateBudingList, serchBudingList);
	}

	@Override
	public Integer updateWaitList_Unit(ModelWaitList_Unit updateUnitList, ModelWaitList_Unit serchUnitList) {
		// TODO Auto-generated method stub
		return super.updateWaitList_Unit(updateUnitList, serchUnitList);
	}

	@Override
	public Integer deleteCastleTroop(Integer locationID) {
		// TODO Auto-generated method stub
		return super.deleteCastleTroop(locationID);
	}

	@Override
	public Integer deleteCastleBuildings(Integer locationID) {
		// TODO Auto-generated method stub
		return super.deleteCastleBuildings(locationID);
	}

	@Override
	public Integer deleteOutResource(Integer locationID) {
		// TODO Auto-generated method stub
		return super.deleteOutResource(locationID);
	}

	@Override
	public Integer deleteCastle(Integer userID) {
		// TODO Auto-generated method stub
		return super.deleteCastle(userID);
	}

	@Override
	public ModelHeroTable getHero(ModelHeroTable targetHero) {
		// TODO Auto-generated method stub
		return super.getHero(targetHero);
	}

	@Override
	public List<ModelHeroTroop> getHeroTroop_SlotList(ModelHeroTable targetTroop) {
		// TODO Auto-generated method stub
		return super.getHeroTroop_SlotList(targetTroop);
	}

	@Override
	public Integer insertHerotable(ModelHeroTable newHero) {
		// TODO Auto-generated method stub
		return super.insertHerotable(newHero);
	}

	@Override
	public Integer insertHerotroop(ModelHeroTroop newHeroTroop) {
		// TODO Auto-generated method stub
		return super.insertHerotroop(newHeroTroop);
	}

	@Override
	public Integer updateHero(ModelHeroTable updateHero, ModelHeroTable serchHero) {
		// TODO Auto-generated method stub
		return super.updateHero(updateHero, serchHero);
	}

	@Override
	public Integer deleteHeroTable(Integer heroID) {
		// TODO Auto-generated method stub
		return super.deleteHeroTable(heroID);
	}

	@Override
	public Integer deleteHeroTroop(Integer heroID) {
		// TODO Auto-generated method stub
		return super.deleteHeroTroop(heroID);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	//위치랑 타입을 가지고 맵 오브젝트를 만들어줄 메서드. 이걸로 외부자원지도 만듬.
	public int insertMapObject(ModelXYval location, int locationTypeCastle) {
		// TODO Auto-generated method stub
		return ERROR_UNHANDLED_EXCEPTION;
	}

	//'공터'상태인 맵 어딘가의 위치를 반납하줄 메서드.
	public ModelXYval getEmptyField() {
		// TODO Auto-generated method stub
		return null;
	}

	//에러메세지를 반납해줄 메서드
	public String getErrorMsg(int errorCode) {
		// TODO 오류종류가 추가되면 여기도 수정 ㄱㄱ
		/**
	public static final int ERROR_GENERAL_EXCEPTION = -1;
	public static final int ERROR_NOT_MAKE_YET = -100;
	public static final int ERROR_UNHANDLED_EXCEPTION = -10;
	public static final int ERROR_CANNOT_FIND_USER = -20;
	public static final int ERROR_INVAILD_LOCATIONID = -30;
	public static final int ERROR_CANNOT_BUILD_THIS_PLACE = -40;
		 */
		switch(errorCode){
		case ERROR_NOT_MAKE_YET:
			return "아직 만들지 않은 메서드가 포함됩니다.";
		case ERROR_CANNOT_FIND_USER:
			return "유저를 찾을 수 업습니다.";
		case ERROR_INVAILD_ACCESS:
			return "부적절한 접근입니다.";
		case ERROR_CANNOT_BUILD_THIS_PLACE:
			return "여기에 건설할 수 없습니다.";
				
		case ERROR_GENERAL_EXCEPTION:
			return "알 수 없는 오류거나 에러코드를 설정하지 않았습니다.";
		case ERROR_UNHANDLED_EXCEPTION://아무 내용도, break도 없는 case구절.
		default:
			return "알 수 없는 오류입니다.";
		}
	}

	//맴버를 추가함
	public int registerMember(ModelMembers member) {
		// TODO Auto-generated method stub
		int result = ERROR_UNHANDLED_EXCEPTION;
		try{
			result = dao.insertMembers(member);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	//건물을 추가함
	public Integer insertBuilding(ModelBuilding building) {
		// TODO Auto-generated method stub
		return ERROR_NOT_MAKE_YET;
	}

	//특정누군가의 어디에 있는 건물정보를 가져옴
	public ModelBuilding getBuilding(Integer locationID, int roomNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	//건물 기본정보를 가져옴
	public ModelStructures getSturcture(Integer kind) {
		// TODO Auto-generated method stub
		return null;
	}

	//건물레벨증가에 필요한 자원을 가져옴
	public int getUpgradeValue(Object target, int baseValue) {
		if(target instanceof ModelBuilding){
			//건설 계산식
			return (((ModelBuilding) target).getLevel()+1) * baseValue;
		}
		return ERROR_NOT_MAKE_YET;
	}

	//건물 DB를 갱신
	public int updateBuilding(ModelBuilding target) {
		// TODO Auto-generated method stub
		return ERROR_NOT_MAKE_YET;
	}

	//유저의 자원만을 갱신. TODO 자원 외의 정보만 갱신할 매서드. 코딩 중 헷갈리지 않기 위함.+ DB에 필요이상의 변화 방지.
	public int updateMemberResource(ModelMembers who) {
		// TODO Auto-generated method stub
		return ERROR_NOT_MAKE_YET;
	}
	
	// FIXME 임시. 건물과 유닛으로 나눠놓자.
	public List<Object> getProducingList(){
		// TODO Auto-generated method stub
		return null;
	}

	//해당 성의 병력을 가져옴. FIXME 그런데 SLOT 리스트를 리턴하는편이 더 낫지않을까...?
	public List<ModelCastleTroop> getCastleTroops(int locationID) {
		// TODO Auto-generated method stub
		return null;
	}

	//슬롯 아이디로 모델슬롯을 가져옴
	public ModelSlot getSlot(Integer slotID) {
		// TODO Auto-generated method stub
		return null;
	}

	//유닛의 총 가격을 가져온다.
	public int getUnitValue(int kind, int amount) {
		// TODO Auto-generated method stub
		return ERROR_NOT_MAKE_YET;
	}

	//유닛 꼬라지를 가져온다.
	public ModelUnitBuild getUnitBuild(int kind) {
		// TODO Auto-generated method stub
		return null;
	}

	//슬롯을 하나 만들어서 성에 등록한다. 인자로 받은 슬롯의 정보를 쑤셔넣으면 됨.
	public int insertSlotToCastle(int locationID, ModelSlot slot) {
		// TODO Auto-generated method stub
		return ERROR_NOT_MAKE_YET;
	}

	//x와 y값으로 xyval을 리턴
	public ModelXYval getModelXYval(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
	//로케이션id로 xyval을 리턴
	public ModelXYval getModelXYval(Integer locationID) {
		// TODO Auto-generated method stub
		return null;
	}

	//히당 위치의 병력을 리턴. 성이든 야외자원지이든....
	public List<ModelSlot> getLocalArmySlotList(Integer targetLocationID) {
		// TODO Auto-generated method stub
		return null;
	}


}
