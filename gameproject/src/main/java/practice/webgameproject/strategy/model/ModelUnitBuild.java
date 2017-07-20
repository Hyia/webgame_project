package practice.webgameproject.strategy.model;

//import java.sql.Date;

import java.util.Date;

public class ModelUnitBuild {
	private Integer UnitID 		;//	INT(11)
	private String  Name 		;//VARCHAR(20)
	private Date BuildTime 	;//TIME
	private Integer Values 		;//INT(11)
	
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
	public Date getBuildTime() {
		return BuildTime;
	}
	public void setBuildTime(java.sql.Date buildTime) {
		Date d = new Date(buildTime.getTime());
		BuildTime = d;
	}
	public Integer getValues() {
		return Values;
	}
	public void setValues(Integer values) {
		Values = values;
	}
	
	public ModelUnitBuild(Integer unitID, String name, Date buildTime, Integer values) {
		UnitID = unitID;
		Name = name;
		BuildTime = buildTime;
		Values = values;
	}
	
	
	
	
}
