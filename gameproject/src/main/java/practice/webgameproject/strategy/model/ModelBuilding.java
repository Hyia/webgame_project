package practice.webgameproject.strategy.model;

public class ModelBuilding {
	private Integer	LocationID 	 ;//INT(11)
	private Integer	Kind 		 ;// INT(11)
	private Integer	Level 		 ;//INT(11)	DEFAULT '1'
	private Integer	RoomNumber	 ;//INT(11)
	public Integer getLocationID() {
		return LocationID;
	}
	public void setLocationID(Integer locationID) {
		LocationID = locationID;
	}
	public Integer getKind() {
		return Kind;
	}
	public void setKind(Integer kind) {
		Kind = kind;
	}
	public Integer getLevel() {
		return Level;
	}
	public void setLevel(Integer level) {
		Level = level;
	}
	public Integer getRoomNumber() {
		return RoomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		RoomNumber = roomNumber;
	}
	public ModelBuilding(Integer locationID, Integer kind, Integer level, Integer roomNumber) {
		LocationID = locationID;
		Kind = kind;
		Level = level;
		RoomNumber = roomNumber;
	}
	
	
	
}
