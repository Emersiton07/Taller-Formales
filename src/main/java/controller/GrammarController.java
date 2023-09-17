/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author camil
 */
public class GrammarController {

    private String productions;
    private String terminalNoSymbols;
    private String terminalSymbols;
    private String word;
    private Map<String, String> productionsMap;
    private boolean result;

    public GrammarController(String terminalSymbols, String terminalNoSymbols, String productions, String word) {
        this.word = word;
        productionsMap = new HashMap<>();

        String[] terminalNoSymbolsArray = terminalNoSymbols.split(",");
        String[] productionsArray = terminalSymbols.split(",");

        for (String production : productionsArray) {
            String[] parts = production.trim().split("-");

            if (parts.length == 2) {
                String nonTerminal = parts[0].trim();
                String terminalOrNonTerminal = parts[1].trim();
                productionsMap.put(nonTerminal, terminalOrNonTerminal);
            } else if (parts.length == 1) {
                // Si no se encontró el separador "-", asumimos que es un terminal
                String terminal = parts[0].trim();
                productionsMap.put(terminal, terminal);
            }

        }
        result = validateWord(word);

    }

    public boolean getResult() {
        return result;
    }

    public boolean validateWord(String word) {
        // Comenzamos con el símbolo inicial, que generalmente es 'S'
        return validateSymbol("S", word);
    }

    private boolean validateSymbol(String symbol, String word) {
        // Si el símbolo es un terminal, verificamos si coincide con el inicio de la palabra
        if (symbol.equals(word)) {
            // Si coincide, la palabra es válida si no quedan caracteres por validar
            return true;
        }

        // Si el símbolo es un símbolo no terminal según las producciones
        if (productionsMap.containsKey(symbol)) {
            // Obtenemos la producción asociada al símbolo
            String production = productionsMap.get(symbol);

            // Intentamos expandir el símbolo no terminal utilizando sus producciones
            for (String productionSymbol : production.split("\\|")) {
                if (validateProduction(productionSymbol, word)) {
                    return true;
                }
            }
        }

        return false; // Si no se cumple ninguna de las condiciones anteriores, la palabra no es válida
    }

    private boolean validateProduction(String production, String word) {
        // Dividir la producción en símbolos individuales
        String[] symbols = production.split(" ");
        int currentIndex = 0;

        // Validar cada símbolo en la producción
        for (String symbol : symbols) {
            if (validateSymbol(symbol, word.substring(currentIndex))) {
                // Si el símbolo actual es válido, avanzamos en la palabra
                currentIndex += symbol.length();
            } else {
                return false; // Si no es válido, la producción no es válida
            }
        }

        // La producción es válida si se validaron todos los símbolos
        return true;
    }

}
