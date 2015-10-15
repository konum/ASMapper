package org.asmapper.example.mappers;

import org.asmapper.core.AbstractGenericMap;
import org.asmapper.example.Car;
import org.asmapper.example.CarDTO;

public class CarMap extends AbstractGenericMap<CarDTO, Car>{

	public static final CarMap MAP = new CarMap();
	
	private CarMap() {
		super(CarDTO.class, Car.class);
	}

}
