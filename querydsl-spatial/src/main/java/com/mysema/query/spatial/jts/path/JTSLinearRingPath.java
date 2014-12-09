/*
 * Copyright 2014, Timo Westkämper
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mysema.query.spatial.jts.path;

import java.lang.reflect.AnnotatedElement;

import com.vividsolutions.jts.geom.LinearRing;

import com.mysema.query.spatial.jts.JTSLinearRingExpression;
import com.mysema.query.types.Path;
import com.mysema.query.types.PathImpl;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.PathMetadataFactory;
import com.mysema.query.types.Visitor;

/**
 * @author tiwe
 *
 * @param <T>
 */
public class JTSLinearRingPath<T extends LinearRing> extends JTSLinearRingExpression<T> implements Path<T> {

    private static final long serialVersionUID = 312776751843333543L;

    private final PathImpl<T> pathMixin;

    public JTSLinearRingPath(Path<?> parent, String property) {
        this((Class<? extends T>) LinearRing.class, parent, property);
    }

    public JTSLinearRingPath(Class<? extends T> type, Path<?> parent, String property) {
        this(type, PathMetadataFactory.forProperty(parent, property));
    }

    public JTSLinearRingPath(PathMetadata<?> metadata) {
        this((Class<? extends T>) LinearRing.class, metadata);
    }

    public JTSLinearRingPath(Class<? extends T> type, PathMetadata<?> metadata) {
        super(new PathImpl<T>(type, metadata));
        this.pathMixin = (PathImpl<T>)mixin;
    }

    public JTSLinearRingPath(String var) {
        this((Class<? extends T>) LinearRing.class, PathMetadataFactory.forVariable(var));
    }

    @Override
    public final <R,C> R accept(Visitor<R,C> v, C context) {
        return v.visit(pathMixin, context);
    }

    public JTSLinearRingPath(Class<? extends T> type, String var) {
        this(type, PathMetadataFactory.forVariable(var));
    }

    @Override
    public PathMetadata<?> getMetadata() {
        return pathMixin.getMetadata();
    }

    @Override
    public Path<?> getRoot() {
        return pathMixin.getRoot();
    }

    @Override
    public AnnotatedElement getAnnotatedElement() {
        return pathMixin.getAnnotatedElement();
    }

}