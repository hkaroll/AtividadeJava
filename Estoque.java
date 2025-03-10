import java.util.LinkedList;
import java.util.Queue;
import java.time.LocalDate;

public class Estoque {
    private Queue<Produto> produtos;

    public Estoque() {
        produtos = new LinkedList<>();
    }

    // Adicionar produto ao estoque
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    // Listar produtos organizados por data de vencimento
    public void listarProdutos() {
        produtos.stream()
                .sorted((p1, p2) -> p1.getDataVencimento().compareTo(p2.getDataVencimento()))
                .forEach(System.out::println);
    }

    // Remover produto do estoque
    public void removerProduto() {
        if (!produtos.isEmpty()) {
            Produto produtoRemovido = produtos.poll();
            System.out.println("Produto removido: " + produtoRemovido);
        } else {
            System.out.println("Estoque vazio!");
        }
    }

    // Avisar produtos próximos do vencimento
    public void avisarProdutosProximosVencimento(int dias) {
        LocalDate hoje = LocalDate.now();
        produtos.stream()
                .filter(produto -> produto.getDataVencimento().isBefore(hoje.plusDays(dias)))
                .forEach(produto -> System.out.println("Aviso: " + produto.getNome() + " está próximo do vencimento!"));
    }
}