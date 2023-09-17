/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.lenguajes.lenguajes.models.Grammar;
import com.lenguajes.lenguajes.models.Production;
import com.lenguajes.lenguajes.structures.NaryTree;
import com.lenguajes.lenguajes.structures.TreeNode;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author emers
 */
public class GrammarController2 {

    private Grammar grammar;
    private NaryTree generalTree;

    public GrammarController2(Grammar grammar) {
        this.grammar = grammar;
    }

    public void fillTree() {
        int level = 1;
        ArrayList<Production> productions = grammar.getProductions();
        String axiomatix = String.valueOf(grammar.getSymbolAxiomatic());
        generalTree = new NaryTree(axiomatix);

        //agrega las producciones del axiomatico
        for (int i = 0; i < productions.size(); i++) {
            Production production = productions.get(i);
            if (production.getSymbolNoTerminal() == grammar.getSymbolAxiomatic()) {
                TreeNode newNode = new TreeNode(production.getProduction());
                generalTree.addChild(generalTree.getRoot(), newNode);
            }
        }
        while(level<3){
        ArrayList<TreeNode> lastNodes = generalTree.getNodesAtLevel(level);
        System.out.println(lastNodes.toString());
        for (int i = 0; i < lastNodes.size(); i++) {
            char[] charData = lastNodes.get(i).getData().toCharArray();
            for (int j = 0; j < charData.length; j++) {
                for (int k = 0; k < grammar.getTerminalNoSymbols().size(); k++) {
                    if (charData[j] == grammar.getTerminalNoSymbols().get(k)) {
                        String productiosForAdd = searchProductions(lastNodes.get(i).getData());
                        String[] productiosForAddSplit = productiosForAdd.split(",");
                        for (int l = 0; l < productiosForAddSplit.length; l++) {
                            TreeNode aux = new TreeNode(productiosForAddSplit[l]);
                            generalTree.addChild(lastNodes.get(i), aux);
                        }
                        System.out.println("Nodo Para agregar hijos" + lastNodes.get(i));

                    }
                }
            }
        }
        level++;
        }
        SwingUtilities.invokeLater(() -> {
            generalTree.drawTree();
        });
    }

    private String searchProductions(String strProduction) {
        int position = searchPositionSymbolNoTerminal(strProduction);
        String productiosForAdd = "";
        ArrayList<Production> productions = grammar.getProductions();
        ArrayList<Production> productionsFound = new ArrayList<>();
        for (int i = 0; i < productions.size(); i++) {
            Production production = productions.get(i);
            if (production.getSymbolNoTerminal() == searchSymbolNoTerminal(strProduction)) {           
                productionsFound.add(production);
            }
        }
        for (int i = 0; i < productionsFound.size(); i++) {
            char[] charProduction = strProduction.toCharArray();
            String newProduction = concatenate(charProduction,productionsFound.get(i).getProduction(),strProduction);
            productiosForAdd = productiosForAdd + newProduction + ",";
        }
        return productiosForAdd;
    }

    private int searchPositionSymbolNoTerminal(String production) {
        int position = 0;
        char[] productionChar = production.toCharArray();
        for (int j = 0; j < productionChar.length; j++) {
            for (int k = 0; k < grammar.getTerminalNoSymbols().size(); k++) {
                if (productionChar[j] == grammar.getTerminalNoSymbols().get(k)) {
                    position = j;
                }
            }
        }
        return position;
    }
    private char searchSymbolNoTerminal(String production) {
        char symbolNotTerminal = ' ';
        char[] productionChar = production.toCharArray();
        for (int j = 0; j < productionChar.length; j++) {
            for (int k = 0; k < grammar.getTerminalNoSymbols().size(); k++) {
                if (productionChar[j] == grammar.getTerminalNoSymbols().get(k)) {
                    symbolNotTerminal = productionChar[j];
                }
            }
        }
        return symbolNotTerminal;
    }

    
    
    private String concatenate(char[] chars, String newProduction, String productionInitial){
        String newString = "";
        int position = searchPositionSymbolNoTerminal(productionInitial);
        for (int i = 0; i < position; i++) {
            newString = newString + chars[i];
        }
        newString += newProduction;
        
        for (int i = position+1; i < chars.length; i++) {
            newString = newString + chars[i];
        }
        return newString;
    }

    public static void main(String[] args) {
        Character symbolAxiomatic = 'S';
        Character terminalSymbols1 = 'a';
        Character terminalSymbols2 = 'b';
        Character terminalNoSymbols1 = 'A';
        Character terminalNoSymbols2 = 'B';
        Production production1 = new Production(symbolAxiomatic, "aA");
        Production production5 = new Production(symbolAxiomatic, "a");
        Production production2 = new Production(terminalNoSymbols1, "aA");
        Production production3 = new Production(terminalNoSymbols1, "bA");
        Production production4 = new Production(terminalNoSymbols1, "b");
        Production production6 = new Production(symbolAxiomatic, "Aa");
        Grammar grammar = new Grammar(symbolAxiomatic);
        grammar.setTerminalNoSymbols(terminalNoSymbols1);
        grammar.setTerminalNoSymbols(symbolAxiomatic);
        grammar.setTerminalSymbols(terminalSymbols2);
        grammar.setTerminalSymbols(terminalSymbols2);
        grammar.setProductions(production1);
        grammar.setProductions(production2);
        grammar.setProductions(production3);
        grammar.setProductions(production4);
        grammar.setProductions(production5);
        grammar.setProductions(production6);

        GrammarController2 controller2 = new GrammarController2(grammar);
        controller2.fillTree();
        //System.out.println(controller2.verifyWord("aabbb"));

    }
}
