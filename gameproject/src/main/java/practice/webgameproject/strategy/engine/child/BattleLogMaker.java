package practice.webgameproject.strategy.engine.child;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.action.internal.UnresolvedEntityInsertActions;

import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelUnit;

public class BattleLogMaker {
	private static final String LOGFILEROOT = "$LOGFILEROOT";//로그파일의 루트 디렉토리
	private static final String LOGFILETAIL = ".log";//로그파일의 루트 디렉토리
	
	public static final int MAX_ROUND = 6;
	private static int serialNumber = 0;
	private int logID;
	private String logName;
	private int currentRound;
	private Date logDate;
	
	//공격측 병력정보
	String attacker_ID;
	List<ModelHeroTable> attacker;
	List<Army> attackerArmy;
	
	//방어측 병력정보
	String defender_ID;
	List<ModelHeroTable> defender;
	List<Army> defenderArmy;
	
	List<Round> round;
	
	public BattleLogMaker(String attacker_ID,List<ModelHeroTable> attacker, List<Army> attackerArmy,
			String defender_ID,List<ModelHeroTable> defender, List<Army> defenderArmy) {
		serialNumber++;
		logID = serialNumber;
		logDate = new Date();
		this.attacker = attacker;
		this.attackerArmy = attackerArmy;
		this.defender = defender;
		this.defenderArmy = defenderArmy;
		this.attacker_ID = attacker_ID;
		this.defender_ID = defender_ID;
		round = new ArrayList<Round>();
		currentRound = 0;
		
		logName = null;
	}

	public Date getLogDate() {
		return logDate;
	}
	


	public BattleLogMaker() {
		serialNumber++;
		logID = serialNumber;
		round = new ArrayList<Round>();
		currentRound = 0;
		logDate = new Date();
		logName = null;
	}

	public static String getLogfileRoot() {
		return LOGFILEROOT;
	}



	public String getAttacker_ID() {
		return attacker_ID;
	}

	public void setAttacker_ID(String attacker_ID) {
		this.attacker_ID = attacker_ID;
	}

	public String getDefender_ID() {
		return defender_ID;
	}

	public void setDefender_ID(String defender_ID) {
		this.defender_ID = defender_ID;
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
		if(logName == null){
			return "ERROR_call_getLogName_before_write";
		}
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

	public void writeLog() {
		logName = new SimpleDateFormat("YYYYMMDDhhmmssSSS").format(logDate)+"-"+attacker_ID+"-"+defender_ID;
		BufferedWriter out = null;
	    try {
	        out = new BufferedWriter(new FileWriter(LOGFILEROOT+"/"+logName+LOGFILETAIL));
	        //헤더만듬
	        out.write(makeHeader());
	        //각 라운드 기록함
	        for(int i = 0; i< round.size(); i++){
		        out.write(round.get(i).toString());
		        //out.newLine();
	        }
	        //전투 결산 부분
	        out.write(makeTail());
	        
	        out.close();
	      } catch (IOException e) {
	      }
	}
	
	private String makeHeader(){
		//TODO 초기병력 정보를 담는 가장 위쪽을 만들 것
		String str = "<div id='log' class='logarea'><table class='logtable'><tr><td colspan='12' class='logtitle' align='left'>"+logDate+"</td>"+
		"</tr><tr><td colspan='6' class='logtitle'>"+attacker_ID+"</td><td colspan='6' class='logtitle'>"+defender_ID+"</td>"+
        "</tr>";
		for(int i=0; i<attacker.size();i++){
			if(attacker.get(i).getHeroID()!=null){
				for(int j=0; j<attackerArmy.size();j++){
					
				}
			}
		}
		
		/*
		<tr><td colspan='6'>${공격자영웅ID}</td>
            <td colspan='6'>${방어자영웅ID}</td>
        </tr>
        <tr>
            <td colspan='2'>${슬롯}</td>
            <td colspan='2'>${슬롯}</td>
            <td colspan='2'>${슬롯}</td>
            <td colspan='2'>${슬롯}</td>
            <td colspan='2'>${슬롯}</td>
            <td colspan='2'>${슬롯}</td>
        </tr>
        <tr><td colspan=12><hr></td></tr>
        <tr>
            <td colspan='3'>${공격자총공}</td>
            <td colspan='3'>${공격자총체}</td>
            <td colspan='3'>${방어자총공}</td>
            <td colspan='3'>${방어자총체}</td>
        </tr>
";
*/
		return str;
	}
	private String makeTail(){
		//TODO 초기병력 정보를 담는 가장 위쪽을 만들 것
		String str = "여기에 로그 결산이 붙어요. 어떻게 보여줄지는 상의필요.";
		return str;
	}
	public static String getLogFile(String logName,String attacker_ID, String defender_ID){
		//		logName = new SimpleDateFormat("YYYYMMDDhhmmssSSS").format(logDate)+"-"+attacker_ID+"-"+defender_ID;
		String logText = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(LOGFILEROOT+"/"+logName+"-"+attacker_ID+"-"+defender_ID+LOGFILETAIL));
			
			while(true){
				String line = br.readLine();
				if(line ==null){
					break;
				}
				
				logText.concat("\r\n"+line);
			}
			
			br.close();
		} catch (IOException e) {
			// Do nothing
		}
		
		return "";
	}
	public static String getLogFile(String fullLogname){
		//		logName = new SimpleDateFormat("YYYYMMDDhhmmssSSS").format(logDate)+"-"+attacker_ID+"-"+defender_ID;
		String logText = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(LOGFILEROOT+"/"+fullLogname+LOGFILETAIL));
			
			while(true){
				String line = br.readLine();
				if(line ==null){
					break;
				}
				
				logText.concat("\r\n"+line);
			}
			
			br.close();
		} catch (IOException e) {
			// Do nothing
		}
		
		return "";
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
		
		public Round(){};
		public int getRound() {
			return round;
		}
		public List<Army> getAttackerArmy() {
			return attackerArmy;
		}
		public List<Army> getDefenderArmy() {
			return defenderArmy;
		}
		
		@Override
		public String toString() {
			//TODO 각 라운드 정보를 작성할것.
			String str= "여기에  라운드("+round+")에 대한 교전기록이 붙어요";
			
			return str;
		}
	}
	
}
