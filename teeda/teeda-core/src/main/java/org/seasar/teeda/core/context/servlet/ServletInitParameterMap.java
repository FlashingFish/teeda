/*
 * Copyright 2004-2005 the Seasar Foundation and the Others.
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
package org.seasar.teeda.core.context.servlet;

import java.util.Enumeration;

import javax.servlet.ServletContext;

import org.seasar.teeda.core.context.AbstractUnmodifiableExternalContextMap;

/**
 * @author Shinpei Ohtani
 */
public class ServletInitParameterMap extends
        AbstractUnmodifiableExternalContextMap {

    private final ServletContext context_;
    public ServletInitParameterMap(final ServletContext context){
        context_ = context;
    }
    
    protected Object getAttribute(String key) {
        return context_.getInitParameter(key);
    }

    protected Enumeration getAttributeNames() {
        return context_.getInitParameterNames();
    }

}
