import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


// Este é o Arquivo do Cliente, onde é escolhida a operação a ser feita.


public class Cliente {

	public static void main(String arg[]) throws UnknownHostException, IOException {

		try {

			boolean continua = true;

			while (continua) {
				int operacaoSelecionadaNumero = 0;
				// Solicitando seleção de operação ao usuário
				System.out.println("Qual operação você deseja executar? Por favor, digite o número do índice: ");
				boolean operacaoValida = false;
				Scanner scanner = new Scanner(System.in);
				String operacaoSelecionada = null;
				while (!operacaoValida) {

					System.out.println("1. Adição");
					System.out.println("2. Subtração");
					System.out.println("3. Multiplicação");
					System.out.println("4. Divisão");
					System.out.println("5. Porcentagem");
					System.out.println("6. Raiz Quadrada");
					System.out.println("7. Potenciação");

					operacaoSelecionadaNumero = scanner.nextInt();

					switch (operacaoSelecionadaNumero) {

						case 1:
							operacaoSelecionada = "adi";
							operacaoValida = true;
							break;
						case 2:
							operacaoSelecionada = "sub";
							operacaoValida = true;
							break;
						case 3:
							operacaoSelecionada = "mul";
							operacaoValida = true;
							break;
						case 4:
							operacaoSelecionada = "div";
							operacaoValida = true;
							break;
						case 5:
							operacaoSelecionada = "por";
							operacaoValida = true;
							break;
						case 6:
							operacaoSelecionada = "rad";
							operacaoValida = true;
							break;
						case 7:
							operacaoSelecionada = "pot";
							operacaoValida = true;
							break;
						default:
							System.out.println("Escolha inválida: ");
							operacaoValida = false;
							break;
					}
				}

				// Solicitando dois parâmetros para as operações selecionadas
				System.out.println("Por favor, insira dois parâmetros para esta operação: ");
				System.out.println("Parâmetro 1 :");
				int primeiro = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Parâmetro 2 :");
				int segundo = scanner.nextInt();

				// Conectando com o servidor de nomeação
				Socket opSocket = new Socket("localhost", 12345);
				DataOutputStream opToClient = new DataOutputStream(opSocket.getOutputStream());
				opToClient.writeInt(primeiro);
				opToClient.writeInt(segundo);
				
				opToClient.writeUTF(operacaoSelecionada);

				// Obtendo resposta do Servidor de Nomeação
				DataInputStream opInStream = new DataInputStream(opSocket.getInputStream());
				String resposta = opInStream.readUTF();

				// Imprimindo Resposta e pedindo continuação
				System.out.println(resposta);
				System.out.println("Digite 1 se desejar continuar ou 0 para sair:");
				int continuacao = scanner.nextInt();
				if (continuacao == 0)
					continua = false;
			}
			System.out.println("Saída realizada com sucesso.");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// opSocket.close();
		}
	}

}
