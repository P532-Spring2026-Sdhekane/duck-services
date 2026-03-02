package edu.iu.habahram.ducksservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DuckTest {

    @Test
    void toLine() {
        DuckData duck = new DuckData(
                1,
                "mallardly",
                "Mallard",
                "Green",
                5,
                10,
                "Quack",
                true
        );

        String line = duck.toLine();
        String expected = "1,mallardly,Mallard,Green,5,10,Quack,true";
        assertEquals(expected, line);
    }

    @Test
    void fromLine() {
        String line = "1,mallardly,Mallard,Green,5,10,Quack,true";

        DuckData duck = DuckData.fromLine(line);

        assertEquals(1, duck.id());
        assertEquals("mallardly", duck.name());
        assertEquals("Mallard", duck.type());
        assertEquals("Green", duck.color());
        assertEquals(5, duck.size());
        assertEquals(10, duck.price());
        assertEquals("Quack", duck.quackSound());
        assertTrue(duck.flyBehavior());
    }
}