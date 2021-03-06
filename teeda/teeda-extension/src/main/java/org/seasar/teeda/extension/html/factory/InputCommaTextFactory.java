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
public class InputCommaTextFactory extends InputTextFactory {

    private static final String TAG_NAME = "inputCommaText";

    public boolean isMatch(ElementNode elementNode, PageDesc pageDesc,
            ActionDesc actionDesc) {
        String classProperty = elementNode.getProperty(JsfConstants.CLASS_ATTR);
        boolean isInputTextMatch = super.isMatch(elementNode, pageDesc,
                actionDesc);
        if (!isInputTextMatch) {
            return false;
        }
        boolean hasTcurrency = StringUtil.startsWithIgnoreCase(classProperty,
                ExtensionConstants.TEEDA_CURRENY_STYLE_CLASS);
        return (classProperty != null) && hasTcurrency;
    }

    protected void customizeProperties(Map properties, ElementNode elementNode,
            PageDesc pageDesc, ActionDesc actionDesc) {
        super
                .customizeProperties(properties, elementNode, pageDesc,
                        actionDesc);
        if (pageDesc == null) {
            return;
        }
        if (!properties.containsKey(ExtensionConstants.FRACTION_ATTR)) {
            String fraction = elementNode
                    .getProperty(ExtensionConstants.FRACTION_ATTR);
            if (fraction == null) {
                fraction = getBindingExpression(pageDesc.getPageName(),
                        elementNode.getId()
                                + ExtensionConstants.FRACTION_SUFFIX);
            }
            properties.put(ExtensionConstants.FRACTION_ATTR, fraction);
        }
        if (!properties.containsKey(ExtensionConstants.FRACTION_SEPARATOR_ATTR)) {
            String fractionSep = elementNode
                    .getProperty(ExtensionConstants.FRACTION_SEPARATOR_ATTR);
            if (fractionSep == null) {
                fractionSep = getBindingExpression(pageDesc.getPageName(),
                        elementNode.getId()
                                + ExtensionConstants.FRACTION_SEPARATOR_SUFFIX);
            }
            properties.put(ExtensionConstants.FRACTION_SEPARATOR_ATTR,
                    fractionSep);
        }
        if (!properties.containsKey(ExtensionConstants.GROUPING_SEPARATOR_ATTR)) {
            String groupSep = elementNode
                    .getProperty(ExtensionConstants.GROUPING_SEPARATOR_ATTR);
            if (groupSep == null) {
                groupSep = getBindingExpression(pageDesc.getPageName(),
                        elementNode.getId()
                                + ExtensionConstants.GROUPING_SEPARATOR_SUFFIX);
            }
            properties
                    .put(ExtensionConstants.GROUPING_SEPARATOR_ATTR, groupSep);
        }
    }

    protected String getTagName() {
        return TAG_NAME;
    }

    protected String getUri() {
        return ExtensionConstants.TEEDA_EXTENSION_URI;
    }

}