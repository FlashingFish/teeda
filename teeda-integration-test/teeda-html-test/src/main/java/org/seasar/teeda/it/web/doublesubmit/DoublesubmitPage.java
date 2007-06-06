/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.teeda.it.web.doublesubmit;

public class DoublesubmitPage {

	public static final String arg1_TGreaterThanConstantValidator = null;

	public static final String arg2_TGreaterThanConstantValidator = null;

	private int arg1;

	private int arg2;

	// never userd, just for readonly test
	private int arg3;

	private int result;

	public int getArg1() {
		return arg1;
	}

	public void setArg1(int arg1) {
		this.arg1 = arg1;
	}

	public int getArg2() {
		return arg2;
	}

	public void setArg2(int arg2) {
		this.arg2 = arg2;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String doOnceCalculate() {
		result = arg1 + arg2;
		return null;
	}

	public int getArg3() {
		return arg3;
	}

	public void setArg3(int arg3) {
		this.arg3 = arg3;
	}
}