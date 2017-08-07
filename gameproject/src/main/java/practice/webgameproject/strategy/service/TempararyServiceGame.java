package practice.webgameproject.strategy.service;

import static org.mockito.Mockito.stub;

import java.util.ArrayList;
import java.util.List;

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

/**
 * 데이터베이스를 사용하지 않는 임시 서비스
 * A temporary game service without dependency in database. 
 * @author Hyia
 * @usage dao와 서비스, 맵퍼가 완전해지기 전까지 임시 클래스.
 */
public class TempararyServiceGame extends ServiceGame{
	private List<ModelBuilding> buildings_in_castle;
	private List<ModelCastle> castles;
	private List<ModelCastleTroop> troops_in_castle;
	private List<ModelHeroTable> heros;
	private List<ModelHeroTroop> troops_of_heros;
	private List<ModelMembers> users;
	private List<ModelOutResource> resource_outplace;
	private List<ModelSlot> unitSolts;
	private List<ModelStructures> structures;
	private List<ModelUnit> units;
	private List<ModelUnitBuild> unitbuildTime;
	private List<ModelXYval> locations;
	private List<ModelWaitList_Unit> leftedUnitBuildTime;
	private List<ModelWaitList_Building> leftedStructureBuildTime;
	
	
	public TempararyServiceGame() {
		//init and make instances
		buildings_in_castle = new ArrayList();
		castles = new ArrayList();
		troops_in_castle = new ArrayList();
		heros = new ArrayList();
		troops_of_heros = new ArrayList();
		users = new ArrayList();
		resource_outplace = new ArrayList();
		unitSolts = new ArrayList();
		structures = new ArrayList();
		units = new ArrayList();
		unitbuildTime = new ArrayList();
		locations = new ArrayList();
		leftedUnitBuildTime= new ArrayList();
		leftedStructureBuildTime= new ArrayList();
		
		//sturctures	name kind buildtime value
		structures.add(new ModelStructures("Centural_center", IServices.BUILDING_TYPE_CENTER, 10, 100));
		structures.add(new ModelStructures("Barracks", IServices.BUILDING_TYPE_BARRACKS, 20, 200));
		structures.add(new ModelStructures("Resource_center", IServices.BUILDING_TYPE_RESOURCECENTER, 5, 50));
		structures.add(new ModelStructures("Tavern", IServices.BUILDING_TYPE_TAVERN, 20, 500));
		
		//units		unit_id name atk spd hp
		units.add(new ModelUnit(IServices.UNIT_TYPE_LV1,"Infantry", 5, 3, 20));
		units.add(new ModelUnit(IServices.UNIT_TYPE_LV2,"Archer", 10, 2, 14));
		units.add(new ModelUnit(IServices.UNIT_TYPE_LV3,"Cavalry", 7, 5, 30));

		//make map
		for(int i=0,k=0; i< IServices.MAP_MAX_WIDTH;i++){
			for(int j=0; j< IServices.MAP_MAX_HEIGHT;j++,k++){
				locations.add(new ModelXYval(k,i,j,IServices.LOCATION_TYPE_NORMAL));
			}
		}
	}
	

	@Override
	public int insertMapObject(ModelXYval location, int locationTypeCastle) {
		// TODO Auto-generated method stub
		return super.insertMapObject(location, locationTypeCastle);
	}

	@Override
	public ModelXYval getEmptyField() {
		ModelXYval place = null;
		do{
			int randomLocation = (int)(Math.random()*MAP_MAX_HEIGHT*MAP_MAX_WIDTH);
			place = locations.get(randomLocation);
			
		}while(place.getKind()!=IServices.LOCATION_TYPE_NORMAL);
		return place;
	}

	@Override
	public int registerMember(ModelMembers member) {
		int result = users.indexOf(member);
		
		if(result == -1){
			users.add(member);
			result = IServices.SUCCESS;
		}else{
			result = IServices.ERROR_CANNOT_FIND_USER;
		}
		return result;
	}

	@Override
	public Integer insertBuilding(ModelBuilding building) {
		
		int result = IServices.ERROR_UNHANDLED_EXCEPTION;
		
		int index= buildings_in_castle.indexOf(building);
		if(index!=-1){
			ModelBuilding target = buildings_in_castle.get(index);
			if(target.getKind().intValue() == IServices.BUILDING_TYPE_BLANK){
				
				target.setKind(building.getKind());
				target.setLevel(building.getLevel());
				
				result = IServices.SUCCESS;
				
			}else{
				result = IServices.ERROR_CANNOT_BUILD_THIS_PLACE;
			}
		}else{
			buildings_in_castle.add(building);
			result = IServices.SUCCESS;
		}
		return result;
	}

