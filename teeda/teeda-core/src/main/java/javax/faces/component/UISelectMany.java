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
package javax.faces.component;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.internal.FacesMessageUtil;
import javax.faces.internal.SelectItemsIterator;

import org.seasar.framework.util.ArrayUtil;
import org.seasar.framework.util.AssertionUtil;

/**
 * @author shot
 * @author manhole
 */
public class UISelectMany extends UIInput {

    public static final String COMPONENT_FAMILY = "javax.faces.SelectMany";

    public static final String COMPONENT_TYPE = "javax.faces.SelectMany";

    public static final String INVALID_MESSAGE_ID = "javax.faces.component.UISelectMany.INVALID";

    private static final String DEFAULT_RENDER_TYPE = "javax.faces.Listbox";

    public UISelectMany() {
        setRendererType(DEFAULT_RENDER_TYPE);
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public Object[] getSelectedValues() {
        return (Object[]) getValue();
    }

    public void setSelectedValues(Object[] selectedValues) {
        setValue(selectedValues);
    }

    public ValueBinding getValueBinding(String name) {
        return super.getValueBinding(convertAlias(name));
    }

    public void setValueBinding(String name, ValueBinding binding) {
        super.setValueBinding(convertAlias(name), binding);
    }

    private String convertAlias(String name) {
        if ("selectedValues".equals(name)) {
            return "value";
        }
        return name;
    }

    protected boolean compareValues(Object previous, Object value) {
        if (previous == null && value == null) {
            return false;
        }
        if (previous == null || value == null) {
            return true;
        }
        if ((previous instanceof List) && (value instanceof List)) {
            previous = ((List) previous).toArray();
            value = ((List) value).toArray();
        }
        if (!isArray(previous) && !isArray(value)) {
            return !previous.equals(value);
        }
        if (!isArray(previous) || !isArray(value)) {
            return true;
        }
        Object oldArray[] = toObjectArray(previous);
        Object newArray[] = toObjectArray(value);
        if (oldArray.length != newArray.length) {
            return true;
        }

        for (int oldCounts = 0, newCounts = 0, i = 0; i < oldArray.length; ++i) {
            oldCounts = countElementOccurrence(oldArray[i], oldArray);
            newCounts = countElementOccurrence(oldArray[i], newArray);
            if (oldCounts != newCounts) {
                return true;
            }
        }
        return false;
    }

    private boolean isArray(Object o) {
        return o.getClass().isArray();
    }

    protected void validateValue(FacesContext context, Object value) {
        super.validateValue(context, value);
        if (!isValid() || (value == null)) {
            return;
        }
        boolean isList = (value instanceof List);
        int length = (isList) ? ((List) value).size() : Array.getLength(value);
        for (int i = 0; i < length; i++) {
            Iterator items = new SelectItemsIterator(this);
            Object indexValue = (isList) ? ((List) value).get(i) : Array.get(
                    value, i);
            if (!ComponentUtil_.valueMatches(indexValue, items)) {
                Object[] args = { getId() };
                FacesMessageUtil.addErrorComponentMessage(context, this,
                        INVALID_MESSAGE_ID, args);
                setValid(false);
                break;
            }
        }
    }

    private int countElementOccurrence(Object element, Object[] array) {
        int count = 0;
        for (int i = 0; i < array.length; ++i) {
            Object arrayElement = array[i];
            if (arrayElement == null && element == null) {
                count++;
            } else if (arrayElement != null && element != null
                    && arrayElement.equals(element)) {
                count++;
            }
        }
        return count;
    }

    private Object[] toObjectArray(Object obj) {
        AssertionUtil.assertNotNull("primitiveArray", obj);
        if (ComponentUtil_.isObjectArray(obj)) {
            return (Object[]) obj;
        } else if (obj instanceof List) {
            return ((List) obj).toArray();
            // } else if (!isArray(obj)) {
            // return null;
        }
        Object[] array = ArrayUtil.toObjectArray(obj);
        return array;
    }

}
