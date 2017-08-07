package practice.webgameproject.strategy.model;

public class ModelMembers {


	private static final int STARTING_USER_RESOURCE_AMOUNT = 0;// 신규유저 시작자원량

	private	String	UserID			;	//` VARCHAR(20)
	private	String 	UserPW			;	//`	VARCHAR(20)
	private	String 	UserNicName		;	//`	VARCHAR(20)
	private	String 	UserEmail		;	//` VARCHAR(50)
	private	Integer UserLevel		;	//`	INT(11)
	private	Integer SaveProduction	;	//` DECIMAL(11,0)
	private	Integer UserEXP			;	//`	INT(11)
	
	
	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getUserPW() {
		return UserPW;
	}
	public void setUserPW(String userPW) {
		UserPW = userPW;
	}
	public String getUserNicName() {
		return UserNicName;
	}
	public void setUserNicName(String userNicName) {
		UserNicName = userNicName;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public Integer getUserLevel() {
		return UserLevel;
	}
	public void setUserLevel(Integer userLevel) {
		UserLevel = userLevel;
	}
	public Integer getSaveProduction() {
		return SaveProduction;
	}
	public void setSaveProduction(Integer saveProduction) {
		SaveProduction = saveProduction;
	}
	public Integer getUserEXP() {
		return UserEXP;
	}
	public void setUserEXP(Integer userEXP) {
		UserEXP = userEXP;
	}
	
	public ModelMembers(String userID, String userPW, String userNicName, String userEmail) {
		UserID = userID;
		UserPW = userPW;
		UserNicName = userNicName;
		UserEmail = userEmail;
		UserLevel = 1;
		SaveProduction = STARTING_USER_RESOURCE_AMOUNT;
	}
	
	
	public ModelMembers(String userID, String userPW, String userNicName, String userEmail, Integer userLevel,
			Integer saveProduction) {
		super();
		UserID = userID;
		UserPW = userPW;
		UserNicName = userNicName;
		UserEmail = userEmail;
		UserLevel = userLevel;
		SaveProduction = saveProduction;
	}
	
	
	public ModelMembers() {
		super();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ModelMembers){
			ModelMembers otherUser = (ModelMembers)obj;
			if(this.UserID.equals(otherUser.UserID) ){
				return true;
			}
		}
		return false;
	}
	
	
	
}
