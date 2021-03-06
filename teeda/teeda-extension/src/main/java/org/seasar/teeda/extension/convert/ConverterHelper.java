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
package org.seasar.teeda.extension.convert;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

/**
 * @author shot
 * just a workaround class for target and UIInput.setValid(false).
 * see https://www.seasar.org/issues/browse/TEEDA-352
 */
public class ConverterHelper {

    public static boolean isTargetCommand(final FacesContext context,
            final UIComponent component, final String[] targets,
            final ConvertTargetSelectable convertTarget) {
        final boolean ret = convertTarget.isTargetCommandConvert(context,
                targets);
        if (!ret) {
            if (component instanceof UIInput) {
                ((UIInput) component).setValue(null);
            }
        }
        return ret;
    }
}
