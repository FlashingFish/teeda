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
package org.seasar.teeda.extension.util;

import java.io.IOException;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.internal.scope.RedirectScope;
import javax.faces.internal.scope.SubApplicationScope;
import javax.servlet.ServletRequest;

import org.seasar.framework.log.Logger;
import org.seasar.framework.util.AssertionUtil;
import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.core.exception.AlreadyRedirectingException;
import org.seasar.teeda.core.util.DIContainerUtil;
import org.seasar.teeda.core.util.NavigationHandlerUtil;
import org.seasar.teeda.core.util.ServletErrorPageManagerImpl;
import org.seasar.teeda.core.util.ServletExternalContextUtil;
import org.seasar.teeda.extension.html.PagePersistence;

/**
 * @author shot
 */
public class TeedaExtensionErrorPageManagerImpl extends
        ServletErrorPageManagerImpl {

    private static final Logger logger = Logger
            .getLogger(TeedaExtensionErrorPageManagerImpl.class);

    public boolean handleException(Throwable exception, FacesContext context,
            ExternalContext extContext) throws IOException {
        AssertionUtil.assertNotNull("exception", exception);
        if (logger.isDebugEnabled()) {
            logger.debug(exception);
        }
        final String location = getLocation(exception.getClass());
        if (location == null) {
            return false;
        }
        saveException(exception, context);
        ServletRequest request = ServletExternalContextUtil
                .getRequest(extContext);
        ServletExternalContextUtil
                .storeErrorInfoToAttribute(request, exception);
        SubApplicationScope.removeContext(context);
        PagePersistence pagePersistence = getPagePersistence();
        pagePersistence.save(context, location);
        String actionURL = location;
        if (location != null && location.startsWith("/")) {
            actionURL = extContext.getRequestContextPath() + location;
        }
        final String redirectingPath = RedirectScope
                .getRedirectingPath(context);
        if (RedirectScope.isRedirecting(context)
                && actionURL.equals(redirectingPath)) {
            throw new AlreadyRedirectingException();
        }
        NavigationHandlerUtil.redirect(context, actionURL);
        return true;
    }

    public static void saveException(Throwable exception, FacesContext context)
            throws IOException {
        AssertionUtil.assertNotNull("exception", exception);
        Map redirectScope = RedirectScope.getOrCreateContext(context);
        redirectScope.put(JsfConstants.ERROR_MANAGER_EXCEPTION_KEY, exception);
    }

    public static void restoreMessage(FacesContext context) {
        Map redirectScope = RedirectScope.getContext(context);
        if (redirectScope == null) {
            return;
        }
        Throwable exception = (Throwable) redirectScope
                .get(JsfConstants.ERROR_MANAGER_EXCEPTION_KEY);
        if (exception == null) {
            return;
        }
        String message = exception.getMessage();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                message, message));
    }

    protected PagePersistence getPagePersistence() {
        return (PagePersistence) DIContainerUtil
                .getComponentNoException(PagePersistence.class);
    }
}
