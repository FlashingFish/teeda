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
import java.util.List;

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;

public class DIContainerUtil {

    private static final Logger logger_ = Logger
            .getLogger(DIContainerUtil.class);

    private DIContainerUtil() {
    }

    public static Object getComponentNoException(Object componentKey) {
        S2Container container = getContainer();
        if (container == null) {
            return null;
        }
        if (container.hasComponentDef(componentKey)) {
            return container.getComponent(componentKey);
        }
        return null;
    }

    public static ComponentDef getComponentDefNoException(Object componentKey) {
        if (getContainer().hasComponentDef(componentKey)) {
            return getContainer().getComponentDef(componentKey);
        }
        return null;
    }

    public static Object getComponent(Object componentKey) {
        return getContainer().getComponent(componentKey);
    }

    public static synchronized void register(ComponentDef componentDef) {
        if (logger_.isDebugEnabled()) {
            logger_.debug("componentDef name  = "
                    + componentDef.getComponentName());
            logger_.debug("componentDef class = "
                    + componentDef.getComponentClass());
        }
        getContainer().register(componentDef);
    }

    public static Object[] getComponentKeys(Class clazz) {
        ComponentDef[] componentDefs = getContainer().findComponentDefs(clazz);
        List list = new ArrayList();
        for (int i = 0; i < componentDefs.length; i++) {
            ComponentDef componentDef = componentDefs[i];
            list.add(componentDef.getComponentName());
        }
        return list.toArray();
    }

    public static boolean hasComponent(Object componentKey) {
        return getContainer().hasComponentDef(componentKey);
    }

    public static boolean hasContainer() {
        return SingletonS2ContainerFactory.hasContainer();
    }

    private static final Object[] EMPTY = new Object[0];

    public static Object[] findAllComponents(Object componentKey) {
        if (!hasContainer()) {
            return EMPTY;
        }
        return getContainer().findAllComponents(componentKey);
    }

    public static S2Container getContainer() {
        return SingletonS2ContainerFactory.getContainer();
    }

}
