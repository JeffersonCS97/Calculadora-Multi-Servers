import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


// Servidor de Potenciação: executa operação de potenciação.


public class PotenciacaoServer {

	public static void main(String []arg) throws IOException {
		
		try {
			
			while (true) {
				ServerSocket serverSocket = new ServerSocket(6004);
				Socket sc = serverSocket.accept();
				try {
					System.out.println("Obtendo solicitação de potenciação...");
					DataInputStream inFromClient = new DataInputStream(sc.getInputStream());
					int primeiro = inFromClient.readInt();
					int segundo = inFromClient.readInt();

				//	PrintWriter pr = new PrintWriter(sc.getOutputStream(), true);
					double resposta = Math.pow(primeiro, segundo);
					
					DataOutputStream outFromClient = new DataOutputStream(sc.getOutputStream());
					
					outFromClient.writeUTF("A resposta da operação de potenciação é: " + resposta);
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
