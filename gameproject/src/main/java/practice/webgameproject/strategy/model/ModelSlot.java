package practice.webgameproject.strategy.model;

public class ModelSlot {
	private Integer SlotID;// 		INT(11)		AUTO_INCREMENT
	private Integer SlotUID;// 	unit id
	private Integer SlotAmount;//	amount of units

	public Integer getSlotID() {
		return SlotID;
	}
	
	public void setSlotID(Integer SlotID) {
		SlotID = SlotID;
	}

	public Integer getSlotUID() {
		return SlotUID;
	}
	public void setSlotUID(Integer SlotUID) {
		SlotUID = SlotUID;
	}
	public Integer getSlotAmount() {
		return SlotAmount;
	}
	public void setSlotAmount(Integer SlotAmount) {
		SlotAmount = SlotAmount;
	}
	public ModelSlot(Integer SlotUID, Integer SlotAmount) {
		SlotUID = SlotUID;
		SlotAmount = SlotAmount;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ModelSlot){
			ModelSlot target = (ModelSlot) obj;
			return target.getSlotID().intValue() == SlotID.intValue();
		}
		return false;
	}
	
	
	
}
