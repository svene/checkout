package org.svenehrke.checkout.javaslang;

import javaslang.collection.List;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;

import static javaslang.API.*;

public class JavaSlangTest extends TestCase {

    public void test1() throws Exception {

        assertEquals(Option(1), Some(1));

        assertEquals("one", Match(Option(1)).of(
            Case(Some(1), "one"),
            Case(None(), "none")
        ));
        assertEquals(List(0,1,2), List.range(0, 3));
        //assertEquals(List(1, 2, 3), Stream(1, 2, 3));
        assertEquals(List(1), List(None(), Some(1)).flatMap(x -> x));
        assertEquals(Set(1,2), Set(
            Collections.emptyList(),
            Collections.singletonList(1),
            Arrays.asList(1, 2)
        ).flatMap(x -> x)
        );

    }
}
