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
package org.seasar.teeda.extension.html.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.FileInputStreamUtil;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.extension.html.HtmlDesc;
import org.seasar.teeda.extension.html.HtmlDescCache;
import org.seasar.teeda.extension.html.HtmlNode;
import org.seasar.teeda.extension.html.HtmlParser;

/**
 * @author higa
 *
 */
public class HtmlDescCacheImpl implements HtmlDescCache {

    private Map htmlDescs = new HashMap();

    private ServletContext servletContext;

    private HtmlParser htmlParser;

    private S2Container container;

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public synchronized HtmlDesc getHtmlDesc(String viewId) {
        return (HtmlDesc) htmlDescs.get(viewId);
    }

    public synchronized HtmlDesc createHtmlDesc(String viewId) {
        HtmlDesc htmlDesc = null;
        String realPath = servletContext.getRealPath(viewId);
        if (realPath != null) {
            File file = new File(realPath);
            if (file.exists()) {
                htmlDesc = createHtmlDescFromRealPath(file, viewId);
            }
        }
        if (htmlDesc == null) {
            htmlDesc = createHtmlDescFromResource(viewId);
        }
        htmlDescs.put(viewId, htmlDesc);
        return htmlDesc;
    }

    protected HtmlDesc createHtmlDescFromRealPath(File file, String viewId) {
        InputStream is = new BufferedInputStream(FileInputStreamUtil
                .create(file));
        return createHtmlDesc(is, file, viewId);
    }

    protected HtmlDesc createHtmlDescFromResource(String viewId) {
        InputStream is = servletContext.getResourceAsStream(viewId);
        if (is == null) {
            throw new IllegalArgumentException(viewId);
        }
        return createHtmlDesc(is, null, viewId);
    }

    private HtmlDesc createHtmlDesc(InputStream is, File file, String viewId) {
        HtmlNode htmlNode = null;
        HttpServletRequest request = (HttpServletRequest) container.getRoot()
                .getExternalContext().getRequest();
        String encoding = request.getCharacterEncoding();
        if (encoding == null) {
            encoding = JsfConstants.DEFAULT_ENCODING;
        }
        htmlParser.setEncoding(encoding);
        try {
            htmlNode = htmlParser.parse(is, viewId);
        } finally {
            InputStreamUtil.close(is);
        }
        return new HtmlDescImpl(htmlNode, file);
    }

    public HtmlParser getHtmlParser() {
        return htmlParser;
    }

    public void setHtmlParser(HtmlParser htmlParser) {
        this.htmlParser = htmlParser;
    }

}