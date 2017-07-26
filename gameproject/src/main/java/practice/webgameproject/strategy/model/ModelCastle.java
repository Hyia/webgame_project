package practice.webgameproject.strategy.model;

public class ModelCastle {
	private String	UserID 		 ;//VARCHAR(20)
	private String	Name 		 ;//VARCHAR(20)
	private Integer	Kind 		 ;//INT(11) 
	private Integer	LocationID	 ;//INT(11) 	AUTO_INCREMENT
	public String getUserID() {
		return UserID;
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
	public ModelCastle(String userID, String name, Integer kind, Integer locationID) {
		UserID = userID;
		Name = name;
		Kind = kind;
		LocationID = locationID;
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
