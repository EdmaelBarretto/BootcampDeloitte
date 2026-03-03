import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();

    // CREATE
    public void adicionar(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto cadastrado!");
    }

    // READ
    public void listar() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            produtos.forEach(System.out::println);
        }
    }

    // UPDATE
    public void atualizar(int id, String nome, double valor) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                p.setNome(nome);
                p.setValor(valor);
                System.out.println("Produto atualizado!");
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    // DELETE
    public void remover(int id) {
        boolean removido = produtos.removeIf(p -> p.getId() == id);

        if (removido) {
            System.out.println("Produto removido!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}