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
package org.seasar.teeda.extension.html;

import javax.faces.context.FacesContext;

/**
 * @author higa
 * @author shot
 */
public interface PagePersistence {

    String SUBAPPLICATION_SCOPE_KEY = PagePersistence.class.getName()
            + ".SUBAPPLICATION";

    String REDIRECT_SCOPE_KEY = PagePersistence.class.getName() + ".REDIRECT";

    String PAGE_SCOPE_KEY = PagePersistence.class.getName() + ".PAGE";

    void save(FacesContext context, String viewId);

    void restore(FacesContext context, String viewId);

}