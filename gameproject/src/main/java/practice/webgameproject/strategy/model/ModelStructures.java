package practice.webgameproject.strategy.model;

import java.util.Date;

public class ModelStructures {

	private String Name;// 		VARCHAR(20)
	private Integer Kind;// 		INT(11) 	AUTO_INCREMENT
	private Date Time; 		//INT(11) 
	private Integer Values;// 		INT(11)
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
	public Date getTime() {
		return Time;
	}
	public void setTime(Date time) {
		Time = time;
	}
	public Integer getValues() {
		return Values;
	}
	public void setValues(Integer values) {
		Values = values;
	}
	public ModelStructures(String name, Integer kind, Date time, Integer values) {
		Name = name;
		Kind = kind;
		Time = time;
		Values = values;
	}
	
	
	public ModelStructures() {
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if( obj instanceof ModelStructures){
			return ((ModelStructures) obj).getKind().intValue() ==  this.Kind.intValue();
		}
		if(obj instanceof Integer){
			return ((Integer) obj).intValue() == this.Kind.intValue();
		}
		return false;
	}
	
	
	
	
}
