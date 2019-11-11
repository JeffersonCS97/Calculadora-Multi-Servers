import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server implements Runnable {

    public Socket cliente;

    private static List<Socket> listaDeClientes = new ArrayList<Socket>();

    public Server(Socket cliente) {
        this.cliente = cliente;
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(12345);

        System.out.println("Aguardando conexao do cliente...");

        while (true) {
            Socket cliente = serverSocket.accept();

            listaDeClientes.add(cliente);

            Server tratamento = new Server(cliente);
            Thread t = new Thread(tratamento);

            t.start();
        }
    }

    public void run() {
       
		try {

			while (true) {
			//	ServerSocket serverSocket = new ServerSocket(9000);
			//	Socket sc = serverSocket.accept();
				try {
					System.out.println("Obtendo a solicitação principal...");
					DataInputStream inFromClient = new DataInputStream(cliente.getInputStream());
					int primeiro = inFromClient.readInt();
					int segundo = inFromClient.readInt();
					String operacaoSelecionada = inFromClient.readUTF();
					int port = 0;
					// Porta de configuração de acordo com a solicitação
					System.out.println(operacaoSelecionada);
					if ("adi".equals(operacaoSelecionada)) {
						port = 6000;
					} else if ("sub".equals(operacaoSelecionada)) {
						port = 6006;
					} else if ("mul".equals(operacaoSelecionada)) {
						port = 6002;
					} else if ("div".equals(operacaoSelecionada)) {
						port = 6001;
					} else if ("por".equals(operacaoSelecionada)) {
						port = 6003;
					} else if ("rad".equals(operacaoSelecionada)) {
						port = 6005;
					} else if ("pot".equals(operacaoSelecionada)) {
						port = 6004;
					}

					// Conectando com o servidor real
					
					System.out.println(port);
					Socket opSocket = new Socket("localhost", port);
					DataOutputStream opToClient = new DataOutputStream(opSocket.getOutputStream());
					opToClient.writeInt(primeiro);
					opToClient.writeInt(segundo);

					// Obtendo resposta
					DataInputStream opInStream = new DataInputStream(opSocket.getInputStream());
					String resposta = opInStream.readUTF();

					// Enviando resposta ao cliente
					DataOutputStream outFromClient = new DataOutputStream(cliente.getOutputStream());
					outFromClient.writeUTF(resposta);

					// outFromClient.flush();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
}
