package sr.unasat.service.serviceimpl;

import sr.unasat.model.Car;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NodeCar implements Iterable<Car> {

    private class Node {
        Car data;
        Node next;
        Node prev;

        Node(Car data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;

    public void insert(Car car) {
        Node newNode = new Node(car);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void remove(Car car) {
        if (head == null) return;

        Node current = head;
        while (current != null) {
            if (current.data.equals(car)) {
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    }
                } else if (current == tail) {
                    tail = tail.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                break;
            }
            current = current.next;
        }
    }

    public Car removeFirst() {
        if (head == null) {
            return null; // or throw an exception if you prefer
        }
        Car firstCar = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null; // If the list is now empty
        }
        return firstCar;
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<Car> iterator() {
        return new NodeCarIterator();
    }

    private class NodeCarIterator implements Iterator<Car> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Car next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Car data = current.data;
            current = current.next;
            return data;
        }
    }

    //----------------------------------------------------Priority----------------------------------------------------//
    public static int prioriteit(String car){
        return switch (car.toLowerCase()) {
            case "politie" -> 1;
            case "brandweer" -> 2;
            case "ambulance" -> 3;
            default -> 0;
        };
    }

    public static void insertionSort(NodeCar nodeCar) {
        if (nodeCar.head == null || nodeCar.head.next == null) {
            return; // List is already sorted if it has 0 or 1 element
        }

        NodeCar sorted = new NodeCar(); // Start with an empty list

        NodeCar.Node current = nodeCar.head;
        while (current != null) {
            NodeCar.Node next = current.next; // Store the next node to process

            if (sorted.head == null || prioriteit(sorted.head.data.getCarName()) > prioriteit(current.data.getCarName())) {
                // Insert at the beginning
                current.next = sorted.head;
                sorted.head = current;
            } else {
                // Insert in the correct position
                NodeCar.Node sortedCurrent = sorted.head;
                while (sortedCurrent.next != null &&
                        prioriteit(sortedCurrent.next.data.getCarName()) <= prioriteit(current.data.getCarName())) {
                    sortedCurrent = sortedCurrent.next;
                }
                current.next = sortedCurrent.next;
                sortedCurrent.next = current;
            }

            current = next; // Move to the next node in the original list
        }

        nodeCar.head = sorted.head; // Update the head to the sorted list's head
    }

}


