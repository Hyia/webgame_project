package practice.webgameproject.strategy.model;

public class ModelSlot {
	private Integer SlotID;// 		INT(11)		AUTO_INCREMENT
	private Integer SlotUID;// 	unit id
	private Integer SlotAmount;//	amount of units

	public Integer getSoltID() {
		return SlotID;
	}
	
	public void setSoltID(Integer soltID) {
		SlotID = soltID;
	}

	public Integer getSoltUID() {
		return SlotUID;
	}
	public void setSoltUID(Integer soltUID) {
		SlotUID = soltUID;
	}
	public Integer getSoltAmount() {
		return SlotAmount;
	}
	public void setSoltAmount(Integer soltAmount) {
		SlotAmount = soltAmount;
	}
	public ModelSlot(Integer soltUID, Integer soltAmount) {
		SlotUID = soltUID;
		SlotAmount = soltAmount;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ModelSlot){
			ModelSlot target = (ModelSlot) obj;
			return target.getSoltID().intValue() == SlotID.intValue();
		}
		return false;
	}
	
	
	
}
