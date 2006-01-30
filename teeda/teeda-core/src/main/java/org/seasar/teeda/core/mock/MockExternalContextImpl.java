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
package org.seasar.teeda.core.mock;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.FacesException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.framework.mock.servlet.MockHttpServletRequestImpl;
import org.seasar.framework.mock.servlet.MockHttpServletResponse;
import org.seasar.framework.mock.servlet.MockHttpServletResponseImpl;
import org.seasar.framework.mock.servlet.MockServletContext;
import org.seasar.framework.mock.servlet.MockServletContextImpl;

public class MockExternalContextImpl extends MockExternalContext {

    private MockServletContext mockServletContext_;

    private MockHttpServletRequest mockHttpServletRequest_;

    private HttpServletResponse httpServletResponse_;

    private Map applicationMap_;

    private Map requestParameterMap_;

    private Map requestCookieMap_;

    private Map sessionMap_;

    private Map requestMap_;

    public MockExternalContextImpl() {
    }

    public MockExternalContextImpl(MockServletContext context,
            MockHttpServletRequest request, MockHttpServletResponse response) {
        mockServletContext_ = context;
        mockHttpServletRequest_ = request;
        httpServletResponse_ = response;
        applicationMap_ = new MockApplicationMap(mockServletContext_);
    }

    public void addRequestCookieMap(Cookie cookie) {
        requestParameterMap_.put(cookie.getName(), cookie);
    }

    public void setRequestCookieMap(Map map) {
        requestParameterMap_ = map;
    }

    public void addRequestParameterMap(String key, String value) {
        requestParameterMap_.put(key, value);
    }

    public void setRequestParameterMap(Map map) {
        requestParameterMap_ = map;
    }

    public void dispatch(String requestURI) throws IOException, FacesException {
        throw new UnsupportedOperationException();
    }

    public String encodeActionURL(String sb) {
        throw new UnsupportedOperationException();
    }

    public String encodeNamespace(String aValue) {
        throw new UnsupportedOperationException();
    }

    public String encodeResourceURL(String url) {
        return getHttpServletResponse().encodeURL(url);
    }

    public Map getApplicationMap() {
        return applicationMap_;
    }

    public String getAuthType() {
        return getHttpServletRequest().getAuthType();
    }

    public Object getContext() {
        return getMockServletContext();
    }

    MockServletContext getMockServletContext() {
        if (mockServletContext_ == null) {
            mockServletContext_ = new MockServletContextImpl("/mock-context");
        }
        return mockServletContext_;
    }

    public String getInitParameter(String name) {
        return getMockServletContext().getInitParameter(name);
    }

    public Map getInitParameterMap() {
        Map parameterMap = new HashMap();
        Enumeration names = getMockServletContext().getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            parameterMap.put(name, getMockServletContext().getInitParameter(
                    name));
        }
        return Collections.unmodifiableMap(parameterMap);
    }

    public String getRemoteUser() {
        return getHttpServletRequest().getRemoteUser();
    }

    public Object getRequest() {
        return getHttpServletRequest();
    }

    MockHttpServletRequest getHttpServletRequest() {
        if (mockHttpServletRequest_ == null) {
            mockHttpServletRequest_ = new MockHttpServletRequestImpl(
                    getMockServletContext(), "/mock-path.html");
        }
        return mockHttpServletRequest_;
    }

    public String getRequestContextPath() {
        return getHttpServletRequest().getContextPath();
    }

    public Map getRequestCookieMap() {
        return requestCookieMap_;
    }

    public Map getRequestHeaderMap() {
        throw new UnsupportedOperationException();
    }

    public Map getRequestHeaderValuesMap() {
        throw new UnsupportedOperationException();
    }

    public Locale getRequestLocale() {
        return getHttpServletRequest().getLocale();
    }

    public Iterator getRequestLocales() {
        return new LocalesIterator(getHttpServletRequest().getLocales());
    }

    public Map getRequestMap() {
        if (requestMap_ == null) {
            requestMap_ = new HashMap();
        }
        return requestMap_;
    }

    public Map getRequestParameterMap() {
        if (requestParameterMap_ == null) {
            requestParameterMap_ = new HashMap();
        }
        return requestParameterMap_;
    }

    public Iterator getRequestParameterNames() {
        throw new UnsupportedOperationException();
    }

    public Map getRequestParameterValuesMap() {
        throw new UnsupportedOperationException();
    }

    public String getRequestPathInfo() {
        return getHttpServletRequest().getPathInfo();
    }

    public String getRequestServletPath() {
        return getHttpServletRequest().getServletPath();
    }

    public URL getResource(String path) throws MalformedURLException {
        return getMockServletContext().getResource(path);
    }

    public InputStream getResourceAsStream(String path) {
        return getMockServletContext().getResourceAsStream(path);
    }

    public Set getResourcePaths(String path) {
        return getMockServletContext().getResourcePaths(path);
    }

    public Object getResponse() {
        return getHttpServletResponse();
    }

    HttpServletResponse getHttpServletResponse() {
        if (httpServletResponse_ == null) {
            httpServletResponse_ = new MockHttpServletResponseImpl(
                    getHttpServletRequest());
        }
        return httpServletResponse_;
    }

    public Object getSession(boolean create) {
        return getHttpServletRequest().getSession(create);
    }

    public Map getSessionMap() {
        if (sessionMap_ == null) {
            HttpSession session = getHttpServletRequest().getSession(true);
            sessionMap_ = new MockSessionMap(session);
        }
        return sessionMap_;
    }

    public java.security.Principal getUserPrincipal() {
        return getHttpServletRequest().getUserPrincipal();
    }

    public boolean isUserInRole(String role) {
        return getHttpServletRequest().isUserInRole(role);
    }

    public void log(String message) {
        getMockServletContext().log(message);
    }

    public void log(String message, Throwable throwable) {
        getMockServletContext().log(message, throwable);
    }

    public void redirect(String requestURI) throws IOException {
        throw new UnsupportedOperationException();
    }

    private class LocalesIterator implements Iterator {
        public LocalesIterator(Enumeration locales) {
            this.locales = locales;
        }

        private Enumeration locales;

        public boolean hasNext() {
            return locales.hasMoreElements();
        }

        public Object next() {
            return locales.nextElement();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
