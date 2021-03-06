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

import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.seasar.teeda.core.render.html.HtmlOutputTextRenderer;
import org.seasar.teeda.core.util.RendererUtil;
import org.seasar.teeda.core.util.ValueHolderUtil;
import org.seasar.teeda.extension.ExtensionConstants;
import org.seasar.teeda.extension.component.html.THtmlOutputText;

/**
 * @author shot
 * @author yone
 */
public class THtmlOutputTextRenderer extends HtmlOutputTextRenderer {

    public static final String COMPONENT_FAMILY = THtmlOutputText.COMPONENT_FAMILY;

    public static final String RENDERER_TYPE = THtmlOutputText.DEFAULT_RENDERER_TYPE;

    public THtmlOutputTextRenderer() {
        addIgnoreAttributeName("tagName");
        addIgnoreAttributeName(ExtensionConstants.INVISIBLE_ATTR);
        addIgnoreAttributeName(ExtensionConstants.OMITTAG_ATTR);
    }

    protected void encodeHtmlOutputTextEnd(FacesContext context,
            HtmlOutputText htmlOutputText) throws IOException {
        final THtmlOutputText text = (THtmlOutputText) htmlOutputText;
        final ResponseWriter writer = context.getResponseWriter();
        final String id = getIdForRender(context, htmlOutputText);
        boolean renderTag = false;
        final boolean invisible = text.isInvisible();
        final boolean omittag = text.isOmittag();
        if (containsAttributeForRender(htmlOutputText, getIgnoreAttribute())) {
            if (!(invisible || omittag)) {
                writer.startElement(text.getTagName(), htmlOutputText);
                renderTag = true;
                RendererUtil.renderIdAttributeIfNecessary(writer,
                        htmlOutputText, id);
                renderRemainAttributes(htmlOutputText, writer,
                        getIgnoreAttribute());
            }
        }
        final String value = ValueHolderUtil.getValueForRender(context,
                htmlOutputText);
        if (htmlOutputText.isEscape()) {
            writer.writeText(value, null);
        } else {
            writer.write(value);
        }
        if (renderTag) {
            writer.endElement(text.getTagName());
        }
    }

}
