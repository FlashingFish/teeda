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
package org.seasar.teeda.core.util;

import org.seasar.framework.util.ResourceUtil;

/**
 * @author shot
 */
public class DiconConditionUtil {

    public static boolean hasConvention() {
        return ResourceUtil.getResourceNoException("convention.dicon") != null;
    }

    public static boolean hasErrorPageDicon() {
        return ResourceUtil.getResourceNoException("teedaErrorPage.dicon") != null;
    }

    public static boolean hasCustomizeDicon() {
        return ResourceUtil.getResourceNoException("teedaCustomize.dicon") != null;
    }

}
