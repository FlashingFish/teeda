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
package org.seasar.teeda.core.config.faces.handler;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.teeda.core.config.faces.element.ConverterElement;
import org.seasar.teeda.core.config.faces.element.FacesConfig;
import org.seasar.teeda.core.config.faces.element.impl.ConverterElementImpl;
import org.xml.sax.Attributes;

/**
 * @author shot
 */
public class ConverterTagHandler extends JsfTagHandler {

    private static final long serialVersionUID = 1L;

    public ConverterTagHandler() {
    }

    public void start(TagHandlerContext context, Attributes attributes) {
        super.start(context, attributes);
        ConverterElement converter = createConverterElement();
        context.push(converter);
    }

    public void end(TagHandlerContext context, String body) {
        ConverterElement converter = (ConverterElement) context.pop();
        FacesConfig facesConfig = (FacesConfig) context.peek();
        facesConfig.addConverterElement(converter);
    }

    protected ConverterElement createConverterElement() {
        return new ConverterElementImpl();
    }
}
