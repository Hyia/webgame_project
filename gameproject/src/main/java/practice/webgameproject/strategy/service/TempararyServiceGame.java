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

	

}
