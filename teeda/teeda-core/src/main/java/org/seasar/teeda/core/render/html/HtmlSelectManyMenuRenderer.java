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
import javax.faces.component.html.HtmlSelectManyMenu;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.core.util.RendererUtil;

/**
 * @author manhole
 */
public class HtmlSelectManyMenuRenderer extends HtmlSelectManyListboxRenderer {

    public static final String COMPONENT_FAMILY = "javax.faces.SelectMany";

    public static final String RENDERER_TYPE = "javax.faces.Menu";

    protected void renderSize(FacesContext context, UIComponent component,
            ResponseWriter writer) throws IOException {
        RendererUtil.renderAttribute(writer, JsfConstants.SIZE_ATTR, "1");
    }

    protected void renderStyleClass(FacesContext context,
            UIComponent component, ResponseWriter writer) throws IOException {
        HtmlSelectManyMenu htmlSelectManyMenu = (HtmlSelectManyMenu) component;
        final String styleClass = htmlSelectManyMenu.getStyleClass();
        RendererUtil.renderAttribute(writer, JsfConstants.STYLE_CLASS_ATTR,
                styleClass);
    }

}
