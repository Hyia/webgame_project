package practice.webgameproject.strategy.model;

public class ModelSlot {
	private Integer SoltID;// 		INT(11)		AUTO_INCREMENT
	private Integer SoltUID;// 	unit id
	private Integer SoltAmount;//	amount of units

	public Integer getSoltID() {
		return SoltID;
	}
	public Integer getSoltUID() {
		return SoltUID;
	}
	public void setSoltUID(Integer soltUID) {
		SoltUID = soltUID;
	}
	public Integer getSoltAmount() {
		return SoltAmount;
	}
	public void setSoltAmount(Integer soltAmount) {
		SoltAmount = soltAmount;
	}
	public ModelSlot(Integer soltUID, Integer soltAmount) {
		SoltUID = soltUID;
		SoltAmount = soltAmount;
	}
	
	
	
	
}