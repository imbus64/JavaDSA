package sample_project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

class BinTreeTest {
    @Test
    void basic() {
        BinTree<Integer> tree = new BinTree<Integer>();
        tree.add(10);
        assertEquals(10, tree.get());
        assertNotEquals(12, tree.get());
    }

    @Test
    void test_iterator() {
        BinTree<Integer> tree = new BinTree<Integer>();
        tree.add(60);
        tree.add(70);
        tree.add(80);
        tree.add(90);
        tree.add(100);
        tree.add(10);
        tree.add(20);
        tree.add(30);
        tree.add(40);
        tree.add(50);

        Iterator<Integer> iter = tree.iterator();
        int i = 0;
        while (iter.hasNext()) {
            assertEquals(i * 10 + 10, iter.next());
            i++;
        }
    }

    @Test
    void test_contains() {
        BinTree<Integer> tree = new BinTree<Integer>();
        tree.add(60);
        tree.add(70);
        tree.add(80);
        tree.add(90);
        tree.add(100);
        tree.add(10);
        tree.add(20);
        tree.add(30);
        tree.add(40);
        tree.add(50);

        assertTrue(tree.contains(60));
        assertTrue(tree.contains(70));
        assertTrue(tree.contains(80));
        assertTrue(tree.contains(90));
        assertTrue(tree.contains(100));
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(30));
        assertTrue(tree.contains(40));
        assertTrue(tree.contains(50));
        assertFalse(tree.contains(0));
        assertFalse(tree.contains(110));
    }

    @Test
    void from_array_test() {
        BinTree<Integer> tree = new BinTree<Integer>();
        Integer[] nums = {1,2,3,4,5}; // In order
        tree.fromArray(nums);

        for (int i = 0; i < nums.length; i++) {
            assertEquals(nums[i], tree.get(i));
        }

        assertTrue(tree.contains(1));
    }

    @Test 
    void length_test() {
        BinTree<Integer> tree = new BinTree<Integer>();
        Integer[] nums = {1,2,3,4,5};
        tree.fromArray(nums);
        assertEquals(5, tree.length());

        tree.clear();
        assertEquals(0, tree.length());
    }
}