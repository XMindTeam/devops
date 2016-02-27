package com.intel.spring.boot.sample.boot.utils;

/**
 * Created by Ecic Chen on 2016/2/27.
 */
import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.example.list.List;

public class ListTest {
    private List list;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        list = new List();

        // Insert elements
        list.add("Hello");
        list.add("yes");
        list.add("No");
        list.add("Yolo");
    }

    @Test
    public void testRemovals() {
        assertEquals(true, list.remove("Hello"));
        assertEquals("The size of the list must be 3", 3, list.size());
        assertEquals(false, list.remove("Noob"));
        assertEquals("The size of the list must be 3", 3, list.size());
        assertEquals(true, list.remove("No"));
        assertEquals("The size of the list must be 2", 2, list.size());
    }

    @Test
    public void testEmptyList() {
        list.remove("Hello");
        list.remove("yes");
        list.remove("No");
        list.remove("Yolo");

        // Check if the exception for empty list is throw
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage("Empty List");
        list.remove("Omg");
        assertEquals(0, list.size());
    }

    @Test
    public void testExistence() {
        assertEquals(true, list.contains("yes"));
        assertEquals(true, list.contains("Yolo"));
        assertEquals(false, list.contains("OMG"));
    }
}