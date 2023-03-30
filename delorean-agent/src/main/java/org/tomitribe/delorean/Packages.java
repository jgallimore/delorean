/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.tomitribe.delorean;

import org.tomitribe.delorean.api.Delorean;
import org.objectweb.asm.Type;

public class Packages {

    public static final String from = "api";
    public static final String to = "gen";

    static final String apiPackage = getPackage(Delorean.class);
    static final String genPackage = apiPackage.replace(from, to);
    static final String genClass = relocate(Delorean.class);
    static final String apiClass = Delorean.class.getName().replace(".", "/");

    private Packages() {
    }

    public static String relocate(Class<?> clazz) {
        return clazz.getName().replace(".", "/").replace(from, to);
    }

    public static String getPackage(Class<?> clazz) {
        return clazz.getName().replace(clazz.getSimpleName(), "").replace(".", "/");
    }


    public static String[] replace(final String[] ss) {
        if (ss == null) return null;

        for (int i = 0; i < ss.length; i++) {
            ss[i] = replace(ss[i]);
        }
        return ss;
    }

    public static String replace(final String s) {
        if (s == null) return null;
        if (s.startsWith("$SwitchMap$")) {
            final String before = "$SwitchMap$" + apiPackage.replace("/", "$");
            final String after = "$SwitchMap$" + genPackage.replace("/", "$");
            return s.replace(before, after);
        }
        return s.replace(apiPackage, genPackage);
    }

    public static Type replace(final Type s) {
        if (s == null) return null;
        return Type.getType(replace(s.getDescriptor()));
    }

    public static Object replaceObj(final Object o) {
        if (o instanceof Type) {
            Type type = (Type) o;
            return replace(type);
        }

        if (o instanceof String) {
            String string = (String) o;
            return replace(string);
        }

        return o;
    }

    public static Object[] replaceObj(final Object[] o) {
        if (o == null) return null;

        for (int i = 0; i < o.length; i++) {
            o[i] = replaceObj(o[i]);
        }

        return o;
    }
}
