package practice.webgameproject.strategy.model;

public class ModelHeroTable {
	
	public static final boolean SEX_MALE=true;
	public static final boolean SEX_FEMALE=false;
	
	private Integer HeroID			;				// 		INT(11)		AUTO_INCREMENT
	private Integer STR 			;//	INT(11) 
	private Integer AGI 			;//INT(11) 
	private Integer CON 			;//INT(11) 
	private String  Owner 			;//VARCHAR(20)			UserID
	private Integer LocationID  	;//INT(11) 
	private Integer Statuus 		;//INT(11) 
	private Integer Specialty 		;//INT(11) 
	private Integer Potrait 		;//INT(11) 
	private Boolean Sex 			;//BIT(1)
	private Integer HeroLevel		;//INT(11)	
	private Integer HeroEXP  		;//INT(11)
	
	
	public Integer getHeroID() {
		return HeroID;
	}
	public void setHeroID(Integer heroID) {
		HeroID = heroID;
	}
	public Integer getSTR() {
		return STR;
	}
	public void setSTR(Integer sTR) {
		STR = sTR;
	}
	public Integer getAGI() {
		return AGI;
	}
	public void setAGI(Integer aGI) {
		AGI = aGI;
	}
	public Integer getCON() {
		return CON;
	}
	public void setCON(Integer cON) {
		CON = cON;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public Integer getLocationID() {
		return LocationID;
	}
	public void setLocationID(Integer locationID) {
		LocationID = locationID;
	}
	public Integer getStatuus() {
		return Statuus;
	}
	public void setStatuus(Integer statuus) {
		Statuus = statuus;
	}
	public Integer getSpecialty() {
		return Specialty;
	}
	public void setSpecialty(Integer specialty) {
		Specialty = specialty;
	}
	public Integer getPotrait() {
		return Potrait;
	}
	public void setPotrait(Integer potrait) {
		Potrait = potrait;
	}
	public Boolean getSex() {
		return Sex;
	}
	public void setSex(Boolean sex) {
		Sex = sex;
	}
	
	public Integer getHeroLevel() {
		return HeroLevel;
	}
	public void setHeroLevel(Integer heroLevel) {
		HeroLevel = heroLevel;
	}
	public Integer getHeroEXP() {
		return HeroEXP;
	}
	public void setHeroEXP(Integer heroEXP) {
		HeroEXP = heroEXP;
	}
	public ModelHeroTable(){
		
	}
	
	public ModelHeroTable(Integer heroID, Integer sTR, Integer aGI, Integer cON, String owner, Integer locationID,
			Integer statuus, Integer specialty, Integer potrait, Boolean sex) {
		HeroID = heroID;
		STR = sTR;
		AGI = aGI;
		CON = cON;
		Owner = owner;
		LocationID = locationID;
		Statuus = statuus;
		Specialty = specialty;
		Potrait = potrait;
		Sex = sex;
	}
	public ModelHeroTable(Integer heroID, Integer sTR, Integer aGI, Integer cON, Integer locationID,
			Integer statuus, Integer specialty, Integer potrait, Boolean sex) {
		HeroID = heroID;
		STR = sTR;
		AGI = aGI;
		CON = cON;
		Owner = null;
		LocationID = locationID;
		Statuus = statuus;
		Specialty = specialty;
		Potrait = potrait;
		Sex = sex;
	}
	
	
	public ModelHeroTable(Integer heroID, Integer sTR, Integer aGI, Integer cON, String owner, Integer locationID,
			Integer statuus, Integer specialty, Integer potrait, Boolean sex, Integer heroLevel, Integer heroEXP) {
		super();
		HeroID = heroID;
		STR = sTR;
		AGI = aGI;
		CON = cON;
		Owner = owner;
		LocationID = locationID;
		Statuus = statuus;
		Specialty = specialty;
		Potrait = potrait;
		Sex = sex;
		HeroLevel = heroLevel;
		HeroEXP = heroEXP;
	}
	@Override
	public String toString() {
		return "ModelHeroTable [HeroID=" + HeroID + ", STR=" + STR + ", AGI=" + AGI + ", CON=" + CON + ", Owner="
				+ Owner + ", LocationID=" + LocationID + ", Statuus=" + Statuus + ", Specialty=" + Specialty
				+ ", Potrait=" + Potrait + ", Sex=" + Sex + ", HeroLevel=" + HeroLevel + ", HeroEXP=" + HeroEXP + "]";
	}
	
}
