/*
 * Copyright 2004-2011 the Seasar Foundation and the Others.
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
package org.seasar.teeda.core.mock;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class MockConverter implements Converter {

    private String name_;

    public Object getAsObject(FacesContext context, UIComponent component,
            String value) throws ConverterException {
        return value;
    }

    public String getAsString(FacesContext context, UIComponent component,
            Object value) throws ConverterException {
        return (String) value;
    }

    public void setName(String name) {
        name_ = name;
    }

    public String getName() {
        return name_;
    }
}
