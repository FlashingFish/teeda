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
package org.seasar.teeda.core.config.faces.element;

/**
 * @author shot
 */
public interface NavigationCaseElement extends JsfConfigElement {

    public void setFromAction(String fromAction);

    public void setFromOutcome(String outcome);

    public void setToViewId(String toViewId);

    public void setRedirect(String dummy);

    public String getFromAction();

    public String getFromOutcome();

    public String getToViewId();

    public boolean isRedirect();

}
