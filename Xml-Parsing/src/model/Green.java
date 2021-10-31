package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("green")
public class Green {
	
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("age")
	private int age;
	@XStreamAlias("status")
	private String status;
	@XStreamAlias("height")
	private int height;
	
	public Green(String name, int age, String status, int height) {
		this.name = name;
		this.age = age;
		this.status = status;
		this.height = height;
	}
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}

	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}


	public void planting() {
		this.status = "Planted";
	}

	@Override
	public String toString() {
		return "[name=" + name + ", age=" + age + ", status=" + status + ", height=" + height +"]";
	}
	
	
}
