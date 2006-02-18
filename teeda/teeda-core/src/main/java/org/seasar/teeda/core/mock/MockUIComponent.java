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
package org.seasar.teeda.core.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;
import javax.faces.render.Renderer;

public class MockUIComponent extends UIComponent {

    public static final String COMPONENT_FAMILY = "javax.faces.mock";

    public static final String COMPONENT_TYPE = "javax.faces.mock";

    private UIComponent parent_ = null;

    private FacesEvent event_;

    private String id_;

    private String clientId_ = null;

    private Map facets_ = new HashMap();

    private String family_ = COMPONENT_FAMILY;

    private String type_ = COMPONENT_TYPE;

    private boolean rendered_ = true;

    private FacesContext context_ = null;

    private Map valueBindingMap_ = new HashMap();
    
    private List children_ = new ArrayList();
    
    public Map getAttributes() {
        return null;
    }

    public ValueBinding getValueBinding(String name) {
        return (ValueBinding) valueBindingMap_.get(name);
    }

    public void setValueBinding(String s, ValueBinding valueBinding) {
        valueBindingMap_.put(s, valueBinding);
    }

    public String getClientId(FacesContext context) {
        return clientId_;
    }

    public void setClientId(String clientId) {
        clientId_ = clientId;
    }

    public String getFamily() {
        return family_;
    }

    public void setFamily(String family) {
        family_ = family;
    }

    public String getId() {
        return id_;
    }

    public void setId(String id) {
        id_ = id;
    }

    public UIComponent getParent() {
        return parent_;
    }

    public void setParent(UIComponent parent) {
        parent_ = parent;
    }

    public boolean isRendered() {
        return rendered_;
    }

    public void setRendered(boolean flag) {
        rendered_ = flag;
    }

    public String getRendererType() {
        return type_;
    }

    public void setRendererType(String type) {
        type_ = type;
    }

    public boolean getRendersChildren() {
        return false;
    }

    public List getChildren() {
        return children_;
    }

    public int getChildCount() {
        return getChildren().size();
    }

    public UIComponent findComponent(String expr) {
        return null;
    }

    public Map getFacets() {
        return facets_;
    }

    public UIComponent getFacet(String s) {
        return (UIComponent) facets_.get(s);
    }

    public Iterator getFacetsAndChildren() {
        return null;
    }

    public void broadcast(FacesEvent event) throws AbortProcessingException {
    }

    public void decode(FacesContext context) {
    }

    public void encodeBegin(FacesContext context) throws IOException {
    }

    public void encodeChildren(FacesContext context) throws IOException {
    }

    public void encodeEnd(FacesContext context) throws IOException {
    }

    protected void addFacesListener(FacesListener listener) {
    }

    protected FacesListener[] getFacesListeners(Class clazz) {
        return null;
    }

    protected void removeFacesListener(FacesListener listener) {
    }

    public void queueEvent(FacesEvent event) {
        event_ = event;
    }

    public void processRestoreState(FacesContext context, Object state) {
    }

    public void processDecodes(FacesContext context) {
    }

    public void processValidators(FacesContext context) {
    }

    public void processUpdates(FacesContext context) {
    }

    public Object processSaveState(FacesContext context) {
        return null;
    }

    protected FacesContext getFacesContext() {
        if (context_ == null) {
            context_ = FacesContext.getCurrentInstance();
        }
        return context_;
    }

    protected Renderer getRenderer(FacesContext context) {
        return null;
    }

    public boolean isTransient() {
        return false;
    }

    public void setTransient(boolean transientValue) {
    }

    public Object saveState(FacesContext context) {
        return null;
    }

    public void restoreState(FacesContext context, Object state) {
    }

    public FacesEvent getQueueEvent() {
        return event_;
    }

    public void setFacesContext(FacesContext context) {
        context_ = context;
    }

}
