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
package org.tomitribe.delorean.service;

import org.tomitribe.delorean.api.Delorean;

import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Date;

import static org.tomitribe.delorean.api.Delorean.parse;
import static javax.ejb.LockType.READ;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Lock(READ)
@Singleton
@Path("/delorean")
public class DeloreanService {

    @GET
    @Produces({APPLICATION_JSON})
    public Status get() {
        return new Status(Delorean.getOffset(), new Date().getTime());
    }

    @Path("offset")
    @POST
    @Produces({APPLICATION_JSON})
    public Status offset(String offset) {
        return offset(parse(offset));
    }

    @Path("offset/{offset}")
    @POST
    @Produces({APPLICATION_JSON})
    @Consumes({APPLICATION_JSON})
    public Status offset(@PathParam("offset") long offset) {
        Delorean.setOffset(offset);
        return new Status(Delorean.getOffset(), new Date().getTime());
    }

    @Path("offset/increment")
    @POST
    @Produces({APPLICATION_JSON})
    public Status offsetIncrement(String duration) {
        return offsetIncrement(parse(duration));
    }

    @Path("offset/increment/{delta}")
    @POST
    @Produces({APPLICATION_JSON})
    @Consumes({APPLICATION_JSON})
    public Status offsetIncrement(@PathParam("delta") long delta) {
        Delorean.addOffset(delta);
        return new Status(Delorean.getOffset(), new Date().getTime());
    }

    @Path("offset/decrement")
    @POST
    @Produces({APPLICATION_JSON})
    public Status offsetDecrement(String duration) {
        return offsetDecrement(parse(duration));
    }

    @Path("offset/decrement/{delta}")
    @POST
    @Produces({APPLICATION_JSON})
    @Consumes({APPLICATION_JSON})
    public Status offsetDecrement(@PathParam("delta") long delta) {
        Delorean.addOffset(-1 * delta);
        return new Status(Delorean.getOffset(), new Date().getTime());
    }

    @Path("date")
    @POST
    @Produces({APPLICATION_JSON})
    public Status date(String date) {
        final long time = Dates.parse(date).getTime();
        return date(time);
    }

    @Path("date/{date}")
    @POST
    @Produces({APPLICATION_JSON})
    @Consumes({APPLICATION_JSON})
    public Status date(@PathParam("date") long time) {
        final long offset = time - Delorean.actualTimeMillis();
        Delorean.setOffset(offset);
        return new Status(Delorean.getOffset(), new Date().getTime());
    }
}