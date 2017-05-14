package com.tomitribe.fluxcapacitor;

import com.tomitribe.fluxcapacitor.api.FluxCapacitor;
import org.junit.Assert;
import org.junit.Test;

public class FluxCapacitorTest extends Assert {

    /**
     * We added "ago" syntax to Duration.
     * Test we can represent offsets in the past and the future.
     *
     * @throws Exception
     */
    @Test
    public void testParseOffsetToMillis() throws Exception {

        assertEquals(2300, FluxCapacitor.parseOffsetToMillis("2 seconds and 300 milliseconds"));
        assertEquals(2300, FluxCapacitor.parseOffsetToMillis("2 seconds, 300 milliseconds"));
        assertEquals(2300, FluxCapacitor.parseOffsetToMillis("2 seconds,300 milliseconds"));
        assertEquals(125000, FluxCapacitor.parseOffsetToMillis("2 minutes and 5 seconds"));

        assertEquals(-2300, FluxCapacitor.parseOffsetToMillis("2 seconds and 300 milliseconds ago"));
        assertEquals(-2300, FluxCapacitor.parseOffsetToMillis("2 seconds, 300 milliseconds  ago"));
        assertEquals(-2300, FluxCapacitor.parseOffsetToMillis("2 seconds,300 milliseconds ago "));
        assertEquals(-125000, FluxCapacitor.parseOffsetToMillis("2 minutes and 5 seconds ago"));
    }
}