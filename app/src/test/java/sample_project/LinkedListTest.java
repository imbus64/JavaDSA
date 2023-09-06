package sample_project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

class LinkedListTest {
    @Test
    void basic_and_bounds_testing() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        Iterator<Integer> iter = list.iterator();
        assert (iter.hasNext());
        assertNotNull(iter.next());
        assertFalse(iter.hasNext());

        boolean exceptionThrown = false;
        try {
            iter.next();
        } catch (Exception e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
    }

    @Test
    void test_size() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i : new int[] { 0, 1, 2, 3 }) {
            list.add(i);
            assertEquals(i + 1, list.size());
        }
    }

    // This also runs add()
    @Test
    void test_get_node_at_index() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        ListNode<Integer> node = list.get_node_at_index(0);
        assertEquals(1, node.data);

        node = list.get_node_at_index(1);
        assertEquals(2, node.data);

        node = list.get_node_at_index(2);
        assertEquals(3, node.data);

        node = list.get_node_at_index(3);
        assertNull(node);
    }

    @Test
    void test_get_node_at_index_with_1_element() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);

        ListNode<Integer> node = list.get_node_at_index(0);
        assertEquals(1, node.data);
    }

    @Test
    void testInsertAt() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (Integer n : new Integer[] { 0, 1, 2, 3 }) {
            list.insert_at(n, n + 1);
        }

        assert (list.length == 4);

        System.out.println("testInsertAt");
        for (Iterator<Integer> iter = list.iterator(); iter.hasNext();) {
            Integer n = iter.next();
            assertTrue(n >= 0 && n <= 4);
        }

        Iterator<Integer> iter = list.iterator();
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());
        assertEquals(3, iter.next());
        assertEquals(4, iter.next());

        // Make sure edge cases where bounds are hit are handled correctly
        list.clear();
        list.add(1);
        iter = list.iterator(); // Reset iterator
        list.insert_at(1, 2);
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());

        list.clear();
        list.add(2);
        list.insert_at(0, 0);
        iter = list.iterator(); // Reset iterator
        assertTrue(list.size() == 2);
        assertEquals(0, iter.next());
        assertEquals(2, iter.next());
    }

    @Test
    void testFromArray() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.fromArray(new Integer[] { 1, 2, 3, 4 });

        Iterator<Integer> iter = list.iterator();
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());
        assertEquals(3, iter.next());
        assertEquals(4, iter.next());
    }

    @Test
    void testInsertAtWithEmptyList() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.insert_at(0, 1);
        assertTrue(list.size() == 1);
        assertEquals(1, list.get_node_at_index(0).data);
    }

    @Test
    void add_with_index() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(0, 1);
        list.add(1, 2);
        list.add(2, 3);

        Iterator<Integer> iter = list.iterator();
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());
        assertEquals(3, iter.next());
    }

    @Test
    void add_without_index() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iter = list.iterator();
        assertEquals(1, iter.next());
        assertEquals(2, iter.next());
        assertEquals(3, iter.next());
    }
}