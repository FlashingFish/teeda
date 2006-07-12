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
package org.seasar.teeda.core.config.faces.element.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.seasar.framework.util.LocaleUtil;
import org.seasar.teeda.core.config.faces.element.LocaleConfigElement;

/**
 * @author shot
 */
public class LocaleConfigElementImpl implements LocaleConfigElement {

    private Locale defaultLocale_;

    private List supportedLocales_;

    public LocaleConfigElementImpl() {
        supportedLocales_ = new ArrayList();
    }

    public void setDefaultLocale(String defaultLocaleName) {
        defaultLocale_ = LocaleUtil.getLocale(defaultLocaleName);
    }

    public Locale getDefaultLocale() {
        return defaultLocale_;
    }

    public void addSupportedLocale(String supportedLocaleName) {
        Locale locale = LocaleUtil.getLocale(supportedLocaleName);
        supportedLocales_.add(locale);
    }

    public List getSupportedLocales() {
        return supportedLocales_;
    }

}
