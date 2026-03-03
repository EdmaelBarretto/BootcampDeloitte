import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ProdutoService service = new ProdutoService();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== CRUD DE PRODUTOS ===");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Remover");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();

                    service.adicionar(new Produto(id, nome, valor));
                    break;

                case 2:
                    service.listar();
                    break;

                case 3:
                    System.out.print("ID do produto: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Novo valor: ");
                    double novoValor = scanner.nextDouble();

                    service.atualizar(idAtualizar, novoNome, novoValor);
                    break;

                case 4:
                    System.out.print("ID do produto: ");
                    int idRemover = scanner.nextInt();
                    service.remover(idRemover);
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