	@Override
	public ModelMembers getMember(ModelMembers member) {
		int index = users.indexOf(member);
		if(index == -1){
			return null;
		}else{
			return users.get(index);
		}
	}
	
	@Override
	public ModelBuilding getBuilding(Integer locationID, int roomNumber) {
		ModelBuilding target= new ModelBuilding(locationID,null,null,roomNumber);
		
		int index= buildings_in_castle.indexOf(target);
		if(index!=-1){
			//해당 건물이 있음
			target = buildings_in_castle.get(index);
		}else{
			//해당 건물이 없음
			target.setKind(IServices.BUILDING_TYPE_BLANK);
			target.setLevel(IServices.BUILDING_BASIC_LEVEL);
			buildings_in_castle.add(target);
		}
		
		return target;
	}
	
	@Override
	public ModelStructures getSturcture(Integer kind) {
		ModelStructures target = null;

		int index = structures.indexOf(kind);
		if(index != -1){
			target = structures.get(index);
		}
		return target;
	}


	@Override
	public int updateBuilding(ModelBuilding target) {
		int index = buildings_in_castle.indexOf(target);

		if(index != -1){
			ModelBuilding building = buildings_in_castle.get(index);
			building.setLevel(target.getLevel());
			building.setKind(target.getKind());
			building.setRoomNumber(target.getRoomNumber());
			
			return SUCCESS;
		}
		
		return ERROR_INVAILD_ACCESS;
	}


	@Override
	public int updateMemberResource(ModelMembers who) {
		users.get(users.indexOf(who)).setSaveProduction(who.getSaveProduction());
		
		int index = users.indexOf(who);
		if(index == -1){
			return IServices.ERROR_CANNOT_FIND_USER;
		}else{
			ModelMembers target = users.get(index);
			target.setSaveProduction(who.getSaveProduction());
			return IServices.SUCCESS;
		}
	}


	@Override
	public List<Object> getProducingList() {
		List<Object> result = new ArrayList<Object>();
		for(int i=0; i<leftedStructureBuildTime.size();i++){
			result.add(leftedStructureBuildTime.get(i));
		}
		for(int i=0; i<leftedUnitBuildTime.size();i++){
			result.add(leftedUnitBuildTime.get(i));
		}
		
		return result;
	}

	@Override
	public List<ModelCastleTroop> getCastleTroops(int locationID) {
		List<ModelCastleTroop> result = new ArrayList<ModelCastleTroop>();
		for(int i=0; i < troops_in_castle.size(); i++){
			if(troops_in_castle.get(i).getLocationID().intValue() == locationID){
				result.add(troops_in_castle.get(i));
			}
		}
		return result;
	}


	@Override
	public ModelSlot getSlot(Integer slotID) {
		ModelSlot result = unitSolts.get(unitSolts.indexOf(slotID));
		return result;
	}


	@Override
	public int getUnitValue(int kind, int amount) {
		ModelUnitBuild target = new ModelUnitBuild(kind,  null, null);
		int index = unitbuildTime.indexOf(target);
		if(index != -1){
			
			target = unitbuildTime.get(index);
			
			return target.getValues()*amount;
		}else{
			return ERROR_INVAILD_ACCESS;
		}
	}


	@Override
	public ModelUnitBuild getUnitBuild(int kind) {
		ModelUnitBuild target = new ModelUnitBuild(kind, null, null);
		int index = unitbuildTime.indexOf(target);
		if(index != -1){
			
			target = unitbuildTime.get(index);
			
			return target;
		}else{
			return null;
		}
	}


	@Override
	public int insertSlotToCastle(int locationID, ModelSlot slot) {
		
		ModelCastle castle = new ModelCastle(null, null, null,locationID, null);
		int index = castles.indexOf(castle);
		if(index != -1){
			ModelSlot newSlot = slot;
			newSlot.setSlotID(unitSolts.size());
			unitSolts.add(newSlot);
			ModelCastleTroop troop = new ModelCastleTroop(locationID, newSlot.getSlotID());
			troops_in_castle.add(troop);
			
			return SUCCESS;
		}else{
			return ERROR_INVAILD_ACCESS;
		}
	}


