import java.time.LocalDate;

public class Produto {
    private String nome;
    private String codigo;
    private int quantidade;
    private LocalDate dataVencimento;
    private String categoria;

    public Produto(String nome, String codigo, int quantidade, LocalDate dataVencimento, String categoria) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.dataVencimento = dataVencimento;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void atualizarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", codigo='" + codigo + '\'' +
                ", quantidade=" + quantidade +
                ", dataVencimento=" + dataVencimento +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}