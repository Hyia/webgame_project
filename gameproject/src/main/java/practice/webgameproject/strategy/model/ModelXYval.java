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
	
	public ModelXYval() {
		
	}
	
	
	@Override
	public String toString() {
		return "ModelXYval [LocationID=" + LocationID + ", CastleX=" + CastleX + ", CastleY=" + CastleY + ", Kind="
				+ Kind + "]";
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof ModelXYval){
			ModelXYval target = (ModelXYval)obj;
			if(target.getLocationID() !=null && this.LocationID != null){
				return target.LocationID.intValue() == this.LocationID.intValue();
			}else{
				
				return (target.getCastleX().intValue()==this.CastleX.intValue()) && (target.getCastleY().intValue()==this.CastleY.intValue());
			}
		}
		if(obj instanceof Integer){
			Integer target = (Integer)obj;
			return target.intValue() == this.LocationID.intValue();
		}
		return super.equals(obj);
	}
	
	
	
}
