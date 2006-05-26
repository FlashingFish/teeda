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

import javax.faces.component.UIParameter;
import javax.faces.render.AbstractRendererTest;

import org.seasar.teeda.core.mock.MockFacesContext;

/**
 * @author manhole
 */
public class HtmlFormAndCommandLinkRendererTest extends AbstractRendererTest {

    private HtmlFormRenderer formRenderer_;

    private HtmlCommandLinkRenderer commandLinkRenderer_;

    private MockHtmlForm htmlForm_;

    protected void setUp() throws Exception {
        super.setUp();
        formRenderer_ = new HtmlFormRenderer();
        formRenderer_.setComponentIdLookupStrategy(getComponentIdLookupStrategy());
        htmlForm_ = new MockHtmlForm();
        htmlForm_.setRenderer(formRenderer_);
        htmlForm_.setEnctype(null);

        commandLinkRenderer_ = new HtmlCommandLinkRenderer();
        commandLinkRenderer_.setComponentIdLookupStrategy(getComponentIdLookupStrategy());
    }

    public void test1() throws Exception {
        // ## Arrange ##
        MockHtmlCommandLink htmlCommandLink = new MockHtmlCommandLink();
        htmlCommandLink.setRenderer(commandLinkRenderer_);
        htmlCommandLink.setId("fooLink");

        htmlForm_.setId("fooForm");
        htmlForm_.getChildren().add(htmlCommandLink);

        MockFacesContext context = getFacesContext();
        context.getViewRoot().setViewId("/aa");

        // <input type="hidden" name="fooForm:__link_clicked__" />

        // ## Act ##
        formRenderer_.encodeBegin(context, htmlForm_);
        formRenderer_.encodeChildren(context, htmlForm_);
        formRenderer_.encodeEnd(context, htmlForm_);

        // ## Assert ##
        assertEquals(
                "<form id=\"fooForm\" name=\"fooForm\" method=\"post\""
                        + " enctype=\"application/x-www-form-urlencoded\""
                        + " action=\"/aa\">"
                        + "<a"
                        + " id=\"fooLink\""
                        + " href=\"#\""
                        + " onclick=\""
                        + "var f = document.forms['fooForm'];"
                        + " f['fooForm:__link_clicked__'].value = 'fooForm:fooLink';"
                        + " if (f.onsubmit) { f.onsubmit(); }"
                        + " f.submit();"
                        + " return false;"
                        + "\"></a>"
                        + "<input type=\"hidden\" name=\"fooForm/aa\" value=\"fooForm\" />"
                        + "<input type=\"hidden\" name=\"fooForm:__link_clicked__\" />"
                        + "</form>", getResponseText());
    }

    public void testWithUIParameters() throws Exception {
        // ## Arrange ##
        MockHtmlCommandLink htmlCommandLink = new MockHtmlCommandLink();
        htmlCommandLink.setRenderer(commandLinkRenderer_);
        htmlCommandLink.setId("fooLink");

        htmlForm_.setId("fooForm");
        htmlForm_.getChildren().add(htmlCommandLink);
        {
            UIParameter param = new UIParameter();
            param.setName("x");
            param.setValue("1");
            htmlCommandLink.getChildren().add(param);
        }
        {
            UIParameter param = new UIParameter();
            param.setName("y");
            param.setValue("2");
            htmlCommandLink.getChildren().add(param);
        }

        MockFacesContext context = getFacesContext();
        context.getViewRoot().setViewId("/abc");

        // <input type="hidden" name="fooForm:__link_clicked__" />

        // ## Act ##
        formRenderer_.encodeBegin(context, htmlForm_);
        formRenderer_.encodeChildren(context, htmlForm_);
        formRenderer_.encodeEnd(context, htmlForm_);

        // ## Assert ##
        assertEquals(
                "<form id=\"fooForm\" name=\"fooForm\" method=\"post\""
                        + " enctype=\"application/x-www-form-urlencoded\""
                        + " action=\"/abc\">"
                        + "<a"
                        + " id=\"fooLink\""
                        + " href=\"#\""
                        + " onclick=\""
                        + "var f = document.forms['fooForm'];"
                        + " f['fooForm:__link_clicked__'].value = 'fooForm:fooLink';"
                        + " f['x'].value = '1';"
                        + " f['y'].value = '2';"
                        + " if (f.onsubmit) { f.onsubmit(); }"
                        + " f.submit();"
                        + " return false;"
                        + "\"></a>"
                        + "<input type=\"hidden\" name=\"fooForm/abc\" value=\"fooForm\" />"
                        + "<input type=\"hidden\" name=\"fooForm:__link_clicked__\" />"
                        + "<input type=\"hidden\" name=\"x\" />"
                        + "<input type=\"hidden\" name=\"y\" />" + "</form>",
                getResponseText());
    }

}
