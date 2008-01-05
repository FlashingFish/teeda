/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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

import javax.faces.context.FacesContext;
import javax.faces.el.EvaluationException;
import javax.faces.el.MethodBinding;
import javax.faces.el.MethodNotFoundException;

public class MockMethodBinding extends MethodBinding {

    private String expression_;

    private boolean invokeCalled_;

    private Object[] invokeParams_;

    public MockMethodBinding() {
    }

    public MockMethodBinding(String expression) {
        expression_ = expression;
    }

    public Object invoke(FacesContext context, Object[] params)
            throws EvaluationException, MethodNotFoundException {
        invokeCalled_ = true;
        invokeParams_ = params;
        return null;
    }

    public Class getType(FacesContext context) throws MethodNotFoundException {
        return null;
    }

    public void setExpressionString(String expression) {
        expression_ = expression;
    }

    public String getExpressionString() {
        return expression_;
    }

    public boolean isInvokeCalled() {
        return invokeCalled_;
    }

    public Object[] getInvokeParams() {
        return invokeParams_;
    }

}
