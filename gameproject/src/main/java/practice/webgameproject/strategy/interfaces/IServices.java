package practice.webgameproject.strategy.interfaces;

import java.util.List;

import practice.webgameproject.strategy.model.ModelBuilding;
import practice.webgameproject.strategy.model.ModelCastle;
import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelMembers;

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




	//general methods - members
	public int insertMember(ModelMembers member);	
	public ModelMembers getMember(ModelMembers member);	
	
	//general methods - castles
	public List<ModelCastle> getCastleList(ModelMembers targetUser);
	public List<ModelHeroTable> getHeroList_InCastle(ModelCastle targetCastle);

	//general methods - castles - buildings
	public int insertBuilding(ModelBuilding building);

	
	
}
