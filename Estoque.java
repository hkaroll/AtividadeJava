import java.util.LinkedList;
import java.util.Queue;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Estoque {
    private Queue<Produto> produtos;

    public Estoque() {
        produtos = new LinkedList<>();
    }

    // Verificar se o código já existe no estoque
    public boolean codigoExiste(String codigo) {
        return produtos.stream()
                .anyMatch(produto -> produto.getCodigo().equals(codigo));
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
    
    // Remover produto específico por código
    public boolean removerProdutoPorCodigo(String codigo) {
        return produtos.removeIf(produto -> produto.getCodigo().equals(codigo));
    }
    
    // Buscar produto por código
    public Produto buscarProdutoPorCodigo(String codigo) {
        return produtos.stream()
                .filter(produto -> produto.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
    
    // Atualizar produto existente
    public boolean atualizarProduto(String codigo, Produto produtoAtualizado) {
        // Verificar se o produto existe
        Produto produtoExistente = buscarProdutoPorCodigo(codigo);
        if (produtoExistente == null) {
            return false;
        }
        
        // Remover o produto antigo
        removerProdutoPorCodigo(codigo);
        
        // Adicionar o produto atualizado
        produtos.add(produtoAtualizado);
        
        return true;
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
                .filter(produto -> produto.getDataVencimento().isAfter(hoje) && 
                                  produto.getDataVencimento().isBefore(hoje.plusDays(dias)))
                .forEach(produto -> System.out.println("Aviso: " + produto.getNome() + " está próximo do vencimento!"));
    }
    
    // Método para obter avisos de produtos próximos do vencimento como lista de Strings
    public List<String> obterAvisosProdutosProximosVencimento(int dias) {
        LocalDate hoje = LocalDate.now();
        return produtos.stream()
                .filter(produto -> produto.getDataVencimento().isAfter(hoje) && 
                                  produto.getDataVencimento().isBefore(hoje.plusDays(dias)))
                .map(produto -> "Aviso: " + produto.getNome() + " (Código: " + produto.getCodigo() + 
                      ") - Vence em: " + produto.getDataVencimento())
                .collect(Collectors.toList());
    }
    
    // Método para obter produtos já vencidos
    public List<String> obterProdutosVencidos() {
        LocalDate hoje = LocalDate.now();
        return produtos.stream()
                .filter(produto -> produto.getDataVencimento().isBefore(hoje))
                .map(produto -> "VENCIDO: " + produto.getNome() + " (Código: " + produto.getCodigo() + 
                      ") - Venceu em: " + produto.getDataVencimento())
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
    
    // Obter todas as categorias existentes no estoque
    public Set<String> getCategorias() {
        return produtos.stream()
                .map(Produto::getCategoria)
                .collect(Collectors.toSet());
    }
    
    // Filtrar produtos por categoria
    public List<Produto> filtrarPorCategoria(String categoria) {
        return produtos.stream()
                .filter(produto -> produto.getCategoria().equals(categoria))
                .sorted((p1, p2) -> p1.getDataVencimento().compareTo(p2.getDataVencimento()))
                .collect(Collectors.toList());
    }
}