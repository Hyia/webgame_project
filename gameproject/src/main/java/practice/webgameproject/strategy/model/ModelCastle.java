package practice.webgameproject.strategy.model;

public class ModelCastle {


	private String	UserID 		 ;//VARCHAR(20)
	private String	Name 		 ;//VARCHAR(20)
	private Integer	Kind 		 ;//INT(11) 
	private Integer	LocationID	 ;//INT(11) 	AUTO_INCREMENT
	private Integer Production	 ;//INT(11)		NOT NULL DEFAULT '0'
	
	
	public String getUserID() {
		return UserID;
	}
	public Integer getProduction() {
		return Production;
	}
	public void setProduction(Integer production) {
		Production = production;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
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
	public void setLocationID(Integer locationID) {
		LocationID = locationID;
	}
	
	public ModelCastle() {
		
	}	
	
	public ModelCastle(String userID, String name, Integer kind, Integer locationID, Integer production) {
		super();
		UserID = userID;
		Name = name;
		Kind = kind;
		LocationID = locationID;
		Production = production;
	}
	
	
	@Override
	public String toString() {
		return "ModelCastle [UserID=" + UserID + ", Name=" + Name + ", Kind=" + Kind + ", LocationID=" + LocationID
				+ ", Production=" + Production + "]";
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof ModelCastle){
			ModelCastle target = (ModelCastle)obj;
			return target.LocationID.intValue() == this.LocationID.intValue();
		}
		if(obj instanceof Integer){//LocationID로 비교
			Integer target = (Integer)obj;
			return target.intValue() == LocationID.intValue();
		}
		
		return super.equals(obj);
	}
	
}
