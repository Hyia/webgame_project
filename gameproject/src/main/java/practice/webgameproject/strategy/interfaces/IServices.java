package practice.webgameproject.strategy.interfaces;

import java.util.List;

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

public interface IServices {
	//success code
	public static final Integer SUCCESS = 100;

	//error codes
	public static final Integer ERROR_GENERAL_EXCEPTION = -1;
	public static final Integer ERROR_NOT_MAKE_YET = -100;
	public static final Integer ERROR_UNHANDLED_EXCEPTION = -10;
	public static final Integer ERROR_CANNOT_FIND_USER = -20;
	public static final Integer ERROR_INVAILD_ACCESS = -30;
	public static final Integer ERROR_CANNOT_BUILD_THIS_PLACE = -40;
	
	//types - location
	public static final Integer LOCATION_TYPE_INVAILD = -1;
	public static final Integer LOCATION_TYPE_NORMAL = 1;
	public static final Integer LOCATION_TYPE_CASTLE = 2;
	public static final Integer LOCATION_TYPE_EXTERNALRESOURCE = 3;

	//types - buildings
	public static final Integer BUILDING_TYPE_INVALID = -1;
	public static final Integer BUILDING_TYPE_BLANK = 0;
	public static final Integer BUILDING_TYPE_CENTER = 1;
	public static final Integer BUILDING_TYPE_RESOURCECENTER = 2;
	public static final Integer BUILDING_TYPE_BARRACKS = 3;
	public static final Integer BUILDING_TYPE_TAVERN = 4;
	
	public static final Integer BUILDING_BASIC_LEVEL = 1;

	//types - units
	public static final Integer UNIT_TYPE_LV1 = 0;
	public static final Integer UNIT_TYPE_LV2 = 1;
	public static final Integer UNIT_TYPE_LV3 = 2;

	//location mapsize
	public static final Integer MAP_MAX_HEIGHT = 50;
	public static final Integer MAP_MAX_WIDTH = 50;

	public static final long TRAVEL_UNIT_TIME = 5000;




	//general methods - members -GET
	public ModelMembers getMember(ModelMembers targetMember);
	

	//general methods - members -INSERT
	public Integer insertMembers(ModelMembers newMember);	
	
	
	//general methods - Others Information -GET
	public ModelUnit 		 	 getUnitInformation	(Integer UnitID);
	
	public List<ModelUnitBuild>  getUnitBuild		(ModelUnitBuild targetUnitBuild);
	
	public List<ModelStructures> getStructures		(ModelStructures targetStructures);
	
	public List<ModelXYval>		 getAllXYval		();
	
	public ModelOutResource 	 getOutResource		(Integer locationID);
	
	
	//general methods - castles - GET
	public List<ModelCastle> 			 getCastleList				(ModelMembers targetUser);
	
	public ModelCastle 			 		 getCastleOne				(Integer locationID);
	
	public List<ModelHeroTable> 		 getHeroList_InCastle		(ModelCastle targetCastle);
	
	public List<ModelSlot> 				 getCastleTroop_SlotList	(ModelCastle targetTroop);
	
	public ModelXYval					 getXYval_LocationID		(Integer locationID);
	
	public ModelXYval					 getXYval_XY				(ModelXYval targetXYval);
	
	public List<ModelWaitList_Building>  getWaitList_Building		(ModelCastle targetWitList);
	
	public List<ModelWaitList_Unit>		 getWaitList_Unit			(ModelCastle targetWitList);
	
	public List<ModelBuilding>			 getBuilding				(ModelCastle targetBuilding);
	
	public Integer						 getAllProduction			(ModelCastle targetCastleProduction);
	
	
	

	//general methods - castles - INSERT
	public Integer 		insertCastle				(ModelCastle newCastle);
	
	public Integer 		insertXYval					(ModelXYval newXYval);
	
	public Integer 		insertBuilding				(ModelBuilding newBuilding);
	
	public Integer		insertWaitBuildingList		(ModelWaitList_Building newBuildingList);
	
	public Integer 		insertWaitUnitgList			(ModelWaitList_Unit newUnitList);
	
	public Integer 		insertCastletroop			(ModelCastleTroop newCastleTroop);
		
	public Integer		insertSlot					(ModelSlot newSlot);
	
	public Integer 		insertOutResource			(ModelOutResource newOutResource);
	
	public Integer		mInsertWaitList_Building	(List<ModelWaitList_Building> list);
	
	public Integer		mInsertWaitList_Unit		(List<ModelWaitList_Unit> list);
	
	
	//general methods - castles - UPDATE	
	public Integer		updateMembers				(ModelMembers members);
	public Integer		updateCastle				(ModelCastle castle);
	public Integer		updateBuilding				(ModelBuilding building);
	public Integer		updateHero					(ModelHeroTable heroTable);
	public Integer		updateSlot					(ModelSlot slot);
	public Integer		updateOutResource			(ModelOutResource outResource);
	public Integer		updateWaitList_Building		(ModelWaitList_Building slot);
	public Integer		updateWaitList_Unit			(ModelWaitList_Unit slot);
	public Integer		updateXYval					(ModelWaitList_Unit slot);
	//public Integer		updateWaitList_Unit			(ModelWaitList_Unit slot);
	//public Integer		updateWaitList_Unit			(ModelWaitList_Unit slot);
	
	
	
	//general methods - hero - GET
	public ModelHeroTable 		getHero					(ModelHeroTable targetHero);
	public List<ModelHeroTroop> getHeroTroop_SlotList	(ModelHeroTable targetTroop);
	
	
	//general methods - hero - INSERT
	public Integer insertHerotable		(ModelHeroTable newHero);
	
	public Integer insertHerotroop		(ModelHeroTroop newHeroTroop);
	
	
}
