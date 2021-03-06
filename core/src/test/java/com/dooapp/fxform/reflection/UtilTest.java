/*
 * Copyright (c) 2012, dooApp <contact@dooapp.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of dooApp nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.dooapp.fxform.reflection;

import com.dooapp.fxform.TestEnum;
import com.dooapp.fxform.TestUtils;
import com.dooapp.fxform.model.Element;
import com.dooapp.fxform.model.impl.ReadOnlyPropertyFieldElement;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 12/09/11
 * Time: 16:57
 */
public class UtilTest {

    private static class BeanWithMapProperty {

        public Map<String, String> stringMap;

        public Map<String, Boolean> booleanMap;

    }

    @Test
    public void testGetObjectPropertyGeneric() throws Exception {
        List<Element> fields = TestUtils.getTestFields();
        Element objectPropertyField = fields.get(4);
        Class clazz = ReflectionUtils.getObjectPropertyGeneric(null, ((ReadOnlyPropertyFieldElement) objectPropertyField).getField());
        Assert.assertEquals(TestEnum.class, clazz);
    }

    @Test
    public void tesGetMapPropertyValueType() throws NoSuchFieldException {
        Field stringMapField = BeanWithMapProperty.class.getField("stringMap");
        Assert.assertEquals(String.class, ReflectionUtils.getMapPropertyValueType(stringMapField));
        Field booleanMapField = BeanWithMapProperty.class.getField("booleanMap");
        Assert.assertEquals(Boolean.class, ReflectionUtils.getMapPropertyValueType(booleanMapField));

    }
}
