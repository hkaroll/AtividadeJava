import java.util.LinkedList;
import java.util.Queue;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    
    // Método para remover produto e retorná-lo (para a interface gráfica)
    public Produto removerProdutoComRetorno() {
        return produtos.poll();
    }
    
    // Verificar se o estoque está vazio
    public boolean isProdutosVazio() {
        return produtos.isEmpty();
    }
    
    // Obter lista de produtos ordenados por data
    public List<Produto> getProdutosOrdenadosPorData() {
        return produtos.stream()
                .sorted((p1, p2) -> p1.getDataVencimento().compareTo(p2.getDataVencimento()))
                .collect(Collectors.toList());
    }

    // Avisar produtos próximos do vencimento
    public void avisarProdutosProximosVencimento(int dias) {
        LocalDate hoje = LocalDate.now();
        produtos.stream()
                .filter(produto -> produto.getDataVencimento().isBefore(hoje.plusDays(dias)))
                .forEach(produto -> System.out.println("Aviso: " + produto.getNome() + " está próximo do vencimento!"));
    }
    
    // Método para obter avisos de produtos próximos do vencimento como lista de Strings
    public List<String> obterAvisosProdutosProximosVencimento(int dias) {
        LocalDate hoje = LocalDate.now();
        return produtos.stream()
                .filter(produto -> produto.getDataVencimento().isBefore(hoje.plusDays(dias)))
                .map(produto -> "Aviso: " + produto.getNome() + " (Código: " + produto.getCodigo() + 
                      ") - Vence em: " + produto.getDataVencimento())
                .collect(Collectors.toList());
    }
    
    // Método para pesquisar produtos por nome (pesquisa parcial, case insensitive)
    public List<Produto> pesquisarPorNome(String termo) {
        String termoBusca = termo.toLowerCase();
        return produtos.stream()
                .filter(produto -> produto.getNome().toLowerCase().contains(termoBusca))
                .sorted((p1, p2) -> p1.getDataVencimento().compareTo(p2.getDataVencimento()))
                .collect(Collectors.toList());
    }
    
    // Método para pesquisar produtos por código (pesquisa parcial, case insensitive)
    public List<Produto> pesquisarPorCodigo(String termo) {
        String termoBusca = termo.toLowerCase();
        return produtos.stream()
                .filter(produto -> produto.getCodigo().toLowerCase().contains(termoBusca))
                .sorted((p1, p2) -> p1.getDataVencimento().compareTo(p2.getDataVencimento()))
                .collect(Collectors.toList());
    }
}