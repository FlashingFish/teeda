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
package org.seasar.teeda.core.render.html;

import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;
import javax.faces.render.RendererTest;

import org.custommonkey.xmlunit.Diff;

/**
 * @author manhole
 */
public class HtmlOutputTextRendererTest extends RendererTest {

    private HtmlOutputTextRenderer renderer_;

    private MockHtmlOutputText htmlOutputText_;

    protected void setUp() throws Exception {
        super.setUp();
        renderer_ = createHtmlOutputTextRenderer();
        htmlOutputText_ = new MockHtmlOutputText();
        htmlOutputText_.setRenderer(renderer_);
    }

    public void testEncodeEnd() throws Exception {
        htmlOutputText_.setValue("abc");

        renderer_.encodeEnd(getFacesContext(), htmlOutputText_);

        assertEquals("abc", getResponseText());
    }

    public void testEncodeEnd_RenderFalse() throws Exception {
        // ## Arrange ##
        htmlOutputText_.setRendered(false);
        htmlOutputText_.setValue("abc");

        // ## Act ##
        renderer_.encodeEnd(getFacesContext(), htmlOutputText_);

        // ## Assert ##
        assertEquals("", getResponseText());
    }

    public void testEncodeEnd_NullValue() throws Exception {

        htmlOutputText_.setValue(null);

        renderer_.encodeEnd(getFacesContext(), htmlOutputText_);

        assertEquals("", getResponseText());
    }

    public void testEncodeEnd_EscapeTrue() throws Exception {

        assertTrue("default is true", htmlOutputText_.isEscape());
        htmlOutputText_.setValue("<a>");

        renderer_.encodeEnd(getFacesContext(), htmlOutputText_);

        assertEquals("&lt;a&gt;", getResponseText());
    }

    public void testEncodeEnd_EscapeFalse() throws Exception {

        htmlOutputText_.setEscape(false);
        htmlOutputText_.setValue("<a>");

        renderer_.encodeEnd(getFacesContext(), htmlOutputText_);

        assertEquals("<a>", getResponseText());
    }

    public void testEncodeEnd_WithStyle() throws Exception {
        htmlOutputText_.setStyle("some style");
        htmlOutputText_.setValue("a");
        htmlOutputText_.setEscape(false);

        renderer_.encodeEnd(getFacesContext(), htmlOutputText_);

        assertEquals("<span style=\"some style\">a</span>", getResponseText());
    }

    public void testEncodeEnd_WithStyleClass() throws Exception {
        htmlOutputText_.setStyleClass("some styleClass");
        htmlOutputText_.setValue("a");

        renderer_.encodeEnd(getFacesContext(), htmlOutputText_);

        assertEquals("styleClass -> class",
                "<span class=\"some styleClass\">a</span>", getResponseText());
    }

    public void testEncodeEnd_CommonAttributtes() throws Exception {
        htmlOutputText_.getAttributes().put("onmouseout", "do something");
        htmlOutputText_.getAttributes().put("title", "someTitle");
        htmlOutputText_.setValue("a");

        renderer_.encodeEnd(getFacesContext(), htmlOutputText_);

        Diff diff = new Diff(
                "<span title=\"someTitle\" onmouseout=\"do something\">a</span>",
                getResponseText());
        assertEquals(diff.toString(), true, diff.identical());
    }

    public void testEncodeEnd_Id() throws Exception {
        // ## Arrange ##
        htmlOutputText_.setId("someId");
        htmlOutputText_.setValue("a");

        // ## Act ##
        renderer_.encodeEnd(getFacesContext(), htmlOutputText_);

        // ## Assert ##
        assertEquals("<span id=\"someId\">a</span>", getResponseText());
    }

    public void testEncodeEnd_NotWriteId() throws Exception {
        // ## Arrange ##
        htmlOutputText_.setId(UIViewRoot.UNIQUE_ID_PREFIX + "someId");
        htmlOutputText_.setValue("a");

        // ## Act ##
        renderer_.encodeEnd(getFacesContext(), htmlOutputText_);

        // ## Assert ##
        assertEquals("<span>a</span>", getResponseText());
    }

    public void testEncodeEnd_WithAllAttributes() throws Exception {
        // ## Arrange ##
        htmlOutputText_.setId("fooId");
        htmlOutputText_.setTitle("someTitle");
        htmlOutputText_.getAttributes().put("onmouseout", "do something");
        htmlOutputText_.setValue("a");

        // ## Act ##
        renderer_.encodeEnd(getFacesContext(), htmlOutputText_);

        // ## Assert ##
        Diff diff = new Diff("<span" + " id=\"fooId\"" + " title=\"someTitle\""
                + " onmouseout=\"do something\">a</span>", getResponseText());
        assertEquals(diff.toString(), true, diff.identical());
    }

    public void testGetRendersChildren() throws Exception {
        assertEquals(false, renderer_.getRendersChildren());
    }

    private HtmlOutputTextRenderer createHtmlOutputTextRenderer() {
        return (HtmlOutputTextRenderer) createRenderer();
    }

    protected Renderer createRenderer() {
        return new HtmlOutputTextRenderer();
    }

    private static class MockHtmlOutputText extends HtmlOutputText {

        private Renderer renderer_ = null;

        public void setRenderer(Renderer renderer) {
            renderer_ = renderer;
        }

        protected Renderer getRenderer(FacesContext context) {
            if (renderer_ != null) {
                return renderer_;
            }
            return super.getRenderer(context);
        }
    }

}
