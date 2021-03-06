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
package org.seasar.teeda.extension.render.html;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.internal.IgnoreAttribute;

import org.seasar.framework.util.StringUtil;
import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.core.render.AbstractInputRenderer;
import org.seasar.teeda.core.util.RendererUtil;
import org.seasar.teeda.core.util.ValueHolderUtil;

/**
 * @author manhole
 */
public class THtmlGridInputTextRenderer extends AbstractInputRenderer {

    public static final String COMPONENT_FAMILY = HtmlInputText.COMPONENT_FAMILY;

    public static final String RENDERER_TYPE = "org.seasar.teeda.extension.GridInputText";

    static final String DISPLAY_NONE = "display:none;";

    static final String EDIT_ON = "Teeda.THtmlGrid.editOn(this);";

    static final String EDIT_OFF = "Teeda.THtmlGrid.editOff(this);";

    static final String GRID_CELL_EDIT_CLASS_NAME = "gridCellEdit";

    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException {
        assertNotNull(context, component);
        if (!component.isRendered()) {
            return;
        }
        if (!(component instanceof HtmlInputText)) {
            throw new ClassCastException(component.getClass().getName());
        }
        encodeHtmlGridInputTextEnd(context, component);
    }

    protected void encodeHtmlGridInputTextEnd(final FacesContext context,
            final UIComponent gridInputText) throws IOException {
        renderStartDiv(context, gridInputText);
        final String value = getValue(context, gridInputText);
        renderSpan(context, gridInputText, value);
        renderInput(context, gridInputText);
        renderEndDiv(context, gridInputText);
    }

    protected void renderStartDiv(final FacesContext context,
            final UIComponent gridInputText) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        writer.startElement(JsfConstants.DIV_ELEM, gridInputText);
        RendererUtil
                .renderAttribute(writer, JsfConstants.ONCLICK_ATTR, EDIT_ON);
    }

    protected void renderEndDiv(final FacesContext context,
            final UIComponent gridInputText) throws IOException {
        final ResponseWriter writer = context.getResponseWriter();
        writer.write("&nbsp;");
        writer.endElement(JsfConstants.DIV_ELEM);
    }

    protected void renderSpan(final FacesContext context,
            final UIComponent gridInputText, final String value)
            throws IOException {

        final ResponseWriter writer = context.getResponseWriter();
        writer.startElement(JsfConstants.SPAN_ELEM, gridInputText);
        if (value != null) {
            writer.writeText(value, null);
        }
        // TODO escape
        //            if (gridInputText.isEscape()) {
        //                writer.writeText(value, null);
        //            } else {
        //                writer.write(value);
        //            }
        writer.endElement(JsfConstants.SPAN_ELEM);
    }

    protected String getValue(final FacesContext context,
            final UIComponent gridInputText) {
        return ValueHolderUtil.getValueForRender(context, gridInputText);
    }

    protected void renderInput(final FacesContext context,
            final UIComponent gridInputText) throws IOException {

        final ResponseWriter writer = context.getResponseWriter();
        final String value = getValue(context, gridInputText);
        writer.startElement(JsfConstants.INPUT_ELEM, gridInputText);
        RendererUtil.renderAttribute(writer, JsfConstants.TYPE_ATTR,
                JsfConstants.TEXT_VALUE);
        RendererUtil.renderIdAttributeIfNecessary(writer, gridInputText,
                getIdForRender(context, gridInputText));
        RendererUtil.renderAttribute(writer, JsfConstants.NAME_ATTR,
                gridInputText.getClientId(context));
        RendererUtil.renderAttribute(writer, JsfConstants.ONBLUR_ATTR,
                createOnblurAttribute(gridInputText));
        RendererUtil.renderAttribute(writer, JsfConstants.CLASS_ATTR,
                GRID_CELL_EDIT_CLASS_NAME);
        RendererUtil.renderAttribute(writer, JsfConstants.STYLE_ATTR,
                DISPLAY_NONE);
        RendererUtil.renderAttribute(writer, JsfConstants.VALUE_ATTR, value);
        renderRemain(gridInputText, writer);
        writer.endElement(JsfConstants.INPUT_ELEM);
    }

    private String createOnblurAttribute(final UIComponent gridInputText) {
        final String blur = (String) gridInputText.getAttributes().get(
                JsfConstants.ONBLUR_ATTR);
        if (StringUtil.isBlank(blur)) {
            return EDIT_OFF;
        }
        return EDIT_OFF + " " + blur;
    }

    public void decode(FacesContext context, UIComponent component) {
        assertNotNull(context, component);
        decodeHtmlGridInputText(context, (HtmlInputText) component);
    }

    protected void decodeHtmlGridInputText(FacesContext context,
            HtmlInputText gridInputText) {
        getDecoder().decode(context, gridInputText);
    }

    protected void renderRemain(final UIComponent component,
            final ResponseWriter writer) throws IOException {
        final IgnoreAttribute ignore = buildIgnoreComponent();
        renderRemainAttributes(component, writer, ignore);
    }

    protected IgnoreAttribute buildIgnoreComponent() {
        final IgnoreAttribute ignore = new IgnoreAttribute();
        ignore.addAttributeName(JsfConstants.ID_ATTR);
        ignore.addAttributeName(JsfConstants.TYPE_ATTR);
        ignore.addAttributeName(JsfConstants.NAME_ATTR);
        ignore.addAttributeName(JsfConstants.VALUE_ATTR);
        ignore.addAttributeName(JsfConstants.CLASS_ATTR);
        ignore.addAttributeName(JsfConstants.STYLE_ATTR);
        ignore.addAttributeName(JsfConstants.ONBLUR_ATTR);
        return ignore;
    };

}
