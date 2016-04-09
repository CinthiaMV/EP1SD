/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epServidor;

import epCliente.Mensageiro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;


public class Gestor extends UnicastRemoteObject implements Mensageiro{
    String nome = null;
    PartsRepository repositorio = new PartsRepository();
    LinkedList<Pares> subcomponentesCorrentes = new LinkedList<Pares>();
    protected Gestor() throws RemoteException{
        super();
    }
    
    public void setNome(String nome) throws RemoteException{
        this.nome = nome;
    }
    
    @Override
    public String sucesso() throws RemoteException{
        return "conexao com sucesso no servidor " + nome;
    }

    @Override
    public void listarp() {
        repositorio.listar();
    }

    @Override
    public boolean getp(int codigo) throws RemoteException {
        for (int x = 0; x < repositorio.partes.size(); x++){
            if(repositorio.partes.get(x).codigo == codigo){
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> showp(int codigo) throws RemoteException {
        Part parte = null;
        ArrayList<String> respostas = new ArrayList<String>();
        for (int x = 0; x < repositorio.partes.size(); x++){
            if(repositorio.partes.get(x).codigo == codigo){
                parte = repositorio.partes.get(x);
                x = repositorio.partes.size();
            }
        }
        if(parte != null){
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

    @Override
    public void clearlist() throws RemoteException {
        subcomponentesCorrentes.clear();
    }    

    @Override
    public void addsubpart(int codigo, int quantidade) throws RemoteException {
       for(int x = 0; x < repositorio.partes.size(); x++){
           if(repositorio.partes.get(x).codigo == codigo){
               Pares correntes = new Pares(repositorio.partes.get(x), quantidade);
               subcomponentesCorrentes.add(correntes);
           }
       }
           
    }

    @Override
    public void insereParte(String nome, String descricao) throws RemoteException {
        int codigo = 1;
        if(repositorio.partes.size() != 0)
            codigo = repositorio.partes.getLast().codigo + 1;
        Part parteNova = new Part(codigo, nome, descricao, subcomponentesCorrentes);
        repositorio.partes.add(parteNova);
        subcomponentesCorrentes.clear();
    }

    @Override
    public ArrayList<String> exibirPartes() throws RemoteException {
        ArrayList<String> respostas = new ArrayList<String>();
        for(int v=0; v< repositorio.partes.size(); v++){
            respostas.add("Parte "+ (v+1) +": " + repositorio.partes.get(v).nome);
        }
        return respostas;
    }

    @Override
    public String checkp(int codigoCorrente) throws RemoteException {
        for(int s = 0; s<repositorio.partes.size();s++){
            if(repositorio.partes.get(s).codigo == codigoCorrente){
                if(repositorio.partes.get(s).subcomponentes.size() > 0)
                    return "Parte corrente agregada";
                else
                    return "Parte corrente primitiva";
            }
        }
        return "parte nao encontrada";
    }
}
