
package Part;

import java.io.Serializable;
import java.util.LinkedList;

/*Esta classe implementa o Objeto Part que possui os atributos codigo, 
nome, descricao que são recebidos pelo usuario na linha de comando, 
e finalmente uma lista ligada seus subcomponentes.*/
public class Part implements Serializable {
    public int codigo; //gerado automaticamente pelo Gestor;
    public String nome;
    public String descricao;
    public LinkedList<Pares> subcomponentes = new LinkedList<Pares>();
    
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
