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

import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.extension.ExtensionConstants;
import org.seasar.teeda.extension.html.ActionDesc;
import org.seasar.teeda.extension.html.ElementNode;
import org.seasar.teeda.extension.html.PageDesc;

/**
 * @author manhole
 */
public class SelectManyListboxFactory extends AbstractElementProcessorFactory {

    private static final String TAG_NAME = "selectManyListbox";

    public SelectManyListboxFactory() {
    }

    /*
     * selectManyMenuとの違いを見るために、size != 1かどうかも判定する...?
     */
    public boolean isMatch(final ElementNode elementNode,
            final PageDesc pageDesc, final ActionDesc actionDesc) {
        if (!JsfConstants.SELECT_ELEM
                .equalsIgnoreCase(elementNode.getTagName())) {
            return false;
        }
        if (pageDesc == null) {
            return false;
        }
        String id = elementNode.getId();
        if (id.lastIndexOf(ExtensionConstants.ITEMS_SUFFIX) < 0) {
            id = id + ExtensionConstants.ITEMS_SUFFIX;
        }
        if (pageDesc.hasItemsProperty(id) || pageDesc.hasMapItemsProperty(id)) {
            return (elementNode.getProperty(JsfConstants.MULTIPLE_ATTR) != null);
        }
        return false;
    }

    protected void customizeProperties(final Map properties,
            final ElementNode elementNode, final PageDesc pageDesc,
            final ActionDesc actionDesc) {
        super
                .customizeProperties(properties, elementNode, pageDesc,
                        actionDesc);
        if (pageDesc == null) {
            return;
        }
        final String pageName = pageDesc.getPageName();
        final String id = elementNode.getId();
        String items = null;
        if (id.lastIndexOf(ExtensionConstants.ITEMS_SUFFIX) >= 0) {
            items = id;
        } else {
            items = id + ExtensionConstants.ITEMS_SUFFIX;
        }
        final String target = items.substring(0, items.length() -
                ExtensionConstants.ITEMS_SUFFIX.length());
        properties.put(JsfConstants.VALUE_ATTR, getBindingExpression(pageName,
                target));
        properties.put(ExtensionConstants.ITEMS_ATTR, getBindingExpression(
                pageName, items));
    }

    protected String getTagName() {
        return TAG_NAME;
    }

    protected String getUri() {
        return ExtensionConstants.TEEDA_EXTENSION_URI;
    }

    public boolean isLeaf() {
        return true;
    }

}
