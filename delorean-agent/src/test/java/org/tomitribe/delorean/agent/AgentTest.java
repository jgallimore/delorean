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
package org.tomitribe.delorean.agent;

import org.tomitribe.delorean.Agent;
import org.tomitribe.delorean.util.Archive;
import io.superbiz.colors.Blue;
import io.superbiz.colors.Color;
import io.superbiz.colors.Green;
import io.superbiz.colors.Main;
import io.superbiz.colors.Red;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.ASMifier;

import java.io.File;

/**
 * @version $Revision$ $Date$
 */
public class AgentTest extends Assert {

    final File agentJar = Archive.archive()
            .manifest("Premain-Class", Agent.class)
            .manifest("Agent-Class", Agent.class)
            .manifest("Can-Redefine-Classes", true)
            .manifest("Can-Retransform-Classes", true)
            .addDir(JarLocation.jarLocation(Agent.class))
            .addJar(JarLocation.jarLocation(ASMifier.class))
            .addJar(JarLocation.jarLocation(Opcodes.class))
            .asJar();

    final File testJar = Archive.archive()
            .add(Main.class)
            .add(Color.class)
            .add(Red.class)
            .add(Green.class)
            .add(Blue.class).asJar();

    @Test
    public void test() throws Exception {
        final Java.Result result = Java.java(
//                "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005",
                "-Ddelorean.debug=true ",
                "-Ddelorean.offset=30 days ago",
                "-javaagent:" + agentJar.getAbsolutePath(),
                "-classpath", testJar.getAbsolutePath(),// + ":" + agentJar.getAbsolutePath(),
                Main.class.getName()
        );

        assertEquals(0, result.getExitCode());
        final String out = result.getOut();
        final String err = result.getErr();
        System.out.println(out);
        assertTrue(out.contains("Delorean: Setting offset to '30 days ago'"));
        assertTrue(out.contains("Delorean: Agent installed successfully."));
        assertTrue(out.contains("Delorean: Replaced 3 System.currentTimeMillis usages in io/superbiz/colors/Blue"));
    }

}
