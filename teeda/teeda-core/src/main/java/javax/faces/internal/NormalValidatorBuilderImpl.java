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
package javax.faces.internal;

import java.util.HashMap;
import java.util.Map;

import javax.faces.internal.ValidatorResource.ValidatorPair;
import javax.faces.validator.Validator;

import org.seasar.framework.beans.util.BeanUtil;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author shot
 *
 */
public class NormalValidatorBuilderImpl implements ValidatorBuilder {

    private S2Container container;

    private Map validators = new HashMap();

    public NormalValidatorBuilderImpl() {
        container = SingletonS2ContainerFactory.getContainer();
    }

    public NormalValidatorBuilderImpl(S2Container container) {
        this.container = container;
    }

    public Validator build(String expression, ValidatorPair[] pairs) {
        if (pairs == null || pairs.length == 0) {
            return null;
        }
        if (validators.containsKey(expression)) {
            return (Validator) validators.get(expression);
        }
        if (pairs.length == 1) {
            Validator validator = getSingleValidator(pairs[0]);
            validators.put(expression, validator);
            return validator;
        }
        final ValidatorChain chain = new ValidatorChain();
        for (int i = 0; i < pairs.length; i++) {
            chain.add(getSingleValidator(pairs[i]));
        }
        validators.put(expression, chain);
        return chain;
    }

    protected Validator getSingleValidator(ValidatorPair pair) {
        final Validator validator = (Validator) container
                .getComponent(pair.validatorName);
        BeanUtil.copyProperties(pair.properties, validator);
        return validator;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public void clearAll() {
        validators.clear();
    }

    public void clearValidator(String expression) {
        validators.remove(expression);
    }

}
