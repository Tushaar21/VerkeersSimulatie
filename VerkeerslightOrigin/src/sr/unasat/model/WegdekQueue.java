package sr.unasat.model;

import java.util.LinkedList;

public class WegdekQueue {
    private LinkedList<Wegdek> queue;

    public WegdekQueue() {
        this.queue = new LinkedList<>();
    }

    // Add a Wegdek to the end of the queue
    public void enqueue(Wegdek wegdek) {
        queue.addLast(wegdek);
    }

    // Remove and return the Wegdek from the front of the queue
    public Wegdek dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.removeFirst();
    }

    // Peek at the Wegdek at the front of the queue without removing it
    public Wegdek peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.getFirst();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Get the size of the queue
    public int size() {
        return queue.size();
    }
}
