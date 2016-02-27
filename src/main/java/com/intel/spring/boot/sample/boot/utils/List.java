package com.intel.spring.boot.sample.boot.utils;

/**
 * Created by Ecic Chen on 2016/2/27.
 */
import java.util.NoSuchElementException;

public class List {
    private static class ListNode {
        private Object data;
        private ListNode nextNode;

        /**
         * Constructor. It initializes data and sets next node to null
         *
         * @param object a reference to node's data
         */
        ListNode(Object object) {
            this(object, null);
        }

        /**
         * Constructor creates ListNode with passed data and next node.
         *
         * @param object the reference to node's data
         * @param node the next node in the list
         */
        ListNode(Object object, ListNode node) {
            data = object;
            nextNode = node;
        }
    }

    /**
     * The head of the list.
     */
    private ListNode firstNode;
    /**
     * The tail of the list.
     */
    private ListNode lastNode;
    /**
     * The size of the list.
     */
    private int size;

    /**
     * Constructor creates empty List.
     */
    public List() {
        firstNode = lastNode = null;
        this.size = 0;
    }

    /**
     * Inserts an element at the the list.
     *
     * @param insertItem the inserted item
     */
    public void add(Object insertItem) {
        ListNode node = new ListNode(insertItem);

        if (isEmpty()) {
            firstNode = lastNode = node;
        } else {
            // lastNode's nextNode refers to new node
            lastNode.nextNode = node;
            lastNode = node;
        }

        size++;
    }

    /**
     * Removes the data from the list.
     *
     * @param item the object to be removed
     * @return true if the object has been found and removed
     * @throws NoSuchElementException if the list is empty
     */
    public boolean remove(Object item) throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty List");
        }

        // if the node which contains the item is the head
        if (firstNode.data.equals(item)) {
            removeFromFront();
            return true;
            // if the node which contains the item is the tail
        } else if (lastNode.data.equals(item)) {
            removeFromBack();
            return true;
        } else {
            ListNode current = firstNode;

            while (current != lastNode) {
                if (current.nextNode.data.equals(item)) {
                    current.nextNode = current.nextNode.nextNode;
                    size--;
                    return true;
                }

                current = current.nextNode;
            }
        }

        return false; // return removed node data
    }

    /**
     * Returns and removes the data from the list head.
     */
    private void removeFromFront() {
        // update references firstNode and lastNode
        if (firstNode == lastNode) {
            firstNode = lastNode = null;
        } else {
            firstNode = firstNode.nextNode;
        }

        size--;
    }

    /**
     * Returns and removes the data from the list tail.
     */
    private void removeFromBack() {
        // update references firstNode and lastNode
        if (firstNode == lastNode) {
            firstNode = lastNode = null;
            // locate new last node
        } else {
            ListNode current = firstNode;

            // loop while current node does not refer to lastNode
            while (current.nextNode != lastNode) {
                current = current.nextNode;
            }

            lastNode = current; // current is new lastNode
            current.nextNode = null;
        }

        size--;
    }

    /**
     * Checks if the given item exists in the list.
     *
     * @param item the element to search for
     * @return true if the item exists
     */
    public boolean contains(Object item) {
        ListNode current = firstNode;

        while (current != null) {
            if (current.data.equals(item)) {
                return true;
            }

            current = current.nextNode;
        }

        return false;
    }

    /**
     * Determine whether list is empty.
     *
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return firstNode == null; // return true if List is empty
    }

    /**
     * Returns the size of the list.
     *
     * @return list size
     */
    public int size() {
        return size;
    }

    /**
     * Prints the list's contents.
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("Empty list");
            return;
        }

        System.out.println("The list is: ");

        ListNode current = firstNode;

        // while not at end of list, output current node's data
        while (current != null) {
            System.out.printf("%s ", current.data);
            current = current.nextNode;
        }

        System.out.println();
    }
}