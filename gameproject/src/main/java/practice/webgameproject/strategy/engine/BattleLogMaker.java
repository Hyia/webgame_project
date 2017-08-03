package practice.webgameproject.strategy.engine;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.action.internal.UnresolvedEntityInsertActions;

import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelUnit;

public class BattleLogMaker {
	
	public static final int MAX_ROUND = 6;
	private static int serialNumber = 0;
	private int logID;
	private String logName;
	private int currentRound;
	private Date logDate;
	
	//공격측 병력정보
	List<ModelHeroTable> attacker;
	List<Army> attackerArmy;
	
	//방어측 병력정보
	List<ModelHeroTable> defender;
	List<Army> defenderArmy;
	
	List<Round> round;
	
	public BattleLogMaker(List<ModelHeroTable> attacker, List<Army> attackerArmy,
			List<ModelHeroTable> defender, List<Army> defenderArmy) {
		serialNumber++;
		logID = serialNumber;
		logDate = new Date();
		logName = logDate.toString()+"-"+logID;
		this.attacker = attacker;
		this.attackerArmy = attackerArmy;
		this.defender = defender;
		this.defenderArmy = defenderArmy;
		
		round = new ArrayList<Round>();
		currentRound = 0;
	}

	public Date getLogDate() {
		return logDate;
	}
	


	public BattleLogMaker() {
		serialNumber++;
		logID = serialNumber;
		logName = (new Date()).toString()+"-"+logID;
		round = new ArrayList<Round>();
		currentRound = 0;
	}

	



	public List<ModelHeroTable> getAttacker() {
		return attacker;
	}




	public void setAttacker(List<ModelHeroTable> attacker) {
		this.attacker = attacker;
	}




	public List<Army> getAttackerArmy() {
		return attackerArmy;
	}




	public void setAttackerArmy(List<Army> attackerArmy) {
		this.attackerArmy = attackerArmy;
	}




	public List<ModelHeroTable> getDefender() {
		return defender;
	}




	public void setDefender(List<ModelHeroTable> defender) {
		this.defender = defender;
	}




	public List<Army> getDefenderArmy() {
		return defenderArmy;
	}




	public void setDefenderArmy(List<Army> defenderArmy) {
		this.defenderArmy = defenderArmy;
	}




	public int getLogID() {
		return logID;
	}
	public String getLogName() {
		return logName;
	}

	//해당 라운드를 ModelBattleResult에 추가하며 추가에 성공하면 true를 반환.
	public boolean addRound(List<Army> attackerArmyStatus,List<Army> defenderArmyStatus){
		currentRound++;
		if(currentRound >= MAX_ROUND){
			return false;
		}
		
		Round r = new Round(currentRound, attackerArmyStatus,defenderArmyStatus);
		round.add(r);
		return true;
	}
	public List<Round> getRound() {
		return round;
	}

	
	
	public class Round{
		int round;
		List<Army> attackerArmy;
		List<Army> defenderArmy;
		public Round(int round, List<Army> attackerArmy, List<Army> defenderArmy) {
			this.round = round;
			this.attackerArmy = attackerArmy;
			this.defenderArmy = defenderArmy;
		}
		public int getRound() {
			return round;
		}
		public List<Army> getAttackerArmy() {
			return attackerArmy;
		}
		public List<Army> getDefenderArmy() {
			return defenderArmy;
		}
	}
	
	public class Army{
		Integer HeroID;
		List<ModelUnit> units;
		List<Integer> unitAmountList;
		
		public Integer getHeroID() {
			return HeroID;
		}
		public List<ModelUnit> getUnits() {
			return units;
		}
		
		public void setUnits(List<ModelUnit> units){
			this.units = units;
		}
		
		public void addUnit(ModelUnit unit, Integer amount){
			this.units.add(unit);
			this.unitAmountList.add(amount);
		}
		
		
		public List<Integer> getUnitAmountList() {
			return unitAmountList;
		}
		public void setUnitAmountList(List<Integer> unitAmountList) {
			this.unitAmountList = unitAmountList;
		}
		public Army(Integer heroID, List<ModelUnit> units) {
			super();
			HeroID = heroID;
			this.units = units;
		}
		public Army(Integer heroID) {
			super();
			HeroID = heroID;
			this.units = new ArrayList<ModelUnit>();
			this.unitAmountList = new ArrayList<Integer>();
		}
		
		@Override
		public boolean equals(Object obj) {
			// FIXME 만약 이게 무조건 false가 리턴되는 버그가 있다면 instanceof로 몽땅 바꿀것.
			//그 경우 Integer, ModelHeroTable, ModelHeroTroop, 그리고 NULL에 대하여 처리.
			if(obj instanceof Integer){
				return this.HeroID.intValue() ==( (Integer)obj).intValue(); 
			}
			try {
				Class<?> someClass = obj.getClass();
				Field field = someClass.getField("HeroID");
				Integer targetID = (Integer) field.get(obj);
				return targetID.intValue() == this.HeroID.intValue();
			} catch (NoSuchFieldException | SecurityException e) {
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
			return false;
		}
		public boolean isDefeat() {
			for(int i=0; i<unitAmountList.size();i++){
				if(unitAmountList.get(i).intValue() > 0){
					return false;
				}
			}
			return true;
		}
	}

}
