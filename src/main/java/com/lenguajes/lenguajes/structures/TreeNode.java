/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lenguajes.lenguajes.structures;

import com.lenguajes.lenguajes.models.Production;
import java.util.ArrayList;

/**
 *
 * @author emers
 */
public class TreeNode {
    String data;
    ArrayList<TreeNode> children;

    public TreeNode(String data) {
        this.data = data;
        this.children = new ArrayList<>();
    
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "data=" + data + ", children=" + children + '}';
    }
    
    
}
