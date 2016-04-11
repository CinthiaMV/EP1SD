
package epServidor;

import Part.PartsRepository;
import Part.Pares;
import Part.Part;
import epCliente.Mensageiro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
/*Classe que implementa a interface Mensageiro, 
responsavel por executar as funcoes pedidas pelo Cliente para o servidor. 
Tambem armazena o repositorio do servidor, e tambem o seu nome, definido na execucao do Main do servidor.*/

public class Gestor extends UnicastRemoteObject implements Mensageiro{
    String nome = null; //nome do Servidor
    PartsRepository repositorio = new PartsRepository(); // Repositorio do servidor
    protected Gestor() throws RemoteException{
        super();
    }
    
    public void setNome(String nome) throws RemoteException{
        this.nome = nome;
    }
    
    //Alerta conexao bem sucedida
    @Override
    public String sucesso() throws RemoteException{
        return "conexao com sucesso no servidor " + nome;
    }
    
    //Getp procura o codigo fornecido pelo Cliente no repositorio do servidor e retorna se foi encontrado
    @Override
    public boolean getp(int codigo) throws RemoteException {
        for (int x = 0; x < repositorio.partes.size(); x++){
            if(repositorio.partes.get(x).codigo == codigo){
                return true;
            }
        }
        return false;
    }

    //Retorna uma array de string descrevendo cada caracteristica da part especificada
    @Override
    public ArrayList<String> showp(int codigo) throws RemoteException {
        Part parte = null;
        ArrayList<String> respostas = new ArrayList<String>();
        //procura pela parte corrente
        for (int x = 0; x < repositorio.partes.size(); x++){
            if(repositorio.partes.get(x).codigo == codigo){
                parte = repositorio.partes.get(x);
                x = repositorio.partes.size();
            }
        }
        if(parte != null){
            //recupera as descricoes da part
            respostas.add("Nome: " + parte.nome);  
            respostas.add("Codigo: " + parte.codigo);
            respostas.add("Descricao: " + parte.descricao);
            respostas.add("Subcomponentes:");
            int c = 0;
            for(c = 0; c < parte.subcomponentes.size(); c++ ){
                if(parte.subcomponentes.get(c).subPart.subcomponentes.size() > 0)
                    respostas.add("Nome: " + parte.subcomponentes.get(c).subPart.nome + " Quantidade: "+parte.subcomponentes.get(c).quantidade + " Tipo: Agregado");
                else
                    respostas.add("Nome: " + parte.subcomponentes.get(c).subPart.nome + " Quantidade: "+parte.subcomponentes.get(c).quantidade + " Tipo: Primitivo");
            }
            respostas.add("Numero total de subcomponentes: "+ c);       
        }
        return respostas;
    }
  
    //Insere part desejada no repositorio desejada, atribuindo codigo automatico 
    @Override
    public void insereParte(String nome, String descricao,LinkedList<Pares> subCompCorrentes) throws RemoteException {
        int codigo = 1; //padrao caso lista vazia
        if(repositorio.partes.size() != 0)
            codigo = repositorio.partes.getLast().codigo + 1; //garante que o codigo sera o maior numero da lista sem repeticao
        Part parteNova = new Part(codigo, nome, descricao, subCompCorrentes); //instancia nova part
        repositorio.partes.add(parteNova);
    }
    
    //Retorna um array de Strings com todas as partes do repositorio, enumerando-as
    @Override
    public ArrayList<String> exibirPartes() throws RemoteException {
        ArrayList<String> respostas = new ArrayList<String>();
        for(int v=0; v< repositorio.partes.size(); v++){
            respostas.add("Parte "+ (v+1) +": " + repositorio.partes.get(v).nome);
        }
        return respostas;
    }

    //Retorna se tipo da part: agregada ou primitiva
    @Override
    public String checkp(int codigoCorrente) throws RemoteException {
        for(int s = 0; s<repositorio.partes.size();s++){ //busca part corrente
            if(repositorio.partes.get(s).codigo == codigoCorrente){
                if(repositorio.partes.get(s).subcomponentes.size() > 0) //checa tamanho da lista de subpartes
                    return "Parte corrente agregada"; //part com subpartes
                else
                    return "Parte corrente primitiva"; //part sem subpart, tamanho da lista de subpartes = 0
            }
        }
        return "parte nao encontrada";
    }

    // Retorna a part desejada pelo cliente
    @Override
    public Part getPart(int codigoCorrente) throws RemoteException {
        Part p = null;
        for(int c = 0; c<repositorio.partes.size();c++){
            if(repositorio.partes.get(c).codigo == codigoCorrente){ 
                p = repositorio.partes.get(c);
            }
                
        }
        return p;
    }
}
