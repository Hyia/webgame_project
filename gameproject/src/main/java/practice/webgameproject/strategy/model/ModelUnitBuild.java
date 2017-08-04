package practice.webgameproject.strategy.model;

//import java.sql.Date;

import java.util.Date;

import android.text.format.Time;

public class ModelUnitBuild {
	

	private Integer UnitID 		;//	INT(11)
	private Date BuildTime	 	;//TIME
	private Integer Values 		;//INT(11)
	
	
	public Integer getUnitID() {
		return UnitID;
	}



	public void setUnitID(Integer unitID) {
		UnitID = unitID;
	}



	public Date getBuildTime() {
		return BuildTime;
	}



	public void setBuildTime(Date buildTime) {
		BuildTime = buildTime;
	}



	public Integer getValues() {
		return Values;
	}



	public void setValues(Integer values) {
		Values = values;
	}

	public ModelUnitBuild() {
		super();
	}


	@Override
	public String toString() {
		return "ModelUnitBuild [UnitID=" + UnitID + ", BuildTime=" + BuildTime + ", Values=" + Values + "]";
	}



	public ModelUnitBuild(Integer unitID,  Date buildTime, Integer values) {
		super();
		UnitID = unitID;
		BuildTime = buildTime;
		Values = values;
	}
	
	
	
}
