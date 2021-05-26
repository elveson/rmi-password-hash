import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Elveson S. Costa
 * @created 26/05/2021 - 8:22 PM
 * @project SD_RMI_PasswordHash
 */
public class HashServer implements Hash {
    private static final String SIMPLE_SALT = "0983ab392837de37838f9873aass3983acda323f";

    public HashServer() {
    }

    public static void main(String[] args) {
        try {
            HashServer server = new HashServer();
            //Exporta o server para o stub ("apendice ou toco") do RMI na porta 0
            Hash stub = (Hash) UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.getRegistry();
            // Registra a stub no RMI para que ela seja obtida pelos clientes
            registry.bind("Servidor", stub);

            System.out.println("Servidor inicializado com sucesso.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String bytesToHexa(byte[] messageDigest) {
        StringBuilder hexaStringBuilder = new StringBuilder();
        for (byte b : messageDigest) {
            hexaStringBuilder.append(String.format("%02X", 0xFF & b));
        }
        return hexaStringBuilder.toString();
    }

    public String passToHash(String password) throws NoSuchAlgorithmException {
        MessageDigest algoritmo = MessageDigest.getInstance("SHA3-256");
        byte[] hash = algoritmo.digest((password + SIMPLE_SALT).getBytes(StandardCharsets.UTF_8));

        return bytesToHexa(hash);
    }

    public String passwordHash(String s) throws RemoteException, NoSuchAlgorithmException {
        System.out.println("Executando metodo neste host");
        return "O Hash da senha e: " + passToHash(s);
    }

}
