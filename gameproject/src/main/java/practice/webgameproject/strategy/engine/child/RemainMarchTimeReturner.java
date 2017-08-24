package practice.webgameproject.strategy.engine.child;

import java.util.Map;

import practice.webgameproject.strategy.model.ModelCastle;

public class RemainMarchTimeReturner {
	//Map<ModelCastle, Map<Integer, String>>
//	public ModelCastle key;
//	public Integer heroID;
	public boolean isReturning;
	public String remainTime;
	public RemainMarchTimeReturner(boolean isReturning, String remainTime) {
		super();
		this.isReturning = isReturning;
		this.remainTime = remainTime;
	}
	public boolean isReturning() {
		return isReturning;
	}
	public void setReturning(boolean isReturning) {
		this.isReturning = isReturning;
	}
	public String getRemainTime() {
		return remainTime;
	}
	public void setRemainTime(String remainTime) {
		this.remainTime = remainTime;
	}
	
	
	
	

}
