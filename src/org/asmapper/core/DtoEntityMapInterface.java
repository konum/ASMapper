package org.asmapper.core;

import java.util.List;

public interface DtoEntityMapInterface<DTO,ENTITY> {

	ENTITY mapDtoToEntity(DTO dto);
	DTO mapEntityToDto(ENTITY entity);
	List<ENTITY>  mapListDtoToListEntity(List<DTO> listDtos);
	List<DTO>  mapListEntityToListDto(List<ENTITY> listEntities);
	
	/**
	 * Automatic mapping: If source is a DTO class object it returns and ENTITY object and vicecersa.
	 * Returns null if source is of other class. 
	 * @param source
	 */
	Object mapAutomatic(Object source);
}
