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
package org.seasar.teeda.it.render;

import junit.framework.Test;

import org.seasar.teeda.unit.web.TeedaWebTestCase;
import org.seasar.teeda.unit.web.TeedaWebTester;

/**
 * @author manhole
 */
public class InputRadioTest extends TeedaWebTestCase {

	public static Test suite() throws Exception {
		return setUpTest(InputRadioTest.class);
	}

	public void testSelectValueAndSubmit() throws Exception {
		// ## Arrange ##
		TeedaWebTester tester = new TeedaWebTester();

		// ## Act ##
		tester.beginAt(getBaseUrl(), "view/radio/inputRadio.html");
		tester.dumpHtml();

		tester.clickRadioOptionByName("inputRadioForm:aaa", "2");
		tester.submitByName("inputRadioForm:doAction");
		tester.dumpHtml();

		// ## Assert ##
		tester.assertRadioOptionSelectedByName("inputRadioForm:aaa", "2");
		tester.assertTextEqualsById("aaa-display", "2");
	}

	public void testInputRadio_required() throws Exception {
		// ## Arrange ##
		TeedaWebTester tester = new TeedaWebTester();

		// ## Act ##
		// ## Assert ##
		tester.beginAt(getBaseUrl(), "view/radio/inputRadio.html");
		tester.dumpHtml();

		tester.submitByName("inputRadioForm:doAction");
		tester.dumpHtml();

		tester.assertTextInElementById("allMessages", "aaa");

		tester.clickRadioOptionByName("inputRadioForm:aaa", "1");
		tester.submitByName("inputRadioForm:doAction");
		tester.dumpHtml();

		tester.assertElementNotPresentById("allMessages");
		tester.assertRadioOptionSelectedByName("inputRadioForm:aaa", "1");
	}

	public void testSelectWithSuffix_TEEDA429() throws Exception {
		// ## Arrange ##
		TeedaWebTester tester = new TeedaWebTester();

		// ## Act ##
		tester.beginAt(getBaseUrl(), "view/radio/inputRadio2.html");
		tester.dumpHtml();

		tester.clickRadioOptionByName("inputRadioForm:aaa-hoge", "2");
		tester.submitByName("inputRadioForm:doAction");
		tester.dumpHtml();

		// ## Assert ##
		tester.assertRadioOptionSelectedByName("inputRadioForm:aaa-hoge", "2");
		tester.assertTextEqualsById("aaa-display", "2");
	}

}
