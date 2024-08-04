/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package datastructuresvisual;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class DataStructuresVisual extends JFrame {
    private static final int NODE_SIZE = 30; // Size of nodes in pixels
    private static final int GAP = 50; // Gap between nodes in pixels

    public DataStructuresVisual() {
        super("Data Structures Visual");
        System.out.println("Welcome to Data Structure Visualizer! \n"
                + "This is a simple program which depicts Arrays, Linked Lists, Queues, Stacks, and Trees \n"
                + "Please select a data structure from the dropdown menu and enter a dataset");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Prompt the user to choose data structures
        String[] options = {"Array", "Linked List", "Queue", "Stack", "Tree"};
        String selectedOption = (String) JOptionPane.showInputDialog(null, "Select a data structure to visualize:",
                "Data Structure Visualization", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        // Create and visualize selected data structure
        switch (selectedOption) {
            case "Array":
                visualizeArray();
                break;
            case "Linked List":
                visualizeLinkedList();
                break;
            case "Queue":
                visualizeQueue();
                break;
            case "Stack":
                visualizeStack();
                break;
            case "Tree":
                visualizeBinaryTree();
                break;
        }

        pack();
        setVisible(true);
    }

    // Visualize array
    private void visualizeArray() {
        String input = JOptionPane.showInputDialog(null, "Enter array elements separated by spaces:");
        String[] elements = input.split("\\s+");
        int[] array = Arrays.stream(elements).mapToInt(Integer::parseInt).toArray();
        add(new ArrayVisual(array));
    }

    // Visualize linked list
    private void visualizeLinkedList() {
        String input = JOptionPane.showInputDialog(null, "Enter linked list elements separated by spaces:");
        String[] elements = input.split("\\s+");
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (String element : elements) {
            linkedList.add(Integer.parseInt(element));
        }
        add(new LinkedListVisual(linkedList));
    }

    // Visualize queue
    private void visualizeQueue() {
        String input = JOptionPane.showInputDialog(null, "Enter queue elements separated by spaces:");
        String[] elements = input.split("\\s+");
        Queue<Integer> queue = new LinkedList<>();
        for (String element : elements) {
            queue.add(Integer.parseInt(element));
        }
        add(new QueueVisual(queue));
    }

    // Visualize stack
    private void visualizeStack() {
        String input = JOptionPane.showInputDialog(null, "Enter stack elements separated by spaces:");
        String[] elements = input.split("\\s+");
        Stack<Integer> stack = new Stack<>();
        for (String element : elements) {
            stack.push(Integer.parseInt(element));
        }
        add(new StackVisual(stack));
    }

    // Visualize binary tree
private void visualizeBinaryTree() {
    String input = JOptionPane.showInputDialog(null, "Enter binary tree elements in level order separated by spaces (use 'null' for empty nodes):");
    String[] elements = input.split("\\s+");
    TreeNode root = createBinaryTreeFromInput(elements);
    add(new BinaryTreeVisual(root));
}

    // Helper method to create a sample binary tree
    private TreeNode createBinaryTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        return root;
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DataStructuresVisual::new);
    }

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

// Component for visualizing an array
static class ArrayVisual extends JPanel {
    private int[] array;

    public ArrayVisual(int[] array) {
        this.array = array;
        // Ensuring preferred size includes enough padding to avoid cut-offs
        setPreferredSize(new Dimension(array.length * (NODE_SIZE + GAP) + GAP, NODE_SIZE + 4 * GAP));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < array.length; i++) {
            // Calculate positions
            int nodeX = i * (NODE_SIZE + GAP) + GAP;
            int nodeY = 2 * GAP;
            
            // Draw index
            g.drawString(String.valueOf(i), nodeX + NODE_SIZE / 2 - 3, nodeY - 10);
            
            // Draw node
            g.drawRect(nodeX, nodeY, NODE_SIZE, NODE_SIZE);
            g.drawString(String.valueOf(array[i]), nodeX + NODE_SIZE / 2 - 3, nodeY + NODE_SIZE / 2 + 5);
            
            // Draw line (if not the last node)
            if (i < array.length - 1) {
                int nextNodeX = (i + 1) * (NODE_SIZE + GAP) + GAP;
                g.drawLine(nodeX + NODE_SIZE, nodeY + NODE_SIZE / 2, nextNodeX, nodeY + NODE_SIZE / 2);
            }
        }
    }
}

