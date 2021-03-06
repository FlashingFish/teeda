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
package org.seasar.teeda.core.taglib.core;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.internal.ValueBindingUtil;
import javax.faces.validator.DoubleRangeValidator;
import javax.faces.validator.Validator;
import javax.servlet.jsp.JspException;

import org.seasar.framework.util.AssertionUtil;
import org.seasar.teeda.core.util.BindingUtil;
import org.seasar.teeda.core.util.ConverterUtil;

/**
 * @author yone
 * @author shot
 */
public class ValidateDoubleRangeTag extends MaxMinValidatorTag {

    private static final long serialVersionUID = 1L;

    private static final String VALIDATOR_ID = DoubleRangeValidator.VALIDATOR_ID;

    protected double minimum = 0;

    protected double maximum = 0;

    public ValidateDoubleRangeTag() {
        super();
    }

    protected Validator createValidator() throws JspException {
        super.setValidatorId(VALIDATOR_ID);
        DoubleRangeValidator validator = null;
        validator = (DoubleRangeValidator) super.createValidator();
        AssertionUtil.assertNotNull("DoubleRangeValidator", validator);

        evaluateExpressions();
        if (isMinimumSet()) {
            validator.setMinimum(minimum);
        }
        if (isMaximumSet()) {
            validator.setMaximum(maximum);
        }
        return validator;
    }

    private void evaluateExpressions() throws JspException {
        FacesContext context = FacesContext.getCurrentInstance();
        final String min = getMinimum();
        if (min != null) {
            if (BindingUtil.isValueReference(min)) {
                ValueBinding vb = ValueBindingUtil.createValueBinding(context,
                        min);
                minimum = ConverterUtil.convertToDouble(vb.getValue(context));
            } else {
                minimum = ConverterUtil.convertToDouble(min);
            }
        }
        final String max = getMaximum();
        if (max != null) {
            if (BindingUtil.isValueReference(max)) {
                ValueBinding vb = context.getApplication().createValueBinding(
                        max);
                maximum = ConverterUtil.convertToDouble(vb.getValue(context));
            } else {
                maximum = ConverterUtil.convertToDouble(max);
            }
        }
    }
}
