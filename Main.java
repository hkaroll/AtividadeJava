import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        // Adicionando produtos ao estoque
        estoque.adicionarProduto(new Produto("Leite", "001", 10, LocalDate.of(2025, 02, 15)));
        estoque.adicionarProduto(new Produto("Pão", "002", 5, LocalDate.of(2025, 03, 10)));
        estoque.adicionarProduto(new Produto("Queijo", "003", 20, LocalDate.of(2025, 04, 01)));
        estoque.adicionarProduto(new Produto("Ovos", "004", 30, LocalDate.of(2025, 04, 20)));
        estoque.adicionarProduto(new Produto("Manteiga", "005", 15, LocalDate.of(2025, 02, 25)));
        
        // Listando produtos
        System.out.println("Produtos no estoque:");
        estoque.listarProdutos();

        // Removendo um produto
        System.out.println("\nRemovendo um produto:");
        estoque.removerProduto();

        // Avisando produtos próximos do vencimento
        System.out.println("\nAvisos de produtos próximos do vencimento:");
        estoque.avisarProdutosProximosVencimento(7);
    }
}