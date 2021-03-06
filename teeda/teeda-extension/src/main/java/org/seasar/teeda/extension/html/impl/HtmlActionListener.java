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
package org.seasar.teeda.extension.html.impl;

import java.util.Iterator;
import java.util.Map.Entry;

import javax.faces.component.ActionSource;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import org.seasar.teeda.core.application.ActionListenerImpl;
import org.seasar.teeda.core.util.MethodBindingUtil;
import org.seasar.teeda.core.util.NavigationHandlerUtil;
import org.seasar.teeda.extension.component.TForEach;
import org.seasar.teeda.extension.event.TActionEvent;
import org.seasar.teeda.extension.html.HtmlComponentInvoker;

/**
 * @author higa
 * @author shot
 */
public class HtmlActionListener extends ActionListenerImpl {

    private HtmlComponentInvoker htmlComponentInvoker;

    /**
     * @param htmlComponentInvoker The htmlComponentInvoker to set.
     */
    public void setHtmlComponentInvoker(
            HtmlComponentInvoker htmlComponentInvoker) {
        this.htmlComponentInvoker = htmlComponentInvoker;
    }

    public void processAction(ActionEvent actionEvent)
            throws AbortProcessingException {
        final FacesContext context = FacesContext.getCurrentInstance();
        if (actionEvent instanceof TActionEvent) {
            bindRowIndices(context, (TActionEvent) actionEvent);
        }

        final ActionSource actionSource = (ActionSource) actionEvent
                .getComponent();
        final MethodBinding mb = actionSource.getAction();
        if (mb == null) {
            NavigationHandlerUtil.handleNoNavigation(context);
            context.renderResponse();
            return;
        }
        final String fromAction = mb.getExpressionString();
        if (fromAction != null) {
            final String componentName = MethodBindingUtil
                    .getComponentName(fromAction);
            final String methodName = MethodBindingUtil
                    .getMethodName(fromAction);
            htmlComponentInvoker.invoke(context, componentName, methodName);
        } else {
            super.processAction(actionEvent);
        }
    }

    protected void bindRowIndices(final FacesContext context,
            final TActionEvent event) {
        for (final Iterator it = event.getIndices(); it.hasNext();) {
            final Entry entry = (Entry) it.next();
            final TForEach forEach = (TForEach) entry.getKey();
            final Integer rowIndex = (Integer) entry.getValue();
            forEach.bindRowIndex(context, rowIndex);
        }
    }

}
