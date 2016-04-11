
package Part;

import Part.Part;
import java.io.Serializable;
/*Esta classe implementa o Objeto Pares, que compõe a lista ligada de subcomponentes do Objeto Part. 
Consiste de pares de subPart, que é uma parte, e quantidade de vezes que o subcomponente compõe uma parte.*/
public class Pares implements Serializable{
    public Part subPart = null;
    public int quantidade = -1;//O atributo quantidade pode ser recebido pelo usuário, porém, caso omitido, o default é 1. 
    public Pares (Part subPart, int quantidade){
        this.subPart = subPart;
        this.quantidade = quantidade;
    }
}
