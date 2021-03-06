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
package org.seasar.teeda.core.config.faces.assembler.impl;

import javax.faces.application.ViewHandler;

import org.seasar.teeda.core.mock.MockSingleConstructorViewHandler;
import org.seasar.teeda.core.mock.MockViewHandlerImpl;
import org.seasar.teeda.core.unit.TeedaTestCase;

/**
 * @author shot
 */
public class ViewHandlerAssemblerTest extends TeedaTestCase {

    /**
     * Constructor for ViewHandlerAssemblerTest.
     * 
     * @param name
     */
    public ViewHandlerAssemblerTest(String name) {
        super(name);
    }

    public void testSimpleAssembleVariableResolver() throws Exception {
        // ## Arrange ##
        String resolverName = "org.seasar.teeda.core.mock.MockViewHandlerImpl";
        ViewHandlerAssembler assembler = new ViewHandlerAssembler(resolverName,
                getApplication());

        // ## Act ##
        assembler.assemble();

        // ## Assert ##
        ViewHandler handler = getApplication().getViewHandler();
        assertNotNull(handler);
        assertTrue(handler instanceof MockViewHandlerImpl);
    }

    public void testMarshalAssembleVariableResolver() throws Exception {
        // ## Arrange ##
        getApplication().setViewHandler(new MockViewHandlerImpl());
        String handlerName = "org.seasar.teeda.core.mock.MockSingleConstructorViewHandler";
        ViewHandlerAssembler assembler = new ViewHandlerAssembler(handlerName,
                getApplication());

        // ## Act ##
        assembler.assemble();

        // ## Assert ##
        ViewHandler handler = getApplication().getViewHandler();
        assertNotNull(handler);
        assertTrue(handler instanceof MockSingleConstructorViewHandler);
        MockSingleConstructorViewHandler h = (MockSingleConstructorViewHandler) handler;
        assertTrue(h.getOriginal() instanceof MockViewHandlerImpl);
    }

}
