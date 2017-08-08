package practice.webgameproject.strategy.interfaces;

import java.util.List;

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
	public static final int HEROID_NO_HERO_TROOPS = 0; // troops no hero had.
                      
	//location mapsize
	public static final int MAP_MAX_HEIGHT = 50;
	public static final int MAP_MAX_WIDTH = 50;

	public static final long TRAVEL_UNIT_TIME = 5000;



	//general methods - members -GET
	public Integer			getMembersLogin		(ModelMembers targetMember);	//Test OK
	
	public ModelMembers		getMember			(ModelMembers targetMember);	//Test OK
	

	//general methods - members -INSERT
	public Integer insertMembers	(ModelMembers newMember);	//Test OK
	
	public Integer insertLog		(ModelLog newLog);
	

	
	//general methods - members -UPDATE
	public Integer		updateMembers_Level					(ModelMembers updateMembers,ModelMembers searchMembers);
	public Integer		updateMembers_EXP					(ModelMembers updateMembers,ModelMembers searchMembers);
	public Integer		updateMembers_SaveProduction		(ModelMembers updateMembers,ModelMembers searchMembers);
	public Integer		updateMembers_UserData				(ModelMembers updateMembers,ModelMembers searchMembers);

	
	//general methods - members -DELETE
	public Integer		deleteMembers				(ModelMembers members);
	
	
	//general methods - Others Information -GET
	public ModelUnit 		 	 getUnitInformation		(Integer UnitID);
	
	public List<ModelUnit>		 getUnitInformationList	();
	
	public List<ModelUnitBuild>  getUnitBuild			();
	
	public List<ModelStructures> getStructures			();
	
	public List<ModelXYval>		 getAllXYval			();
	
	public List<ModelLog>		 getLog_ATK_DF_All		(String userID);
	
	public List<ModelLog>		 getLog_ATK				(String userID);
	
	public List<ModelLog>		 getLog_DF				(String userID);
	
	
	
	//general methods - Others Information -UPDATE
	public Integer		updateSlot					(ModelSlot updateSlot			, ModelSlot searchSlot);
	
	

	
	//general methods - Others Information -DELETE
	public Integer		deleteXYval					(Integer locationID);
	
	public Integer		deleteSlot					(Integer slotID);
	
	
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
	
	public ModelOutResource 			 getOutResource				(Integer locationID);
	
	
	

	//general methods - castles - INSERT
	public Integer 		insertCastle				(ModelCastle newCastle);
	
	public Integer 		insertXYval					(ModelXYval newXYval);
	
	public Integer 		insertBuilding				(ModelBuilding newBuilding);
	
	public Integer		insertWaitBuildingList		(ModelWaitList_Building newBuildingList);
	
	public Integer 		insertWaitUnitList			(ModelWaitList_Unit newUnitList);
	
	public Integer 		insertCastletroop			(ModelCastleTroop newCastleTroop);
		
	public Integer		insertSlot					(ModelSlot newSlot);
	
	public Integer 		insertOutResource			(ModelOutResource newOutResource);
	
	public Integer		mInsertWaitList_Building	(List<ModelWaitList_Building> list);
	
	public Integer		mInsertWaitList_Unit		(List<ModelWaitList_Unit> list);
	
	
	//general methods - castles - UPDATE	
	public Integer		updateCastle				(ModelCastle updateCastle					, ModelCastle searchCastle);
	
	public Integer		updateBuilding				(ModelBuilding updateBuilding				, ModelBuilding searchBuilding);
	
	public Integer		updateOutResource			(ModelOutResource updateOutResource			, ModelOutResource searchOutResource);
	
	
	
	//general methods - castles - DELETE
	public Integer		deleteCastleTroop			(Integer locationID);
	
	public Integer		deleteCastleBuilding_All	(Integer locationID);
	
	public Integer		deleteCastleBuilding_One	(ModelBuilding delBuilding);
	
	public Integer		deleteOutResource			(Integer locationID);
	
	public Integer		deleteCastle_All			(String userID);
	
	public Integer		deleteCastle_One			(Integer locationID);
	
	public Integer		deleteWaitList_Building		();
	
	public Integer		deleteWaitList_Unit			();
	
	
	
	
	
	
	//general methods - hero - GET
	public ModelHeroTable 		getHero					(ModelHeroTable targetHero);
	
	public List<ModelSlot>		getHeroTroop_SlotList	(Integer heroID);
	
	
	//general methods - hero - INSERT
	public Integer 		insertHerotable				(ModelHeroTable newHero);
	
	public Integer 		insertHerotroop				(ModelHeroTroop newHeroTroop);
	
	
	//general methods - hero - UPDATE
	public Integer		updateHero					(ModelHeroTable updateHero	, ModelHeroTable searchHero);
	
	
	//general methods - hero - DELETE
	public Integer		deleteHeroTable				(Integer heroID);
	
	public Integer		deleteHeroTroop				(Integer heroID);
	
}
