/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulaautomato;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Otávio
 */
public class Automato {

    private ArrayList<Estado> estados;
    private ArrayList<Character> alfabeto;
    private ArrayList<Transicao> transicoes;

    /**
     * Instancia o automato.
     */
    public Automato() {
        this.estados = new ArrayList<>();
        this.alfabeto = new ArrayList<>();
        this.transicoes = new ArrayList<>();
    }

    /**
     * Adiciona uma lista de estados ao autõmato.
     *
     * @param estados
     */
    public void setEstados(ArrayList<Estado> estados) {
        this.estados = estados;
    }

    /**
     * Pega um determinado estado do automato a partir do identificador.
     *
     * @param id
     * @return estado
     */
    public Estado getEstado(int id) {
        Iterator<Estado> e = estados.iterator();
        while (e.hasNext()) {
            if (e.next().getId() == id) {
                return e.next();
            }
        }
        return null;
    }

    /**
     * Adiciona uma lista de símbolos ao alfabeto do autômato
     *
     * @param alfabeto
     */
    public void setAlfabeto(ArrayList<Character> alfabeto) {
        this.alfabeto = alfabeto;
    }

    /**
     *
     * @return
     */
    public ArrayList<Character> getAlfabeto() {
        return alfabeto;
    }

    /**
     * Adiciona uma lista de transições ao autômato.
     *
     * @param transicoes
     */
    public void setTransicoes(ArrayList<Transicao> transicoes) {
        this.transicoes = transicoes;
    }

    /**
     * Pega uma determinada transicao do automato.
     *
     * @param origem
     * @param destino
     * @param valor
     * @return transicao
     */
    public Transicao getTransicao(int origem, int destino, char valor) {
        Iterator<Transicao> t = transicoes.iterator();
        while (t.hasNext()) {
            if ((t.next().getOrigem() == origem) && (t.next().getDestino() == destino)
                    && (t.next().getValor() == valor)) {
                return t.next();
            }
        }
        return null;
    }

    /**
     * Adiciona uma transição ao estado.
     *
     * @param transicao
     */
    public void addTransicao(Transicao transicao) {
        this.transicoes.add(transicao);
    }

    /**
     * Apaga uma transição.
     *
     * @param transicao
     */
    public void removeTransicao(Transicao transicao) {
        Iterator<Transicao> t = transicoes.iterator();
        Transicao aux;
        while (t.hasNext()) {
            aux = t.next();
            if (aux.equals(transicao)) {
                t.remove();
            }
        }
    }

    /**
     * Retorna o próximo estado de transição de acordo com o valor.
     *
     * @param valor
     * @return id destino
     */
    public int transita(char valor) {
        Iterator<Transicao> t = transicoes.iterator();
        while (t.hasNext()) {
            if (t.next().getValor() == valor) {
                return t.next().getDestino();
            }
        }
        return 0;
    }

    /**
     * Adiciona um estado ao automato.
     *
     * @param estado
     */
    public void addEstado(Estado estado) {
        this.estados.add(estado);
    }

    /**
     * Apaga um estado de um autômato.
     *
     * @param estado
     */
    public void removeEstado(Estado estado) {
        Iterator<Estado> e = estados.iterator();
        Estado aux;
        while (e.hasNext()) {
            aux = e.next();
            if (aux.equals(estado)) {
                e.remove();
            }
        }
    }

