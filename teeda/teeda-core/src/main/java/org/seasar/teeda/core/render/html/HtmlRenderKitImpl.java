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
package org.seasar.teeda.core.render.html;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.faces.render.ResponseStateManager;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.AssertionUtil;
import org.seasar.teeda.core.application.ComponentLookupStrategy;
import org.seasar.teeda.core.context.html.HtmlResponseWriter;
import org.seasar.teeda.core.render.AbstractRenderKit;
import org.seasar.teeda.core.render.html.support.HtmlRenderKitKeyGenerateUtil;
import org.seasar.teeda.core.util.HtmlRenderKitUtil;

/**
 * @author shot
 * @author manhole
 */
public class HtmlRenderKitImpl extends AbstractRenderKit {

    private static String DEFAULT_CONTENTTYPE = "text/html";

    private ResponseStateManager responseStateManager;

    private S2Container container;

    private ComponentLookupStrategy componentLookupStrategy;

    public HtmlRenderKitImpl() {
    }

    public void addRenderer(String family, String renderType, Renderer renderer) {
        AssertionUtil.assertNotNull("family", family);
        AssertionUtil.assertNotNull("renderType", renderType);
        AssertionUtil.assertNotNull("renderer", renderer);
        String key = HtmlRenderKitKeyGenerateUtil.getGeneratedKey(family,
                renderType);
        getContainer().register(renderer, key);
    }

    public Renderer getRenderer(String family, String renderType) {
        AssertionUtil.assertNotNull("family", family);
        AssertionUtil.assertNotNull("renderType", renderType);
        String key = HtmlRenderKitKeyGenerateUtil.getGeneratedKey(family,
                renderType);
        return (Renderer) componentLookupStrategy.getComponentByName(key);
    }

    public ResponseStream createResponseStream(final OutputStream out) {
        return new ResponseStream() {

            public void write(int b) throws IOException {
                out.write(b);
            }

            public void close() throws IOException {
                out.close();
            }

            public void flush() throws IOException {
                out.flush();
            }

            public void write(byte[] bytes) throws IOException {
                out.write(bytes, 0, bytes.length);
            }

            public void write(byte[] bytes, int off, int len)
                    throws IOException {
                out.write(bytes, off, len);
            }

        };
    }

    public ResponseWriter createResponseWriter(Writer writer,
            String contentTypeList, String characterEncoding) {
        HtmlResponseWriter htmlResponseWriter = new HtmlResponseWriter();
        htmlResponseWriter.setWriter(writer);
        String contentType = HtmlRenderKitUtil.getContentType(contentTypeList);
        if (contentType == null) {
            contentType = DEFAULT_CONTENTTYPE;
        }
        htmlResponseWriter.setContentType(contentType);
        htmlResponseWriter.setCharacterEncoding(characterEncoding);
        return htmlResponseWriter;
    }

    public ResponseStateManager getResponseStateManager() {
        return responseStateManager;
    }

    public void setResponseStateManager(
            ResponseStateManager responseStateManager) {
        this.responseStateManager = responseStateManager;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public S2Container getContainer() {
        return container;
    }

    public ComponentLookupStrategy getComponentLookupStrategy() {
        return componentLookupStrategy;
    }

    public void setComponentLookupStrategy(
            ComponentLookupStrategy componentLookupStrategy) {
        this.componentLookupStrategy = componentLookupStrategy;
    }

}
