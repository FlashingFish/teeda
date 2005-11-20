package org.seasar.teeda.core.context.servlet;

import javax.faces.context.ExternalContext;
import javax.faces.mock.servlet.MockServletRequestImpl;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.mock.servlet.MockHttpServletRequest;
import org.seasar.framework.mock.servlet.MockHttpServletRequestImpl;


public class TestServletExternalContextImpl extends S2TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestServletExternalContextImpl.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for TestServletExternalContextImpl.
     * @param arg0
     */
    public TestServletExternalContextImpl(String arg0) {
        super(arg0);
    }

    public void testGetRequestServletPath() {
        MockHttpServletRequest request = 
            new MockHttpServletRequestImpl(getServletContext(), "/hoge");
        ExternalContext context = 
            new ServletExternalContextImpl(getServletContext(), request, getResponse());
        assertEquals("/hoge", context.getRequestServletPath());
        
        ServletRequest request2 = new MockServletRequestImpl(getServletContext(), "/hoge");
        ExternalContext context2 = 
            new ServletExternalContextImpl(getServletContext(), request2, (ServletResponse)getResponse());
        assertNull(context2.getRequestServletPath());
        
    }

    public void testGetRequestPathInfo() {
        MockHttpServletRequest request = 
            new MockHttpServletRequestImpl(getServletContext(), "/");
        request.setPathInfo("hoge");
        ExternalContext context = 
            new ServletExternalContextImpl(getServletContext(), request, getResponse());
        assertEquals("hoge", context.getRequestPathInfo());

        ServletRequest request2 = new MockServletRequestImpl(getServletContext(), "/hoge");
        ExternalContext context2 = 
            new ServletExternalContextImpl(getServletContext(), request2, (ServletResponse)getResponse());
        assertNull(context2.getRequestPathInfo());
    }

    public void testGetAuthType() {
        //TODO getAuthType() ���������܂��B
    }

    public void testGetContext() {
        //TODO getContext() ���������܂��B
    }

    public void testGetInitParameter() {
        //TODO getInitParameter() ���������܂��B
    }

    public void testGetRemoteUser() {
        //TODO getRemoteUser() ���������܂��B
    }

    public void testGetRequest() {
        //TODO getRequest() ���������܂��B
    }

    public void testGetRequestContextPath() {
        //TODO getRequestContextPath() ���������܂��B
    }

    public void testGetRequestCookieMap() {
        //TODO getRequestCookieMap() ���������܂��B
    }

    public void testGetRequestHeaderMap() {
        //TODO getRequestHeaderMap() ���������܂��B
    }

    public void testGetRequestHeaderValuesMap() {
        //TODO getRequestHeaderValuesMap() ���������܂��B
    }

    public void testGetRequestLocale() {
        //TODO getRequestLocale() ���������܂��B
    }

    public void testGetRequestLocales() {
        //TODO getRequestLocales() ���������܂��B
    }

    public void testGetRequestMap() {
        //TODO getRequestMap() ���������܂��B
    }

    public void testGetRequestParameterMap() {
        //TODO getRequestParameterMap() ���������܂��B
    }

    public void testGetRequestParameterNames() {
        //TODO getRequestParameterNames() ���������܂��B
    }

    public void testGetRequestParameterValuesMap() {
        //TODO getRequestParameterValuesMap() ���������܂��B
    }

    public void testGetResourcePaths() {
        //TODO getResourcePaths() ���������܂��B
    }

    public void testGetResponse() {
        //TODO getResponse() ���������܂��B
    }

    public void testGetSession() {
        //TODO getSession() ���������܂��B
    }

    public void testGetSessionMap() {
        //TODO getSessionMap() ���������܂��B
    }

    public void testIsUserInRole() {
        //TODO isUserInRole() ���������܂��B
    }

    /*
     * void log �̃e�X�g���̃N���X(String)
     */
    public void testLogString() {
        //TODO log() ���������܂��B
    }

    /*
     * void log �̃e�X�g���̃N���X(String, Throwable)
     */
    public void testLogStringThrowable() {
        //TODO log() ���������܂��B
    }

    public void testRedirect() {
        //TODO redirect() ���������܂��B
    }

    public void testServletExternalContextImpl() {
        //TODO ServletExternalContextImpl() ���������܂��B
    }

    /*
     * URL getResource �̃e�X�g���̃N���X(String)
     */
    public void testGetResourceString() {
        //TODO getResource() ���������܂��B
    }

    /*
     * InputStream getResourceAsStream �̃e�X�g���̃N���X(String)
     */
    public void testGetResourceAsStreamString() {
        //TODO getResourceAsStream() ���������܂��B
    }

    /*
     * Principal getUserPrincipal �̃e�X�g���̃N���X()
     */
    public void testGetUserPrincipal() {
        //TODO getUserPrincipal() ���������܂��B
    }

    /*
     * java.net.URL getResource �̃e�X�g���̃N���X(String)
     */
    public void testGetResourceString1() {
        //TODO getResource() ���������܂��B
    }

    /*
     * java.io.InputStream getResourceAsStream �̃e�X�g���̃N���X(String)
     */
    public void testGetResourceAsStreamString1() {
        //TODO getResourceAsStream() ���������܂��B
    }

    /*
     * java.security.Principal getUserPrincipal �̃e�X�g���̃N���X()
     */
    public void testGetUserPrincipal1() {
        //TODO getUserPrincipal() ���������܂��B
    }

}
