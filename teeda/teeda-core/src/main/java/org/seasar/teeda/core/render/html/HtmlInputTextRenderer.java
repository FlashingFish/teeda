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
package org.seasar.teeda.core.render.html;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.internal.IgnoreAttribute;

import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.core.render.AbstractInputRenderer;
import org.seasar.teeda.core.util.HTMLEncodeUtil;
import org.seasar.teeda.core.util.RendererUtil;
import org.seasar.teeda.core.util.ValueHolderUtil;

/**
 * @author manhole
 */
public class HtmlInputTextRenderer extends AbstractInputRenderer {

    public static final String COMPONENT_FAMILY = "javax.faces.Input";

    public static final String RENDERER_TYPE = "javax.faces.Text";

    private final IgnoreAttribute ignoreComponent = new IgnoreAttribute();
    {
        ignoreComponent.addAttributeName(JsfConstants.ID_ATTR);
        ignoreComponent.addAttributeName(JsfConstants.NAME_ATTR);
        ignoreComponent.addAttributeName(JsfConstants.TYPE_ATTR);
        ignoreComponent.addAttributeName(JsfConstants.VALUE_ATTR);
        ignoreComponent.addAttributeName(JsfConstants.STYLE_CLASS_ATTR);
        ignoreComponent.addAttributeName(JsfConstants.AUTOCOMPLETE_ATTR);
    }

    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {
        assertNotNull(context, component);
        if (!component.isRendered()) {
            return;
        }
        encodeHtmlInputTextEnd(context, (HtmlInputText) component);
    }

    protected void encodeHtmlInputTextEnd(FacesContext context,
            HtmlInputText htmlInputText) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement(JsfConstants.INPUT_ELEM, htmlInputText);
        RendererUtil.renderAttribute(writer, JsfConstants.TYPE_ATTR,
                JsfConstants.TEXT_VALUE);
        RendererUtil.renderIdAttributeIfNecessary(writer, htmlInputText,
                getIdForRender(context, htmlInputText));
        RendererUtil.renderAttribute(writer, JsfConstants.NAME_ATTR,
                htmlInputText.getClientId(context));
        String value = ValueHolderUtil
                .getValueForRender(context, htmlInputText);
        RendererUtil.renderAttribute(writer, JsfConstants.VALUE_ATTR,
                HTMLEncodeUtil.encode(value, true, true));
        RendererUtil.renderAttribute(writer, JsfConstants.AUTOCOMPLETE_ATTR,
                htmlInputText.getAutocomplete());
        renderStyleClass(context, htmlInputText, writer);
        renderRemainAttributes(htmlInputText, writer, ignoreComponent);
        writer.endElement(JsfConstants.INPUT_ELEM);
    }

    protected void renderStyleClass(FacesContext context,
            HtmlInputText htmlInputText, ResponseWriter writer)
            throws IOException {
        final String styleClass = htmlInputText.getStyleClass();
        RendererUtil.renderAttribute(writer, JsfConstants.STYLE_CLASS_ATTR,
                styleClass);
    }

    public void decode(FacesContext context, UIComponent component) {
        assertNotNull(context, component);
        decodeHtmlInputText(context, (HtmlInputText) component);
    }

    protected void decodeHtmlInputText(FacesContext context,
            HtmlInputText htmlInputText) {
        getDecoder().decode(context, htmlInputText);
    }

    public void addIgnoreAttributeName(final String name) {
        ignoreComponent.addAttributeName(name);
    }

    public IgnoreAttribute getIgnoreAttributes() {
        return ignoreComponent;
    }

}
