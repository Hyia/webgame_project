package practice.webgameproject.strategy.engine.child;

import static org.mockito.Matchers.intThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import practice.webgameproject.strategy.model.ModelUnit;

public class Army{
	Integer HeroID;
	List<ModelUnit> units;
	List<Integer> unitAmountList;
	List<Integer> unitSlotIDList;
	
	public Integer getHeroID() {
		return HeroID;
	}
	public List<ModelUnit> getUnits() {
		return units;
	}
	
	public void addUnit(ModelUnit unit, Integer amount,Integer slotID){
		this.units.add(unit);
		this.unitAmountList.add(amount);
		this.unitSlotIDList.add(slotID);
	}
	
	
	public List<Integer> getUnitAmountList() {
		return unitAmountList;
	}

	public List<Integer> getUnitSlotIDList() {
		return unitSlotIDList;
	}
	
	public Army(Integer heroID) {
		super();
		HeroID = heroID;
		this.units = new ArrayList<ModelUnit>();
		this.unitAmountList = new ArrayList<Integer>();
		this.unitSlotIDList = new ArrayList<Integer>();
	}
	public Army(Army army) {
		if(army.HeroID != null){
			this.HeroID = army.HeroID.intValue();
		}else{
			this.HeroID = null;
		}
		
		this.units = new ArrayList<ModelUnit>();
		this.unitAmountList = new ArrayList<Integer>();
		this.unitSlotIDList = new ArrayList<Integer>();
		
		for(int i=0; i< army.units.size(); i++){
			ModelUnit prefUnit = army.units.get(i); 
			ModelUnit unit = new ModelUnit(prefUnit.getUnitID(), prefUnit.getName(), prefUnit.getATK(), prefUnit.getSPD(), prefUnit.getHP(), prefUnit.getEXP());
			this.addUnit(unit, army.unitAmountList.get(i).intValue(), army.getUnitSlotIDList().get(i));
		}
		
	}
	
	@Override
	public boolean equals(Object obj) {
		// FIXME 만약 이게 무조건 false가 리턴되는 버그가 있다면 instanceof로 몽땅 바꿀것. 다시 커밋
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
	
	
	@Override
	public Army clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Army arm = new Army(this.HeroID.intValue());
		for(int i=0; i<this.units.size(); i++){
			ModelUnit prefUnit = this.units.get(i); 
			ModelUnit unit = new ModelUnit(prefUnit.getUnitID(), prefUnit.getName(), prefUnit.getATK(), prefUnit.getSPD(), prefUnit.getHP(), prefUnit.getEXP());
			arm.addUnit(unit, this.unitAmountList.get(i).intValue(), this.getUnitSlotIDList().get(i));
		}
		return arm;
	}
}

