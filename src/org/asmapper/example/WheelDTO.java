package org.asmapper.example;

public class WheelDTO {

	private Integer radius;
	private Float presure;
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
	public WheelDTO(){}
	public WheelDTO(Integer radius, Float presure) {
		super();
		this.radius = radius;
		this.presure = presure;
	}
	
	
}
