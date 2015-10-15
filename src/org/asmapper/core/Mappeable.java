package org.asmapper.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used for identifying nested mappeable objects or list of objects.
 * @author ggefaell
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) 
public @interface Mappeable {
	/**
	 * Only needed when the field is a Collection. 
	 * @return
	 */
	public Class listOf() default Object.class;
}
