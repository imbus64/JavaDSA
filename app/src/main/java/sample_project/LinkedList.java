package sample_project;

import java.util.Iterator;

class ListNode<T> {
    ListNode(T data) {
        this.next = null;
        this.prev = null;
        this.data = data;
    }

    ListNode<T> next;
    ListNode<T> prev;
    T data;
}

// Doubly linked list
public class LinkedList<T> implements Iterable<T> {
    ListNode<T> head;
    ListNode<T> tail;
    Integer length = 0;

    public void add(T data) {
        this.insert_after(this.tail, data);
    }

    public void add(int index, T data) {
        this.insert_at(index, data);
    }

    public boolean is_empty() {
        return this.head == null;
    }

    public int size() {
        return this.length;
    }

    public void clear() {
        // Heavily reliant on GC
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public void fromArray(T[] array) {
        for (T data : array) {
            this.insert_after(this.tail, data);
        }
    }

    void insert_after(ListNode<T> node, T data) {
        ListNode<T> newnode = new ListNode<T>(data);

        if (this.is_empty()) {
            this.head = newnode;
            this.tail = newnode;
            this.length += 1;
            return;
        }

        newnode.next = node.next;
        newnode.prev = node;
        node.next = newnode;
        if (newnode.next != null)
            newnode.next.prev = newnode;
        if (this.tail == node)
            this.tail = newnode;
        this.length += 1;
    }

    void insert_before(ListNode<T> node, T data) {
        ListNode<T> newnode = new ListNode<T>(data);
        newnode.next = node;
        newnode.prev = node.prev;
        node.prev = newnode;
        if (newnode.prev != null)
            newnode.prev.next = newnode;
        if (this.head == node)
            this.head = newnode;
        this.length += 1;
    }

    // Makes a node containing T data and inserts it at index
    // Conceptually shifts all nodes after index to the right
    // If index is out of bounds, does nothing
    void insert_at(Integer index, T data) {
        // Bounds checking and early returns, not throwing exceptions
        if (index == null || index < 0 || index > this.size())
            return;

        if (index == this.size())
            this.insert_after(this.tail, data);
        else
            this.insert_before(this.get_node_at_index(index), data);
    }

    // May return null for now
    ListNode<T> get_node_at_index(Integer index) {
        if (this.is_empty() || index == null || index > this.size() - 1)
            return null;

        ListNode<T> cursor = this.head;
        for (Integer i = 0; i < index; i++)
            cursor = cursor.next;

        return cursor;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            ListNode<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}