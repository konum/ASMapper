package org.asmapper.example.test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.asmapper.example.BrandDTO;
import org.asmapper.example.Car;
import org.asmapper.example.CarDTO;
import org.asmapper.example.WheelDTO;
import org.asmapper.example.mappers.CarMap;
import org.junit.Assert;
import org.junit.Test;

public class CarMapTest {

	private CarMap map = CarMap.MAP;
	
	private CarDTO createCar(){
		CarDTO dto = new CarDTO();
		dto.setModel("CarModel");
		return dto;
	}
	
	
	private void assertCar(Car car){
		Assert.assertEquals("CarModel", car.getModel());
	}
	@Test
	public void dtoToEntityTest(){
		CarDTO dto = createCar();
		Car ent = map.mapDtoToEntity(dto);
		assertCar(ent);
	}
	
	@Test
	public void dtoToEntityWithWheelTest(){
		CarDTO dto = createCar();
		List<WheelDTO> wheels = new ArrayList<WheelDTO>(2);
		wheels.add(new WheelDTO(10, 4.2F));
		wheels.add(new WheelDTO(11, 3.4F));
		dto.setWheels(wheels);
		Car ent = map.mapDtoToEntity(dto);
		assertCar(ent);
		Assert.assertEquals(10, ent.getWheels().get(0).getRadius().intValue());
		Assert.assertEquals(4.2F, ent.getWheels().get(0).getPresure().floatValue(),0.0F);
		Assert.assertEquals(11, ent.getWheels().get(1).getRadius().intValue());
		Assert.assertEquals(3.4F, ent.getWheels().get(1).getPresure().floatValue(),0.0F);
	}
	
	@Test
	public void dtoToEntityWithBrandTest(){
		CarDTO dto = createCar();
		BrandDTO brand = new BrandDTO();
		Date testDate = new Date();
		brand.setCreationDate(testDate);
		brand.setName("Suzuki");
		dto.setBrand(brand);
		Car ent = map.mapDtoToEntity(dto);
		assertCar(ent);
		Assert.assertEquals("Suzuki", ent.getBrand().getName());
		Assert.assertEquals(testDate.getTime(), ent.getBrand().getCreationDate().getTime());
	}
}
