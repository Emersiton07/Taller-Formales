package com.lenguajes.lenguajes.structures;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class NaryTree2 {
    TreeNode root;
    int circleRadius = 20;
    int horizontalSpacing = 100; // Espacio horizontal entre nodos
    int verticalSpacing = 80;   // Espacio vertical entre niveles
    int horizontalSpacing2 = 80; // Espacio horizontal entre nodos
    int verticalSpacing2 = 100;   // Espacio vertical entre niveles

    public NaryTree2(String data) {
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
    int treeWidth = calculateTreeWidth(root);
    int treeHeight = calculateTreeHeight(root);
    frame.setSize(treeWidth + 100, treeHeight + 100);

    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawNode(g, root, treeWidth / 2, 50, treeWidth / 4, 0);
        }
    };

    frame.add(panel);
    frame.setVisible(true);
}

private void drawNode(Graphics g, TreeNode node, int x, int y, int xOffset, int level) {
    g.drawOval(x - circleRadius, y - circleRadius, circleRadius * 2, circleRadius * 2);
    g.drawString(node.data.toString(), x - 5, y + 5);

    int childCount = node.children.size();
    int startX = x - xOffset * (childCount - 1) / 2;

    for (int i = 0; i < childCount; i++) {
        int childX = startX + i * xOffset;
        int childY = y + verticalSpacing * (level + 1); // Aumenta la separación vertical según el nivel
        int childXOffset = xOffset / 2 - 50; // Disminuye la separación horizontal en 5 unidades por nivel

        g.drawLine(x, y + circleRadius, childX, childY - circleRadius);
        drawNode(g, node.children.get(i), childX, childY, childXOffset, level + 1);
    }
}

public void drawTreeHorizontal() {
    JFrame frame = new JFrame("Árbol No Binario (Horizontal)");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    int treeWidth = calculateTreeWidthHorizontal(root);
    int treeHeight = calculateTreeHeight(root);
    frame.setSize(1080, 720);

    JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawNodeHorizontal(g, root, 50, treeHeight / 2, 50, 0);
        }
    };

    frame.add(panel);
    frame.setVisible(true);
}

private int calculateTreeWidthHorizontal(TreeNode node) {
    if (node == null) {
        return 0;
    }

    int childCount = node.children.size();
    if (childCount == 0) {
        return circleRadius * 2;
    }

    int totalWidth = 0;
    for (TreeNode child : node.children) {
        totalWidth += calculateTreeWidthHorizontal(child) + horizontalSpacing2;
    }

    // Restamos el último espacio horizontal adicional
    totalWidth -= horizontalSpacing2;

    return Math.max(totalWidth, circleRadius * 2 * childCount);
}

private void drawNodeHorizontal(Graphics g, TreeNode node, int x, int y, int yOffset, int level) {
    g.drawOval(x - circleRadius, y - circleRadius, circleRadius * 2, circleRadius * 2);
    g.drawString(node.data.toString(), x - 5, y + 5);

    int childCount = node.children.size();
    int startY = y - yOffset * (childCount - 1) / 2;

    for (int i = 0; i < childCount; i++) {
        int childX = x + horizontalSpacing2;
        int childY = startY + i * yOffset;

        g.drawLine(x + circleRadius, y, childX - circleRadius, childY);
        drawNodeHorizontal(g, node.children.get(i), childX, childY, yOffset / 2, level + 1);
    }
}


    private int calculateTreeWidth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int childCount = node.children.size();
        if (childCount == 0) {
            return circleRadius * 2;
        }

        int totalWidth = 0;
        for (TreeNode child : node.children) {
            totalWidth += calculateTreeWidth(child) + horizontalSpacing2;
        }

        // Restamos el último espacio horizontal adicional
        totalWidth -= horizontalSpacing2;

        return Math.max(totalWidth, circleRadius * 2 * childCount);
    }

    private int calculateTreeHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int maxHeight = 0;
        for (TreeNode child : node.children) {
            int childHeight = calculateTreeHeight(child);
            if (childHeight > maxHeight) {
                maxHeight = childHeight;
            }
        }

        return maxHeight + verticalSpacing;
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
        NaryTree2 tree = new NaryTree2("1");
        TreeNode node2 = new TreeNode("3");
        TreeNode node3 = new TreeNode("2");
        TreeNode node4 = new TreeNode("4");
        TreeNode node5 = new TreeNode("4");
        TreeNode node6 = new TreeNode("4");
        TreeNode node7 = new TreeNode("4");
        TreeNode node8 = new TreeNode("4");
        TreeNode node9 = new TreeNode("4");
        TreeNode node10 = new TreeNode("4");
        TreeNode node11 = new TreeNode("4");
        TreeNode node12 = new TreeNode("4");
        TreeNode node13 = new TreeNode("4");

        tree.addChild(tree.root, node2);
        tree.addChild(tree.root, node3);
        tree.addChild(tree.root, node3);
        tree.addChild(node2, node4);
        tree.addChild(node3, node8);
        tree.addChild(node3, node9);
        tree.addChild(node3, node10);
        tree.addChild(node2, node5);
        tree.addChild(node2, node6);
        tree.addChild(node2, node7);

        System.out.println(tree.searchNode("2", 0));
        System.out.println(tree.getNodesAtLevel(2).toString());
        
        SwingUtilities.invokeLater(() -> {
            tree.drawTree();
        });
    }
}