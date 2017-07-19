package practice.webgameproject.strategy.model;

public class ModelXYval {
	private Integer LocationID	 ;//INT(11)
	private Integer CastleX 	 ;//INT(11)
	private Integer CastleY 	 ;//INT(11)
	private Integer Kind 		 ;//INT(11)
	public Integer getLocationID() {
		return LocationID;
	}
	public void setLocationID(Integer locationID) {
		LocationID = locationID;
	}
	public Integer getCastleX() {
		return CastleX;
	}
	public void setCastleX(Integer castleX) {
		CastleX = castleX;
	}
	public Integer getCastleY() {
		return CastleY;
	}
	public void setCastleY(Integer castleY) {
		CastleY = castleY;
	}
	public Integer getKind() {
		return Kind;
	}
	public void setKind(Integer kind) {
		Kind = kind;
	}
	public ModelXYval(Integer locationID, Integer castleX, Integer castleY, Integer kind) {
		LocationID = locationID;
		CastleX = castleX;
		CastleY = castleY;
		Kind = kind;
	}
	
	
	
	
}
