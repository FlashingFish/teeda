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
package org.seasar.teeda.extension.taglib;

import java.util.Locale;
import java.util.Map;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.seasar.framework.util.ResourceBundleUtil;
import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.core.taglib.html.OutputLabelTag;
import org.seasar.teeda.core.util.FacesContextUtil;

/**
 * @author shot
 */
public class TOutputLabelTag extends OutputLabelTag {

    private String key;

    private String defaultKey;

    private String propertiesName;

    private String defaultPropertiesName;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public String getDefaultPropertiesName() {
        return defaultPropertiesName;
    }

    public void setDefaultPropertiesName(String defaultPropertiesName) {
        this.defaultPropertiesName = defaultPropertiesName;
    }

    public void release() {
        super.release();
        key = null;
        propertiesName = null;
        defaultPropertiesName = null;
        defaultKey = null;
    }

    public String getDefaultKey() {
        return defaultKey;
    }

    public void setDefaultKey(String defaultKey) {
        this.defaultKey = defaultKey;
    }

    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        //TODO cache for locale is needed.
        //TODO change ResourceBundle to HotdeployResourceBundle
        FacesContext context = getFacesContext();
        ViewHandler viewHandler = FacesContextUtil.getViewHandler(context);
        Locale locale = viewHandler.calculateLocale(context);
        String value = null;
        if (key != null && propertiesName != null) {
            Map map = ResourceBundleUtil.convertMap(propertiesName, locale);
            value = (String) map.get(key);
            if (value == null) {
                value = (String) map.get(defaultKey);
            }
        }
        if (value == null) {
            Map map = ResourceBundleUtil.convertMap(defaultPropertiesName,
                    locale);
            value = (String) map.get(defaultKey);
        }
        if (value != null) {
            setComponentProperty(component, JsfConstants.VALUE_ATTR, value);
        }
    }

}