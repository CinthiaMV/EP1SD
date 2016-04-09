/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epServidor;

import java.util.LinkedList;

public class Part {
    int codigo;
    String nome;
    String descricao;
    LinkedList<Pares> subcomponentes = new LinkedList<Pares>();
    
    public Part(int codigo, String nome, String descricao, LinkedList<Pares> subcomponentes){
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        for(int c = 0; c < subcomponentes.size(); c++){
            Pares par = new Pares(subcomponentes.get(c).subPart, subcomponentes.get(c).quantidade);
            this.subcomponentes.add(par);
        }
    }
    
}
