/*
 * Copyright (c) 2013, dooApp <contact@dooapp.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of dooApp nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.dooapp.fxform.utils;

import com.dooapp.fxform.model.Element;
import javafx.beans.property.ObjectProperty;

import java.lang.annotation.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 26/11/2013
 * Time: 11:09
 */
public abstract class AnnotationLoader<A extends Annotation, T> {

    private final static Logger logger = Logger.getLogger(AnnotationLoader.class.getName());

    protected <E> T load(Class<? extends Annotation> annotation, Element<E> element) {
        // check field annotation
        if (element.getAnnotation(annotation) != null) {
            // use factory provided by the annotation
            try {
                return instantiate((A) element.getAnnotation(annotation));
            } catch (Exception e) {
                logger.log(Level.WARNING, "Unable to get new instance for " + element.getAnnotation(annotation), e);
            }
        }
        // check annotation on wrapped type
        if (ObjectProperty.class.isAssignableFrom(element.getType())) {
            try {
                Class genericClass = element.getWrappedType();
                if (genericClass.getAnnotation(annotation) != null) {
                    return instantiate((A) genericClass.getAnnotation(annotation));
                }
            } catch (Exception e) {
                // ignore
            }
        }
        return null;
    }

    protected abstract T instantiate(A annotation) throws IllegalAccessException, InstantiationException;

}
