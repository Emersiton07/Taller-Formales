/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lenguajes.lenguajes.structures;

import com.lenguajes.lenguajes.models.Production;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author emers
 */
public class NaryTree {

    TreeNode root;
    int circleRadius = 20;
    int verticalSpacing = 60;

    public NaryTree(String data) {
        this.root = new TreeNode(data);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void addChild(TreeNode parent, TreeNode child) {
        parent.children.add(child);
    }

    public TreeNode searchNode(TreeNode currentNode, String valueToFind, int targetLevel, int currentLevel) {
        if (currentNode == null) {
            return null;
        }

        if (currentLevel == targetLevel && currentNode.data.equals(valueToFind)) {
            return currentNode;
        }

        if (currentLevel >= targetLevel) {
            return null; // No es necesario buscar más allá de este nivel
        }

        for (TreeNode child : currentNode.children) {
            TreeNode result = searchNode(child, valueToFind, targetLevel, currentLevel + 1);
            if (result != null) {
                return result;
            }
        }

        return null; // Nodo no encontrado en este subárbol o nivel
    }

    public TreeNode searchNode(String valueToFind, int targetLevel) {
        return searchNode(root, valueToFind, targetLevel, 0);
    }

    public void drawTree() {
        JFrame frame = new JFrame("Árbol No Binario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawNode(g, root, 400, 50, 100);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    private void drawNode(Graphics g, TreeNode node, int x, int y, int xOffset) {
        g.drawOval(x - 20, y - 20, 40, 40);
        g.drawString(node.data.toString(), x - 5, y + 5);

        int childCount = node.children.size();
        int startX = x - xOffset * (childCount - 1) / 2;

        for (int i = 0; i < childCount; i++) {
            int childX = startX + i * xOffset;
            int childY = y + 80;

            g.drawLine(x, y + 20, childX, childY - 20);
            drawNode(g, (TreeNode) node.children.get(i), childX, childY, xOffset / 2);

        }
    }
    
    public ArrayList<TreeNode> getNodesAtLevel(int targetLevel) {
        ArrayList<TreeNode> nodesAtLevel = new ArrayList<>();
        if (targetLevel < 0) {
            return nodesAtLevel; // Nivel inválido, devuelve una lista vacía
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currentLevel = 0;

        while (!queue.isEmpty() && currentLevel <= targetLevel) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (currentLevel == targetLevel) {
                    nodesAtLevel.add(node);
                }
                for (TreeNode child : node.children) {
                    queue.offer(child);
                }
            }
            currentLevel++;
        }

        return nodesAtLevel;
    }

    public static void main(String[] args) {
        NaryTree tree = new NaryTree("1");
        TreeNode node2 = new TreeNode("3");
        TreeNode node3 = new TreeNode("2");
        TreeNode node4 = new TreeNode("4");

        tree.addChild(tree.root, node2);
        tree.addChild(tree.root, node3);
        tree.addChild(tree.root, node3);
        //tree.addChild(tree.root, node4);
        tree.addChild(node2, node4);

        
        System.out.println(tree.searchNode("2", 0));
        System.out.println(tree.getNodesAtLevel(2).toString());
        SwingUtilities.invokeLater(() -> {
            tree.drawTree();
        });
        
    }
}
