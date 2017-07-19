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
	
	

	/**
	 * fixme:// LocationID를 통해 비교할 다른 무언가가 나타나면 추가할 것.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ModelResources){
			return ((ModelResources) obj).getLocationID().equals(LocationID);
		}
		
		return false;
	}
	
	
	
	
}
