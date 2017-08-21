package practice.webgameproject.strategy.model;

import java.util.Date;

public class ModelLog {
	
	
		private Integer LogNumber;			//  INT(11) NOT NULL AUTO_INCREMENT, PRIMARY KEY
		private String 	LogName;
		private String 	AttackUserID;		//  VARCHAR(50) NULL DEFAULT NULL 
		private String 	DefenseUserID;		//  VARCHAR(50) NULL DEFAULT NULL 
		private boolean AttackUserUseYN; 	//  BIT(1) NULL DEFAULT NULL
		private boolean DefenseUserUseYN;	//  BIT(1) NULL DEFAULT NULL
		private Date 	BattleDate;		 	//  DATETIME NULL DEFAULT NULL
		private String 	WhoWins;
		
		public Integer getLogNumber() {
			return LogNumber;
		}
		
		public void setLogNumber(Integer logNumber) {
			LogNumber = logNumber;
		}
		
		public String getAttackUserID() {
			return AttackUserID;
		}
		
		public void setAttackUserID(String attackUserID) {
			AttackUserID = attackUserID;
		}
		
		public String getDefenseUserID() {
			return DefenseUserID;
		}
		
		public void setDefenseUserID(String defenseUserID) {
			DefenseUserID = defenseUserID;
		}
		
		public boolean isAttackUserUseYN() {
			return AttackUserUseYN;
		}
		
		public void setAttackUserUseYN(boolean attackUserUseYN) {
			AttackUserUseYN = attackUserUseYN;
		}
		
		public boolean isDefenseUserUseYN() {
			return DefenseUserUseYN;
		}
		
		public void setDefenseUserUseYN(boolean defenseUserUseYN) {
			DefenseUserUseYN = defenseUserUseYN;
		}
		
		public Date getBattleDate() {
			return BattleDate;
		}
		
		public void setBattleDate(Date battleDate) {
			BattleDate = battleDate;
		}
		public String getWhoWins() {
			return WhoWins;
		}

		public void setWhoWins(String whoWins) {
			WhoWins = whoWins;
		}
		public String getLogName() {
			return LogName;
		}

		public void setLogName(String logName) {
			LogName = logName;
		}
		public ModelLog() {
			super();
			
		}
		public ModelLog(String logName, String attackUserID, String defenseUserID, boolean attackUserUseYN,
				boolean defenseUserUseYN, Date battleDate) {
			super();
			LogName = logName;
			AttackUserID = attackUserID;
			DefenseUserID = defenseUserID;
			AttackUserUseYN = attackUserUseYN;
			DefenseUserUseYN = defenseUserUseYN;
			BattleDate = battleDate;
		}

		public ModelLog(String logName, String attackUserID, String defenseUserID,
				boolean attackUserUseYN, boolean defenseUserUseYN, Date battleDate, String whoWins) {
			super();
			LogName = logName;
			AttackUserID = attackUserID;
			DefenseUserID = defenseUserID;
			AttackUserUseYN = attackUserUseYN;
			DefenseUserUseYN = defenseUserUseYN;
			BattleDate = battleDate;
			WhoWins = whoWins;
		}
		
		
		
		
}
