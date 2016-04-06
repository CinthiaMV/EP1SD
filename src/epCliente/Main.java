/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epCliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {
   public static void main (String[] args) throws NotBoundException, MalformedURLException, RemoteException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do servidor:");
        String servidor = sc.nextLine();
        Mensageiro gerente = (Mensageiro) Naming.lookup("http://localhost/"+servidor);
        System.out.println(gerente.sucesso());
        while(true){
            System.out.println("Digite um comando:");
            String comando = sc.nextLine();
            String[] comandoSplit = comando.split(" ");
            if(comandoSplit[0].equals("quit"))
                System.exit(0);
            else if(comandoSplit[0].equals("bind")){
                if(comandoSplit.length > 1){
                    gerente = (Mensageiro) Naming.lookup("http://localhost/"+comandoSplit[1]);
                    System.out.println(gerente.sucesso());
                }
                else
                    System.out.println("Digite o nome do servidor junto do comando");                
            }
            else if(comandoSplit[0].equals("listp")){
                gerente.listarp();
            }
            else if(comandoSplit[0].equals("getp")){

            }
            else if(comandoSplit[0].equals("showp")){

            }
            else if(comandoSplit[0].equals("clearlist")){

            }
            else if(comandoSplit[0].equals("addsubpart")){

            }
            else if(comandoSplit[0].equals("addp")){

            }
        }
    } 
}
