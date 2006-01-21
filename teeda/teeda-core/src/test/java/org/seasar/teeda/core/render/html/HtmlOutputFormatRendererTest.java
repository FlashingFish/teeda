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

import java.util.Locale;

import javax.faces.component.UIParameter;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlOutputFormat;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;
import javax.faces.render.RendererTest;

import org.custommonkey.xmlunit.Diff;
import org.seasar.teeda.core.mock.MockFacesContext;

/**
 * @author manhole
 */
public class HtmlOutputFormatRendererTest extends RendererTest {

    private HtmlOutputFormatRenderer renderer_;

    private MockHtmlOutputFormat htmlOutputFormat_;

    protected void setUp() throws Exception {
        super.setUp();
        renderer_ = createHtmlOutputFormatRenderer();
        htmlOutputFormat_ = new MockHtmlOutputFormat();
        htmlOutputFormat_.setRenderer(renderer_);
    }

    public void testEncodeEnd() throws Exception {
        htmlOutputFormat_.setValue("abc");

        renderer_.encodeEnd(getFacesContext(), htmlOutputFormat_);

        assertEquals("abc", getResponseText());
    }

    public void testEncodeEnd_NullValue() throws Exception {
        htmlOutputFormat_.setValue(null);

        renderer_.encodeEnd(getFacesContext(), htmlOutputFormat_);

        assertEquals("", getResponseText());
    }

    public void testEncodeBeginToEnd() throws Exception {
        htmlOutputFormat_.setId("a");
        htmlOutputFormat_.setValue("b");

        renderer_.encodeBegin(getFacesContext(), htmlOutputFormat_);
        renderer_.encodeEnd(getFacesContext(), htmlOutputFormat_);

        assertEquals("<span id=\"a\">b</span>", getResponseText());
    }

    public void testEncodeBeginToEnd_RenderFalse() throws Exception {
        htmlOutputFormat_.setRendered(false);
        htmlOutputFormat_.setId("a");
        htmlOutputFormat_.setValue("b");
        
        renderer_.encodeBegin(getFacesContext(), htmlOutputFormat_);
        renderer_.encodeEnd(getFacesContext(), htmlOutputFormat_);
        
        assertEquals("", getResponseText());
    }
    
    public void testEncodeEnd_WithParams() throws Exception {
        // ## Arrange ##
        htmlOutputFormat_.setValue("1{0}2{1}3");

        UIParameter param1 = new UIParameter();
        param1.setValue("a");
        UIParameter param2 = new UIParameter();
        param2.setValue("b");
        htmlOutputFormat_.getChildren().add(param1);
        htmlOutputFormat_.getChildren().add(param2);

        // ## Act ##
        renderer_.encodeEnd(getFacesContext(), htmlOutputFormat_);

        // ## Assert ##
        assertEquals("1a2b3", getResponseText());
    }

    public void testEncodeEnd_WithJapaneseParam() throws Exception {
        // ## Arrange ##
        htmlOutputFormat_.setValue("1{0}2");

        UIParameter param2 = new UIParameter();
        param2.setValue(new Character((char) 12354));
        htmlOutputFormat_.getChildren().add(param2);

        // ## Act ##
        renderer_.encodeEnd(getFacesContext(), htmlOutputFormat_);

        // ## Assert ##
        assertEquals("1" + new Character((char) 12354) + "2", getResponseText());
    }

    public void testEncodeEnd_EscapeTrue() throws Exception {
        htmlOutputFormat_.setEscape(true);
        htmlOutputFormat_.setValue("<a>");

        renderer_.encodeEnd(getFacesContext(), htmlOutputFormat_);

        assertEquals("&lt;a&gt;", getResponseText());
    }

    public void testEncodeEnd_EscapeFalse() throws Exception {
        htmlOutputFormat_.setEscape(false);
        htmlOutputFormat_.setValue("<a>");

        renderer_.encodeEnd(getFacesContext(), htmlOutputFormat_);

        assertEquals("<a>", getResponseText());
    }

    public void testEncodeBeginToEnd_WithAllAttributes() throws Exception {
        htmlOutputFormat_.setId("a");
        htmlOutputFormat_.setValue("b");
        htmlOutputFormat_.setStyle("c");
        htmlOutputFormat_.setStyleClass("d");
        htmlOutputFormat_.setTitle("e");

        renderer_.encodeBegin(getFacesContext(), htmlOutputFormat_);
        renderer_.encodeEnd(getFacesContext(), htmlOutputFormat_);

        Diff diff = new Diff("<span" + " id=\"a\"" + " style=\"c\""
                + " class=\"d\"" + " title=\"e\"" + ">b</span>",
                getResponseText());
        assertEquals(diff.toString(), true, diff.identical());
    }

    public void testGetLocaleFromUIViewRoot() throws Exception {
        // ## Arrange ##
        FacesContext context = getFacesContext();
        context.setViewRoot(new UIViewRoot());
        context.getViewRoot().setLocale(Locale.CANADA);

        // ## Act & Assert ##
        assertEquals(Locale.CANADA, renderer_.getLocale(context));

        context.getViewRoot().setLocale(Locale.CHINESE);
        assertEquals(Locale.CHINESE, renderer_.getLocale(context));
    }

    public void testGetRendersChildren() throws Exception {
        assertEquals(false, renderer_.getRendersChildren());
    }

    protected MockFacesContext getFacesContext() {
        MockFacesContext context = super.getFacesContext();
        UIViewRoot viewRoot = new UIViewRoot();
        viewRoot.setLocale(Locale.getDefault());
        context.setViewRoot(viewRoot);
        return context;
    }

    private HtmlOutputFormatRenderer createHtmlOutputFormatRenderer() {
        return (HtmlOutputFormatRenderer) createRenderer();
    }

    protected Renderer createRenderer() {
        return new HtmlOutputFormatRenderer();
    }

    private static class MockHtmlOutputFormat extends HtmlOutputFormat {

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
