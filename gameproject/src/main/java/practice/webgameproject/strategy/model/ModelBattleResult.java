package practice.webgameproject.strategy.model;

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
	ModelHeroTable attacker;
	List<ModelUnit> attackerArmy;
	
	//방어측 병력정보
	List<ModelHeroTable> defender;
	List<ModelUnit> defenderArmy;
	
	List<Round> round;
	
	public ModelBattleResult(ModelHeroTable attacker, List<ModelUnit> attackerArmy,
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
	public boolean addRound(List<ModelUnit> attackerArmyStatus,List<ModelUnit> defenderArmyStatus){
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
		List<ModelUnit> attackerArmyStatus;
		List<ModelUnit> defenderArmyStatus;
		
		public Round(int round, List<ModelUnit> attackerArmyStatus, List<ModelUnit> defenderArmyStatus) {
			this.round = round;
			this.attackerArmyStatus = attackerArmyStatus;
			this.defenderArmyStatus = defenderArmyStatus;
		}
		
		
	}

}
