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

import java.util.concurrent.atomic.AtomicBoolean;

public interface Blue {

    AtomicBoolean blue = new AtomicBoolean();

    Purple purple(final Purple purple, final Blue blue) throws Red, Rojo;

}
