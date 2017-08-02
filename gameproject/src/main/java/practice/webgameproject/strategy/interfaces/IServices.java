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
	public static final int SUCCESS = 100;

	//error codes
	public static final int ERROR_GENERAL_EXCEPTION = -1;
	public static final int ERROR_NOT_MAKE_YET = -100;
	public static final int ERROR_UNHANDLED_EXCEPTION = -10;
	public static final int ERROR_CANNOT_FIND_USER = -20;
	public static final int ERROR_INVAILD_ACCESS = -30;
	public static final int ERROR_CANNOT_BUILD_THIS_PLACE = -40;
	
	//types - location
	public static final int LOCATION_TYPE_INVAILD = -1;
	public static final int LOCATION_TYPE_NORMAL = 1;
	public static final int LOCATION_TYPE_CASTLE = 2;
	public static final int LOCATION_TYPE_EXTERNALRESOURCE = 3;

	//types - buildings
	public static final int BUILDING_TYPE_INVALID = -1;
	public static final int BUILDING_TYPE_BLANK = 0;
	public static final int BUILDING_TYPE_CENTER = 1;
	public static final int BUILDING_TYPE_RESOURCECENTER = 2;
	public static final int BUILDING_TYPE_BARRACKS = 3;
	public static final int BUILDING_TYPE_TAVERN = 4;
	
	public static final int BUILDING_BASIC_LEVEL = 1;

	//types - units
	public static final int UNIT_TYPE_LV1 = 0;
	public static final int UNIT_TYPE_LV2 = 1;
	public static final int UNIT_TYPE_LV3 = 2;

	//location mapsize
	public static final int MAP_MAX_HEIGHT = 50;
	public static final int MAP_MAX_WIDTH = 50;

	public static final long TRAVEL_UNIT_TIME = 5000;




	//general methods - members -GET
	public ModelMembers getMember(ModelMembers targetMember);
	

	//general methods - members -INSERT
	public int insertMember(ModelMembers newMember);	
	
	
	//general methods - Others Information -GET
	public ModelUnit 		 	 getUnitInformation	(int UnitID);
	
	public List<ModelUnitBuild>  getUnitBuild		(ModelUnitBuild targetUnitBuild);
	
	public List<ModelStructures> getStructures		(ModelStructures targetStructures);
	
	public List<ModelXYval>		 getAllXYval		();
	
	public ModelOutResource 	 getOutResource		(Integer locationID);
	
	
	//general methods - castles - GET
	public List<ModelCastle> 			 getCastleList				(ModelMembers targetUser);
	
	public ModelCastle 			 		 getCastleOne				(Integer locationID);
	
	public List<ModelHeroTable> 		 getHeroList_InCastle		(ModelCastle targetCastle);
	
	public List<ModelSlot> 				 getCastleTroop_SlotList	(ModelCastle targetTroop);
	
	public ModelXYval					 getModelXYval				(ModelCastle targetXYval);
	
	public List<ModelWaitList_Building>  getModelWaitList_Building	(ModelCastle targetWitList);
	
	public List<ModelWaitList_Unit>		 getModelWaitList_Unit		(ModelCastle targetWitList);
	
	public List<ModelBuilding>			 getBuilding				(ModelCastle targetBuilding);
	
	public int							 getAllProduction			(ModelCastle targetCastleProduction);
	
	
	

	//general methods - castles - INSERT
	public int 		insertCastle(ModelCastle newCastle);
	
	public int 		insertXYval				(ModelXYval newXYval);
	
	public int 		insertBuilding			(ModelBuilding newBuilding);
	
	public int		insertWaitBuildingList	(ModelWaitList_Building newBuildingList);
	
	public int 		insertWaitUnitgList		(ModelWaitList_Unit newUnitList);
	
	public int 		insertCastletroop		(ModelCastleTroop newCastleTroop);
	
	public int		insertSlot				(ModelSlot newSlot);
	
	public int 		insertOutResource		(ModelOutResource newOutResource);
	
	
	
	//general methods - hero - GET
	public ModelHeroTable getHero(ModelHeroTable targetHero);
	public List<ModelHeroTroop> getHeroTroop_SlotList(ModelHeroTable targetTroop);
	
	
	//general methods - hero - INSERT
	public int insertHerotable		(ModelHeroTable newHero);
	
	public int insertHerotroop		(ModelHeroTroop newHeroTroop);
	
	
}