	@Override
	public ModelXYval getModelXYval(int x, int y) {
		ModelXYval xy = new ModelXYval(null, x, y, null);
		int index = locations.indexOf(xy);
		if(index != -1){
			return locations.get(index);
		}
		return null;
	}


	@Override
	public ModelXYval getModelXYval(Integer locationID) {
		int index = locations.indexOf(locationID);
		if(index != -1){
			return locations.get(index);
		}
		return null;
	}


	@Override
	public Integer getMembersLogin(ModelMembers targetMember) {
		// TODO Auto-generated method stub
		return super.getMembersLogin(targetMember);
	}


	@Override
	public Integer insertMembers(ModelMembers newMember) {
		// TODO Auto-generated method stub
		return super.insertMembers(newMember);
	}


	@Override
	public Integer insertLog(ModelLog newLog) {
		// TODO Auto-generated method stub
		return super.insertLog(newLog);
	}


	@Override
	public Integer updateMembers_Level(ModelMembers updateMembers, ModelMembers searchMembers) {
		// TODO Auto-generated method stub
		return super.updateMembers_Level(updateMembers, searchMembers);
	}


	@Override
	public Integer updateMembers_EXP(ModelMembers updateMembers, ModelMembers searchMembers) {
		// TODO Auto-generated method stub
		return super.updateMembers_EXP(updateMembers, searchMembers);
	}


	@Override
	public Integer updateMembers_SaveProduction(ModelMembers updateMembers, ModelMembers searchMembers) {
		// TODO Auto-generated method stub
		return super.updateMembers_SaveProduction(updateMembers, searchMembers);
	}


	@Override
	public Integer updateMembers_UserData(ModelMembers updateMembers, ModelMembers searchMembers) {
		// TODO Auto-generated method stub
		return super.updateMembers_UserData(updateMembers, searchMembers);
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
	public List<ModelUnit> getUnitInformationList() {
		// TODO Auto-generated method stub
		return super.getUnitInformationList();
	}


	@Override
	public List<ModelUnitBuild> getUnitBuild() {
		// TODO Auto-generated method stub
		return super.getUnitBuild();
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
	public Integer updateSlot(ModelSlot updateSlot, ModelSlot searchSlot) {
		// TODO Auto-generated method stub
		return super.updateSlot(updateSlot, searchSlot);
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
	public Integer updateCastle(ModelCastle updateCastle, ModelCastle searchCastle) {
		// TODO Auto-generated method stub
		return super.updateCastle(updateCastle, searchCastle);
	}


	@Override
	public Integer updateBuilding(ModelBuilding updateBuilding, ModelBuilding searchBuilding) {
		// TODO Auto-generated method stub
		return super.updateBuilding(updateBuilding, searchBuilding);
	}


	@Override
	public Integer updateOutResource(ModelOutResource updateOutResource, ModelOutResource searchOutResource) {
		// TODO Auto-generated method stub
		return super.updateOutResource(updateOutResource, searchOutResource);
	}


	@Override
	public Integer updateWaitList_Building(ModelWaitList_Building updateBudingList,
			ModelWaitList_Building searchBudingList) {
		// TODO Auto-generated method stub
		return super.updateWaitList_Building(updateBudingList, searchBudingList);
	}


	@Override
	public Integer updateWaitList_Unit(ModelWaitList_Unit updateUnitList, ModelWaitList_Unit searchUnitList) {
		// TODO Auto-generated method stub
		return super.updateWaitList_Unit(updateUnitList, searchUnitList);
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
	public Integer deleteWaitList_Building() {
		// TODO Auto-generated method stub
		return super.deleteWaitList_Building();
	}


	@Override
	public Integer deleteWaitList_Unit() {
		// TODO Auto-generated method stub
		return super.deleteWaitList_Unit();
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
	public Integer updateHero(ModelHeroTable updateHero, ModelHeroTable searchHero) {
		// TODO Auto-generated method stub
		return super.updateHero(updateHero, searchHero);
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
	public String getErrorMsg(int errorCode) {
		// TODO Auto-generated method stub
		return super.getErrorMsg(errorCode);
	}


	@Override
	public int getUpgradeValue(Object target, int baseValue) {
		// TODO Auto-generated method stub
		return super.getUpgradeValue(target, baseValue);
	}


	@Override
	public List<ModelSlot> getLocalArmySlotList(Integer targetLocationID) {
		// TODO Auto-generated method stub
		return super.getLocalArmySlotList(targetLocationID);
	}
	
	

}