    /**
     *
     * @param caminho
     * @throws JDOMException
     * @throws IOException
     */
    public void carrega(String caminho) throws JDOMException, IOException {
        File arquivo = new File(caminho);
        if (arquivo.exists()) {
            SAXBuilder construtor = new SAXBuilder();
            Document xml = construtor.build(arquivo);
            Element raiz = (Element) xml.getRootElement();
            if (raiz.getChildTextNormalize("type").equals("fa")) {
                List<Element> listaEstados = raiz.getChild("automaton").getChildren("state");
                List<Element> listaTransicoes = raiz.getChild("automaton").getChildren("transition");
                Iterator<Element> elemEstado = listaEstados.iterator();
                while (elemEstado.hasNext()) {
                    Element estado = (Element) elemEstado.next();
                    Estado estadoAux = new Estado();
                    estadoAux.setId(Integer.parseInt(estado.getAttributeValue("id")));
                    if (estado.getChild("initial") != null) {
                        estadoAux.setInicial();
                    }
                    if (estado.getChild("final") != null) {
                        estadoAux.setFinal();
                    }
                    estados.add(estadoAux);
                }

                String s;
                Iterator<?> elementoTransicao = listaTransicoes.iterator();
                while (elementoTransicao.hasNext()) {
                    Transicao transicaoAux = new Transicao();
                    Element transicao = (Element) elementoTransicao.next();
                    transicaoAux.setOrigem(Integer.parseInt(transicao.getChildText("from")));
                    transicaoAux.setDestino(Integer.parseInt(transicao.getChildText("to")));
                    s = transicao.getChildText("read");
                    transicaoAux.setValor(s.charAt(0));
                    // Adiciona simbolo na lista do alfabeto
                    if (!this.getAlfabeto().contains(s.charAt(0))) {
                        this.getAlfabeto().add(s.charAt(0));
                    }
                    transicoes.add(transicaoAux);
                }

            } else {
                System.out.println("O arquivo carregado não corresponde a um autômato!");
                System.exit(0);
            }
        } else {
            System.out.println("Erro ao ler o arquivo '" + caminho + "'."
                    + " O arquivo não existe!");
            System.exit(0);
        }
    }

    public void Salve(String caminhoSaida) throws IOException {
        Document documento = new Document();

        Element raiz = new Element("structure");
        Element automato = new Element("automaton");
        Element tipo = new Element("type");
        tipo.setText("fa");
        raiz.addContent(tipo);
        raiz.addContent(automato);

        double xl = 175.0;
        double yl = 150.0;

        Iterator<Estado> itEstado = estados.iterator();
        while (itEstado.hasNext()) {
            Estado estadoAux;
            estadoAux = itEstado.next();
            Element estado = new Element("state");
            Attribute id = new Attribute("id", Integer.toString(estadoAux.getId()));
            Attribute name = new Attribute("name", "q" + Integer.toString(estadoAux.getId()));
            estado.setAttribute(id);
            estado.setAttribute(name);
            Element x = new Element("x");
            Element y = new Element("y");
            x.setText(Double.toString(xl));
            y.setText(Double.toString(yl));
            estado.addContent(x);
            estado.addContent(y);
            if (estadoAux.ehInicial()) {
                Element inicial = new Element("initial");
                estado.addContent(inicial);
            }
            if (estadoAux.ehFinal()) {
                Element eFinal = new Element("final");
                estado.addContent(eFinal);
            }
            automato.addContent(estado);
            xl += 75;
            yl += 50;
        }
        Iterator<Transicao> itTransicao = transicoes.iterator();
        while (itTransicao.hasNext()) {
            Transicao transicao = (Transicao) itTransicao.next();
            Element elemTransicao = new Element("transition");
            Element origem = new Element("from");
            Element destino = new Element("to");
            Element valor = new Element("read");
            origem.setText(String.valueOf(transicao.getOrigem()));
            destino.setText(String.valueOf(transicao.getDestino()));
            valor.setText(Character.toString(transicao.getValor()));
            elemTransicao.addContent(origem);
            elemTransicao.addContent(destino);
            elemTransicao.addContent(valor);
            automato.addContent(elemTransicao);
        }
        documento.setRootElement(raiz);
        XMLOutputter saidaXML = new XMLOutputter();
        OutputStream saida = new FileOutputStream(new File(caminhoSaida));
        saidaXML.output(documento, saida);
    }

    //implementar equivalência
    //implementar minimização
    //implementar complementação, união, interseção, diferença
}
