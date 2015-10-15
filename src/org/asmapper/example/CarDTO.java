package org.asmapper.example;

import java.util.List;

import org.asmapper.core.Mappeable;

public class CarDTO {

	private String model;
	@Mappeable
	private BrandDTO brand;
	
	@Mappeable(listOf=WheelDTO.class)
	private List<WheelDTO> wheels;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BrandDTO getBrand() {
		return brand;
	}

	public void setBrand(BrandDTO brand) {
		this.brand = brand;
	}

	public List<WheelDTO> getWheels() {
		return wheels;
	}

	public void setWheels(List<WheelDTO> wheels) {
		this.wheels = wheels;
	}
	
}
