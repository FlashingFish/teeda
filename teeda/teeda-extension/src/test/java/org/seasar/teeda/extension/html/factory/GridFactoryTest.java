/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.teeda.extension.html.factory;

import java.util.HashMap;
import java.util.Map;

import org.seasar.teeda.extension.ExtensionConstants;
import org.seasar.teeda.extension.config.taglib.element.TagElement;
import org.seasar.teeda.extension.config.taglib.element.TaglibElement;
import org.seasar.teeda.extension.config.taglib.element.impl.TagElementImpl;
import org.seasar.teeda.extension.config.taglib.element.impl.TaglibElementImpl;
import org.seasar.teeda.extension.html.ElementNode;
import org.seasar.teeda.extension.html.ElementProcessor;
import org.seasar.teeda.extension.html.PageDesc;
import org.seasar.teeda.extension.mock.MockTaglibManager;
import org.seasar.teeda.extension.taglib.TGridTag;
import org.seasar.teeda.extension.unit.TeedaExtensionTestCase;

/**
 * @author manhole
 */
public class GridFactoryTest extends TeedaExtensionTestCase {

    private GridFactory factory;

    protected void setUp() throws Exception {
        super.setUp();
        factory = new GridFactory();
    }

    public void testIsMatch_Ok() throws Exception {
        Map properties = new HashMap();
        properties.put("id", "hogeGridXY");
        ElementNode elementNode = createElementNode("table", properties);
        PageDesc pageDesc = createPageDesc(FooPage.class, "fooPage");
        assertEquals(true, factory.isMatch(elementNode, pageDesc, null));
    }

    public void testIsMatch_NoId() throws Exception {
        Map properties = new HashMap();
        ElementNode elementNode = createElementNode("table", properties);
        PageDesc pageDesc = createPageDesc(FooPage.class, "fooPage");
        assertEquals(false, factory.isMatch(elementNode, pageDesc, null));
    }

    public void testIsMatch_BadId() throws Exception {
        Map properties = new HashMap();
        ElementNode elementNode = createElementNode("table", properties);
        PageDesc pageDesc = createPageDesc(FooPage.class, "fooPage");
        assertEquals(false, factory.isMatch(elementNode, pageDesc, null));
    }

    public void testIsMatch_BadElement() throws Exception {
        Map properties = new HashMap();
        properties.put("id", "hogeGridXY");
        ElementNode elementNode = createElementNode("input", properties);
        PageDesc pageDesc = createPageDesc(FooPage.class, "fooPage");
        assertEquals(false, factory.isMatch(elementNode, pageDesc, null));
    }

    public void testIsMatch_NoPageDesc() throws Exception {
        Map properties = new HashMap();
        properties.put("id", "hogeGridXY");
        ElementNode elementNode = createElementNode("table", properties);
        assertEquals(false, factory.isMatch(elementNode, null, null));
    }

    public void testIsMatch_NoItemsProperty() throws Exception {
        Map properties = new HashMap();
        properties.put("id", "fooGridXY");
        ElementNode elementNode = createElementNode("table", properties);
        PageDesc pageDesc = createPageDesc(FooPage.class, "fooPage");
        assertEquals(false, factory.isMatch(elementNode, pageDesc, null));
    }

    public void testCreateFactory() throws Exception {
        // ## Arrange ##
        MockTaglibManager taglibManager = new MockTaglibManager();
        TaglibElement taglibElem = new TaglibElementImpl();
        taglibElem.setUri(ExtensionConstants.TEEDA_EXTENSION_URI);
        TagElement tagElement = new TagElementImpl();
        tagElement.setName("grid");
        tagElement.setTagClass(TGridTag.class);
        taglibElem.addTagElement(tagElement);
        taglibManager.addTaglibElement(taglibElem);

        factory.setTaglibManager(taglibManager);
        Map properties = new HashMap();
        properties.put("id", "hogeGridXY");
        ElementNode elementNode = createElementNode("table", properties);
        PageDesc pageDesc = createPageDesc(FooPage.class, "fooPage");

        // ## Act ##
        ElementProcessor processor = factory.createProcessor(elementNode,
                pageDesc, null);

        // ## Assert ##
        assertNotNull(processor);
        assertEquals(TGridTag.class, processor.getTagClass());
    }

}
