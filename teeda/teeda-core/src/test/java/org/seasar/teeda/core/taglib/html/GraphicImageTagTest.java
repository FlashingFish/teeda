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
package org.seasar.teeda.core.taglib.html;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;

import junit.framework.TestCase;

/**
 * @author yone
 */
public class GraphicImageTagTest extends TestCase {

    public void testGetComponentType() throws Exception {
        // # Arrange #
        GraphicImageTag tag = new GraphicImageTag();

        // # Act & Assert #
        assertEquals("javax.faces.HtmlGraphicImage", tag.getComponentType());
    }

    public void GraphicImageTag() throws Exception {
        // # Arrange #
        GraphicImageTag tag = new GraphicImageTag();

        // # Act & Assert #
        assertEquals("javax.faces.Image", tag.getRendererType());
    }

    public void testSetProperties_All() throws Exception {
        // # Arrange #
        HtmlGraphicImage component = createHtmlGraphicImage();
        GraphicImageTag tag = new GraphicImageTag();

        tag.setUrl("url");
        tag.setValue("value");
        tag.setAlt("alt");
        tag.setDir("dir");
        tag.setHeight("130");
        tag.setIsmap("true");
        tag.setLang("lang");
        tag.setLongdesc("descURI");
        tag.setOnclick("onclick");
        tag.setOndblclick("ondblclick");
        tag.setOnkeydown("onkeydown");
        tag.setOnkeypress("onkeypress");
        tag.setOnkeyup("onkeyup");
        tag.setOnmousedown("onmousedown");
        tag.setOnmousemove("onmousemove");
        tag.setOnmouseout("onmouseout");
        tag.setOnmouseover("onmouseover");
        tag.setOnmouseup("onmouseup");
        tag.setStyle("style");
        tag.setStyleClass("styleclass");
        tag.setTitle("title");
        tag.setUsemap("mapName");
        tag.setWidth("width");

        // # Act #
        tag.setProperties(component);

        // # Assert #
        assertEquals("url", component.getUrl());
        // see UIGraphic
        assertEquals("url", component.getValue());
        assertEquals("alt", component.getAlt());
        assertEquals("dir", component.getDir());
        assertEquals("130", component.getHeight());
        assertTrue(component.isIsmap());
        assertEquals("lang", component.getLang());
        assertEquals("descURI", component.getLongdesc());
        assertEquals("onclick", component.getOnclick());
        assertEquals("ondblclick", component.getOndblclick());
        assertEquals("onkeydown", component.getOnkeydown());
        assertEquals("onkeypress", component.getOnkeypress());
        assertEquals("onkeyup", component.getOnkeyup());
        assertEquals("onmousedown", component.getOnmousedown());
        assertEquals("onmousemove", component.getOnmousemove());
        assertEquals("onmouseout", component.getOnmouseout());
        assertEquals("onmouseover", component.getOnmouseover());
        assertEquals("onmouseup", component.getOnmouseup());
        assertEquals("style", component.getStyle());
        assertEquals("styleclass", component.getStyleClass());
        assertEquals("title", component.getTitle());
        assertEquals("mapName", component.getUsemap());
        assertEquals("width", component.getWidth());
    }

    public void testRelease() throws Exception {
        // # Arrange #
        GraphicImageTag tag = new GraphicImageTag();
        tag.setUrl("url");

        // # Act #
        tag.release();

        // # Assert #
        assertEquals(null, tag.getUrl());
    }

    private HtmlGraphicImage createHtmlGraphicImage() {
        return (HtmlGraphicImage) createUIComponent();
    }

    protected UIComponent createUIComponent() {
        return new HtmlGraphicImage();
    }

}
