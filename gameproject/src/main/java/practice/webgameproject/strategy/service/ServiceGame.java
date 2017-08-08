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
import practice.webgameproject.strategy.model.ModelLog;
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
		
		Integer result=-1;
        
        try {
        	result = dao.insertMembers(newMember);
        } catch (Exception e) {
            logger.error("insertMembers " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer insertLog(ModelLog newLog) {
		
		Integer result=-1;
        
        try {
        	result = dao.insertLog(newLog);
        } catch (Exception e) {
            logger.error("insertLog " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public Integer updateMembers_Level(ModelMembers updateMembers, ModelMembers searchMembers) {

		Integer result=-1;
        
        try {
        	result = dao.updateMembers_Level(updateMembers,searchMembers);
        } catch (Exception e) {
            logger.error("updateMembers_Level " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public Integer updateMembers_EXP(ModelMembers updateMembers, ModelMembers searchMembers) {

		Integer result=-1;
        
        try {
        	result = dao.updateMembers_EXP(updateMembers,searchMembers);
        } catch (Exception e) {
            logger.error("updateMembers_EXP " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public Integer updateMembers_SaveProduction(ModelMembers updateMembers, ModelMembers searchMembers) {

		int result=-1;
        
        try {
        	result = dao.updateMembers_SaveProduction(updateMembers,searchMembers);
        } catch (Exception e) {
            logger.error("updateMembers_SaveProduction " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public Integer updateMembers_UserData(ModelMembers updateMembers, ModelMembers searchMembers) {

		int result=-1;
        
        try {
        	result = dao.updateMembers_UserData(updateMembers,searchMembers);
        } catch (Exception e) {
            logger.error("updateMembers_UserData " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer deleteMembers(ModelMembers members) {

		int result=-1;
        
        try {
        	result = dao.deleteMembers(members);
        } catch (Exception e) {
            logger.error("deleteMembers " + e.getMessage() );
        }
        
        return result;
		
	}

	@Override
	public ModelUnit getUnitInformation(Integer UnitID) {

		ModelUnit result=new ModelUnit();
        
        try {
        	result = dao.getUnitInformation(UnitID);
        } catch (Exception e) {
            logger.error("getUnitInformation " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public List<ModelUnit> getUnitInformationList() {

		List<ModelUnit> result=null;
        
        try {
        	result = dao.getUnitInformationList();
        } catch (Exception e) {
            logger.error("getUnitInformationList " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public List<ModelUnitBuild> getUnitBuild() {

		List<ModelUnitBuild> result=null;
        
        try {
        	result = dao.getUnitBuild();
        } catch (Exception e) {
            logger.error("getUnitBuild " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public List<ModelStructures> getStructures() {

		List<ModelStructures> result=null;
        
        try {
        	result = dao.getStructures();
        } catch (Exception e) {
            logger.error("getStructures " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public List<ModelXYval> getAllXYval() {

		List<ModelXYval> result=null;
        
        try {
        	result = dao.getAllXYval();
        } catch (Exception e) {
            logger.error("getAllXYval " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer updateSlot(ModelSlot updateSlot, ModelSlot searchSlot) {
		
		Integer result=null;
        
        try {
        	result = dao.updateSlot(updateSlot,searchSlot);
        } catch (Exception e) {
            logger.error("updateSlot " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer deleteXYval(Integer locationID) {

		Integer result=null;
        
        try {
        	result = dao.deleteXYval(locationID);
        } catch (Exception e) {
            logger.error("deleteXYval " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer deleteSlot(Integer slotID) {

		Integer result=null;
        
        try {
        	result = dao.deleteSlot(slotID);
        } catch (Exception e) {
            logger.error("deleteSlot " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public List<ModelCastle> getCastleList(ModelMembers targetUser) {

		List<ModelCastle> result=null;
        
        try {
        	result = dao.getCastleList(targetUser);
        } catch (Exception e) {
            logger.error("getCastleList " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public ModelCastle getCastleOne(Integer locationID) {

		ModelCastle result=null;
        
        try {
        	result = dao.getCastleOne(locationID);
        } catch (Exception e) {
            logger.error("getCastleOne " + e.getMessage() );
        }
        
        return result;
        
	}

	@Override
	public List<ModelHeroTable> getHeroList_InCastle(ModelCastle targetCastle) {

		List<ModelHeroTable> result=null;
        
        try {
        	result = dao.getHeroList_InCastle(targetCastle);
        } catch (Exception e) {
            logger.error("getHeroList_InCastle " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public List<ModelSlot> getCastleTroop_SlotList(ModelCastle targetTroop) {

		List<ModelSlot> result=null;
        
        try {
        	result = dao.getCastleTroop_SlotList(targetTroop);
        } catch (Exception e) {
            logger.error("getCastleTroop_SlotList " + e.getMessage() );
        }
        
        return result;
        
	}

	@Override
	public ModelXYval getXYval_LocationID(Integer locationID) {

		ModelXYval result=null;
        
        try {
        	result = dao.getXYval_LocationID(locationID);
        } catch (Exception e) {
            logger.error("getXYval_LocationID " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public ModelXYval getXYval_XY(ModelXYval targetXYval) {

		ModelXYval result=null;
        
        try {
        	result = dao.getXYval_XY(targetXYval);
        } catch (Exception e) {
            logger.error("getXYval_XY " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public List<ModelWaitList_Building> getWaitList_Building(ModelCastle targetWitList) {
		
		List<ModelWaitList_Building> result= null;
        
        try {
        	result = dao.getWaitList_Building(targetWitList);
        } catch (Exception e) {
            logger.error("getWaitList_Building " + e.getMessage() );
        }
        
        return result;
        
	}

	@Override
	public List<ModelWaitList_Unit> getWaitList_Unit(ModelCastle targetWitList) {

		List<ModelWaitList_Unit> result= null;
        
        try {
        	result = dao.getWaitList_Unit(targetWitList);
        } catch (Exception e) {
            logger.error("getWaitList_Unit " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public List<ModelBuilding> getBuilding(ModelCastle targetBuilding) {

		List<ModelBuilding> result= null;
        
        try {
        	result = dao.getBuilding(targetBuilding);
        } catch (Exception e) {
            logger.error("getBuilding " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer getAllProduction(ModelCastle targetCastleProduction) {

		Integer result= -1;
        
        try {
        	result = dao.getAllProduction(targetCastleProduction);
        } catch (Exception e) {
            logger.error("getAllProduction " + e.getMessage() );
        }
        
        return result;
        
	}

	@Override
	public ModelOutResource getOutResource(Integer locationID) {

		ModelOutResource result= null;
        
        try {
        	result = dao.getOutResource(locationID);
        } catch (Exception e) {
            logger.error("getOutResource " + e.getMessage() );
        }
        
        return result;
        
	}

	@Override
	public Integer insertCastle(ModelCastle newCastle) {

		Integer result= -1;
        
        try {
        	result = dao.insertCastle(newCastle);
        } catch (Exception e) {
            logger.error("insertCastle " + e.getMessage() );
        }
        
        return result;
        
	}

	@Override
	public Integer insertXYval(ModelXYval newXYval) {

		Integer result= -1;
        
        try {
        	result = dao.insertXYval(newXYval);
        } catch (Exception e) {
            logger.error("insertXYval " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer insertWaitBuildingList(ModelWaitList_Building newBuildingList) {

		Integer result= -1;
        
        try {
        	result = dao.insertWaitBuildingList(newBuildingList);
        } catch (Exception e) {
            logger.error("insertWaitBuildingList " + e.getMessage() );
        }
        
        return result;
        
	}

	@Override
	public Integer insertWaitUnitList(ModelWaitList_Unit newUnitList) {

		Integer result= -1;
        
        try {
        	result = dao.insertWaitUnitList(newUnitList);
        } catch (Exception e) {
            logger.error("insertWaitUnitgList " + e.getMessage() );
        }
        
        return result;
        
	}

	@Override
	public Integer insertCastletroop(ModelCastleTroop newCastleTroop) {

		Integer result= -1;
        
        try {
        	result = dao.insertCastletroop(newCastleTroop);
        } catch (Exception e) {
            logger.error("insertCastletroop " + e.getMessage() );
        }
        
        return result;
        
	}

	@Override
	public Integer insertSlot(ModelSlot newSlot) {

		Integer result= -1;
        
        try {
        	result = dao.insertSlot(newSlot);
        } catch (Exception e) {
            logger.error("insertSlot " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer insertOutResource(ModelOutResource newOutResource) {

		Integer result= -1;
        
        try {
        	result = dao.insertOutResource(newOutResource);
        } catch (Exception e) {
            logger.error("insertOutResource " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer mInsertWaitList_Building(List<ModelWaitList_Building> list) {

		Integer result= -1;
        
        try {
        	result = dao.mInsertWaitList_Building(list);
        } catch (Exception e) {
            logger.error("mInsertWaitList_Building " + e.getMessage() );
        }
        
        return result;
        
	}

	@Override
	public Integer mInsertWaitList_Unit(List<ModelWaitList_Unit> list) {

		Integer result= -1;
        
        try {
        	result = dao.mInsertWaitList_Unit(list);
        } catch (Exception e) {
            logger.error("mInsertWaitList_Unit " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer updateCastle(ModelCastle updateCastle, ModelCastle searchCastle) {

		Integer result= -1;
        
        try {
        	result = dao.updateCastle(updateCastle,searchCastle);
        } catch (Exception e) {
            logger.error("updateCastle " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer updateBuilding(ModelBuilding updateBuilding, ModelBuilding searchBuilding) {

		Integer result= -1;
        
        try {
        	result = dao.updateBuilding(updateBuilding,searchBuilding);
        } catch (Exception e) {
            logger.error("updateBuilding " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer updateOutResource(ModelOutResource updateOutResource, ModelOutResource searchOutResource) {

		Integer result= -1;
        
        try {
        	result = dao.updateOutResource(updateOutResource,searchOutResource);
        } catch (Exception e) {
            logger.error("updateOutResource " + e.getMessage() );
        }
        
        return result;
        
	}

	@Override
	public Integer deleteCastleTroop(Integer locationID) {

		Integer result= -1;
        
        try {
        	result = dao.deleteCastleTroop(locationID);
        } catch (Exception e) {
            logger.error("deleteCastleTroop " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public Integer deleteCastleBuilding_All(Integer locationID) {

		Integer result= -1;
        
        try {
        	result = dao.deleteCastleBuilding_All(locationID);
        } catch (Exception e) {
            logger.error("deleteCastleBuilding_All " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public Integer deleteCastleBuilding_One(ModelBuilding delBuilding) {

		Integer result= -1;
        
        try {
        	result = dao.deleteCastleBuilding_One(delBuilding);
        } catch (Exception e) {
            logger.error("deleteCastleBuilding_One " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public Integer deleteOutResource(Integer locationID) {

		Integer result= -1;
        
        try {
        	result = dao.deleteOutResource(locationID);
        } catch (Exception e) {
            logger.error("deleteOutResource " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer deleteCastle_All(String userID) {

		Integer result= -1;
        
        try {
        	result = dao.deleteCastle_All(userID);
        } catch (Exception e) {
            logger.error("deleteCastle_All " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public Integer deleteCastle_One(Integer locationID) {

		Integer result= -1;
        
        try {
        	result = dao.deleteCastle_One(locationID);
        } catch (Exception e) {
            logger.error("deleteCastle_One " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer deleteWaitList_Building() {


		Integer result= -1;
        
        try {
        	result = dao.deleteWaitList_Building();
        } catch (Exception e) {
            logger.error("deleteWaitList_Building " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public Integer deleteWaitList_Unit() {

		Integer result= -1;
        
        try {
        	result = dao.deleteWaitList_Unit();
        } catch (Exception e) {
            logger.error("deleteWaitList_Unit " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public ModelHeroTable getHero(ModelHeroTable targetHero) {

		ModelHeroTable result= null;
        
        try {
        	result = dao.getHero(targetHero);
        } catch (Exception e) {
            logger.error("getHero " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public List<ModelSlot> getHeroTroop_SlotList(Integer heroID) {

		List<ModelSlot> result= null;
        
        try {
        	result = dao.getHeroTroop_SlotList(heroID);
        } catch (Exception e) {
            logger.error("getHeroTroop_SlotList " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer insertHerotable(ModelHeroTable newHero) {

		Integer result= -1;
        
        try {
        	result = dao.insertHerotable(newHero);
        } catch (Exception e) {
            logger.error("insertHerotable " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer insertHerotroop(ModelHeroTroop newHeroTroop) {

		Integer result= -1;
        
        try {
        	result = dao.insertHerotroop(newHeroTroop);
        } catch (Exception e) {
            logger.error("insertHerotroop " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer updateHero(ModelHeroTable updateHero, ModelHeroTable searchHero) {

		Integer result= -1;
        
        try {
        	result = dao.updateHero(updateHero,searchHero);
        } catch (Exception e) {
            logger.error("updateHero " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer deleteHeroTable(Integer heroID) {

		Integer result= -1;
        
        try {
        	result = dao.deleteHeroTable(heroID);
        } catch (Exception e) {
            logger.error("deleteHeroTable " + e.getMessage() );
        }
        
        return result;
	}

	@Override
	public Integer deleteHeroTroop(Integer heroID) {

		Integer result= -1;
        
        try {
        	result = dao.deleteHeroTroop(heroID);
        } catch (Exception e) {
            logger.error("deleteHeroTroop " + e.getMessage() );
        }
        
        return result;
	}
	
	@Override
	public List<ModelLog> getLog_ATK_DF_All(String userID){
		List<ModelLog> result= null;
        
        try {
        	result = dao.getLog_ATK_DF_All(userID);
        } catch (Exception e) {
            logger.error("getLog_ATK_DF_All " + e.getMessage() );
        }
        
        return result;
	};
	
	@Override
	public List<ModelLog> getLog_ATK(String userID){
		List<ModelLog> result= null;
        
        try {
        	result = dao.getLog_ATK(userID);
        } catch (Exception e) {
            logger.error("getLog_ATK " + e.getMessage() );
        }
        
        return result;
	};
	
	@Override
	public List<ModelLog> getLog_DF(String userID){
		List<ModelLog> result= null;
        
        try {
        	result = dao.getLog_DF(userID);
        } catch (Exception e) {
            logger.error("getLog_DF " + e.getMessage() );
        }
        
        return result;
	};

	@Override
	public int hashCode() {

		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

	@Override
	public String toString() {

		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {

		super.finalize();
	}

	//위치랑 타입을 가지고 맵 오브젝트를 만들어줄 메서드. 이걸로 외부자원지도 만듬.
	public int insertMapObject(ModelXYval location, int locationTypeCastle) {

		return ERROR_UNHANDLED_EXCEPTION;
	}

	//'공터'상태인 맵 어딘가의 위치를 반납하줄 메서드.
	public ModelXYval getEmptyField() {

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

		int result = -1;
		try{
			result = dao.insertBuilding(building);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
		
	}

	//특정누군가의 어디에 있는 건물정보를 가져옴
	public ModelBuilding getBuilding(Integer locationID, int roomNumber) {

		return null;
	}

	//건물 기본정보를 가져옴
	public ModelStructures getSturcture(Integer kind) {

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

		return ERROR_NOT_MAKE_YET;
	}

	//유저의 자원만을 갱신. TODO 자원 외의 정보만 갱신할 매서드. 코딩 중 헷갈리지 않기 위함.+ DB에 필요이상의 변화 방지.
	public int updateMemberResource(ModelMembers who) {

		return ERROR_NOT_MAKE_YET;
	}
	
	// FIXME 임시. 건물과 유닛으로 나눠놓자.
	public List<Object> getProducingList(){

		return null;
	}

	//해당 성의 병력을 가져옴. FIXME 그런데 SLOT 리스트를 리턴하는편이 더 낫지않을까...?
	public List<ModelCastleTroop> getCastleTroops(int locationID) {

		return null;
	}

	//슬롯 아이디로 모델슬롯을 가져옴
	public ModelSlot getSlot(Integer slotID) {

		return null;
	}

	//유닛의 총 가격을 가져온다.
	public int getUnitValue(int kind, int amount) {

		return ERROR_NOT_MAKE_YET;
	}

	//유닛 꼬라지를 가져온다.
	public ModelUnitBuild getUnitBuild(int kind) {

		return null;
	}

	//슬롯을 하나 만들어서 성에 등록한다. 인자로 받은 슬롯의 정보를 쑤셔넣으면 됨.
	public int insertSlotToCastle(int locationID, ModelSlot slot) {

		return ERROR_NOT_MAKE_YET;
	}

	//x와 y값으로 xyval을 리턴
	public ModelXYval getModelXYval(int x, int y) {

		return null;
	}
	//로케이션id로 xyval을 리턴
	public ModelXYval getModelXYval(Integer locationID) {

		return null;
	}

	//히당 위치의 병력을 리턴. 성이든 야외자원지이든....
	public List<ModelSlot> getLocalArmySlotList(Integer targetLocationID) {

		return null;
	}


}
