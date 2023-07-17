/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.openjpa.enhance;

import java.security.PrivilegedAction;

import org.apache.openjpa.lib.util.J2DoPrivHelper;

import serp.bytecode.BCClass;
import serp.bytecode.BCClassLoader;
import serp.bytecode.BCField;
import serp.bytecode.Project;

/**
 *
 * @Deprecated just for getting rid of Serp in one place
 */
public class SerpPrivacyHelper extends J2DoPrivHelper {

    /**
     * Return a PrivilegeAction object for new BCClassLoader().
     *
     * Requires security policy:
     *   'permission java.lang.RuntimePermission "createClassLoader";'
     *
     * @return BCClassLoader
     */
    public static PrivilegedAction<BCClassLoader> newBCClassLoaderAction(
            final Project project, final ClassLoader parent) {
        return new PrivilegedAction<BCClassLoader>() {
            @Override
            public BCClassLoader run() {
                return new BCClassLoader(project, parent);
            }
        };
    }

    public static PrivilegedAction<BCClassLoader> newBCClassLoaderAction(
        final Project project) {
        return new PrivilegedAction<BCClassLoader>() {
            @Override
            public BCClassLoader run() {
                return new BCClassLoader(project);
            }
        };
    }

    /**
     * Return a PrivilegeAction object for BCClass.getFields().
     *
     * Requires security policy:
     *   'permission java.lang.RuntimePermission "getClassLoader";'
     *
     * @return BCField
     */
    public static PrivilegedAction<BCField[]> getBCClassFieldsAction(
            final BCClass bcClass, final String fieldName) {
        return new PrivilegedAction<BCField []>() {
            @Override
            public BCField [] run() {
                return bcClass.getFields(fieldName);
            }
        };
    }

    /**
     * Return a PrivilegeAction object for Project.loadClass().
     *
     * Requires security policy:
     *   'permission java.lang.RuntimePermission "createClassLoader";'
     *
     * @return BCClass
     */
    public static PrivilegedAction<BCClass> loadProjectClassAction(
        final Project project, final Class<?> clazz) {
        return new PrivilegedAction<BCClass>() {
            @Override
            public BCClass run() {
                return project.loadClass(clazz);
            }
        };
    }
}