// Component for visualizing a linked list
static class LinkedListVisual extends JPanel {
    private LinkedList<Integer> list;

    public LinkedListVisual(LinkedList<Integer> list) {
        this.list = list;
        setPreferredSize(new Dimension(list.size() * (2 * NODE_SIZE + GAP) + GAP, NODE_SIZE + 3 * GAP));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = GAP;
        int prevX = 0; // Previous node's x-coordinate
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            // Draw the first square with the value
            g.drawRect(x, GAP, NODE_SIZE, NODE_SIZE);
            g.drawString(String.valueOf(value), x + NODE_SIZE / 2 - 3, GAP + NODE_SIZE / 2 + 5);
            // Draw the second square (empty)
            g.drawRect(x + NODE_SIZE, GAP, NODE_SIZE, NODE_SIZE);
            // Draw a small dot in the second square
            int dotSize = 5;
            int dotX = x + NODE_SIZE + NODE_SIZE / 2 - dotSize / 2;
            int dotY = GAP + NODE_SIZE / 2 - dotSize / 2;
            g.fillOval(dotX, dotY, dotSize, dotSize);

            // Draw arrow from the dot in the second square of the previous node to the first square of the current node
            if (i > 0) {
                drawArrow(g, prevX + NODE_SIZE / 2, GAP + NODE_SIZE / 2, x, GAP + NODE_SIZE / 2);
            }

            prevX = x + NODE_SIZE;
            x += 2 * NODE_SIZE + GAP;
        }
        g.drawString("Head", GAP, GAP - 10);
    }

    // Helper method to draw an arrow between two points
    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        int arrowSize = 10;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int x1Edge = x1 + (int) (NODE_SIZE / 2 * Math.cos(angle));
        int y1Edge = y1 + (int) (NODE_SIZE / 2 * Math.sin(angle));
        int x2Edge = x2 - (int) (NODE_SIZE / 2 * Math.cos(angle));
        int y2Edge = y2 - (int) (NODE_SIZE / 2 * Math.sin(angle));

        // Draw the line
        g.drawLine(x1, y1, x2Edge, y2Edge);

        // Draw the arrowhead
        int arrowX1 = x2Edge - (int) (arrowSize * Math.cos(angle - Math.PI / 6));
        int arrowY1 = y2Edge - (int) (arrowSize * Math.sin(angle - Math.PI / 6));
        int arrowX2 = x2Edge - (int) (arrowSize * Math.cos(angle + Math.PI / 6));
        int arrowY2 = y2Edge - (int) (arrowSize * Math.sin(angle + Math.PI / 6));

        g.drawLine(x2Edge, y2Edge, arrowX1, arrowY1);
        g.drawLine(x2Edge, y2Edge, arrowX2, arrowY2);
    }
}

    // Component for visualizing a queue
// Component for visualizing a queue
static class QueueVisual extends JPanel {
    private Queue<Integer> queue;

    public QueueVisual(Queue<Integer> queue) {
        this.queue = queue;
        // Ensure the panel is wide enough to accommodate all elements and labels
        setPreferredSize(new Dimension(queue.size() * (NODE_SIZE + GAP) + 2 * GAP, NODE_SIZE + 4 * GAP));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = GAP;
        int prevX = 0;
        int index = 0;
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            int value = iterator.next();
            g.drawRect(x, GAP, NODE_SIZE, NODE_SIZE);
            g.drawString(String.valueOf(value), x + NODE_SIZE / 2 - 3, GAP + NODE_SIZE / 2 + 5);

            // Draw line from previous node to current node
            if (index > 0) {
                g.drawLine(prevX + NODE_SIZE, GAP + NODE_SIZE / 2, x, GAP + NODE_SIZE / 2);
            }

            prevX = x;
            x += NODE_SIZE + GAP;
            index++;
        }

