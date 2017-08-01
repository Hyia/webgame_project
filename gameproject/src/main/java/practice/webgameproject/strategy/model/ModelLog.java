package practice.webgameproject.strategy.model;

import java.util.Date;

public class ModelLog {
		private Integer LogNumber;			//  INT(11) NOT NULL AUTO_INCREMENT, PRIMARY KEY
		private String 	AttackUserID;		//  VARCHAR(50) NULL DEFAULT NULL 
		private String 	DefenseUserID;		//  VARCHAR(50) NULL DEFAULT NULL 
		private boolean AttackUserUseYN; 	//  BIT(1) NULL DEFAULT NULL
		private boolean DefenseUserUseYN;	//  BIT(1) NULL DEFAULT NULL
		private Date 	BattleDate;		 	//  DATETIME NULL DEFAULT NULL
		
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
		
		public ModelLog(Integer logNumber, String attackUserID, String defenseUserID, boolean attackUserUseYN,
				boolean defenseUserUseYN, Date battleDate) {
			super();
			LogNumber = logNumber;
			AttackUserID = attackUserID;
			DefenseUserID = defenseUserID;
			AttackUserUseYN = attackUserUseYN;
			DefenseUserUseYN = defenseUserUseYN;
			BattleDate = battleDate;
		}
		
		
}
