package org.asmapper.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**

 * @author ggefaell
 *
 * @param <DTO>
 * @param <ENTITY>
 */
public abstract class AbstractGenericMap<DTO, ENTITY> implements
		DtoEntityMapInterface<DTO, ENTITY> {

	private Class<ENTITY> entityClass;
	private Class<DTO> dtoClass;

	public AbstractGenericMap(Class<DTO> dtoClass, Class<ENTITY> entClass) {
		this.dtoClass = dtoClass;
		this.entityClass = entClass;
		MappersStore.put(entityClass, (DtoEntityMapInterface) this);
		MappersStore.put(dtoClass, (DtoEntityMapInterface) this);
	}

	public Object mapAutomatic(Object ob) {
		if (ob.getClass().equals(entityClass)) {
			return mapEntityToDto((ENTITY) ob);
		}
		if (ob.getClass().equals(dtoClass)) {
			return mapDtoToEntity((DTO) ob);
		}
		return null;
	}

	@Override
	public ENTITY mapDtoToEntity(DTO dto) {
		ENTITY value = null;
		try {
			value = entityClass.newInstance();
			copyEntity(dto, value);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public DTO mapEntityToDto(ENTITY entity) {
		DTO value = null;
		try {
			value = dtoClass.newInstance();
			copyEntity(entity, value);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public final List<ENTITY> mapListDtoToListEntity(List<DTO> listDtos) {
		List<ENTITY> retorno = new ArrayList<ENTITY>();
		for (DTO dto : listDtos) {
			retorno.add(this.mapDtoToEntity(dto));
		}
		return retorno;
	}

	@Override
	public final List<DTO> mapListEntityToListDto(List<ENTITY> listEntities) {
		List<DTO> retorno = new ArrayList<DTO>();
		for (ENTITY dto : listEntities) {
			retorno.add(this.mapEntityToDto(dto));
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	private <DTO, ENTITY> void copyEntity(DTO sourceObj, ENTITY targetObj) {
		if (sourceObj == null || targetObj == null)
			throw new IllegalArgumentException(
					"Ambos parametros deben estar inicializados.");

		for (Field sourceField : sourceObj.getClass().getDeclaredFields()) {
			if (sourceField.getName().equals("serialVersionUID"))
				continue;
			try {
				sourceField.setAccessible(true);
				Field targetField = targetObj.getClass().getDeclaredField(
						sourceField.getName());
				targetField.setAccessible(true);
				if (sourceField.get(sourceObj) != null
						&& !Collection.class.isAssignableFrom(sourceField
								.getType())) {
					Mappeable mappeable = sourceField
							.getAnnotation(Mappeable.class);
					if (mappeable != null) {
						DtoEntityMapInterface mapper = MappersStore
								.get(targetField.getType());
						Object targetFieldObject = null;
						if (mapper != null) {
							targetFieldObject = mapper.mapAutomatic(sourceField
									.get(sourceObj));
							targetField.set(targetObj, targetFieldObject);
						}else{
							throw new RuntimeException("No mapper declared for the class ".concat(targetField.getType().getName()));
						}
					} else {
						targetField.set(targetObj, sourceField.get(sourceObj));
					}
				} else if (sourceField.get(sourceObj) != null
						&& Collection.class.isAssignableFrom(sourceField
								.getType())) {

					Mappeable mappeable = targetField
							.getAnnotation(Mappeable.class);
					if (mappeable != null) {
						targetField.set(
								targetObj,
								mapCollection((Collection<Object>) sourceField
										.get(sourceObj), mappeable.listOf()));
					}
				}
			} catch (IllegalArgumentException e) {
				System.out
						.println("No mapper declared for the class " + e.getMessage());
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates a Collection of targetClass from the sourceList
	 * @param sourceList
	 * @param targetClass
	 * @return
	 */
	private <A, B> Collection<B> mapCollection(Collection<A> sourceList,
			Class<B> targetClass) {
		if (sourceList == null)
			throw new IllegalArgumentException(
					"List must be initialized.");
		Collection<B> returnCollection = new ArrayList<B>(sourceList.size());
		for (A sourceEntity : sourceList) {
			DtoEntityMapInterface mapper = MappersStore.get(sourceEntity
					.getClass());
			Object targetObject = null;
			if (mapper != null) {
				targetObject = mapper.mapAutomatic(sourceEntity);
				returnCollection.add((B) targetObject);
			}else{
				throw new RuntimeException("No mapper declared for the class ".concat(sourceEntity.getClass().getName()));
			}
		}
		return returnCollection;
	}
}
