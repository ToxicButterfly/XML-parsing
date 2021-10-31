package model;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import model.Plant;

@XStreamAlias("root")
public class Root {
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("plant")
	private Plant plant;
	

	public String getName() {
		return name;
	}

	public Plant getPlant() {
		return plant;
	}

	
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	@Override
	public String toString() {
		return "Root [name=" + name + ", plant=" + plant + "]";
	}
	
	
}
