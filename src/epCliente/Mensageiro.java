
package epCliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Mensageiro extends Remote {
    public String sucesso() throws RemoteException;
    public void listarp();
}
