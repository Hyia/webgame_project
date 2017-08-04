package practice.webgameproject.strategy.model;

public class ModelUnit {
	

	private Integer UnitID;// 		INT(11) 	AUTO_INCREMENT
	private String  Name 	;//	VARCHAR(20)
	private Integer ATK 	;//	INT(11) 
	private Integer SPD 	;//	INT(11) 
	private Integer HP 		;//INT(11)

	public Integer getUnitID() {
		return UnitID;
	}
	
	public void setUnitID(Integer unitID) {
		UnitID = unitID;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getATK() {
		return ATK;
	}
	public void setATK(Integer aTK) {
		ATK = aTK;
	}
	public Integer getSPD() {
		return SPD;
	}
	public void setSPD(Integer sPD) {
		SPD = sPD;
	}
	public Integer getHP() {
		return HP;
	}
	public void setHP(Integer hP) {
		HP = hP;
	}
	public ModelUnit() {
		super();
	}
	public ModelUnit(String name, Integer aTK, Integer sPD, Integer hP) {
		super();
		Name = name;
		ATK = aTK;
		SPD = sPD;
		HP = hP;
	}
	public ModelUnit(Integer unitID,String name, Integer aTK, Integer sPD, Integer hP) {
		this.UnitID = unitID;
		Name = name;
		ATK = aTK;
		SPD = sPD;
		HP = hP;
	}
	
	public ModelUnit(ModelUnit unitInfo) {
		this(unitInfo.UnitID,unitInfo.Name,unitInfo.ATK,unitInfo.SPD,unitInfo.HP);
	}
	
	@Override
	public String toString() {
		return "ModelUnit \nUnitID=" + UnitID + "\n Name=" + Name + "\n ATK=" + ATK + "\n SPD=" + SPD + "\n HP=" + HP;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof ModelUnit){
			return getUnitID().equals(this.UnitID);
		}
		return false;
	}
	
	
	
}
