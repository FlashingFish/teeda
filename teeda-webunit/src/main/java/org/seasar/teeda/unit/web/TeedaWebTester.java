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
package org.seasar.teeda.unit.web;

import net.sourceforge.jwebunit.IJWebUnitDialog;
import net.sourceforge.jwebunit.WebTester;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitDialog;

/**
 * @author manhole
 */
public class TeedaWebTester extends WebTester {

    public IJWebUnitDialog initializeDialog() {
        return new TeedaHtmlUnitDialog();
    }

    static class TeedaHtmlUnitDialog extends HtmlUnitDialog {
        public String getPageSource() {
            return super.getPageSource();
        }
    }

}