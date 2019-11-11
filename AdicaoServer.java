import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


// Servidor de Adição: executa operação de adição.

 
public class AdicaoServer {

	public static void main(String []arg) throws IOException {
		
		try {
			
			while (true) {
				ServerSocket serverSocket = new ServerSocket(6000);
				Socket sc = serverSocket.accept();
				try {
					System.out.println("Obtendo solicitação de adição...");
					DataInputStream inFromClient = new DataInputStream(sc.getInputStream());
					int primeiro = inFromClient.readInt();
					int segundo = inFromClient.readInt();

					PrintWriter pr = new PrintWriter(sc.getOutputStream(), true);
					int resultado = primeiro + segundo ;
					
					DataOutputStream outFromClient = new DataOutputStream(sc.getOutputStream());
					
					outFromClient.writeUTF("A resposta da operação de adição é: " + resultado);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					sc.close();
					serverSocket.close();
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
