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
package org.seasar.teeda.extension.annotation.handler;

import java.util.Map;

import org.seasar.teeda.core.unit.TeedaTestCase;
import org.seasar.teeda.extension.ExtensionConstants;

public class ConstantScopeAnnotationHandlerTest extends TeedaTestCase {

    public void testGetPropertyScopes() throws Exception {
        ConstantScopeAnnotationHandler handler = new ConstantScopeAnnotationHandler();
        getContainer().register(HogeBean.class, "hogeBean");
        Map map = handler.getPropertyScopes("hogeBean");
        assertEquals(4, map.size());
        assertEquals(ExtensionConstants.REDIRECT_SCOPE, map.get("aaa"));
        assertEquals(ExtensionConstants.REDIRECT_SCOPE, map.get("ccc"));
        assertEquals(ExtensionConstants.SUBAPP_SCOPE, map.get("bbb"));
        assertEquals(ExtensionConstants.PAGE_SCOPE, map.get("ddd"));
    }

    public void testGetPropertyScopes2() throws Exception {
        ConstantScopeAnnotationHandler handler = new ConstantScopeAnnotationHandler();
        getContainer().register(FooBean.class, "fooBean");
        Map map = handler.getPropertyScopes("fooBean");
        assertEquals(2, map.size());
        assertEquals(ExtensionConstants.REDIRECT_SCOPE, map.get("aaa"));
        assertEquals(ExtensionConstants.REDIRECT_SCOPE, map.get("ccc"));
    }

    public static class HogeBean {

        public static final String REDIRECT_SCOPE = "aaa, ccc";

        public static final String SUBAPPLICATION_SCOPE = "bbb";

        public static final String PAGE_SCOPE = "ddd";

        public String getAaa() {
            return null;
        }

        public String getBbb() {
            return null;
        }

        public String getCcc() {
            return null;
        }

        public String getDdd() {
            return null;
        }
    }

    public static class FooBean {

        public static final String REDIRECT_SCOPE = "aaa, ccc";

        public String getAaa() {
            return null;
        }

        public String getBbb() {
            return null;
        }

        public String getCcc() {
            return null;
        }

        public String getDdd() {
            return null;
        }

    }
}