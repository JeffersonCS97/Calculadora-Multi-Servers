import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Servidor de Divisão: executa operação de divisão.


public class DivisaoServer {

	public static void main(String []arg) throws IOException {
				
		try {
			
			while (true) {
				ServerSocket serverSocket = new ServerSocket(6001);
				Socket sc = serverSocket.accept();
				try {
					System.out.println("Obtendo solicitação de divisão...");
					DataInputStream inFromClient = new DataInputStream(sc.getInputStream());
					int primeiro = inFromClient.readInt();
					int segundo = inFromClient.readInt();
				
				//	PrintWriter pr = new PrintWriter(sc.getOutputStream(), true);
					String error = null;
					if (segundo == 0) {
						error = "Ocorreu um erro. O denominador não deve ser zero.";
					}
					DataOutputStream outFromClient = new DataOutputStream(sc.getOutputStream());
					if (segundo != 0) {
						outFromClient.writeUTF("A resposta da operação de divisão é: " + (primeiro / segundo));
					}
					else {
						outFromClient.writeUTF(error);
					}
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
