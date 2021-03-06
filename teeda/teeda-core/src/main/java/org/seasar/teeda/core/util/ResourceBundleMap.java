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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

public class ResourceBundleMap implements Map {

    private final ResourceBundle bundle;

    private List values;

    public ResourceBundleMap(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(Object key) {
        boolean result = false;
        if (key != null) {
            result = (bundle.getObject(key.toString()) != null);
        }
        return result;
    }

    public int hashCode() {
        return bundle.hashCode();
    }

    public boolean isEmpty() {
        boolean result = true;
        Enumeration keys = bundle.getKeys();
        result = !keys.hasMoreElements();
        return result;
    }

    public Collection values() {
        if (values == null) {
            values = new ArrayList();
            Enumeration keys = bundle.getKeys();
            while (keys.hasMoreElements()) {
                values.add(bundle.getString((String) keys.nextElement()));
            }
        }
        return values;
    }

    public int size() {
        return values().size();
    }

    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    public Set entrySet() {
        HashMap mappings = new HashMap();
        Enumeration keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = bundle.getObject((String) key);
            mappings.put(key, value);
        }
        return mappings.entrySet();
    }

    public boolean equals(Object obj) {
        if ((obj == null) || !(obj instanceof Map)) {
            return false;
        }
        if (entrySet().equals(((Map) obj).entrySet())) {
            return true;
        }
        return false;
    }

    public Object get(Object key) {
        if (key == null) {
            return null;
        }
        Object result = null;
        try {
            result = bundle.getObject(key.toString());
        } catch (MissingResourceException e) {
            result = "???" + key + "???";
        }
        return result;
    }

    public Set keySet() {
        Set set = new HashSet();
        for (Enumeration enumer = bundle.getKeys(); enumer.hasMoreElements();) {
            set.add(enumer.nextElement());
        }
        return set;
    }

    public Object remove(Object key) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    public Object put(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

}
