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
package org.seasar.teeda.core.mock;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;

public class MockLifecycleFactory extends LifecycleFactory {

    private Map lifecycles_ = new HashMap();

    public MockLifecycleFactory() {
        addLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE,
                new MockLifecycleImpl());
    }

    public void addLifecycle(String lifecycleId, Lifecycle lifecycle) {
        lifecycles_.put(lifecycleId, lifecycle);
    }

    public Lifecycle getLifecycle(String lifecycleId) {
        return (Lifecycle) lifecycles_.get(lifecycleId);
    }

    public Iterator getLifecycleIds() {
        return lifecycles_.keySet().iterator();
    }

}