        // Label the front and back of the queue
        if (queue.size() > 0) {
            g.drawString("Front (Dequeue)", GAP, GAP - 10);
            g.drawString("Back (Enqueue)", prevX - NODE_SIZE / 2, GAP - 10);
        }
    }
}



// Component for visualizing a stack
static class StackVisual extends JPanel {
    private Stack<Integer> stack;

    public StackVisual(Stack<Integer> stack) {
        this.stack = stack;
        // Set the preferred size with vertical arrangement
        setPreferredSize(new Dimension(NODE_SIZE + 2 * GAP, stack.size() * (NODE_SIZE + GAP) + GAP));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = GAP;
        Integer prevValue = null; // Track the previous node's position

        for (Integer value : stack) {
            // Draw the rectangle for the current element
            g.drawRect(GAP, y, NODE_SIZE, NODE_SIZE);
            g.drawString(String.valueOf(value), GAP + NODE_SIZE / 2 - 3, y + NODE_SIZE / 2 + 5);

            // Draw line from the previous node to the current node (if not the first node)
            if (prevValue != null) {
                int prevY = y - NODE_SIZE - GAP;
                g.drawLine(GAP + NODE_SIZE / 2, prevY + NODE_SIZE, GAP + NODE_SIZE / 2, y);
            }

            // Update the previous node's position
            prevValue = value;
            y += NODE_SIZE + GAP;
        }
    }
}


private TreeNode createBinaryTreeFromInput(String[] elements) {
    if (elements.length == 0 || elements[0].equals("null")) return null;
    
    Queue<TreeNode> queue = new LinkedList<>();
    TreeNode root = new TreeNode(Integer.parseInt(elements[0]));
    queue.add(root);
    
    int index = 1;
    while (!queue.isEmpty() && index < elements.length) {
        TreeNode current = queue.poll();
        
        // Create left child
        if (index < elements.length && !elements[index].equals("null")) {
            current.left = new TreeNode(Integer.parseInt(elements[index]));
            queue.add(current.left);
        }
        index++;
        
        // Create right child
        if (index < elements.length && !elements[index].equals("null")) {
            current.right = new TreeNode(Integer.parseInt(elements[index]));
            queue.add(current.right);
            }
        index++;
        }
    
    return root;
    }

    // Component for visualizing a binary tree
    static class BinaryTreeVisual extends JPanel {
        private TreeNode root;

        public BinaryTreeVisual(TreeNode root) {
            this.root = root;
            setPreferredSize(new Dimension(800, 600));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawTree(g, root, 400, 50, 200);
        }

    private void drawTree(Graphics g, TreeNode node, int x, int y, int xOffset) {
        if (node != null) {
            g.drawOval(x - NODE_SIZE / 2, y - NODE_SIZE / 2, NODE_SIZE, NODE_SIZE);
            g.drawString(String.valueOf(node.val), x - 5, y + 5);

        if (node.left != null) {
            int childX = x - xOffset;
            int childY = y + GAP;
            drawLine(g, x, y, childX, childY);
            drawTree(g, node.left, childX, childY, xOffset / 2);
        }
        if (node.right != null) {
            int childX = x + xOffset;
            int childY = y + GAP;
            drawLine(g, x, y, childX, childY);
            drawTree(g, node.right, childX, childY, xOffset / 2);
            }
        } 
    }

    // Helper method to draw lines between nodes
    private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        // Calculate coordinates for connection points at the edges of the nodes
        int nodeRadius = NODE_SIZE / 2;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int x1Edge = x1 + (int) (nodeRadius * Math.cos(angle));
        int y1Edge = y1 + (int) (nodeRadius * Math.sin(angle));
        int x2Edge = x2 - (int) (nodeRadius * Math.cos(angle));
        int y2Edge = y2 - (int) (nodeRadius * Math.sin(angle));
    
        // Draw the line
        g.drawLine(x1Edge, y1Edge, x2Edge, y2Edge);
        }
    }
}
