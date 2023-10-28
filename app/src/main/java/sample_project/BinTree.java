package sample_project;

import java.util.Iterator;
import java.util.Stack;

class Node<T extends Comparable<T>> {
    T data;
    Node<T> left;
    Node<T> right;

    /// New leaf node containing data
    Node(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public boolean is_leaf() {
        return this.left == null && this.right == null;
    }

    public boolean is_empty() {
        return this.data == null;
    }

    public boolean contains(T data) {
        if (this.is_empty()) {
            return false;
        }

        if (data.compareTo(this.data) < 0) {
            if (this.left == null) {
                return false;
            }
            return this.left.contains(data);
        } else if (data.compareTo(this.data) > 0) {
            if (this.right == null) {
                return false;
            }
            return this.right.contains(data);
        } else {
            return true;
        }
    }

    public boolean insert(T data) {
        if (this.is_empty()) {
            this.data = data;
            return true;
        }

        if (data.compareTo(this.data) < 0) {
            if (this.left == null) {
                this.left = new Node<T>(data);
                return true;
            }
            return this.left.insert(data);
        } else if (data.compareTo(this.data) > 0) {
            if (this.right == null) {
                this.right = new Node<T>(data);
                return true;
            }
            return this.right.insert(data);
        } else {
            return false;
        }
    }
}

public class BinTree<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> root = null;
    private Integer length = 0;

    BinTree() {
        this.root = null;
    }

    BinTree(T data) {
        this.root = new Node<T>(data);
        length++;
    }

    public boolean contains(T data) {
        if (this.root == null) {
            return false;
        }
        return this.root.contains(data);
    }

    public T get() {
        return this.root.data;
    }

    public T get(Integer index) {
        Iterator<T> iter = this.iterator();
        T data = null;
        for (int i = 0; i <= index; i++) {
            data = iter.next();
        }
        return data;
    }

    public boolean is_empty() {
        return this.root == null;
    }

    public boolean add(T data) {
        this.length++;
        if (this.root == null) {
            this.root = new Node<T>(data);
            return true;
        } else
            return this.root.insert(data);
    }

    public Integer length() {
        return this.length;
    }

    public void clear() {
        this.root = null; // The rest will be GC'd
        this.length = 0;
    }

    public void fromArray(T[] array) {
        for (T data : array) {
            this.add(data);
        }
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public Iterator<T> iterator() {
        return new TreeIterator<T>(this);
    }
}

class TreeIterator<T extends Comparable<T>> implements Iterator<T> {
    private Stack<Node<T>> stack = new Stack<Node<T>>();
    private Node<T> current = null;

    TreeIterator(BinTree<T> tree) {
        this.current = tree.getRoot();
    }

    public boolean hasNext() {
        return this.current != null || !this.stack.isEmpty();
    }

    public T next() {
        while (this.current != null) {
            this.stack.push(this.current);
            this.current = this.current.left;
        }

        this.current = this.stack.pop();
        Node<T> node = this.current;
        this.current = this.current.right;
        return node.data;
    }
}