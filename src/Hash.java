import java.io.UnsupportedEncodingException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Elveson S. Costa
 * @created 26/05/2021 - 7:37 PM
 * @project SD_RMI_PasswordHash
 */
public interface Hash extends Remote {
    public String passwordHash(String s) throws RemoteException, UnsupportedEncodingException, NoSuchAlgorithmException;
}
