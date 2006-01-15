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
package org.seasar.teeda.core.mock;

import javax.faces.el.EvaluationException;
import javax.faces.el.PropertyNotFoundException;
import javax.faces.el.PropertyResolver;

/**
 * @author shot
 */
public class MockSingleConstructorPropertyResolver extends PropertyResolver {

    private PropertyResolver originalResolver_;
    public MockSingleConstructorPropertyResolver(PropertyResolver original){
        originalResolver_ = original;
    }
    
    public Class getType(Object base, int index) throws EvaluationException,
            PropertyNotFoundException {
        return originalResolver_.getType(base, index);
    }

    public Class getType(Object base, Object property)
            throws EvaluationException, PropertyNotFoundException {
        return originalResolver_.getType(base, property);
    }

    public Object getValue(Object base, int index) throws EvaluationException,
            PropertyNotFoundException {
        return originalResolver_.getValue(base, index);
    }

    public Object getValue(Object base, Object property)
            throws EvaluationException, PropertyNotFoundException {
        return originalResolver_.getValue(base, property);
    }

    public boolean isReadOnly(Object base, int index)
            throws EvaluationException, PropertyNotFoundException {
        return originalResolver_.isReadOnly(base, index);
    }

    public boolean isReadOnly(Object base, Object property)
            throws EvaluationException, PropertyNotFoundException {
        return originalResolver_.isReadOnly(base, property);
    }

    public void setValue(Object base, int index, Object value)
            throws EvaluationException, PropertyNotFoundException {
    }

    public void setValue(Object base, Object property, Object value)
            throws EvaluationException, PropertyNotFoundException {
    }

    public PropertyResolver getOriginal(){
        return originalResolver_;
    }
}
