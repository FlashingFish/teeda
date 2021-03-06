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
package javax.faces.mock.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;

import org.seasar.framework.mock.servlet.MockHttpSessionImpl;
import org.seasar.framework.mock.servlet.MockRequestDispatcherImpl;
import org.seasar.framework.util.EnumerationAdapter;

/**
 * @author shot
 */
public class MockServletRequestImpl implements MockServletRequest {

    private ServletContext servletContext_;

    private String servletPath_;

    private String authType_;

    private List cookieList = new ArrayList();

    private Map headers_ = new HashMap();

    private String method_ = "POST";

    private String pathInfo_;

    private String pathTranslated_;

    private String queryString_;

    private MockHttpSessionImpl session_;

    private String scheme_ = "http";

    private int serverPort_ = 80;

    private String protocol_ = "HTTP/1.1";

    private String serverName_ = "localhost";

    private Map attributes_ = new HashMap();

    private String characterEncoding_ = DEFAULT_CHARACTER_ENCODING;

    private int contentLength_;

    private String contentType_;

    private Map parameters_ = new HashMap();

    private String remoteAddr_;

    private String remoteHost_;

    private int remotePort_;

    private String localAddr_;

    private String localName_;

    private int localPort_;

    private Locale locale_;

    public MockServletRequestImpl(ServletContext servletContext,
            String servletPath) {

        servletContext_ = servletContext;
        if (servletPath.charAt(0) == '/') {
            servletPath_ = servletPath;
        } else {
            servletPath_ = "/" + servletPath;
        }
    }

    /**
     * @see javax.servlet.ServletRequest#getAttribute(java.lang.String)
     */
    public Object getAttribute(String name) {
        return attributes_.get(name);
    }

    /**
     * @see javax.servlet.ServletRequest#getAttributeNames()
     */
    public Enumeration getAttributeNames() {
        return new EnumerationAdapter(attributes_.keySet().iterator());
    }

    /**
     * @see javax.servlet.ServletRequest#setAttribute(java.lang.String,
     *      java.lang.Object)
     */
    public void setAttribute(String name, Object value) {
        attributes_.put(name, value);
    }

    /**
     * @see javax.servlet.ServletRequest#removeAttribute(java.lang.String)
     */
    public void removeAttribute(String name) {
        attributes_.remove(name);
    }

    /**
     * @see javax.servlet.ServletRequest#getCharacterEncoding()
     */
    public String getCharacterEncoding() {
        return characterEncoding_;
    }

    /**
     * @see javax.servlet.ServletRequest#setCharacterEncoding(java.lang.String)
     */
    public void setCharacterEncoding(String characterEncoding)
            throws UnsupportedEncodingException {

        characterEncoding_ = characterEncoding;
    }

    /**
     * @see javax.servlet.ServletRequest#getContentLength()
     */
    public int getContentLength() {
        return contentLength_;
    }

    public void setContentLength(int contentLength) {
        contentLength_ = contentLength;
    }

    /**
     * @see javax.servlet.ServletRequest#getContentType()
     */
    public String getContentType() {
        return contentType_;
    }

    public void setContentType(String contentType) {
        contentType_ = contentType;
    }

