package practice.webgameproject.strategy.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelBattleResult {
	
	public static final int MAX_ROUND = 6;
	private static int serialNumber = 0;
	private int logID;
	private String logName;
	private int currentRound;
	
	//공격측 병력정보
	List<ModelHeroTable> attacker;
	List<ModelUnit> attackerArmy;
	
	//방어측 병력정보
	List<ModelHeroTable> defender;
	List<ModelUnit> defenderArmy;
	
	List<Round> round;
	
	public ModelBattleResult(List<ModelHeroTable> attacker, List<ModelUnit> attackerArmy,
			List<ModelHeroTable> defender, List<ModelUnit> defenderArmy) {
		serialNumber++;
		logID = serialNumber;
		logName = (new Date()).toString()+"-"+logID;
		this.attacker = attacker;
		this.attackerArmy = attackerArmy;
		this.defender = defender;
		this.defenderArmy = defenderArmy;
		
		round = new ArrayList<Round>();
		currentRound = 0;
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
		
	}
	
	public class Army{
		Integer HeroID;
		List<ModelUnit> units;
		
		public Integer getHeroID() {
			return HeroID;
		}
		public List<ModelUnit> getUnits() {
			return units;
		}
		
		@Override
		public boolean equals(Object obj) {
			// FIXME 만약 이게 무조건 false가 리턴되는 버그가 있다면 instanceof로 몽땅 바꿀것.
			//그 경우 Integer, ModelHeroTable, ModelHeroTroop, 그리고 NULL에 대하여 처리.
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
	}

}
