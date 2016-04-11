
package Part;

import Part.Part;
import java.io.Serializable;
import java.util.LinkedList;

//Classe responsavel pela lista de todas as partes contidas em um servidor
public class PartsRepository implements Serializable {
    public LinkedList<Part> partes = new LinkedList<>();
    
}
