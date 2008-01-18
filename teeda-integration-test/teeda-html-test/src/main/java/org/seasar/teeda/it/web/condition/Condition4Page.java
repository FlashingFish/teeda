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
package org.seasar.teeda.it.web.condition;

public class Condition4Page {

	public Integer aaa;
	public Integer[] aaaItems;
	public int aaaIndex;
	public Integer selected;

	public boolean isDisp() {
		return aaaIndex % 2 == 0;
	}

	public void initialize() {
		aaaItems = new Integer[] { 0 };
	}

	public void doFoo() {
		selected = aaaIndex;
		aaaItems = new Integer[aaaItems.length + 1];
		for (int i = 0; i < aaaItems.length; ++i) {
			aaaItems[i] = i;
		}
	}

}