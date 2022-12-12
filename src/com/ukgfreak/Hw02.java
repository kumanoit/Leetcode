/*=============================================================================
| Assignment: HW 01 - Building and managing a BST
|
| Author: Your name here
| Language: Java
|
| To Compile: javac Hw01.java
|
| To Execute: java Hw01 filename
| where filename is in the current directory and contains
| commands to insert, delete, print.
|
| Class: COP3503 - CS II Spring 2021
| Instructor: McAlpin
| Due Date: per assignment
|
+=============================================================================*/

package com.ukgfreak;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum InstructionType {
    INSERT, SEARCH, DELETE, PRINT, QUIT
}

public class Hw02 {

    public static void main(String[] args) {
        boolean isSeedUsed = false;
        SkipList skipList = new SkipList();
        if (args.length == 2) {
            if (args[1].equals("R") || args[1].equals("r")) {
                skipList = new SkipList(new Random(System.currentTimeMillis()));
                isSeedUsed = true;
            }
        }

        System.out.println("For the input file named " + args[0]);
        System.out.println(isSeedUsed ? "With the RNG seeded," : "With the RNG unseeded,");

        final List<Instruction> instructions = getInstructions(args[0]); // TODO
        for (Instruction instruction : instructions) {
            switch (instruction.type) {
                case INSERT:
                    skipList.insert(instruction.number);
                    break;
                case SEARCH:
                    if (skipList.search(instruction.number)) {
                        System.out.println(instruction.number + " found");
                    } else {
                        System.out.println(instruction.number + " NOT FOUND");
                    }
                    break;
                case DELETE:
                    if (skipList.delete(instruction.number)) {
                        System.out.println(instruction.number + " deleted");
                    } else {
                        System.out.println(instruction.number + " integer not found - delete not successful");
                    }
                    break;
                case PRINT:
                    skipList.printAll();
                    break;
                case QUIT:
                    break;
                default:
            }
        }
    }

    /**
     * @param filename
     * @return list of instructions after reading it from a file
     */
    private static List<Instruction> getInstructions(final String filename) {
        final String filecontent = readFromFile(filename);
        final List<Instruction> instructions = new ArrayList<>();
        for (String line : filecontent.split("\n")) {
            instructions.add(new Instruction(line));
        }
        return instructions;
    }

    /**
     * @param filename
     * @return content of file
     */
    private static String readFromFile(final String filename) {
        File file = new File(filename);
        StringBuilder fileContent = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Can't read file.");
            e.printStackTrace();
        }
        return fileContent.toString();
    }

    public void complexityIndicator() {
        System.out.println("hard; 38794; 6");
    }
}

class SkipList {

    private static final int DEFAULT_SEED = 42;
    private static Random randomGenerator = new Random(DEFAULT_SEED);

    private final int NEGATIVE_SENTINEL = Integer.MIN_VALUE;
    private final int POSITIVE_SENTINEL = Integer.MAX_VALUE;

    private Node head;
    private Node tail;
    private int height = 0;

    public SkipList(Random randomGenerator) {
        this();
        SkipList.randomGenerator = randomGenerator;
    }

    public SkipList() {
        head = new Node(NEGATIVE_SENTINEL);
        tail = new Node(POSITIVE_SENTINEL);
        head.next = tail;
        tail.prev = head;
    }

    public void insert(int number) {
        Node prevNode = getPreviousNode(number);
        // don't insert duplicates
        if (prevNode.val == number) {
            return;
        }

        promote(prevNode, number);
    }

    private void promote(Node prevNode, int number) {
        int heightSoFar = 0;
        Node downNewNode = null;
        do {
            heightSoFar++;
            increaseHeightIfNeeded(heightSoFar);

            // inserting node at current level
            Node newNode = new Node(number);
            newNode.next = prevNode.next;
            newNode.next.prev = newNode;
            newNode.prev = prevNode;
            prevNode.next = newNode;
            newNode.down = downNewNode;

            if (downNewNode != null) {
                downNewNode.up = newNode;
            }
            downNewNode = newNode;
            while (prevNode.up == null) {
                prevNode = prevNode.prev;
            }
            prevNode = prevNode.up;
        } while (isHead());
    }

    private void increaseHeightIfNeeded(int newHeight) {
        if (newHeight >= height) {
            addAnotherLayer();
            height++;
        }
    }

    /**
     * this function adds another layer to the skip list
     */
    private void addAnotherLayer() {
        Node newHead = new Node(NEGATIVE_SENTINEL);
        Node newTail = new Node(POSITIVE_SENTINEL);
        newHead.next = newTail;
        newTail.next = newHead;

        newHead.down = head;
        newTail.down = tail;

        head.up = newHead;
        tail.up = newTail;

        head = newHead;
        tail = newTail;
    }

    public boolean delete(int number) {
        Node nodeToDelete = getPreviousNode(number);
        if (nodeToDelete.val != number) {
            return false;
        }

        while (nodeToDelete != null) {
            nodeToDelete.prev.next = nodeToDelete.next;
            nodeToDelete.next.prev = nodeToDelete.prev;
            nodeToDelete = nodeToDelete.up;
        }
        return true;
    }

    public void printAll() {
        System.out.println("the current Skip List is shown below: ");
        System.out.println("---infinity");
        Node ptr = head;
        while (ptr.down != null) {
            ptr = ptr.down;
        }
        Node next = ptr.next;
        while (next.val != POSITIVE_SENTINEL) {
            Node iter = next;
            while (iter != null) {
                System.out.print(String.format("%6d", iter.val) + ";");
                iter = iter.up;
            }
            next = next.next;
            System.out.println();
        }
//        ptr = head;
//        do {
//            Node newHead = ptr.next;
//            while (newHead.val != POSITIVE_SENTINEL) {
//                System.out.print(String.format("%5d", newHead.val) + ";");
//                newHead = newHead.next;
//            }
//            System.out.println();
//            ptr = ptr.down;
//        } while (ptr != null);
        System.out.println("+++infinity");
        System.out.println("---End of Skip List---");
    }

    public boolean search(int number) {
        return getPreviousNode(number).val == number;
    }

    /**
     * @param number
     * @return a node which is predecessor of given number in case if given number is not present in skip list
     */
    private Node getPreviousNode(int number) {
        Node ptr = head;
        while (ptr.down != null) {
            ptr = ptr.down;
            while (ptr.next.val <= number) {
                ptr = ptr.next;
            }
        }
        return ptr;
    }

    /**
     * flipping a coin
     *
     * @return true: if it is head
     * false: otherwise
     */
    private boolean isHead() {
        return Math.abs(randomGenerator.nextInt()) % 2 == 1;
    }

}

class Node {
    int val;
    Node prev;
    Node next;
    Node up;
    Node down;

    Node(int value) {
        this.val = value;
    }
}

class Instruction {
    InstructionType type;
    int number;

    public Instruction(final String line) {
        String[] parts = line.split("\\s+");
        switch (parts[0]) {
            case "i":
                this.type = InstructionType.INSERT;
                this.number = Integer.parseInt(parts[1]);
                break;
            case "s":
                this.type = InstructionType.SEARCH;
                this.number = Integer.parseInt(parts[1]);
                break;
            case "d":
                this.type = InstructionType.DELETE;
                this.number = Integer.parseInt(parts[1]);
                break;
            case "p":
                this.type = InstructionType.PRINT;
                break;
            case "q":
                this.type = InstructionType.QUIT;
                break;
        }
    }

    public String toString() {
        return this.type.toString() + " " + this.number;
    }
}
/*=============================================================================
| I [your name] ([your NID]) affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/
