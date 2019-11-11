import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


// Servidor de Radiciação: executa operação de radiciação.


public class RaizQuadradaServer {

	public static void main(String []arg) throws IOException {
		
		try {
			
			while (true) {
				ServerSocket serverSocket = new ServerSocket(6005);
				Socket sc = serverSocket.accept();
				try {
				System.out.println("Obtendo solicitação de radiciação...");
				DataInputStream inFromClient = new DataInputStream(sc.getInputStream());
				int primeiro = inFromClient.readInt();
				System.out.println("primeiro numero" + primeiro);
			//	int segundo = inFromClient.readInt();

			//	PrintWriter pr = new PrintWriter(sc.getOutputStream(), true);
				double resultado = Math.sqrt(primeiro);
				System.out.println("raiz" + resultado);
				
				DataOutputStream outFromClient = new DataOutputStream(sc.getOutputStream());
				
				outFromClient.writeUTF("A resposta da operação de radiciação é: " + resultado);
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
