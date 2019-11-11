import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


// Servidor de Porcentagem: executa operação de porcentagem.


public class PorcentagemServer {

	public static void main(String []arg) throws IOException {
		
		try {
			
			while (true) {
				ServerSocket serverSocket = new ServerSocket(6003);
				Socket sc = serverSocket.accept();
				try {
					System.out.println("Obtendo solicitação de porcentagem...");
					DataInputStream inFromClient = new DataInputStream(sc.getInputStream());
					int primeiro = inFromClient.readInt();
					int segundo = inFromClient.readInt();

				//	PrintWriter pr = new PrintWriter(sc.getOutputStream(), true);
					int Resposta = (segundo * primeiro) / 100;
					
					DataOutputStream outFromClient = new DataOutputStream(sc.getOutputStream());
					
					outFromClient.writeUTF("A resposta da operação de porcentagem é: " + Resposta);
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
