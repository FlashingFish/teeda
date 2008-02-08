/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.teeda.it.web.scope;

/**
 * @author shot
 */
public class SubapplicationScopeResultPage {

	private String message1;

	private String message2;

	/**
	 * @return the message1
	 */
	public String getMessage1() {
		return message1;
	}

	/**
	 * @param message1
	 *            the message1 to set
	 */
	public void setMessage1(String message1) {
		this.message1 = message1;
	}

	/**
	 * @return the message2
	 */
	public String getMessage2() {
		return message2;
	}

	/**
	 * @param message2
	 *            the message2 to set
	 */
	public void setMessage2(String message2) {
		this.message2 = message2;
	}

	/**
	 * @return
	 */
	public Class initialize() {
		return null;
	}

	/**
	 * @return
	 */
	public Class prerender() {
		System.out.println(message1);
		System.out.println(message2);
		return null;
	}

}
