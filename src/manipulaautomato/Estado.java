/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulaautomato;

/**
 *
 * @author Otávio
 */
public class Estado {
    
    private int id;
    private boolean estInicial;
    private boolean estFinal;
    /**
     * Instancia o estado.
     */
    public Estado(){
        this.id = -1;
        this.estInicial = false;
        this.estFinal = false;
    }
    /**
     * Adiciona id ao estado.
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }
    /**
     * Pega id do estado.
     * @return 
     */
    public int getId(){
        return this.id;
    }
    /**
     * Marca o estado como inicial
     */
    public void setInicial(){
        this.estInicial = true;
    }
    /**
     * Pergunta se o estado e inicial.
     * @return 
     */
    public boolean ehInicial(){
        return this.estInicial;
    }
    /**
     * Marca o estado como final.
     */
    public void setFinal(){
        this.estFinal = true;
    }
    /**
     * Pergunta se o estado e final.
     * @return 
     */
    public boolean ehFinal(){
        return this.estFinal;
    }
}
