package com.example.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {

    @Test
    public void testAddition() {
        int sum = 2 + 3;
        assertEquals(5, sum, "2 + 3 should equal 5");
    }
}
