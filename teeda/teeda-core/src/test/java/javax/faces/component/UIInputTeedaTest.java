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
package javax.faces.component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.internal.ConverterResource;
import javax.faces.internal.ValidatorResource;
import javax.faces.render.RenderKitFactory;
import javax.faces.validator.DoubleRangeValidator;
import javax.faces.validator.LengthValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.seasar.teeda.core.JsfConstants;
import org.seasar.teeda.core.mock.MockValueBinding;

/**
 * @author manhole
 */
public class UIInputTeedaTest extends UIOutputTeedaTest {

    public void tearDown() {
        ConverterResource.removeAll();
        ValidatorResource.removeAll();
    }

    public void testSaveAndRestoreState() throws Exception {
        super.testSaveAndRestoreState();

        UIInput input1 = createUIInput();
        {
            final LengthValidator lengthValidator = new LengthValidator();
            lengthValidator.setMaximum(33);
            input1.addValidator(lengthValidator);

            final DoubleRangeValidator doubleRangeValidator = new DoubleRangeValidator();
            doubleRangeValidator.setMinimum(10);
            doubleRangeValidator.setMaximum(21);
            input1.addValidator(doubleRangeValidator);
        }
        final FacesContext context = getFacesContext();
        final Object decoded = serializeAndDeserialize(input1
                .saveState(context));

        final UIInput input2 = createUIInput();
        input2.restoreState(context, decoded);

        final Validator[] validators = input2.getValidators();
        assertEquals(2, validators.length);
        final LengthValidator lengthValidator = (LengthValidator) validators[0];
        assertEquals(33, lengthValidator.getMaximum());

        final DoubleRangeValidator doubleRangeValidator = (DoubleRangeValidator) validators[1];
        assertEquals(10.0, doubleRangeValidator.getMinimum(), 0);
        assertEquals(21.0, doubleRangeValidator.getMaximum(), 0);

        assertEquals(input1.getValue(), input2.getValue());
    }

    public final void testHandleValidationException() throws Exception {
        // ## Arrange ##
        UIInput input = new UIInput();
        input.setId("id");
        ValidatorException ve = new ValidatorException(new FacesMessage("aaa"));
        FacesContext context = getFacesContext();
        UIViewRoot root = new UIViewRoot();
        root.setRenderKitId(RenderKitFactory.HTML_BASIC_RENDER_KIT);
        context.setViewRoot(root);

        // ## Act ##
        input.handleValidationException(context, ve);

        // ## Assert ##
        Iterator itr = context.getMessages();
        Object o = itr.next();
        assertTrue(o instanceof FacesMessage);
        FacesMessage message = (FacesMessage) o;
        assertEquals("aaa", message.getSummary());
    }

    public void testValidateValue() throws Exception {
        // ## Arrange ##
        UIInput input = new UIInput();
        input.setValid(true);
        input.setRequired(true);

        MockValueBinding vb = new MockValueBinding();
        vb.setExpressionString("#{a.name}");
        input.setValueBinding("value", vb);

        register(LengthValidator.class, "lengthValidator");
        Map map = new HashMap();
        map.put("maximum", new Integer(5));
        map.put("minimum", new Integer(2));
        ValidatorResource.addValidator("#{a.name}", "lengthValidator", map);

        input.validateValue(getFacesContext(), new Integer(6));

        Iterator itr = getFacesContext().getMessages();
        Object o = itr.next();
        assertTrue(o instanceof FacesMessage);
        FacesMessage message = (FacesMessage) o;
        assertNotNull(message);
        assertEquals(FacesMessage.SEVERITY_ERROR, message.getSeverity());
    }

    public void testValidate() throws Exception {
        FacesContext context = getFacesContext();
        UIInput input = createUIInput();
        input.setId("aaa");
        input.getAttributes().put(JsfConstants.LABEL_ATTR, "hoge");
        input.setSubmittedValue("");
        register(new Converter() {

            public Object getAsObject(FacesContext context,
                    UIComponent component, String value)
                    throws ConverterException {
                return "";
            }

            public String getAsString(FacesContext context,
                    UIComponent component, Object value)
                    throws ConverterException {
                return "";
            }

        }, "converter");
        ConverterResource.addConverter("#{hoge}", "converter");
        input.setValueBinding("value", new MockValueBinding() {
            public String getExpressionString() {
                return "#{hoge}";
            }
        });
        input.setValid(true);
        input.setRequired(true);
        input.validate(context);

        Iterator messages = context.getMessages();
        assertTrue(messages != null);

        FacesMessage next = (FacesMessage) messages.next();
        assertTrue(next != null);

        String detail = next.getDetail();
        assertTrue(detail.indexOf("hoge") >= 0);
    }

    private UIInput createUIInput() {
        return (UIInput) createUIComponent();
    }

    protected UIComponent createUIComponent() {
        return new UIInput();
    }

}