    /**
     * @see javax.servlet.ServletRequest#getInputStream()
     */
    public ServletInputStream getInputStream() throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * @see javax.servlet.ServletRequest#getParameter(java.lang.String)
     */
    public String getParameter(String name) {
        String[] values = (String[]) parameters_.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * @see javax.servlet.ServletRequest#getParameterNames()
     */
    public Enumeration getParameterNames() {
        return new EnumerationAdapter(parameters_.keySet().iterator());
    }

    /**
     * 
     * @see javax.servlet.ServletRequest#getParameterValues(java.lang.String)
     */
    public String[] getParameterValues(String name) {
        return (String[]) parameters_.get(name);
    }

    /**
     * 
     * @see javax.servlet.ServletRequest#getParameterMap()
     */
    public Map getParameterMap() {
        return parameters_;
    }

    public void addParameter(String name, String value) {
        String[] values = getParameterValues(name);
        if (values == null) {
            setParameter(name, value);
        } else {
            String[] newArray = new String[values.length + 1];
            System.arraycopy(values, 0, newArray, 0, values.length);
            newArray[newArray.length - 1] = value;
            parameters_.put(name, newArray);
        }
    }

    public void addParameter(String name, String[] values) {
        if (values == null) {
            setParameter(name, (String) null);
            return;
        }
        String[] vals = getParameterValues(name);
        if (vals == null) {
            setParameter(name, values);
        } else {
            String[] newArray = new String[vals.length + values.length];
            System.arraycopy(vals, 0, newArray, 0, vals.length);
            System.arraycopy(values, 0, newArray, vals.length, values.length);
            parameters_.put(name, newArray);
        }
    }

    public void setParameter(String name, String value) {
        parameters_.put(name, new String[] { value });
    }

    public void setParameter(String name, String[] values) {
        parameters_.put(name, values);
    }

    /**
     * @see javax.servlet.ServletRequest#getProtocol()
     */
    public String getProtocol() {
        return protocol_;
    }

    public void setProtocol(String protocol) {
        protocol_ = protocol;
    }

    /**
     * @see javax.servlet.ServletRequest#getScheme()
     */
    public String getScheme() {
        return scheme_;
    }

    public void setScheme(String scheme) {
        scheme_ = scheme;
    }

    /**
     * @see javax.servlet.ServletRequest#getServerName()
     */
    public String getServerName() {
        return serverName_;
    }

    public void setServerName(String serverName) {
        serverName_ = serverName;
    }

    /**
     * @see javax.servlet.ServletRequest#getServerPort()
     */
    public int getServerPort() {
        return serverPort_;
    }

    public void setServerPort(int serverPort) {
        serverPort_ = serverPort;
    }

    /**
     * 
     * @see javax.servlet.ServletRequest#getReader()
     */
    public BufferedReader getReader() throws IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @see javax.servlet.ServletRequest#getRemoteAddr()
     */
    public String getRemoteAddr() {
        return remoteAddr_;
    }

    public void setRemoteAddr(String remoteAddr) {
        remoteAddr_ = remoteAddr;
    }

    /**
     * 
     * @see javax.servlet.ServletRequest#getRemoteHost()
     */
    public String getRemoteHost() {
        return remoteHost_;
    }

    public void setRemoteHost(String remoteHost) {
        remoteHost_ = remoteHost;
    }

    /**
     * @see javax.servlet.ServletRequest#getLocalAddr()
     */
    public String getLocalAddr() {
        return localAddr_;
    }

    public void setLocalAddr(String localAddr) {
        localAddr_ = localAddr;
    }

    /**
     * @see javax.servlet.ServletRequest#getLocalName()
     */
    public String getLocalName() {
        return localName_;
    }

    public void setLocalName(String localName) {
        localName_ = localName;
    }

    /**
     * @see javax.servlet.ServletRequest#getLocalPort()
     */
    public int getLocalPort() {
        return localPort_;
    }

    public void setLocalPort(int localPort) {
        localPort_ = localPort;
    }

    /**
     * @see javax.servlet.ServletRequest#getRemotePort()
     */
    public int getRemotePort() {
        return remotePort_;
    }

    public void setRemotePort(int remotePort) {
        remotePort_ = remotePort;
    }

    /**
     * 
     * @see javax.servlet.ServletRequest#getLocale()
     */
    public Locale getLocale() {
        return locale_;
    }

    public void setLocale(Locale locale) {
        locale_ = locale;
    }

    /**
     * 
     * @see javax.servlet.ServletRequest#getLocales()
     */
    public Enumeration getLocales() {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @see javax.servlet.ServletRequest#isSecure()
     */
    public boolean isSecure() {
        return false;
    }

    /**
     * 
     * @see javax.servlet.ServletRequest#getRequestDispatcher(java.lang.String)
     */
    public RequestDispatcher getRequestDispatcher(String path) {
        return new MockRequestDispatcherImpl();
    }

    /**
     * @deprecated
     * @see javax.servlet.ServletRequest#getRealPath(java.lang.String)
     */
    public String getRealPath(String path) {
        return path;
    }

}
