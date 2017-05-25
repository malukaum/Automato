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
public class Transicao {
    
    private int origem;
    private int destino;
    private char valor;
    /**
     * Instancia uma transicao
     */
    public Transicao(){
        this.origem = -1;
        this.destino = -1;
        this.valor = '.';
    }
    /**
     * Retorna o estado de origem da transicao
     * @return 
     */
    public int getOrigem(){
        return this.origem;
    }
    /**
     * Adiciona o estado de origem a transicao
     * @param origem
     */
    public void setOrigem(int origem){
        this.origem = origem;
    }
    /**
     * Retorna o estado de destino da transicao
     * @return destino
     */
    public int getDestino(){
        return this.destino;
    }
    /**
     * Adiciona o estado de destino a transicao
     * @param destino
     */
    public void setDestino(int destino){
        this.destino = destino;
    }
    /**Retorna o valor de transicao
     * @return valor
     */
    public char getValor(){
        return this.valor;
    }
    /**
     * Adiciona o valor de transicao
     * @param valor
     */
    public void setValor(char valor){
        this.valor = valor;
    }
}
