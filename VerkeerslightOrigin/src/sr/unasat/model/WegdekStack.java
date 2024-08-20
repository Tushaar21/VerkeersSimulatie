package sr.unasat.model;

import java.util.EmptyStackException;

public class WegdekStack {
    private static class Node {
        Wegdek data;
        Node next;

        Node(Wegdek data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node top;

    public void push(Wegdek wegdek) {
        top = new Node(wegdek, top);
    }

    public Wegdek pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Wegdek wegdek = top.data;
        top = top.next;
        return wegdek;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public Wegdek peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }
}
