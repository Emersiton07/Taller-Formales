/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lenguajes.lenguajes.models;

import java.util.ArrayList;

/**
 *
 * @author camil
 */
public class Grammar {
    private Character symbolAxiomatic;
    private ArrayList<Character> terminalNoSymbols;
    private ArrayList<Character> terminalSymbols;
    private ArrayList<Production> productions;

    public Grammar(Character symbolAxiomatic) {
        this.symbolAxiomatic = symbolAxiomatic;
        this.terminalNoSymbols = new ArrayList<Character>();
        this.terminalSymbols = new ArrayList<Character>();
        this.productions = new ArrayList<Production>();
    }

    public Character getSymbolAxiomatic() {
        return symbolAxiomatic;
    }

    public void setSymbolAxiomatic(Character symbolAxiomatic) {
        this.symbolAxiomatic = symbolAxiomatic;
    }

    public ArrayList<Character> getTerminalNoSymbols() {
        return terminalNoSymbols;
    }

    public void setTerminalNoSymbols(Character terminalNoSymbols) {
        this.terminalNoSymbols.add(terminalNoSymbols);
    }

    public ArrayList<Character> getTerminalSymbols() {
        return terminalSymbols;
    }

    public void setTerminalSymbols(Character terminalSymbols) {
        this.terminalSymbols.add(terminalSymbols);
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }

    public void setProductions(Production newProduction) {
        this.productions.add(newProduction);
    }

    @Override
    public String toString() {
        return "Grammar{" + "symbolAxiomatic=" + symbolAxiomatic + ", terminalNoSymbols=" + terminalNoSymbols + ", terminalSymbols=" + terminalSymbols + ", productions=" + productions + '}';
    }
    
    
   
    
    
    
}
