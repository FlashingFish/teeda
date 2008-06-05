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
package org.seasar.teeda.extension.taglib;

import javax.faces.component.UIComponent;

import org.seasar.teeda.core.taglib.html.InputTextTag;
import org.seasar.teeda.extension.component.html.THtmlPopupCalendar;

/**
 * @author higa
 */
public class TPopupCalendarTag extends InputTextTag {

    private String datePattern;

    public String getComponentType() {
        return THtmlPopupCalendar.COMPONENT_TYPE;
    }

    public String getRendererType() {
        return THtmlPopupCalendar.DEFAULT_RENDERER_TYPE;
    }

    public void release() {
        super.release();
        datePattern = null;
    }

    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        setComponentProperty(component, "datePattern", datePattern);
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
}
