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
package com.dooapp.fxform.model;

import com.dooapp.fxform.utils.Disposable;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;

import java.lang.annotation.Annotation;

/**
 * User: Antoine Mischler <antoine@dooapp.com>
 * Date: 11/04/11
 * Time: 22:22
 * Model object wrapping an object field.
 */
public interface Element<WrappedType> extends ReadOnlyProperty<WrappedType>, Disposable {

	/**
	 * The raw type of this element.
	 *
	 * @return
	 */
	Class<?> getType();

	/**
	 * The type wrapped by this element
	 *
	 * @return
	 */
	Class<WrappedType> getWrappedType();

	/**
	 * The source bean of this element.
	 *
	 * @return
	 */
	Property<?> sourceProperty();

	/**
	 * Similar to Field#getAnnotation
	 *
	 * @param annotationClass
	 * @return
	 */
	<T extends Annotation> T getAnnotation(Class<T> annotationClass);

	/**
	 * Return the class declaring this element.
	 *
	 * @return
	 */
	Class<?> getDeclaringClass();

	/**
	 * Return the category of this element. The category can be used by the form to group elements.
	 * Can be null if the element has no category.
	 *
	 * @return
	 */
	String getCategory();

	/**
	 * Set the category of this element.
	 *
	 * @param category
	 */
	void setCategory(String category);
}
