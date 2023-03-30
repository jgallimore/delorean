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
package org.tomitribe.delorean.api;

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
