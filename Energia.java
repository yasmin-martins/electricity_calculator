// Yasmin M.Souza;
// Consumo de Energia Eletrica;

import java.util.Scanner;
public class Energia {
    public static void main(String[] args) {
        Scanner dados = new Scanner(System.in);

        String[] nome = new String[20];
        int[] codigoCliente = new int[20];
        double[] mediaConsumo = new double[20];
        double[] mediaValores = new double[20];
        double[][] consumosMensais = new double[20][12];
        double[][] valoresMensais = new double[20][12];
        String[][] bandeirasMensais = new String[20][12];

        double tarifa = 0.80;
        double vermelha = 4.46;
        double amarela = 1.88;
        double verde = 0;

        for (int i = 0; i < nome.length; i++) {
            System.out.println("Digite o nome do cliente " + (i + 1) + ":");
            nome[i] = dados.nextLine();

            System.out.println("Digite o codigo do cliente " + (i + 1) + ":");
            codigoCliente[i] = dados.nextInt();
            dados.nextLine();

            double somaConsumo = 0;
            double somaValores = 0;


            for (int mes = 0; mes < 12; mes++) {
                System.out.println("Digite o consumo em KWh do mes " + (mes + 1) + " para " + nome[i] + ":");
                double consumo = dados.nextDouble();
                dados.nextLine();

                System.out.println("Digite a cor da bandeira tarifaria (verde, amarela ou vermelha) do mes " + (mes + 1) + ":");
                String corBandeira = dados.nextLine().toLowerCase();

                double valorConsumo = consumo * tarifa;
                double adicional=0;

                if (corBandeira.equals("verde")) {
                    adicional = 0;
                } else if (corBandeira.equals("amarela")) {
                    adicional = (consumo/100) * amarela;
                } else if (corBandeira.equals("vermelha")) {
                    adicional = (consumo/100) * vermelha ;
                } else {
                    System.out.println("invalida");
                }

                double valorFinal = valorConsumo + adicional;
                double impostoPC= (valorFinal*4)/100; // Imposto Pis-Cofins;
                double subtotal2= valorFinal+impostoPC;
                double icmsImposto= (subtotal2*30)/100; // Imposto ICMS 30%;
                double valorFinalImposto= subtotal2+icmsImposto;

                consumosMensais[i][mes] = consumo;
                valoresMensais[i][mes] = valorFinalImposto;
                bandeirasMensais[i][mes] = corBandeira;

                somaConsumo += consumo;
                somaValores += valorFinalImposto;
            }

            mediaConsumo[i] = somaConsumo / 12;
            mediaValores[i] = somaValores / 12;
        }
        int opcao;
        do {
            System.out.println(" MENU DE CONSULTA");
            System.out.println("1 - Consultar cliente por codigo");
            System.out.println("2 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = dados.nextInt();

            if (opcao == 1) {
                System.out.print("Digite o codigo do cliente: ");
                int codigo = dados.nextInt();

                int posicao = -1;
                for (int i = 0; i < codigoCliente.length; i++) {
                    if (codigoCliente[i] == codigo) {
                        posicao = i;
                        break;
                    }
                }

                if (posicao == -1) {
                    System.out.println("Cliente não encontrado!");
                } else {
                    System.out.println("DADOS DO CLIENTE");
                    System.out.println("Nome: " + nome[posicao]);
                    System.out.println("Codigo: " + codigoCliente[posicao]);
                    System.out.printf("Media de consumo: %.2f KWh/mes\n",mediaConsumo[posicao]);
                    System.out.printf("Media de gastos: R$ %.2f/mes\n", mediaValores[posicao]);

                    System.out.println("\nDETALHAMENTO MENSAL:");
                    System.out.println("Mes | Consumo (KWh) | Bandeira | Valor (R$)");
                    for (int mes = 0; mes < 12; mes++) {
                        System.out.printf("%2d  | %12.2f | %-8s | %10.2f\n",
                                (mes+1),
                                consumosMensais[posicao][mes],
                                bandeirasMensais[posicao][mes],
                                valoresMensais[posicao][mes]);
                    }
                }
            } else if (opcao != 2) {
                System.out.println("Opcao invalida!");
            }
        } while (opcao != 2);

        System.out.println("Programa encerrado.");
        dados.close();
    }
}