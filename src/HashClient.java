import java.rmi.registry.*;
import java.util.Scanner;

/**
 * @author Elveson S. Costa
 * @created 26/05/2021 - 9:43 PM
 * @project SD_RMI_PasswordHash
 */
public class HashClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // Procura o registro do RMI no Servidor
            Registry registry = LocateRegistry.getRegistry("localhost");

            // Procura a stub do servidor
            Hash stub = (Hash) registry.lookup("Servidor");

            // Chama o método do servidor e imprime a mensagem
            System.out.println("Digite uma senha: ");

            String password = sc.nextLine();
            System.out.println("\n\nMensagem retornada pelo Servidor: \n" + stub.passwordHash(password));
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }
    }
}
