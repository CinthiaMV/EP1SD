
package epServidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class Main {
    /*Classe responsavel por criar o servidor, podendo ser executada multiplas vezes e concorrentemente. 
    Deve ser executada como java Main <nomeDoServidor> em que esse nome de servidor devera ser utilizado pelo Cliente 
    posteriormente para estabelecer conexao com o servidor gerado.*/
    
    public static void main (String[] args) throws RemoteException, MalformedURLException{
       if(args.length == 0)
           System.out.println("Execute como: java Main <nomeServidor>");
       Gestor gestor = new Gestor();
       Naming.rebind("rmi://localhost/"+args[0], gestor);
       gestor.setNome(args[0]);
   } 
}
