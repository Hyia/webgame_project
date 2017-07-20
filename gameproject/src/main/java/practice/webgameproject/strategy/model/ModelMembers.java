package practice.webgameproject.strategy.model;

public class ModelMembers {
	private static final int STARTING_USER_RESOURCE_AMOUNT = 0;// 신규유저 시작자원량

	private	String	UserID			;				//` 		VARCHAR(20) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	private	String 	UserPW			;				//`		 VARCHAR(20) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	private	String 	UserNicName		;			//`	 VARCHAR(20) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	private	String 	UserEmail		;			//` 	VARCHAR(50) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	private	Integer UserLevel		;			//`		 INT(11) NOT NULL,
	private	Integer SaveProduction	;		//` DECIMAL(11,0) NOT NULL,
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
