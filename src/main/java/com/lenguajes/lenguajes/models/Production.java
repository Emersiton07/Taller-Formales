/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lenguajes.lenguajes.models;

/**
 *
 * @author emers
 */
public class Production {
    
    private Character symbolNoTerminal;
    private String production;

    public Production(Character symbolNoTerminal, String production) {
        this.symbolNoTerminal = symbolNoTerminal;
        this.production = production;
    }

    public Character getSymbolNoTerminal() {
        return symbolNoTerminal;
    }

    public void setSymbolNoTerminal(Character symbolNoTerminal) {
        this.symbolNoTerminal = symbolNoTerminal;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return production;
    }
    
    
    
    
}
