package org.asmapper.example;

public class Wheel {

	private Integer radius;
	private Float presure;
	public Wheel(){}
	
	public Integer getRadius() {
		return radius;
	}
	public void setRadius(Integer radius) {
		this.radius = radius;
	}
	public Float getPresure() {
		return presure;
	}
	public void setPresure(Float presure) {
		this.presure = presure;
	}
	public Wheel(Integer radius, Float presure) {
		super();
		this.radius = radius;
		this.presure = presure;
	}

}
