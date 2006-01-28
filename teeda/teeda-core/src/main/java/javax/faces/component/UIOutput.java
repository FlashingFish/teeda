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
package javax.faces.component;

import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @author shot
 */
public class UIOutput extends UIComponentBase implements ValueHolder {

    public static final String COMPONENT_TYPE = "javax.faces.Output";

    public static final String COMPONENT_FAMILY = "javax.faces.Output";

    private Converter converter_ = null;

    private Object value_ = null;

    private static final String DEFAULT_RENDER_TYPE = "javax.faces.Text";

    public UIOutput() {
        super();
        setRendererType(DEFAULT_RENDER_TYPE);
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public Converter getConverter() {
        if (converter_ != null) {
            return converter_;
        }
        return (Converter) ComponentUtils_.getValueBindingValue(this,
                "converter");
    }

    public void setConverter(Converter converter) {
        converter_ = converter;
    }

    public Object getLocalValue() {
        return value_;
    }

    public Object getValue() {
        if (value_ != null) {
            return value_;
        }
        return ComponentUtils_.getValueBindingValue(this, "value");
    }

    public void setValue(Object value) {
        value_ = value;
    }

    public void restoreState(FacesContext context, Object state) {
        Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        converter_ = (Converter) values[1];
        value_ = values[2];
    }

    public Object saveState(FacesContext context) {
        Object[] values = new Object[4];
        values[0] = super.saveState(context);
        values[1] = converter_;
        values[2] = value_;
        return values;
    }
}
