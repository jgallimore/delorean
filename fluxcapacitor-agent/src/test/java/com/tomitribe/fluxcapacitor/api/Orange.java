/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2017
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.tomitribe.fluxcapacitor.api;

@Green
public class Orange implements Blue, Azul {

    public final Purple purple = Purple.SQUARE;

    @Override
    public Purple purple(final Purple purple, @Verde @Green final Blue blue) throws Red, Rojo {
        Blue.blue.set(true);

        final Green annotation = Orange.class.getAnnotation(Green.class);
        Purple value = annotation.value();

        if (value == null) {
            value = Purple.CIRCLE;
        }

        return value;
    }

    @Verde
    @Override
    public void other(@Verde Purple purple, Azul blue) throws Red {
        switch (purple) {
            case CIRCLE:
                System.out.print(purple);
            case SQUARE:
                System.out.print(purple + "square");
            case TRIANGLE:
                System.out.print(purple + "triangle");
        }
    }
}
