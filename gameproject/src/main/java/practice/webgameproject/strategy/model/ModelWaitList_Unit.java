package practice.webgameproject.strategy.model;

import java.util.Date;

public class ModelWaitList_Unit {

	private	Date 	WaitTime 	;//	WaitTime 	DATETIME NOT NULL,
	private Integer UnitID	 	;//	UnitID	 	INT(11) NULL DEFAULT NULL,
	private Integer LocationID	;//	LocationID	INT(11) NOT NULL,
	private Integer Amount	 	;//	Amount	 	INT(11) NULL DEFAULT NULL,
	public Date getWaitTime() {
		return WaitTime;
	}
	public void setWaitTime(Date waitTime) {
		WaitTime = waitTime;
	}
	
	public Integer getUnitID() {
		return UnitID;
	}
	
	public void setUnitID(Integer unitID) {
		UnitID = unitID;
	}
	
	public Integer getLocationID() {
		return LocationID;
	}
	
	public void setLocationID(Integer locationID) {
		LocationID = locationID;
	}
	
	public Integer getAmount() {
		return Amount;
	}
	
	public void setAmount(Integer amount) {
		Amount = amount;
	}
	
	public ModelWaitList_Unit() {
		super();
		
	}
	
	public ModelWaitList_Unit(Date waitTime, Integer unitID, Integer locationID, Integer amount) {
		super();
		WaitTime = waitTime;
		UnitID = unitID;
		LocationID = locationID;
		Amount = amount;
	}
	
	
	
	
}
