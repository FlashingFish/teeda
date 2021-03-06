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
package examples.teeda.ajax;


/**
 * @author mopemope
 */
public class AutoCompleteSampleBean {

    private static final String[] JSON_DATA = { "test1", "test2", "test3",
            "dummy1", "dummy2", "dummy3", "dummy4", "teeda1", "teeda2", "teeda3" };

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object autoComplete() {
        AutoCompleteDto dto = new AutoCompleteDto();
        for (int i = 0; i < JSON_DATA.length; i++) {
            if (JSON_DATA[i].indexOf(this.name) > -1) {
                dto.add(JSON_DATA[i]);
            }
        }
        return dto;
    }
    

}