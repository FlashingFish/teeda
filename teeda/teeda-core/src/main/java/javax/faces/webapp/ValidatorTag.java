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
package javax.faces.webapp;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.internal.WebAppUtils;
import javax.faces.validator.Validator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ValidatorTag extends TagSupport {

    private String validatorId_ = null;

    public ValidatorTag() {
    }

    public void setValidatorId(String validatorId) {
        validatorId_ = validatorId;
    }

    public int doStartTag() throws JspException {
        UIComponentTag tag = UIComponentTag
                .getParentUIComponentTag(pageContext);
        if(tag == null){
            throw new JspException("Not nested in a UIComponentTag");
        }
        if(!tag.getCreated()){
            return EVAL_PAGE;
        }
        Validator validator = createValidator();
        UIComponent component = tag.getComponentInstance();
        if(component == null || !(component instanceof EditableValueHolder)){
            throw new JspException(
                    "Component is null or not editable value holder.");
        }
        EditableValueHolder editableValueHolder = (EditableValueHolder)component;
        editableValueHolder.addValidator(validator);
        return SKIP_BODY;
    }

    public void release(){
        validatorId_ = null;
        super.release();
    }
    
    protected Validator createValidator() throws JspException {
        try{
            String validatorId = validatorId_;
            if(UIComponentTag.isValueReference(validatorId_)){
                validatorId = (String)WebAppUtils
                        .getValueFromCreatedValueBinding(validatorId_);
            }
            return WebAppUtils.createValidator(validatorId);
        }catch (Exception e){
            throw new JspException(e);
        }
    }
}
