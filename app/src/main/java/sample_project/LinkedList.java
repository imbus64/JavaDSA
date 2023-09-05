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

public class LinkedList<T> implements Iterable<T> {
    ListNode<T> head;
    ListNode<T> tail;

    public void add(T data) {
        ListNode<T> node = new ListNode<T>(data);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
    }

    public void insert_at(T data, Integer index) {
        if(this.head == null) return;
        ListNode<T> current = this.head;

        for(Integer i = index; i >= 0; i--) {
            if(current.next == null) return;
            current = current.next;
        }

        ListNode<T> newnode = new ListNode<T>(data);
        current.next.prev = newnode;
        current.next = newnode;
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