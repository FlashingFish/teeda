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
package org.seasar.teeda.extension.html.factory;

import java.util.Map;

import org.seasar.framework.util.StringUtil;
import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.extension.ExtensionConstants;
import org.seasar.teeda.extension.html.ActionDesc;
import org.seasar.teeda.extension.html.ElementNode;
import org.seasar.teeda.extension.html.PageDesc;

/**
 * @author shot
 */
public class InputDateTextFactory extends InputTextFactory {

    private static final String TAG_NAME = "inputDateText";

    public boolean isMatch(ElementNode elementNode, PageDesc pageDesc,
            ActionDesc actionDesc) {
        String classProperty = elementNode.getProperty(JsfConstants.CLASS_ATTR);
        boolean isInputTextMatch = super.isMatch(elementNode, pageDesc,
                actionDesc);
        if (!isInputTextMatch) {
            return false;
        }
        boolean hasTdate = StringUtil.startsWithIgnoreCase(classProperty,
                ExtensionConstants.TEEDA_DATE_STYLE_CLASS);
        return (classProperty != null) && hasTdate;
    }

    protected void customizeProperties(Map properties, ElementNode elementNode,
            PageDesc pageDesc, ActionDesc actionDesc) {
        super
                .customizeProperties(properties, elementNode, pageDesc,
                        actionDesc);
        if (pageDesc == null) {
            return;
        }
        String pattern = elementNode
                .getProperty(ExtensionConstants.PATTERN_ATTR);
        if (pattern == null) {
            pattern = getBindingExpression(pageDesc.getPageName(), elementNode
                    .getId()
                    + ExtensionConstants.PATTERN_SUFFIX);
        }
        properties.put(ExtensionConstants.PATTERN_ATTR, pattern);

        String length = elementNode.getProperty(ExtensionConstants.LENGTH_ATTR);
        if (length == null) {
            length = getBindingExpression(pageDesc.getPageName(), elementNode
                    .getId()
                    + ExtensionConstants.LENGTH_SUFFIX);
        }
        properties.put(ExtensionConstants.LENGTH_ATTR, length);

        String threshold = elementNode
                .getProperty(ExtensionConstants.THRESHOD_ATTR);
        if (threshold == null) {
            threshold = getBindingExpression(pageDesc.getPageName(),
                    elementNode.getId() + ExtensionConstants.THRESHOLD_SUFFIX);
        }
        properties.put(ExtensionConstants.THRESHOD_ATTR, threshold);
    }

    protected String getTagName() {
        return TAG_NAME;
    }

    protected String getUri() {
        return ExtensionConstants.TEEDA_EXTENSION_URI;
    }

}