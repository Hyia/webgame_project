package practice.webgameproject.strategy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import practice.webgameproject.strategy.interfaces.IServices;
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

@Repository("daogame")
public class DaoGame implements IServices{
	
	 @Autowired
	 @Qualifier("sqlSession")
	 SqlSession session;


	 
	// Methods MEMBERS - GET
	@Override
	public ModelMembers getMember(ModelMembers targetMember) {
		
		return session.selectOne("mapper.mysql.mapperWebGame.getMember",targetMember);
	}
	
	@Override
	public Integer getMembersLogin(ModelMembers targetMember){
		
		return session.selectOne("mapper.mysql.mapperWebGame.getMembersLogin",targetMember);
		
	}

	
	// Methods MEMBERS - INSERT
	@Override
	public Integer insertMembers(ModelMembers newMember) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertMembers",newMember);
	}

	
	// Methods MEMBERS - UPDATE
	@Override
	public Integer updateMembers_Level(ModelMembers updateMembers, ModelMembers searchMembers) {
		Map<String, ModelMembers> map = new HashMap<String, ModelMembers>();
		
		map.put("updateValue", updateMembers);
		map.put("searchValue", searchMembers);
		
		return session.insert("mapper.mysql.mapperWebGame.updateMembers_Level",map);
	}
	
	@Override
	public Integer updateMembers_EXP(ModelMembers updateMembers, ModelMembers searchMembers) {

		Map<String, ModelMembers> map = new HashMap<String, ModelMembers>();
		
		map.put("updateValue", updateMembers);
		map.put("searchValue", searchMembers);
		
		return session.insert("mapper.mysql.mapperWebGame.updateMembers_EXP",map);
	}

	@Override
	public Integer updateMembers_SaveProduction(ModelMembers updateMembers, ModelMembers searchMembers) {

		Map<String, ModelMembers> map = new HashMap<String, ModelMembers>();
		
		map.put("updateValue", updateMembers);
		map.put("searchValue", searchMembers);
		
		return session.insert("mapper.mysql.mapperWebGame.updateMembers_SaveProduction",map);
	}

	@Override
	public Integer updateMembers_UserData(ModelMembers updateMembers, ModelMembers searchMembers) {

		Map<String, ModelMembers> map = new HashMap<String, ModelMembers>();
		
		map.put("updateValue", updateMembers);
		map.put("searchValue", searchMembers);
		
		return session.insert("mapper.mysql.mapperWebGame.updateMembers_UserData",map);
	}

	
	// Methods MEMBERS - DELETE
	@Override
	public Integer deleteMembers(ModelMembers members) {
		
		return session.insert("mapper.mysql.mapperWebGame.deleteMembers",members);
	}

	
	
	// Methods Others - GET
	@Override
	public ModelUnit getUnitInformation(Integer UnitID) {
		
		return session.selectOne("mapper.mysql.mapperWebGame.getUnitInformation",UnitID);
	}
	
	@Override
	public List<ModelUnit> getUnitInformationList() {
		
		return session.selectList("mapper.mysql.mapperWebGame.getUnitInformationList");
	}

	@Override
	public List<ModelUnitBuild> getUnitBuild() {
		
		return session.selectList("mapper.mysql.mapperWebGame.getUnitBuild");
	}

	@Override
	public List<ModelStructures> getStructures() {
		
		return session.selectList("mapper.mysql.mapperWebGame.getStructures");
	}
	
	@Override
	public ModelStructures getStructure(ModelStructures targetStructures) {
		
		return session.selectOne("mapper.mysql.mapperWebGame.getStructure");
	}

	@Override
	public List<ModelXYval> getAllXYval() {
		
		return session.selectList("mapper.mysql.mapperWebGame.getAllXYval");
	}

	@Override
	public List<ModelLog> getLog_ATK_DF_All(String userID) {
		
		return session.selectList("mapper.mysql.mapperWebGame.getLog_ATK_DF_All",userID);
	}

	@Override
	public List<ModelLog> getLog_ATK(String userID) {

		return session.selectList("mapper.mysql.mapperWebGame.getLog_ATK",userID);
	}

	@Override
	public List<ModelLog> getLog_DF(String userID) {

		return session.selectList("mapper.mysql.mapperWebGame.getLog_DF",userID);
	}
	
	
	// Methods Others - INSERT
	@Override
	public Integer insertLog(ModelLog newLog) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertLog",newLog);
	}
	
	
	
	// Methods Others - UPDATE
	@Override
	public Integer updateSlot(ModelSlot updateSlot	, ModelSlot searchSlot) {
		
		Map<String, ModelSlot> map = new HashMap<String, ModelSlot>();
		
		map.put("updateValue", updateSlot);
		map.put("searchValue", searchSlot);
		
		return session.insert("mapper.mysql.mapperWebGame.updateSlot",map);
	}


	
	
	// Methods Others - DELETE
	@Override
	public Integer deleteXYval(Integer locationID) {
		
		return session.insert("mapper.mysql.mapperWebGame.deleteXYval",locationID);
	}

	@Override
	public Integer deleteSlot(Integer slotID) {
		
		return session.insert("mapper.mysql.mapperWebGame.deleteSlot",slotID);
	}

	
	
	// Methods Catle - GET 
	@Override
	public List<ModelCastle> getCastleList(ModelMembers targetUser) {
		
		return session.selectList("mapper.mysql.mapperWebGame.getCastleList",targetUser);
	}

	@Override
	public ModelCastle getCastleOne(Integer locationID) {
		
		return session.selectOne("mapper.mysql.mapperWebGame.getCastleOne",locationID);
	}

	@Override
	public List<ModelHeroTable> getHeroList_InCastle(ModelCastle targetCastle) {
		
		return session.selectList("mapper.mysql.mapperWebGame.getHeroList_InCastle",targetCastle);
	}

	@Override
	public List<ModelSlot> getCastleTroop_SlotList(ModelCastle targetTroop) {
		
		return session.selectList("mapper.mysql.mapperWebGame.getCastleTroop_SlotList",targetTroop);
	}

	@Override
	public ModelXYval getXYval_LocationID(Integer locationID) {
		
		return session.selectOne("mapper.mysql.mapperWebGame.getXYval_LocationID",locationID);
	}

	@Override
	public ModelXYval getXYval_XY(ModelXYval targetXYval) {
		
		return session.selectOne("mapper.mysql.mapperWebGame.getXYval_XY",targetXYval);
	}

	@Override
	public List<ModelWaitList_Building> getWaitList_Building(ModelCastle targetWitList) {
		
		return session.selectList("mapper.mysql.mapperWebGame.getWaitList_Building",targetWitList);
	}

	@Override
	public List<ModelWaitList_Unit> getWaitList_Unit(ModelCastle targetWitList) {
		
		return session.selectList("mapper.mysql.mapperWebGame.getWaitList_Unit",targetWitList);
	}

	@Override
	public ModelBuilding getBuilding(ModelBuilding targetBuilding) {
		
		return session.selectOne("mapper.mysql.mapperWebGame.getBuilding",targetBuilding);
	}
	
	@Override
	public List<ModelBuilding> getBuildingList(ModelCastle targetCastle) {
		
		return session.selectList("mapper.mysql.mapperWebGame.getBuildingList",targetCastle);
	}

	@Override
	public Integer getAllProduction(ModelCastle targetCastleProduction) {
		
		return session.selectOne("mapper.mysql.mapperWebGame.getAllProduction",targetCastleProduction);
	}

	@Override
	public ModelOutResource getOutResource(Integer locationID) {
		
		return session.selectOne("mapper.mysql.mapperWebGame.getOutResource",locationID);
	}

	
	
	// Methods Catle - INSERT
	@Override
	public Integer insertCastle(ModelCastle newCastle) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertCastle",newCastle);
	}

	@Override
	public Integer insertXYval(ModelXYval newXYval) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertXYval",newXYval);
	}

	@Override
	public Integer insertBuilding(ModelBuilding newBuilding) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertBuilding",newBuilding);
	}

	@Override
	public Integer insertWaitBuildingList(ModelWaitList_Building newBuildingList) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertWaitBuildingList",newBuildingList);
	}

	@Override
	public Integer insertWaitUnitList(ModelWaitList_Unit newUnitList) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertWaitUnitList",newUnitList);
	}

	@Override
	public Integer insertCastletroop(ModelCastleTroop newCastleTroop) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertCastletroop",newCastleTroop);
	}

	@Override
	public Integer insertSlot(ModelSlot newSlot) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertSlot",newSlot);
	}

	@Override
	public Integer insertOutResource(ModelOutResource newOutResource) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertOutResource",newOutResource);
	}

	@Override
	public Integer mInsertWaitList_Building(List<ModelWaitList_Building> list) {
		
		return session.insert("mapper.mysql.mapperWebGame.mInsertWaitList_Building",list);
	}

	@Override
	public Integer mInsertWaitList_Unit(List<ModelWaitList_Unit> list) {
		
		return session.insert("mapper.mysql.mapperWebGame.mInsertWaitList_Unit",list);
	}

	
	
	// Methods Catle - UPDATE
	@Override
	public Integer updateCastle(ModelCastle updateCastle	, ModelCastle searchCastle) {
		
		Map<String, ModelCastle> map = new HashMap<String, ModelCastle>();
		
		map.put("updateValue", updateCastle);
		map.put("searchValue", searchCastle);
		
		return session.insert("mapper.mysql.mapperWebGame.updateCastle",map);
	}

	@Override
	public Integer updateBuilding(ModelBuilding updateBuilding	, ModelBuilding searchBuilding) {

		Map<String, ModelBuilding> map = new HashMap<String, ModelBuilding>();
		
		map.put("updateValue", updateBuilding);
		map.put("searchValue", searchBuilding);
		
		return session.insert("mapper.mysql.mapperWebGame.updateBuilding",map);
	}

	@Override
	public Integer updateOutResource(ModelOutResource updateOutResource	, ModelOutResource searchOutResource) {

		Map<String, ModelOutResource> map = new HashMap<String, ModelOutResource>();
		
		map.put("updateValue", updateOutResource);
		map.put("searchValue", searchOutResource);
		
		return session.insert("mapper.mysql.mapperWebGame.updateOutResource",map);
	}
	
	
	
	// Methods Catle - DELETE
	@Override
	public Integer deleteCastleTroop(Integer locationID) {
		
		return session.insert("mapper.mysql.mapperWebGame.deleteCastleTroop",locationID);
	}

	@Override
	public Integer deleteCastleBuilding_All(Integer locationID) {
		
		return session.insert("mapper.mysql.mapperWebGame.deleteCastleBuilding_All",locationID);
	}
	
	@Override
	public Integer deleteCastleBuilding_One(ModelBuilding delBuilding) {
		
		return session.insert("mapper.mysql.mapperWebGame.deleteCastleBuilding_One",delBuilding);
	}

	@Override
	public Integer deleteOutResource(Integer locationID) {
		
		return session.insert("mapper.mysql.mapperWebGame.deleteOutResource",locationID);
	}

	@Override
	public Integer deleteCastle_All(String userID) {
		
		return session.insert("mapper.mysql.mapperWebGame.deleteCastle_All",userID);
	}
	
	@Override
	public Integer deleteCastle_One(Integer locationID) {
		
		return session.insert("mapper.mysql.mapperWebGame.deleteCastle_One",locationID);
	}
	
	@Override
	public Integer deleteWaitList_Building() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteWaitList_Unit() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	// Methods Hero - GET 
	@Override
	public ModelHeroTable getHero(ModelHeroTable targetHero) {
		
		return session.selectOne("mapper.mysql.mapperWebGame.getHero",targetHero);
	}

	@Override
	public List<ModelSlot> getHeroTroop_SlotList(Integer heroID) {
		
		return session.selectList("mapper.mysql.mapperWebGame.getHeroTroop_SlotList",heroID);
	}

	
	
	// Methods Hero - INSERT
	@Override
	public Integer insertHerotable(ModelHeroTable newHero) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertHerotable",newHero);
	}

	@Override
	public Integer insertHerotroop(ModelHeroTroop newHeroTroop) {
		
		return session.insert("mapper.mysql.mapperWebGame.insertHerotroop",newHeroTroop);
	}

	
	
	// Methods Hero - UPDATE
	@Override
	public Integer updateHero(ModelHeroTable updateHero	, ModelHeroTable searchHero) {
		
		Map<String, ModelHeroTable> map = new HashMap<String, ModelHeroTable>();
		
		map.put("updateValue", updateHero);
		map.put("searchValue", searchHero);
		
		return session.insert("mapper.mysql.mapperWebGame.updateHero",map);
	}

	
	// Methods Hero - DELETE
	@Override
	public Integer deleteHeroTable(Integer heroID) {
		
		return session.insert("mapper.mysql.mapperWebGame.deleteHeroTable",heroID);
	}

	@Override
	public Integer deleteHeroTroop(Integer heroID) {
		
		return session.insert("mapper.mysql.mapperWebGame.deleteHeroTroop",heroID);
	}

		
}
