
package epCliente;

import Part.Pares;
import Part.Part;
import Part.PartsRepository;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    /*Classe responsavel pela interacao com o Cliente, por meio de uma interface linha de comando. 
      Pede-se o nome do servidor no qual o Cliente deseja realizar a conexao inicial antes de disponibilizar os comandos. 
      Contem as referencias para o repositorio/servidor, Part e lista de subcomponentes correntes. 
      Responsavel tambem por interagir com a classe Gestor, durante o processamento dos comandos recebidos do usuario.*/

   public static void main (String[] args) throws NotBoundException, MalformedURLException, RemoteException{
        int codigoCorrente = -1; //inicialmente nao ha part corrente
        boolean achou = false; //variavel que define se a part foi encontrada no repositorio
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do servidor:");
        String servidor = sc.nextLine();
        Mensageiro gerente = (Mensageiro) Naming.lookup("rmi://localhost/"+servidor);//realiza conexao com servidor por via do rmi
        System.out.println(gerente.sucesso());
        LinkedList<Pares> subCompCorrentes = new LinkedList<Pares>();
        while(true){ //Permite constante espera de input do usuario
            System.out.println("----------------------");
            System.out.println("Digite um comando:");
            String comando = sc.nextLine();
            String[] comandoSplit = comando.split(" "); //separa caso comando exija um argumento
            if(comandoSplit[0].equals("quit")) 
                System.exit(0); //quit fecha o programa
            else if(comandoSplit[0].equals("bind")){
                if(comandoSplit.length > 1){ //exige argumento com nome do servidor
                    try  
                    {
                        gerente = (Mensageiro) Naming.lookup("rmi://localhost/"+comandoSplit[1]); //estabelece nova conexao                  
                        codigoCorrente = -1; //Reseta a part corrente pois o id repete em diferentes servidores
                        System.out.println(gerente.sucesso()); 
                        servidor = comandoSplit[1]; //variavel que recebe o nome do servidor dado como argumento
                    }
                    catch(NotBoundException nb)
                    {
                        System.out.println("Servidor inexistente");
                    }
                        
                }
                else
                    System.out.println("Digite o nome do servidor junto do comando");                
            }
           
            else if(comandoSplit[0].equals("getp")){
                if(comandoSplit.length > 1){//exige argumento com o codigo da parte
                    achou = gerente.getp(Integer.parseInt(comandoSplit[1])); //checa se part existe
                    if(achou == true){
                        codigoCorrente = Integer.parseInt(comandoSplit[1]); //se existe, torna-se corrente
                        System.out.println("getp executado com sucesso");
                    }
                    else
                        System.out.println("A Part desejada nao foi encontrada");
                }
                else
                    System.out.println("Digite o codigo da Part desejada junto ao comando");
            }
            else if(comandoSplit[0].equals("showp")){
                if(codigoCorrente == -1) //checa se ha uma part corrente
                    System.out.println("Nao ha peca corrente. Utilize o comando: getp <codigoParte>");
                else{
                    System.out.println("Atributos da peca corrente:");
                    ArrayList<String> respostas = gerente.showp(codigoCorrente);
                    for(int r = 0; r < respostas.size(); r++) //lista os atributos da part corrente
                        System.out.println(respostas.get(r));
                }

            }
            else if(comandoSplit[0].equals("clearlist")){
                System.out.println("Limpando a lista de subcomponentes corrente...");
                subCompCorrentes.clear(); //limpa a lista de subcomponentes correntes 
                System.out.println("clearlist executado com sucesso");
            }
            else if(comandoSplit[0].equals("addsubpart")){
                if(codigoCorrente == -1) //checa se ha uma parte corrente
                    System.out.println("Nao ha peca corrente. Utilize o comando: getp <codigoParte>");
                else
                {
                    int quantidade = 1; //quantidade default 
                    if(comandoSplit.length > 1) //checa se quantidade foi passada como argumento
                        quantidade = Integer.parseInt(comandoSplit[1]);
                    Part p = gerente.getPart(codigoCorrente); //recebe a part corrente
                    Pares pr = new Pares(p, quantidade); //cria o par subpart e quantidade correspondente
                    subCompCorrentes.add(pr); //adiciona na lista de subcomponentes correntes
                    System.out.println("Subparte adicionada com sucesso");
                }
            }
            else if(comandoSplit[0].equals("addp")){ 
                int confirma = -1; //variavel que recebe a decisao do usuario para confirmar os dados da nova part a ser inserida
                String comandoConfirma = null; //recebe input
                String nome = null; //nome da part
                String descricao = null; //descricao da part
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
                        confirma = 0; //sai do while, cancela o comando e não a adiciona
                }
                if(confirma ==1){ //insere part no repositorio com a lista de subcomponentes correntes como suas subparts
                    gerente.insereParte(nome, descricao, subCompCorrentes);
                    System.out.println("addp executado com sucesso");
                    subCompCorrentes.clear(); //esvazia a lista de subComponentesCorrentes apos inserir na part nova
                }
            }
            else if(comandoSplit[0].equals("listp")){ 
                System.out.println("O servidor corrente é: "+ servidor); //mostra o nome do repositorio corrente
                ArrayList<String> respostas = gerente.exibirPartes();    
                for(int r = 0; r < respostas.size(); r++) //exibe as parts do repositorio
                {
                    System.out.println(respostas.get(r));
                }
            }
            else if(comandoSplit[0].equals("checkp")){
                if(codigoCorrente == -1) //checa se ha uma part corrente 
                    System.out.println("Nao ha peca corrente. Utilize o comando: getp <codigoParte>");
                else{
                    System.out.println(gerente.checkp(codigoCorrente)); //procura a parte e recupera a resposta
                }
            }
            else if(comandoSplit[0].equals("exibesubp")){ 
                if(subCompCorrentes.size() == 0) // checa se a lista de subComponentesCorrentes esta vazia
                    System.out.println("Nao ha subComponentes corrente. Execute o comando addsubpart");
                for(int s = 0; s < subCompCorrentes.size(); s++){ //exibe o nome das subparts e suas respectivas quantidades
                    System.out.println(subCompCorrentes.get(s).subPart.nome + ", " + subCompCorrentes.get(s).quantidade);
                }
            }
        }
    } 
}