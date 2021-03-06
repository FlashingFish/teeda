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
package examples.teeda.web.add;

import org.seasar.teeda.extension.annotation.validator.GreaterThanConstant;

public class AddPage {

	@GreaterThanConstant
	private int arg1;

	@GreaterThanConstant
	private int arg2;

	private int result;

	public void initialize() {
		System.out.println("add init");
	}

	public void prerender() {
		System.out.println("add prerender");
	}

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

	public boolean isArg1Disabled() {
		return false;
	}

}