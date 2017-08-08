package practice.webgameproject.strategy.model;

public class ModelCastleTroop {
	private Integer LocationID;//	 INT(11)
	private Integer SlotID 	;//	 INT(11)
	public Integer getLocationID() {
		return LocationID;
	}
	public void setLocationID(Integer locationID) {
		LocationID = locationID;
	}
	public Integer getSlotID() {
		return SlotID;
	}
	public void setSlotID(Integer slotID) {
		SlotID = slotID;
	}
	
	public ModelCastleTroop() {
		super();
	}
	
	public ModelCastleTroop(Integer locationID, Integer slotID) {
		super();
		LocationID = locationID;
		SlotID = slotID;
	}
	
	
	
}
