package com.tomitribe.fluxcapacitor;

import com.tomitribe.fluxcapacitor.util.Duration;
import org.junit.Assert;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class FluxCapacitorTest extends Assert {

    /**
     * We added "ago" syntax to Duration.
     * Test we can represent offsets in the past and the future.
     *
     * @throws Exception
     */
    @Test
    public void testParseOffsetToMillis() throws Exception {
        assertEquals(new Duration(2300, MILLISECONDS).getTime(MILLISECONDS), FluxCapacitor.parseOffsetToMillis("2 seconds and 300 milliseconds"));
        assertEquals(new Duration(2300, MILLISECONDS).getTime(MILLISECONDS), FluxCapacitor.parseOffsetToMillis("2 seconds, 300 milliseconds"));
        assertEquals(new Duration(2300, MILLISECONDS).getTime(MILLISECONDS), FluxCapacitor.parseOffsetToMillis("2 seconds,300 milliseconds"));
        assertEquals(new Duration(125, SECONDS).getTime(MILLISECONDS), FluxCapacitor.parseOffsetToMillis("2 minutes and 5 seconds"));

        assertEquals(new Duration(-2300, MILLISECONDS).getTime(MILLISECONDS), FluxCapacitor.parseOffsetToMillis("2 seconds and 300 milliseconds ago"));
        assertEquals(new Duration(-2300, MILLISECONDS).getTime(MILLISECONDS), FluxCapacitor.parseOffsetToMillis("2 seconds, 300 milliseconds  ago"));
        assertEquals(new Duration(-2300, MILLISECONDS).getTime(MILLISECONDS), FluxCapacitor.parseOffsetToMillis("2 seconds,300 milliseconds ago "));
        assertEquals(new Duration(-125, SECONDS).getTime(MILLISECONDS), FluxCapacitor.parseOffsetToMillis("2 minutes and 5 seconds ago"));
    }
}