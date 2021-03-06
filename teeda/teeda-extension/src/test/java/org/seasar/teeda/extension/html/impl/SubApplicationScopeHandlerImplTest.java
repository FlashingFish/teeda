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

import java.util.Map;

import org.seasar.teeda.core.unit.TeedaTestCase;
import org.seasar.teeda.extension.html.PageDesc;

/**
 * @author shot
 */
public class SubApplicationScopeHandlerImplTest extends TeedaTestCase {

    public void testToScope1() throws Exception {
        SubApplicationScopeHandlerImpl handler = new SubApplicationScopeHandlerImpl();
        assertFalse(handler.toScope(null, null));
        register(AaaPage.class, "aaaPage");
        PageDesc pageDesc = new PageDescImpl(AaaPage.class, "aaaPage");
        assertFalse(handler.toScope(pageDesc, getFacesContext()));
    }

    public void testToScope2() throws Exception {
        SubApplicationScopeHandlerImpl handler = new SubApplicationScopeHandlerImpl();
        Aaa2Page page = new Aaa2Page();
        page.setAaa("A");
        page.setBbb(new Integer(1));
        register(page, "aaa2Page");
        PageDesc pageDesc = new PageDescImpl(Aaa2Page.class, "aaa2Page");

        handler.toScope(pageDesc, getFacesContext());
        Map map = ScopeValueHelper
                .getOrCreateSubApplicationScopeValues(getFacesContext());
        assertEquals("A", map.get("aaa"));
        assertEquals(null, map.get("bbb"));
    }

    public static class AaaPage {

    }

    public static class Aaa2Page {

        public static final String SUBAPPLICATION_SCOPE = "aaa";

        private String aaa;

        private Integer bbb;

        public String getAaa() {
            return aaa;
        }

        public Integer getBbb() {
            return bbb;
        }

        public void setAaa(String aaa) {
            this.aaa = aaa;
        }

        public void setBbb(Integer bbb) {
            this.bbb = bbb;
        }

    }

}
