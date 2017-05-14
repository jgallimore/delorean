/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2017
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.tomitribe.delorean;

import com.tomitribe.delorean.api.Delorean;
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
