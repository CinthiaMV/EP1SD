
package epCliente;

import Part.Pares;
import Part.Part;
import Part.PartsRepository;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;

//Interface responsavel pela comunicaçao entre Cliente e Servidor, atraves do Gestor. 

public interface Mensageiro extends Remote {
    public String sucesso() throws RemoteException;
    public boolean getp(int codigo) throws RemoteException;
    public ArrayList<String> showp(int codigo) throws RemoteException;
    public void insereParte(String nome, String descricao, LinkedList<Pares> subCompCorrentes) throws RemoteException;
    public  ArrayList<String> exibirPartes() throws RemoteException;
    public String checkp(int codigoCorrente) throws RemoteException;
    public Part getPart(int codigoCorrente) throws RemoteException;
}
