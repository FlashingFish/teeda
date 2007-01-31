/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.teeda.extension.annotation.handler;

import org.seasar.framework.exception.ClassNotFoundRuntimeException;
import org.seasar.framework.util.ClassUtil;

/**
 * @author higa
 */
public class ScopeAnnotationHandlerFactory {

    private static final String TIGER_ANNOTATION_HANDLER_CLASS_NAME = "org.seasar.teeda.extension.annotation.handler.TigerScopeAnnotationHandler";

    private static ScopeAnnotationHandler annotationHandler;

    static {
        Class clazz = ConstantScopeAnnotationHandler.class;
        try {
            clazz = ClassUtil.forName(TIGER_ANNOTATION_HANDLER_CLASS_NAME);
        } catch (ClassNotFoundRuntimeException ignore) {
        }
        annotationHandler = (ScopeAnnotationHandler) ClassUtil
                .newInstance(clazz);
    }

    protected ScopeAnnotationHandlerFactory() {
    }

    public static ScopeAnnotationHandler getAnnotationHandler() {
        return annotationHandler;
    }

    public static void setAnnotationHandler(ScopeAnnotationHandler handler) {
        annotationHandler = handler;
    }
}