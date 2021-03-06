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

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.convert.IntegerConverter;
import javax.faces.el.MethodBinding;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.internal.ConverterResource;
import javax.faces.internal.NormalConverterBuilderImpl;
import javax.faces.validator.Validator;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.impl.S2ContainerImpl;
import org.seasar.teeda.core.mock.MockMethodBinding;
import org.seasar.teeda.core.mock.MockUIComponentBase;
import org.seasar.teeda.core.mock.MockValueBinding;
import org.seasar.teeda.core.mock.NullValidator;
import org.seasar.teeda.core.mock.NullValueChangeListener;
import org.seasar.teeda.core.unit.ExceptionAssert;

/**
 * @author shot
 * @author manhole
 */
public class UIInputTest extends UIOutputTest {

    public void testValue() throws Exception {
        UIInput input = createUIInput();
        input.setValue("");
        MockValueBinding vb = new MockValueBinding();
        vb.setValue(getFacesContext(), "hoge");
        input.setValueBinding("value", vb);
        Object value = input.getValue();
        assertNull(value);
    }

    public final void testSetGetSubmittedValue() {
        UIInput input = createUIInput();
        input.setSubmittedValue("aaa");
        assertEquals("aaa", input.getSubmittedValue());
    }

    public final void testSetGetSubmittedValue_ValueBindingNotWork() {
        UIInput input = createUIInput();
        MockValueBinding vb = new MockValueBinding();
        vb.setValue(getFacesContext(), "b");
        input.setValueBinding("submittedValue", vb);
        assertEquals(null, input.getSubmittedValue());
    }

    public final void testSetGetLocalValueSet() {
        UIInput input = createUIInput();
        input.setLocalValueSet(true);
        assertEquals(true, input.isLocalValueSet());
    }

    public final void testSetValueToIsLocalValueSet() throws Exception {
        UIInput input = createUIInput();
        assertEquals(false, input.isLocalValueSet());
        input.setValue("aaaa");
        assertEquals(true, input.isLocalValueSet());
    }

    public final void testSetGetRequired() {
        UIInput input = createUIInput();
        input.setRequired(true);
        assertEquals(true, input.isRequired());
    }

    public final void testSetGetRequired_ValueBinding() {
        UIInput input = createUIInput();
        MockValueBinding vb = new MockValueBinding();
        vb.setValue(getFacesContext(), Boolean.TRUE);
        input.setValueBinding("required", vb);
        assertEquals(true, input.isRequired());
    }

    public final void testSetGetValid() {
        UIInput input = createUIInput();
        input.setValid(true);
        assertEquals(true, input.isValid());
    }

    public final void testSetGetValid_ValueBinding() {
        UIInput input = createUIInput();
        MockValueBinding vb = new MockValueBinding();
        vb.setValue(getFacesContext(), Boolean.TRUE);
        input.setValueBinding("valid", vb);
        assertEquals(true, input.isValid());
    }

    public final void testSetGetImmediate() {
        UIInput input = createUIInput();
        input.setImmediate(true);
        assertEquals(true, input.isImmediate());
    }

    public final void testSetGetImmediate_ValueBinding() {
        UIInput input = createUIInput();
        MockValueBinding vb = new MockValueBinding();
        vb.setValue(getFacesContext(), new Boolean(true));
        input.setValueBinding("immediate", vb);
        assertEquals(true, input.isImmediate());
    }

    public final void testSetGetValidator() {
        UIInput input = createUIInput();
        MethodBinding methodBinding = new MockMethodBinding();

        // ## Act & Assert ##
        input.setValidator(methodBinding);
        assertEquals(methodBinding, input.getValidator());
    }

    public final void testSetGetValueChangeListener() throws Exception {
        // ## Arrange ##
        UIInput input = createUIInput();
        MethodBinding methodBinding = new MockMethodBinding();

        // ## Act & Assert ##
        input.setValueChangeListener(methodBinding);
        assertEquals(methodBinding, input.getValueChangeListener());
    }

    public final void testBroadcast_PassToListener() throws Exception {
        // ## Arrange ##
        UIInput input = createUIInput();
        MockMethodBinding valueChangeMethod = new MockMethodBinding();
        input.setValueChangeListener(valueChangeMethod);
        ValueChangeEvent event = new ValueChangeEvent(
                new MockUIComponentBase(), "1", "2");

        // ## Act ##
        input.broadcast(event);

        // ## Assert ##
        assertEquals(true, valueChangeMethod.isInvokeCalled());
        assertEquals(1, valueChangeMethod.getInvokeParams().length);
        assertSame(event, valueChangeMethod.getInvokeParams()[0]);
    }

