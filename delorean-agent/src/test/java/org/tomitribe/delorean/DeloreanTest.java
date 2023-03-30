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
import org.junit.Assert;
import org.junit.Test;

public class DeloreanTest extends Assert {

    /**
     * We added "ago" syntax to Duration.
     * Test we can represent offsets in the past and the future.
     *
     * @throws Exception
     */
    @Test
    public void testParseOffsetToMillis() throws Exception {

        assertEquals(2300, Delorean.parse("2 seconds and 300 milliseconds"));
        assertEquals(2300, Delorean.parse("2 seconds, 300 milliseconds"));
        assertEquals(2300, Delorean.parse("2 seconds,300 milliseconds"));
        assertEquals(125000, Delorean.parse("2 minutes and 5 seconds"));

        assertEquals(-2300, Delorean.parse("2 seconds and 300 milliseconds ago"));
        assertEquals(-2300, Delorean.parse("2 seconds, 300 milliseconds  ago"));
        assertEquals(-2300, Delorean.parse("2 seconds,300 milliseconds ago "));
        assertEquals(-125000, Delorean.parse("2 minutes and 5 seconds ago"));
    }
}