/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epServidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.LinkedList;

public class Main {
    
    public static void main (String[] args) throws RemoteException, MalformedURLException{
       if(args.length == 0)
           System.out.println("Execute como: java Main <nomeServidor>");
       Gestor gestor = new Gestor();
       Naming.rebind("http://localhost/"+args[0], gestor);
   } 
}
