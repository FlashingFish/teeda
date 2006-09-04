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

import org.seasar.teeda.extension.html.ElementNode;
import org.seasar.teeda.extension.unit.TeedaExtensionTestCase;

/**
 * @author shot
 */
public class TitleFactoryTest extends TeedaExtensionTestCase {

    public void testIsMatch() throws Exception {
        TitleFactory factory = new TitleFactory();
        Map props = new HashMap();
        props.put("id", "aaa");
        ElementNode elementNode = createElementNode("title", props);
        assertTrue(factory.isMatch(elementNode, null, null));
    }
}