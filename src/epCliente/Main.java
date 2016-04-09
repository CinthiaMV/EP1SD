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
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   public static void main (String[] args) throws NotBoundException, MalformedURLException, RemoteException{
        int codigoCorrente = -1;
        boolean achou = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do servidor:");
        String servidor = sc.nextLine();
        Mensageiro gerente = (Mensageiro) Naming.lookup("rmi://localhost/"+servidor);
        System.out.println(gerente.sucesso());
        while(true){
            System.out.println("Digite um comando:");
            String comando = sc.nextLine();
            String[] comandoSplit = comando.split(" ");
            if(comandoSplit[0].equals("quit"))
                System.exit(0);
            else if(comandoSplit[0].equals("bind")){
                if(comandoSplit.length > 1){
                    try  
                    {
                        gerente = (Mensageiro) Naming.lookup("rmi://localhost/"+comandoSplit[1]);                   
                        codigoCorrente = -1;
                        System.out.println(gerente.sucesso());
                        servidor = comandoSplit[1];
                    }
                    catch(NotBoundException nb)
                    {
                        System.out.println("Servidor inexistente");
                    }
                        
                }
                else
                    System.out.println("Digite o nome do servidor junto do comando");                
            }
            else if(comandoSplit[0].equals("listp")){
                gerente.listarp();
            }
            else if(comandoSplit[0].equals("getp")){
                if(comandoSplit.length > 1){
                    achou = gerente.getp(Integer.parseInt(comandoSplit[1]));
                    if(achou == true)
                        codigoCorrente = Integer.parseInt(comandoSplit[1]);
                    else
                        System.out.println("A Part desejada nao foi encontrada");
                }
                else
                    System.out.println("Digite o codigo da Part desejada junto ao comando");
            }
            else if(comandoSplit[0].equals("showp")){
                if(codigoCorrente == -1)
                    System.out.println("Nao ha peca corrente. Utilize o comando: getp <codigoParte>");
                else{
                    System.out.println("Atributos da peca corrente:");
                    ArrayList<String> respostas = gerente.showp(codigoCorrente);
                    for(int r = 0; r < respostas.size(); r++)
                        System.out.println(respostas.get(r));
                }

            }
            else if(comandoSplit[0].equals("clearlist")){
                System.out.println("Limpando a lista de subcomponentes corrente...");
                gerente.clearlist();

            }
            else if(comandoSplit[0].equals("addsubpart")){
                if(codigoCorrente == -1)
                    System.out.println("Nao ha peca corrente. Utilize o comando: getp <codigoParte>");
                else
                {
                    int quantidade = 1;
                    if(comandoSplit.length > 1)
                        quantidade = Integer.parseInt(comandoSplit[1]);
                    gerente.addsubpart(codigoCorrente, quantidade);
                }
            }
            else if(comandoSplit[0].equals("addp")){
                int confirma = -1;
                String comandoConfirma = null;
                String nome = null;
                String descricao = null;
                while(confirma == -1){
                    System.out.println("Informe o nome da peca: ");
                    nome = sc.nextLine();
                    System.out.println("Informe a descricao da peca: ");
                    descricao = sc.nextLine();
                    System.out.println("Confirma os dados? Digite sim, nao ou cancela");
                    comandoConfirma = sc.nextLine();
                    if(comandoConfirma.equals("sim"))
                        confirma = 1;
                    else if(comandoConfirma.equals("nao"))
                        confirma = -1;
                    else
                        confirma = 0;
                }
                if(confirma ==1){
                    gerente.insereParte(nome, descricao);
                }
            }
            else if(comandoSplit[0].equals("exibp")){
                System.out.println("O servidor corrente é: "+ servidor);
                ArrayList<String> respostas = gerente.exibirPartes();    
                for(int r = 0; r < respostas.size(); r++)
                {
                    System.out.println(respostas.get(r));
                }
            }
            else if(comandoSplit[0].equals("checkp")){
                if(codigoCorrente == -1)
                    System.out.println("Nao ha peca corrente. Utilize o comando: getp <codigoParte>");
                else{
                    System.out.println(gerente.checkp(codigoCorrente));
                }
            }
        }
    } 
}
