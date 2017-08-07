package practice.webgameproject.strategy.engine.child;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import practice.webgameproject.strategy.model.ModelUnit;

public class Army{
	Integer HeroID;
	List<ModelUnit> units;
	List<Integer> unitAmountList;
	
	public Integer getHeroID() {
		return HeroID;
	}
	public List<ModelUnit> getUnits() {
		return units;
	}
	
	public void setUnits(List<ModelUnit> units){
		this.units = units;
	}
	
	public void addUnit(ModelUnit unit, Integer amount){
		this.units.add(unit);
		this.unitAmountList.add(amount);
	}
	
	
	public List<Integer> getUnitAmountList() {
		return unitAmountList;
	}
	public void setUnitAmountList(List<Integer> unitAmountList) {
		this.unitAmountList = unitAmountList;
	}
	public Army(Integer heroID, List<ModelUnit> units) {
		super();
		HeroID = heroID;
		this.units = units;
	}
	public Army(Integer heroID) {
		super();
		HeroID = heroID;
		this.units = new ArrayList<ModelUnit>();
		this.unitAmountList = new ArrayList<Integer>();
	}
	
	@Override
	public boolean equals(Object obj) {
		// FIXME 만약 이게 무조건 false가 리턴되는 버그가 있다면 instanceof로 몽땅 바꿀것.
		//그 경우 Integer, ModelHeroTable, ModelHeroTroop, 그리고 NULL에 대하여 처리.
		if(obj instanceof Integer){
			return this.HeroID.intValue() ==( (Integer)obj).intValue(); 
		}
		try {
			Class<?> someClass = obj.getClass();
			Field field = someClass.getField("HeroID");
			Integer targetID = (Integer) field.get(obj);
			return targetID.intValue() == this.HeroID.intValue();
		} catch (NoSuchFieldException | SecurityException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
		return false;
	}
	public boolean isDefeat() {
		for(int i=0; i<unitAmountList.size();i++){
			if(unitAmountList.get(i).intValue() > 0){
				return false;
			}
		}
		return true;
	}
}

