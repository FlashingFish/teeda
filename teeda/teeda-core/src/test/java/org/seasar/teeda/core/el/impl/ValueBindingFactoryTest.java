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
package org.seasar.teeda.core.el.impl;

import javax.faces.el.ValueBinding;

import org.seasar.teeda.core.el.ELParser;
import org.seasar.teeda.core.el.ExpressionProcessor;
import org.seasar.teeda.core.el.ValueBindingFactory;
import org.seasar.teeda.core.unit.TeedaTestCase;

/**
 * @author shot
 */
public class ValueBindingFactoryTest extends TeedaTestCase {

    public void testCreateValueBinding1() {
        ValueBindingFactory context = new ValueBindingFactoryImpl();
        context.setELParser(new MockELParser());
        ValueBinding vb = context.createValueBinding(getApplication(), "hoge");
        assertNotNull(vb);
        assertTrue(vb instanceof ValueBinding);
    }

    private static class MockELParser implements ELParser {

        public Object parse(String expression) {
            return new Object();
        }

        public void setExpressionProcessor(ExpressionProcessor processor) {
        }

        public ExpressionProcessor getExpressionProcessor() {
            return null;
        }

    }
}
