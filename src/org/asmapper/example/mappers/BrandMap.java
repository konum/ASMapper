package org.asmapper.example.mappers;

import java.sql.Timestamp;
import java.util.Date;

import org.asmapper.core.AbstractGenericMap;
import org.asmapper.example.Brand;
import org.asmapper.example.BrandDTO;

public class BrandMap extends AbstractGenericMap<BrandDTO, Brand>{

	public static final BrandMap MAP = new BrandMap();
	
	private BrandMap() {
		super(BrandDTO.class, Brand.class);
	}
	
	@Override
	//Brand have some incompatible field, so we override the default function to map this field
	public Brand mapDtoToEntity(BrandDTO dto){
		Brand brand = super.mapDtoToEntity(dto);
		brand.setCreationDate(new Timestamp(dto.getCreationDate().getTime()));
		return brand;
	}

	@Override
	public BrandDTO mapEntityToDto(Brand brand){
		BrandDTO dto = super.mapEntityToDto(brand);
		dto.setCreationDate(new Date(dto.getCreationDate().getTime()));
		return dto;
	}
}
