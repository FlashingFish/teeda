/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.teeda.extension.annotation.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.convention.NamingConvention;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.MethodUtil;
import org.seasar.framework.util.tiger.AnnotationUtil;

/**
 * @author higa
 * @author shot
 */
public class TigerConverterAnnotationHandler extends
		ConstantConverterAnnotationHandler {

	protected Class<? extends Annotation> metaAnnotationType = org.seasar.teeda.extension.annotation.convert.Converter.class;

	@Override
	protected void processProperty(S2Container container, Class componentClass,
			String componentName, NamingConvention namingConvention,
			PropertyDesc propertyDesc, Field[] fields) {
		Field field = propertyDesc.getField();
		if (field != null) {
			processField(container, componentClass, componentName,
					namingConvention, field);
		}
		if (propertyDesc.hasReadMethod()) {
			processGetterMethod(container, componentName, propertyDesc);
		}
		if (propertyDesc.hasWriteMethod()) {
			processSetterMethod(container, componentName, propertyDesc);
		}
		super.processProperty(container, componentClass, componentName,
				namingConvention, propertyDesc, fields);
	}

	protected void processField(S2Container container, Class componentClass,
			String componentName, NamingConvention namingConvention, Field field) {
		for (Annotation annotation : field.getDeclaredAnnotations()) {
			processAnnotation(container, componentName, field.getName(),
					annotation);
		}
	}

	protected void processAnnotation(S2Container container,
			String componentName, String propertyName, Annotation annotation) {
		Class<? extends Annotation> annotationType = annotation
				.annotationType();
		Annotation metaAnnotation = annotationType
				.getAnnotation(metaAnnotationType);
		if (metaAnnotation == null) {
			return;
		}
		final String converterName = getConverterName(metaAnnotation);
		final Map props = AnnotationUtil.getProperties(annotation);
		registerConverter(componentName, propertyName, converterName, props);
	}

	protected void processAnnotaions(final S2Container container,
			final String componentName, final PropertyDesc propertyDesc,
			final Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			processAnnotation(container, componentName, propertyDesc
					.getPropertyName(), annotation);
		}
	}

	protected void processGetterMethod(S2Container container,
			String componentName, PropertyDesc propertyDesc) {
		Annotation[] annotations = propertyDesc.getReadMethod()
				.getDeclaredAnnotations();
		processAnnotaions(container, componentName, propertyDesc, annotations);
	}

	protected void processSetterMethod(S2Container container,
			String componentName, PropertyDesc propertyDesc) {
		Annotation[] annotations = propertyDesc.getWriteMethod()
				.getDeclaredAnnotations();
		processAnnotaions(container, componentName, propertyDesc, annotations);
	}

	protected String getConverterName(Annotation annotation) {
		Class<? extends Annotation> annoType = annotation.annotationType();
		Method m = ClassUtil.getMethod(annoType, "value", null);
		return (String) MethodUtil.invoke(m, annotation, null);
	}

}
