package org.asmapper.example;

import java.util.List;

import org.asmapper.core.Mappeable;

public class Car {

	private String model;
	
	@Mappeable
	private Brand brand;
	
	@Mappeable(listOf=Wheel.class)
	private List<Wheel> wheels;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<Wheel> getWheels() {
		return wheels;
	}

	public void setWheels(List<Wheel> wheels) {
		this.wheels = wheels;
	}
	
	
}
