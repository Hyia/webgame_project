package practice.webgameproject.strategy.model;

public class ModelOutResource {
	private Integer LocationID;//AUTO_INCREMENT
	private String UserID; 
	private Integer Production; 
	private Integer Kind;
	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public Integer getProduction() {
		return Production;
	}
	public void setProduction(Integer production) {
		Production = production;
	}
	public Integer getKind() {
		return Kind;
	}
	public void setKind(Integer kind) {
		Kind = kind;
	}
	public Integer getLocationID() {
		return LocationID;
	}
	public ModelOutResource(String userID, Integer production, Integer kind) {
		UserID = userID;
		Production = production;
		Kind = kind;
	}
	public ModelOutResource(Integer locationID, String userID, Integer production, Integer kind) {
		LocationID = locationID;
		UserID = userID;
		Production = production;
		Kind = kind;
	}
	
	
}
