package practice.webgameproject.strategy.model;

public class ModelSlot {
	private Integer SlotID;// 		INT(11)		AUTO_INCREMENT
	private Integer SlotUID;// 	unit id
	private Integer SlotAmount;//	amount of units

	public Integer getSlotID() {
		return SlotID;
	}
	
	public void setSlotID(Integer SlotID) {
		this.SlotID = SlotID;
	}

	public Integer getSlotUID() {
		return SlotUID;
	}
	public void setSlotUID(Integer SlotUID) {
		this.SlotUID = SlotUID;
	}
	public Integer getSlotAmount() {
		return SlotAmount;
	}
	public void setSlotAmount(Integer SlotAmount) {
		this.SlotAmount = SlotAmount;
	}
	
	
	public ModelSlot(Integer slotID, Integer slotUID, Integer slotAmount) {
		super();
		SlotID = slotID;
		SlotUID = slotUID;
		SlotAmount = slotAmount;
	}

	public ModelSlot(Integer SlotUID, Integer SlotAmount) {
		this.SlotUID = SlotUID;
		this.SlotAmount = SlotAmount;
	}
	
	public ModelSlot() {
		super();
		
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
