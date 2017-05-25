/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulaautomato;

import java.io.IOException;
import org.jdom2.JDOMException;

/**
 *
 * @author Otávio
 */
public class ManipulaAutomato {

    /**
     * @param args the command line arguments
     * @throws org.jdom2.JDOMException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws JDOMException, IOException {
        String entrada = "arq.jff.txt";
        String saida = "arq2.jff";
        Automato automato = new Automato();
        automato.carrega(entrada);
        automato.Salve(saida);
    }
    
}
