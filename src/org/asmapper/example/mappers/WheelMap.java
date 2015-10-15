package org.asmapper.example.mappers;

import org.asmapper.core.AbstractGenericMap;
import org.asmapper.example.Wheel;
import org.asmapper.example.WheelDTO;

public class WheelMap  extends AbstractGenericMap<WheelDTO, Wheel>{

	public static final WheelMap MAP = new WheelMap();
	
	private WheelMap() {
		super(WheelDTO.class, Wheel.class);
	}

}
