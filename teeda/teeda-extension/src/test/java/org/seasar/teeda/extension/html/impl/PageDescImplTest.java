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

import java.io.File;

import javax.faces.internal.ValidatorResource;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.impl.ComponentDefImpl;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.teeda.extension.html.PageDesc;
import org.seasar.teeda.extension.html.RedirectDesc;
import org.seasar.teeda.extension.html.TakeOverDesc;
import org.seasar.teeda.extension.html.impl.page.FooPage;
import org.seasar.teeda.extension.unit.TeedaExtensionTestCase;
import org.seasar.teeda.extension.validator.TRequiredValidator;

/**
 * @author higa
 *
 */
public class PageDescImplTest extends TeedaExtensionTestCase {

    public void testHasProperty() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        assertTrue(pd.hasProperty("aaa"));
        assertFalse(pd.hasProperty("xxx"));
        assertFalse(pd.hasProperty(null));
    }

    public void testHasItemsProperty() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        assertTrue(pd.hasItemsProperty("cccItems"));
        assertFalse(pd.hasItemsProperty("dddItems"));
        assertFalse(pd.hasItemsProperty("xxx"));
        assertFalse(pd.hasItemsProperty(null));
    }

    public void testHasDynamicProperty() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        assertTrue(pd.hasDynamicProperty("aaa"));
        assertTrue(pd.hasDynamicProperty("bbb"));
        assertFalse(pd.hasDynamicProperty("xxx"));
        assertFalse(pd.hasDynamicProperty(null));
    }

    public void testHasMethod() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        assertTrue(pd.hasMethod("doBar"));
        assertFalse(pd.hasMethod("doXxx"));
        assertFalse(pd.hasMethod(null));
    }

    public void testIsModified() throws Exception {
        File file = ResourceUtil.getResourceAsFile(ClassUtil
                .getResourcePath(FooPage.class));
        PageDesc pd = createPageDesc(FooPage.class, "fooPage", file);
        assertFalse("1", pd.isModified());
        Thread.sleep(1000);
        file.setLastModified(System.currentTimeMillis());
        assertTrue("2", pd.isModified());
    }

    public void testValidator() throws Exception {
        ComponentDef cd = new ComponentDefImpl(TRequiredValidator.class,
                "TRequiredValidator");
        register(cd);
        createPageDesc(HogePage.class, "hogePage");
        assertNotNull(ValidatorResource.getValidator("#{hogePage.aaa}"));
    }

    public void testGetTakeOverDesc() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        TakeOverDesc tod = pd.getTakeOverDesc("doBar");
        assertNotNull(tod);
        assertEquals(TakeOverTypeDescFactory.INCLUDE, tod.getTakeOverTypeDesc());
        String[] props = tod.getProperties();
        assertEquals(2, props.length);
        assertEquals("aaa", props[0]);
        assertEquals("bbb", props[1]);
    }

    public void testIsRedirectScopeProperty() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        assertTrue(pd.isRedirectScopeProperty("aaa"));
        assertFalse(pd.isRedirectScopeProperty("bbb"));
    }

    public void testIsSubappScopeProperty() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        assertTrue(pd.isSubapplicationScopeProperty("bbb"));
        assertFalse(pd.isSubapplicationScopeProperty("aaa"));
    }

    public void testIsPageScopeProperty() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        assertTrue(pd.isPageScopeProperty("eee"));
        assertFalse(pd.isPageScopeProperty("aaa"));
    }

    public void testGetPageScopePropertyNames() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        String[] pageScopePropertyNames = pd.getPageScopePropertyNames();
        assertNotNull(pageScopePropertyNames);
        assertTrue(pageScopePropertyNames.length == 1);
        assertEquals("eee", pageScopePropertyNames[0]);
    }

    public void testGetRedirectScopePropertyNames() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        String[] redirectScopePropertyNames = pd
                .getRedirectScopePropertyNames();
        assertNotNull(redirectScopePropertyNames);
        assertTrue(redirectScopePropertyNames.length == 1);
        assertEquals("aaa", redirectScopePropertyNames[0]);
    }

    public void testGetSubapplicationScopePropertyNames() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        String[] subappScopePropertyNames = pd
                .getSubapplicationScopePropertyNames();
        assertNotNull(subappScopePropertyNames);
        assertTrue(subappScopePropertyNames.length == 1);
        assertEquals("bbb", subappScopePropertyNames[0]);
    }

    public void testGetRedirectDesc() throws Exception {
        PageDesc pd = createPageDesc(FooPage.class, "fooPage");
        assertTrue(pd.hasRedirectDesc("doBar"));
        RedirectDesc rd = pd.getRedirectDesc("doBar");
        assertNotNull(rd);
        assertEquals(RedirectDesc.HTTPS, rd.getProtocol());
    }

}