    public final void testCompareValues() throws Exception {
        // ## Arrange ##
        UIInput input = createUIInput();

        // ## Act & Assert ##
        assertEquals(false, input.compareValues("1", "1"));
        assertEquals(false, input.compareValues("1", new String("1")));
        assertEquals(false, input.compareValues(new Integer(1234), new Integer(
                1234)));

        assertEquals(false, input.compareValues("", ""));
        assertEquals(false, input.compareValues(null, null));

        assertEquals(true, input.compareValues("", null));
        assertEquals(true, input.compareValues(null, ""));

        assertEquals(true, input.compareValues("1", null));
        assertEquals(true, input.compareValues(null, "1"));
        assertEquals(true, input.compareValues("1", "2"));
        assertEquals(true, input.compareValues("2", "1"));
        assertEquals(true, input.compareValues(new Integer(1234), new Integer(
                1233)));
    }

    public final void testAddValidator_NullArg() throws Exception {
        UIInput input = createUIInput();
        try {
            input.addValidator(null);
            fail();
        } catch (NullPointerException npe) {
            ExceptionAssert.assertMessageExist(npe);
        }
    }

    public final void testAddGetRemoveValidators() throws Exception {
        UIInput input = createUIInput();
        assertEquals(0, input.getValidators().length);
        Validator v1 = new NullValidator();
        Validator v2 = new NullValidator();
        Validator v3 = new NullValidator();
        input.addValidator(v1);
        assertEquals(1, input.getValidators().length);
        input.addValidator(v2);
        assertEquals(2, input.getValidators().length);
        input.addValidator(v3);
        assertEquals(3, input.getValidators().length);

        input.removeValidator(v2);
        assertEquals(2, input.getValidators().length);
        input.removeValidator(v2);
        assertEquals(2, input.getValidators().length);
    }

    public final void testRemoveValidator_NullArg() throws Exception {
        // ## Arrange ##
        UIInput input = createUIInput();

        // ## Act ##
        input.removeValidator(null);

        // ## Assert ##
        assertTrue(true);
    }

    // TODO test: addValueChangeListener
    public final void testAddValueChangeListener_NullArg() throws Exception {
        UIInput input = createUIInput();
        try {
            input.addValueChangeListener(null);
            fail();
        } catch (NullPointerException npe) {
            ExceptionAssert.assertMessageExist(npe);
        }
    }

    public final void testAddGetRemoveValueChangeListeners() throws Exception {
        UIInput input = createUIInput();
        assertEquals(0, input.getValueChangeListeners().length);
        ValueChangeListener v1 = new NullValueChangeListener();
        ValueChangeListener v2 = new NullValueChangeListener();
        ValueChangeListener v3 = new NullValueChangeListener();
        input.addValueChangeListener(v1);
        assertEquals(1, input.getValueChangeListeners().length);
        input.addValueChangeListener(v2);
        assertEquals(2, input.getValueChangeListeners().length);
        input.addValueChangeListener(v3);
        assertEquals(3, input.getValueChangeListeners().length);

        input.removeValueChangeListener(v2);
        assertEquals(2, input.getValueChangeListeners().length);
        input.removeValueChangeListener(v2);
        assertEquals(2, input.getValueChangeListeners().length);
    }

    public final void testRemoveValueChangeListener_NullArg() throws Exception {
        UIInput input = createUIInput();
        try {
            input.removeValueChangeListener(null);
            fail();
        } catch (NullPointerException npe) {
            ExceptionAssert.assertMessageExist(npe);
        }
    }

    public final void testSetValue_ConvertBlankToNull() throws Exception {
        // ## Arrange ##
        UIInput input = createUIInput();

        // ## Act ##
        input.setValue("");

        // ## Assert ##
        assertEquals(null, input.getValue());
    }

    public void testGetConvertedValue() throws Exception {
        final String clientId = "aaa";
        UIInput input = new UIInput() {
            public String getClientId(FacesContext context) {
                return clientId;
            }
        };
        FacesContext context = getFacesContext();
        MockValueBinding vb = new MockValueBinding("aaa");
        vb.setExpressionString("bbb");
        input.setValueBinding("value", vb);
        S2Container container = new S2ContainerImpl();
        container.register(IntegerConverter.class, "integerConverter");
        ConverterResource.setConverterBuilder(new NormalConverterBuilderImpl(
                container));
        ConverterResource.addConverter("bbb", "integerConverter");
        Object submittedValue = "sss";
        assertNotNull(input.getConvertedValue(context, submittedValue));
        assertTrue(context.getMessages() != null);
        Iterator itr = context.getMessages();
        int c = 0;
        while (itr.hasNext()) {
            FacesMessage fm = (FacesMessage) itr.next();
            assertNotNull(fm);
            c++;
        }
        assertTrue(c == 1);
        container.destroy();
    }

    private UIInput createUIInput() {
        return (UIInput) createUIComponent();
    }

    protected UIComponent createUIComponent() {
        return new UIInput();
    }

}
