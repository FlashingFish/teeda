/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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

import javax.faces.convert.Converter;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.util.BeanUtil;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.convention.NamingConvention;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.MethodUtil;
import org.seasar.framework.util.tiger.AnnotationUtil;

/**
 * @author higa
 */
public class TigerConverterAnnotationHandler extends
		ConstantConverterAnnotationHandler {

	protected Class<? extends Annotation> metaAnnotationType = org.seasar.teeda.extension.annotation.convert.Converter.class;

	protected void processField(S2Container container, Class componentClass,
			String componentName, NamingConvention namingConvention,
			BeanDesc beanDesc, Field field) {
		for (Annotation annotation : field.getDeclaredAnnotations()) {
			processAnnotation(container, componentName, field.getName(),
					annotation);
		}
		super.processField(container, componentClass, componentName,
				namingConvention, beanDesc, field);
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
		String converterName = getConverterName(metaAnnotation);
		Converter converter = getConverter(container, converterName);
		if (converter == null) {
			return;
		}
		Map props = AnnotationUtil.getProperties(annotation);
		BeanUtil.copyProperties(props, converter);
		registerConverter(componentName, propertyName, converter);
	}

	protected void processGetterMethod(S2Container container,
			Class componentClass, String componentName,
			NamingConvention namingConvention, BeanDesc beanDesc,
			PropertyDesc propertyDesc) {
		Annotation[] annotations = propertyDesc.getReadMethod()
				.getDeclaredAnnotations();
		for (Annotation annotation : annotations) {
			processAnnotation(container, componentName, propertyDesc
					.getPropertyName(), annotation);
		}
	}

	protected String getConverterName(Annotation annotation) {
		Class<? extends Annotation> annoType = annotation.annotationType();
		Method m = ClassUtil.getMethod(annoType, "value", null);
		return (String) MethodUtil.invoke(m, annotation, null);
	}
}