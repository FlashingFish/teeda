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
package org.seasar.teeda.core.mock;

import java.io.IOException;
import java.util.Locale;

import javax.faces.FacesException;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * @author shot
 */
public class MockViewHandlerImpl extends MockViewHandler {

    private Locale locale_ = Locale.getDefault();

    public Locale calculateLocale(FacesContext context) {
        return locale_;
    }

    public String calculateRenderKitId(FacesContext context) {
        return null;
    }

    public UIViewRoot createView(FacesContext context, String viewId) {
        UIViewRoot root = new UIViewRoot();
        root.setViewId(viewId);
        return root;
    }

    public String getActionURL(FacesContext context, String viewId) {
        return viewId;
    }

    public String getResourceURL(FacesContext context, String path) {
        return path;
    }

    public void renderView(FacesContext context, UIViewRoot viewToRender)
            throws IOException, FacesException {
    }

    public UIViewRoot restoreView(FacesContext context, String viewId) {
        return null;
    }

    public void writeState(FacesContext context) throws IOException {
    }

    public void setLocale(Locale locale) {
        locale_ = locale;
    }

}
