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
package org.seasar.teeda.core.util;

import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.core.unit.TeedaTestCase;

/**
 * @author shot
 */
public class ServletErrorPageManagerImplTest extends TeedaTestCase {

    public void testGetLocation_bySameException() throws Exception {
        // # Arrange #
        ServletErrorPageManagerImpl manager = new ServletErrorPageManagerImpl();
        manager.addErrorPage(FooException.class, "a.jsp");

        // # Act & Assert #
        assertEquals("a.jsp", manager.getLocation(FooException.class));
    }

    public void testGetLocation_bySuperClass() throws Exception {
        // # Arrange #
        ServletErrorPageManagerImpl manager = new ServletErrorPageManagerImpl();
        manager.addErrorPage(HogeException.class, "b.jsp");

        // # Act & Assert #
        assertEquals("b.jsp", manager.getLocation(FooException.class));
    }

    public void testGetLocation_notFound() throws Exception {
        // # Arrange #
        ServletErrorPageManagerImpl manager = new ServletErrorPageManagerImpl();

        // # Act & Assert #
        assertNull(manager.getLocation(FooException.class));
    }

    public void testHandleException_locationNull() throws Exception {
        // # Arrange #
        ServletErrorPageManagerImpl manager = new ServletErrorPageManagerImpl();

        // # Act & Assert #
        assertFalse(manager.handleException(new Exception(), getFacesContext(),
                getExternalContext()));
    }

    public void testHandleException() throws Exception {
        // # Arrange #
        ServletErrorPageManagerImpl manager = new ServletErrorPageManagerImpl();
        FooException e = new FooException();
        e.setMessage("aaa");
        manager.addErrorPage(e.getClass(), "a.jsp");

        // # Act & Assert #
        assertTrue(manager.handleException(e, getFacesContext(),
                getExternalContext()));
        assertEquals(e, getRequest().getAttribute(JsfConstants.ERROR_EXCEPTION));
        assertEquals(e.getClass(), getRequest().getAttribute(
                JsfConstants.ERROR_EXCEPTION_TYPE));
        assertEquals("aaa", getRequest().getAttribute(
                JsfConstants.ERROR_MESSAGE));
    }

    private static class HogeException extends Exception {

        private static final long serialVersionUID = 1L;
    }

    private static class FooException extends HogeException {

        private static final long serialVersionUID = 1L;

        private String message_;

        public void setMessage(String message) {
            message_ = message;
        }

        public String getMessage() {
            return message_;
        }
    }
}
