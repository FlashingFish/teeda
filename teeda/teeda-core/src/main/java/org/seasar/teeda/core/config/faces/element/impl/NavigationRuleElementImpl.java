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
package org.seasar.teeda.core.config.faces.element.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.teeda.core.config.faces.element.NavigationCaseElement;
import org.seasar.teeda.core.config.faces.element.NavigationRuleElement;

/**
 * @author shot
 */
public class NavigationRuleElementImpl implements NavigationRuleElement {

    private String fromViewId_;

    private List navigationCases_ = new ArrayList();

    public NavigationRuleElementImpl() {
    }

    public void setFromViewId(String fromViewId) {
        fromViewId_ = fromViewId;
    }

    public String getFromViewId() {
        return fromViewId_;
    }

    public void addNavigationCase(NavigationCaseElement navigationCase) {
        navigationCases_.add(navigationCase);
    }

    public List getNavigationCaseElements() {
        return navigationCases_;
    }
}
