package practice.webgameproject.strategy.engine.child;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.action.internal.UnresolvedEntityInsertActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import practice.webgameproject.strategy.engine.Engine;
import practice.webgameproject.strategy.interfaces.IServices;
import practice.webgameproject.strategy.model.ModelHeroTable;
import practice.webgameproject.strategy.model.ModelUnit;

public class BattleLogMaker {
	private static final Logger logger = LoggerFactory.getLogger(BattleLogMaker.class);
	
	private static final String LOGFILEROOT = "C:/";//로그파일의 루트 디렉토리
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




	public void setAttacker(final List<ModelHeroTable> attacker) {
		this.attacker = new ArrayList<ModelHeroTable>(attacker);
	}




	public List<Army> getAttackerArmy() {
		return attackerArmy;
	}




	public void setAttackerArmy(final List<Army> attackerArmy) {
		List<Army> cpa = new ArrayList<Army>();
		for(int i=0; i< attackerArmy.size(); i++){
			try {
				cpa.add(attackerArmy.get(i).clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.attackerArmy = cpa;
	}




	public List<ModelHeroTable> getDefender() {
		return defender;
	}




	public void setDefender(final List<ModelHeroTable> defender) {
		this.defender = new ArrayList<ModelHeroTable>(defender);
	}




	public List<Army> getDefenderArmy() {
		return defenderArmy;
	}




	public void setDefenderArmy(final List<Army> defenderArmy) {
		List<Army> cpd = new ArrayList<Army>();
		for(int i=0; i< defenderArmy.size(); i++){
			try {
				cpd.add(defenderArmy.get(i).clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.defenderArmy = cpd;
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
	public boolean addRound(final List<Army> attackerArmyStatus,final List<Army> defenderArmyStatus) {
		currentRound++;
		if(currentRound >= MAX_ROUND){
			return false;
		}
		
		if(attackerArmyStatus.size() == 0 || defenderArmyStatus.size() == 0){
			return false;
		}
		
		try {
		
		List<Army> cpa = new ArrayList<Army>();
		for(int i=0; i< attackerArmyStatus.size(); i++){
				cpa.add(new Army(attackerArmyStatus.get(i)));
		}
		List<Army> cpd = new ArrayList<Army>();
		for(int i=0; i< defenderArmyStatus.size(); i++){
			cpd.add(new Army(defenderArmyStatus.get(i)));
		}
		
		logger.info("실 사이즈:"+attackerArmyStatus.size()+","+defenderArmyStatus.size());
		logger.info("두 사이즈:"+cpa.size()+","+cpd.size());
		
		Round r = new Round(currentRound, cpa,cpd);
		round.add(r);
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("에러잖아요 에드라운드에");
		}
		return true;
	}
	public List<Round> getRound() {
		return round;
	}

	public void writeLog(Map<String,Object> rewards) {
		logName = new SimpleDateFormat("YYYYMMddhhmmssSSS").format(logDate)+"-"+attacker_ID+"-"+defender_ID;
		BufferedWriter out = null;
	    try {
	        out = new BufferedWriter//(new FileWriter(LOGFILEROOT+logName+LOGFILETAIL));
	        (new OutputStreamWriter(new FileOutputStream(LOGFILEROOT+logName+LOGFILETAIL), StandardCharsets.UTF_8));
	        //헤더만듬
	        out.write(makeHeader());
	        //각 라운드 기록함
	        for(int i = 0; i< round.size(); i++){
		        out.write(round.get(i).toString());
		        //out.newLine();
	        }
	        //전투 결산 부분
	        out.write(makeTail(rewards));
	        
	        out.close();
	      } catch (IOException e) {
	      }
	}
	
	private String makeHeader(){
		//TODO 초기병력 정보를 담는 가장 위쪽을 만들 것
		String str = "<table class='logtable'><tr>"
				+ "<td colspan='12' align='left' class='logdate'>"+new SimpleDateFormat("YYYY/MM/dd/hh:mm:ss").format(logDate)+"</td></tr>"
				+ "<tr><td colspan='6' class='log_userid'>"+attacker_ID+"</td>"
				+ "<td colspan='6' class='log_userid'>"+defender_ID+"</td></tr>";
		
		str += armStatus(attackerArmy, defenderArmy);
		
		return str;
	}
	
	@SuppressWarnings("unchecked")
	private String makeTail(Map<String,Object> rewards){
		String str = "<tr><td colspan=12 class=logtitle>피해</td></tr><tr>"
				+ "<td colspan='6'><table width='100%'>";
		
		for(int i=0; i<attackerArmy.size();i++){
			List<Integer> unitInitAmounts = attackerArmy.get(i).getUnitAmountList();
			List<Integer> unitAfterAmounts = round.get(round.size()-1).attackerArmy.get(i).getUnitAmountList();
			str+= "<tr>";
			
			Army arm = attackerArmy.get(i);
			if(arm.getHeroID()!=null && arm.getHeroID().intValue() != IServices.HEROID_NO_HERO_TROOPS){
				str += "<tr><td colspan='100%'>무명의 영웅("+arm.getHeroID()+")</td></tr>";// FIXME 영웅 이름에 대한 컬럼이 추가되면 변경할것.
			}
			for(int j=0; j<unitInitAmounts.size();j++){
				if(unitInitAmounts.get(j).intValue() != unitAfterAmounts.get(j).intValue()){
					String imgName = arm.getUnits().get(j).getName();
					str += "<td><img src='/images/"+imgName+".png' alt='"+imgName+"'>"+""
							+ "["+unitInitAmounts.get(j).intValue()+" - "+(unitInitAmounts.get(j).intValue()-unitAfterAmounts.get(j).intValue())+" ="+unitAfterAmounts.get(j).intValue()+"]</td>";
				}
			}
			str += "</tr>";
		}
		
		str+="</table></td><td colspan='6'><table width='100%'>";

		for(int i=0; i<defenderArmy.size();i++){
			List<Integer> unitInitAmounts = defenderArmy.get(i).getUnitAmountList();
			logger.info("라운드 크기:"+round.size());
			logger.info("라운드0 방어측크기:"+round.get(round.size()-1).defenderArmy.size());
			List<Integer> unitAfterAmounts = round.get(round.size()-1).defenderArmy.get(i).getUnitAmountList();
			str+= "<tr>";
			
			Army arm = defenderArmy.get(i);
			if(arm.getHeroID()!=null && arm.getHeroID().intValue() != IServices.HEROID_NO_HERO_TROOPS){
				str += "<tr><td colspan='100%'>무명의 영웅("+arm.getHeroID()+")</td></tr>";// FIXME 영웅 이름에 대한 컬럼이 추가되면 변경할것.
			}
			for(int j=0; j<unitInitAmounts.size();j++){
				if(unitInitAmounts.get(j).intValue() != unitAfterAmounts.get(j).intValue()){
					String imgName = arm.getUnits().get(j).getName();
					str += "<td><img src='/images/"+imgName+".png' alt='"+imgName+"'>"+""
							+ "["+unitInitAmounts.get(j).intValue()+" - "+(unitInitAmounts.get(j).intValue()-unitAfterAmounts.get(j).intValue())+" ="+unitAfterAmounts.get(j).intValue()+"]</td>";
				}
			}
			str += "</tr>";
		}

		str+="</table></td</tr> <tr><td colspan=12><hr></td></tr>";
		str+= armStatus(round.get(round.size()-1).attackerArmy, round.get(round.size()-1).defenderArmy);
		
		//전리품
		str += "<tr><td colspan='12' class='logtitle'>노획물</td></tr><tr><td colspan=12><hr></td></tr>";
		
		Map<String,Integer> aitem = (Map<String, Integer>) rewards.get("attacker");
		Map<String,Integer> ditem = (Map<String, Integer>) rewards.get("defender");

		str+= "<tr><td colspan='6'><table>";
		for(String key : aitem.keySet()){
			str+="<tr><td>"+key+" : "+aitem.get(key)+"</td></tr>";
		}
		str+="</table></td><td colspan='6'><table>";
		for(String key : ditem.keySet()){
			str+="<tr><td>"+key+" : "+ditem.get(key)+"</td></tr>";
		}
		str+="</table></td></tr></table>";
		return str;
	}
	public static String getLogFile(String logName,String attacker_ID, String defender_ID){
		String fullLogname = logName+"-"+attacker_ID+"-"+defender_ID;
		return getLogFile(fullLogname);
	}
	public static String getLogFile(String fullLogname){
		//		logName = new SimpleDateFormat("YYYYMMDDhhmmssSSS").format(logDate)+"-"+attacker_ID+"-"+defender_ID;
		String logText = "";
		BufferedReader br = null;
		try {
			logger.info(LOGFILEROOT+fullLogname+LOGFILETAIL);
			br = new BufferedReader//(new FileReader(LOGFILEROOT+fullLogname+LOGFILETAIL));
			(new InputStreamReader(new FileInputStream(LOGFILEROOT+fullLogname+LOGFILETAIL), "UTF-8"));			
			while(true){
				String line = br.readLine();
				if(line ==null){
					break;
				}
				
				logText += line;
			}
			
			br.close();
		} catch (IOException e) {
			// Do nothing
			e.printStackTrace();
		}
		
		return logText;
	}
	
	private String armStatus(List<Army> attackerArmy, List<Army> defenderArmy){
		String str = "<tr><td colspan='6'><table width='100%'>";
		int aatksum=0;
		int ahpsum=0;
		int datksum=0;
		int dhpsum=0;
		
		for(int i=0; i<attackerArmy.size();i++){
			Army arm = attackerArmy.get(i);
			//영웅이 있으면 영웅을 달아줌
			if(arm.getHeroID()!=null && arm.getHeroID().intValue() != IServices.HEROID_NO_HERO_TROOPS){
				str += "<tr><td colspan='100%'>무명의 영웅("+arm.getHeroID()+")</td></tr>";// FIXME 영웅 이름에 대한 컬럼이 추가되면 변경할것.
			}
			str +="<tr>";
			List<ModelUnit> units = arm.getUnits();
			for(int j=0; j<units.size(); j++){
				String imgName = units.get(j).getName();
				str += "<td><img src='/images/"+imgName+".png' alt='"+imgName+"'>"+"["+arm.getUnitAmountList().get(j).intValue()+"]</td>";
				aatksum += units.get(j).getATK() * arm.getUnitAmountList().get(j).intValue();
				ahpsum += units.get(j).getHP() * arm.getUnitAmountList().get(j).intValue();
			}
			str +="</tr>";
		}
		str += "</table></td><td colspan='6'><table width='100%'>";
		for(int i=0; i<defenderArmy.size();i++){
			Army arm = defenderArmy.get(i);
			//영웅이 있으면 영웅을 달아줌
			if(arm.getHeroID()!=null && arm.getHeroID().intValue() != IServices.HEROID_NO_HERO_TROOPS){
				str += "<tr><td colspan='100%'>무명의 영웅("+arm.getHeroID()+")</td></tr>";// FIXME 영웅 이름에 대한 컬럼이 추가되면 변경할것.
			}
			str +="<tr>";
			List<ModelUnit> units = arm.getUnits();
			for(int j=0; j<units.size(); j++){
				String imgName = units.get(j).getName();
				str += "<td><img src='/images/"+imgName+".png' alt='"+imgName+"'>"+"["+arm.getUnitAmountList().get(j).intValue()+"]</td>";
				datksum += units.get(j).getATK() * arm.getUnitAmountList().get(j).intValue();
				dhpsum += units.get(j).getHP() * arm.getUnitAmountList().get(j).intValue();
			}
			str +="</tr>";
		}
		str += "</table></td></tr><tr><td colspan='3'>총 공격력: "+aatksum+"</td><td colspan='3'>총 체력: "+ahpsum+"</td>"
				+ "<td colspan='3'>총 공격력: "+datksum+"</td><td colspan='3'>총 체력: "+dhpsum+"</td></tr>";

		return str;
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
			String str= "<tr><td colspan='12'><div name='round' class=logtable><table width='100%'><tr>"
					+ "<td colspan='12' class='logtitle' align='left'>"+round+"번째 회합</td></tr>";
					
			str += armStatus(attackerArmy, defenderArmy);

			return str;
		}
	}
	
}
