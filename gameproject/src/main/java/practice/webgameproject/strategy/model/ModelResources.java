package practice.webgameproject.strategy.model;

import static org.mockito.Matchers.contains;

public class ModelResources {

	private Integer LocationID;//
	private Integer Production;// 	INT(11)

	public Integer getLocationID() {
		return LocationID;
	}
	public Integer getProduction() {
		return Production;
	}
	public void setProduction(Integer production) {
		Production = production;
	}
	
	public ModelResources(Integer production) {
		Production = production;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ModelResources){
			return ((ModelResources) obj).getLocationID().equals(LocationID);
		}
		
		return false;
	}
	
	
	
	
}
