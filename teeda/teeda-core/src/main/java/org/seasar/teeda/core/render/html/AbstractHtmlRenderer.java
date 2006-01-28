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
package org.seasar.teeda.core.render.html;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;

import org.seasar.framework.util.StringUtil;
import org.seasar.teeda.core.util.LoopIterator;

/**
 * @author manhole
 */
public class AbstractHtmlRenderer extends Renderer {

    // TODO to change pluggable
    protected String getIdForRender(FacesContext context, UIComponent component) {
        String id = component.getId();
        if (id != null) {
            return id;
        }
        return component.getClientId(context);
    }

    protected UIComponent toNullIfNotRendered(UIComponent component) {
        if (component != null && !component.isRendered()) {
            component = null;
        }
        return component;
    }

    protected void encodeComponent(FacesContext context, UIComponent component)
            throws IOException {
        component.encodeBegin(context);
        if (component.getRendersChildren()) {
            component.encodeChildren(context);
        }
        component.encodeEnd(context);
    }

    protected void encodeComponentAndDescendant(FacesContext context,
            UIComponent component) throws IOException {
        encodeComponent(context, component);
        for (Iterator it = getRenderedChildrenIterator(component); it.hasNext();) {
            UIComponent child = (UIComponent) it.next();
            encodeComponentAndDescendant(context, child);
        }
    }

    protected void encodeDescendantComponent(FacesContext context,
            UIComponent component) throws IOException {
        for (Iterator it = getRenderedChildrenIterator(component); it.hasNext();) {
            UIComponent child = (UIComponent) it.next();
            encodeComponentAndDescendant(context, child);
        }
    }

    protected void encodeChildrenComponent(FacesContext context,
            UIComponent component) throws IOException {
        for (Iterator it = getRenderedChildrenIterator(component); it.hasNext();) {
            UIComponent child = (UIComponent) it.next();
            encodeComponent(context, child);
        }
    }

    // for parameter check.
    protected void assertNotNull(FacesContext context, UIComponent component) {
        if (context == null) {
            throw new NullPointerException("context");
        }
        if (component == null) {
            throw new NullPointerException("component");
        }
    }

    protected String[] splitByComma(String s) {
        String[] split = StringUtil.split(s, ",");
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split;
    }

    protected LoopIterator toStyleLoopIterator(String s) {
        return new LoopIterator(splitByComma(s));
    }

    protected Iterator getRenderedChildrenIterator(UIComponent component) {
        return new RenderedComponentIterator(component.getChildren());
    }

    protected static class RenderedComponentIterator implements Iterator {

        private Iterator iterator_;

        public RenderedComponentIterator(Collection c) {
            iterator_ = c.iterator();
        }

        private UIComponent component_;

        public boolean hasNext() {
            if (component_ != null) {
                return true;
            }
            while (iterator_.hasNext()) {
                UIComponent component = (UIComponent) iterator_.next();
                if (component.isRendered()) {
                    component_ = component;
                    return true;
                }
            }
            return false;
        }

        public Object next() {
            if (component_ != null) {
                UIComponent component = component_;
                component_ = null;
                return component;
            }
            while (true) {
                UIComponent component = (UIComponent) iterator_.next();
                if (component.isRendered()) {
                    return component;
                }
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

    }

}
