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

import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;
import javax.faces.render.RendererTest;

import org.custommonkey.xmlunit.Diff;
import org.seasar.teeda.core.mock.MockFacesContext;

/**
 * @author manhole
 */
public class HtmlFormRendererTest extends RendererTest {

    // TODO render hidden fields for CommandLink
    // TODO ViewHandler#getActionURL
    // TODO ExternalContext#encodeActionURL

    private HtmlFormRenderer renderer_;

    private MockHtmlForm htmlForm_;

    protected void setUp() throws Exception {
        super.setUp();
        renderer_ = createHtmlFormRenderer();
        htmlForm_ = new MockHtmlForm();
        htmlForm_.setRenderer(renderer_);
        htmlForm_.setEnctype(null);
    }

    public void testEncode_NoValue() throws Exception {
        MockFacesContext context = getFacesContext();

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlForm_);

        // ## Assert ##
        assertEquals(
                "<form name=\"_id0\" method=\"post\" enctype=\"application/x-www-form-urlencoded\">"
                        + "<input type=\"hidden\" name=\"_id0\" value=\"_id0\" />"
                        + "</form>", getResponseText());
    }

    public void testEncode_RenderFalse() throws Exception {
        // ## Arrange ##
        htmlForm_.setRendered(false);
        MockFacesContext context = getFacesContext();

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlForm_);

        // ## Assert ##
        assertEquals("", getResponseText());
    }

    public void testEncode_WithId() throws Exception {
        // ## Arrange ##
        htmlForm_.setId("a");
        MockFacesContext context = getFacesContext();

        // ## Act ##
        encodeByRenderer(renderer_, context, htmlForm_);

        // ## Assert ##
        assertEquals(
                "<form id=\"a\" name=\"a\" method=\"post\" enctype=\"application/x-www-form-urlencoded\">"
                        + "<input type=\"hidden\" name=\"a\" value=\"a\" />"
                        + "</form>", getResponseText());
    }

    public void testEncode_WithAllAttributes() throws Exception {
        htmlForm_.setAccept("a");
        htmlForm_.setAcceptcharset("b");
        htmlForm_.setDir("c");
        htmlForm_.setEnctype("d");
        htmlForm_.setLang("e");
        htmlForm_.setOnclick("f");
        htmlForm_.setOndblclick("g");
        htmlForm_.setOnkeydown("h");
        htmlForm_.setOnkeypress("i");
        htmlForm_.setOnkeyup("j");
        htmlForm_.setOnmousedown("k");
        htmlForm_.setOnmousemove("l");
        htmlForm_.setOnmouseout("m");
        htmlForm_.setOnmouseover("n");
        htmlForm_.setOnmouseup("o");
        htmlForm_.setOnreset("p");
        htmlForm_.setOnsubmit("q");
        htmlForm_.setStyle("r");
        htmlForm_.setStyleClass("s");
        htmlForm_.setTarget("t");
        htmlForm_.setTitle("u");

        htmlForm_.setId("AA");

        MockFacesContext context = getFacesContext();
        encodeByRenderer(renderer_, context, htmlForm_);

        Diff diff = new Diff("<form id=\"AA\" name=\"AA\" method=\"post\""
                + " accept=\"a\"" + " accept-charset=\"b\"" + " dir=\"c\""
                + " enctype=\"d\"" + " lang=\"e\"" + " onclick=\"f\""
                + " ondblclick=\"g\"" + " onkeydown=\"h\""
                + " onkeypress=\"i\"" + " onkeyup=\"j\"" + " onmousedown=\"k\""
                + " onmousemove=\"l\"" + " onmouseout=\"m\""
                + " onmouseover=\"n\"" + " onmouseup=\"o\"" + " onreset=\"p\""
                + " onsubmit=\"q\"" + " style=\"r\"" + " class=\"s\""
                + " target=\"t\"" + " title=\"u\"" + ">"
                + "<input type=\"hidden\" name=\"AA\" value=\"AA\" />"
                + "</form>", getResponseText());
        assertEquals(diff.toString(), true, diff.identical());
    }

    public void testDecode_None() throws Exception {
        // ## Arrange ##
        htmlForm_.setClientId("key");

        MockFacesContext context = getFacesContext();

        // ## Act ##
        renderer_.decode(context, htmlForm_);

        // ## Assert ##
        assertEquals(1, htmlForm_.getSetSubmittedCalls());
        assertEquals(false, htmlForm_.isSubmitted());
    }

    public void testDecode_Success() throws Exception {
        // ## Arrange ##
        htmlForm_.setClientId("key1");

        MockFacesContext context = getFacesContext();
        context.getExternalContext().getRequestParameterMap().put("key1",
                "12345");

        // ## Act ##
        renderer_.decode(context, htmlForm_);

        // ## Assert ##
        assertEquals(1, htmlForm_.getSetSubmittedCalls());
        assertEquals(true, htmlForm_.isSubmitted());
    }

    public void testGetRendersChildren() throws Exception {
        assertEquals(false, renderer_.getRendersChildren());
    }

    private HtmlFormRenderer createHtmlFormRenderer() {
        return (HtmlFormRenderer) createRenderer();
    }

    protected Renderer createRenderer() {
        return new HtmlFormRenderer();
    }

    private static class MockHtmlForm extends HtmlForm {
        private Renderer renderer_;

        private String clientId_;

        private int setSubmittedCalls_;

        public void setRenderer(Renderer renderer) {
            renderer_ = renderer;
        }

        protected Renderer getRenderer(FacesContext context) {
            if (renderer_ != null) {
                return renderer_;
            }
            return super.getRenderer(context);
        }

        public String getClientId(FacesContext context) {
            if (clientId_ != null) {
                return clientId_;
            }
            return super.getClientId(context);
        }

        public void setClientId(String clientId) {
            clientId_ = clientId;
        }

        public void setSubmitted(boolean submitted) {
            setSubmittedCalls_++;
            super.setSubmitted(submitted);
        }

        public int getSetSubmittedCalls() {
            return setSubmittedCalls_;
        }

    }

}
