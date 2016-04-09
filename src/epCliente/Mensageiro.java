
package epCliente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Mensageiro extends Remote {
    public String sucesso() throws RemoteException;
    public void listarp() throws RemoteException;
    public boolean getp(int codigo) throws RemoteException;
    public ArrayList<String> showp(int codigo) throws RemoteException;
    public void clearlist() throws RemoteException;
    public void addsubpart(int codigo, int quantidade) throws RemoteException;
    public void insereParte(String nome, String descricao) throws RemoteException;
    public  ArrayList<String> exibirPartes() throws RemoteException;
    public String checkp(int codigoCorrente)throws RemoteException;
}
