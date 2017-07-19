package practice.webgameproject.strategy.model;

public class ModelHeroTroop {
	private Integer HeroID;//	 INT(11)
	private Integer SlotID;// 	 INT(11)
	public Integer getHeroID() {
		return HeroID;
	}
	public void setHeroID(Integer heroID) {
		HeroID = heroID;
	}
	public Integer getSlotID() {
		return SlotID;
	}
	public void setSlotID(Integer slotID) {
		SlotID = slotID;
	}
	public ModelHeroTroop(Integer heroID, Integer slotID) {
		HeroID = heroID;
		SlotID = slotID;
	}
	
	
	
}
