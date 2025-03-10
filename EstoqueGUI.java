import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class EstoqueGUI extends JFrame {
    private Estoque estoque;
    private JTable tableProdutos;
    private DefaultTableModel tableModel;
    private JTextField txtNome, txtCodigo, txtQuantidade, txtDataVencimento;
    private JTextField txtPesquisa;
    private JComboBox<String> comboPesquisa;
    private JButton btnAdicionar, btnRemover, btnListar, btnAvisar, btnPesquisar, btnLimparPesquisa;
    private JSpinner spinnerDias;

    public EstoqueGUI() {
        estoque = new Estoque();
        
        // Configuração da janela
        setTitle("Gerenciamento de Estoque");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Painel de formulário
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Adicionar Produto"));
        
        panelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panelForm.add(txtNome);
        
        panelForm.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelForm.add(txtCodigo);
        
        panelForm.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        panelForm.add(txtQuantidade);
        
        panelForm.add(new JLabel("Data de Vencimento (yyyy-MM-dd):"));
        txtDataVencimento = new JTextField();
        panelForm.add(txtDataVencimento);
        
        panelForm.add(new JLabel("Dias para aviso de vencimento:"));
        spinnerDias = new JSpinner(new SpinnerNumberModel(7, 1, 100, 1));
        panelForm.add(spinnerDias);
        
        // Painel de pesquisa
        JPanel panelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPesquisa.setBorder(BorderFactory.createTitledBorder("Pesquisar Produto"));
        
        comboPesquisa = new JComboBox<>(new String[]{"Nome", "Código"});
        txtPesquisa = new JTextField(20);
        btnPesquisar = new JButton("Pesquisar");
        btnLimparPesquisa = new JButton("Limpar Pesquisa");
        
        panelPesquisa.add(new JLabel("Pesquisar por:"));
        panelPesquisa.add(comboPesquisa);
        panelPesquisa.add(txtPesquisa);
        panelPesquisa.add(btnPesquisar);
        panelPesquisa.add(btnLimparPesquisa);
        
        // Painel de botões
        JPanel panelButtons = new JPanel(new FlowLayout());
        
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover Produto");
        btnListar = new JButton("Atualizar Lista");
        btnAvisar = new JButton("Verificar Vencimentos");
        
        panelButtons.add(btnAdicionar);
        panelButtons.add(btnRemover);
        panelButtons.add(btnListar);
        panelButtons.add(btnAvisar);
        
        // Tabela de produtos
        String[] colunas = {"Nome", "Código", "Quantidade", "Data de Vencimento"};
        tableModel = new DefaultTableModel(colunas, 0);
        tableProdutos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableProdutos);
        
        // Adicionar componentes à janela
        JPanel panelNorth = new JPanel(new BorderLayout());
        panelNorth.add(panelForm, BorderLayout.NORTH);
        panelNorth.add(panelPesquisa, BorderLayout.SOUTH);
        
        add(panelNorth, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
        
        // Eventos
        configurarEventos();
        
        // Preencher com alguns dados de exemplo
        adicionarDadosExemplo();
    }
    
    private void adicionarDadosExemplo() {
        estoque.adicionarProduto(new Produto("Leite", "001", 10, LocalDate.of(2025, 2, 15)));
        estoque.adicionarProduto(new Produto("Pão", "002", 5, LocalDate.of(2025, 3, 10)));
        estoque.adicionarProduto(new Produto("Queijo", "003", 20, LocalDate.of(2025, 4, 1)));
        estoque.adicionarProduto(new Produto("Ovos", "004", 30, LocalDate.of(2025, 4, 20)));
        estoque.adicionarProduto(new Produto("Manteiga", "005", 15, LocalDate.of(2025, 2, 25)));
        atualizarTabela();
    }
    
    private void configurarEventos() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });
        
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerProduto();
            }
        });
        
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTabela();
            }
        });
        
        btnAvisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarVencimentos();
            }
        });
        
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarProdutos();
            }
        });
        
        btnLimparPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparPesquisa();
            }
        });
    }
    
    private void adicionarProduto() {
        try {
            String nome = txtNome.getText();
            String codigo = txtCodigo.getText();
            int quantidade = Integer.parseInt(txtQuantidade.getText());
            LocalDate dataVencimento = LocalDate.parse(txtDataVencimento.getText());
            
            if (nome.isEmpty() || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }
            
            Produto produto = new Produto(nome, codigo, quantidade, dataVencimento);
            estoque.adicionarProduto(produto);
            
            // Limpar campos
            txtNome.setText("");
            txtCodigo.setText("");
            txtQuantidade.setText("");
            txtDataVencimento.setText("");
            
            JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
            atualizarTabela();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!");
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido! Use yyyy-MM-dd");
        }
    }
    
    private void removerProduto() {
        if (estoque.isProdutosVazio()) {
            JOptionPane.showMessageDialog(this, "Estoque vazio!");
        } else {
            Produto produtoRemovido = estoque.removerProdutoComRetorno();
            JOptionPane.showMessageDialog(this, "Produto removido: " + produtoRemovido.getNome());
            atualizarTabela();
        }
    }
    
    private void atualizarTabela() {
        // Limpar a tabela
        tableModel.setRowCount(0);
        
        // Obter produtos ordenados por data
        List<Produto> produtosOrdenados = estoque.getProdutosOrdenadosPorData();
        
        // Preencher a tabela
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Produto produto : produtosOrdenados) {
            Object[] row = {
                produto.getNome(),
                produto.getCodigo(),
                produto.getQuantidade(),
                produto.getDataVencimento().format(formatter)
            };
            tableModel.addRow(row);
        }
    }
    
    private void verificarVencimentos() {
        int dias = (Integer) spinnerDias.getValue();
        List<String> avisos = estoque.obterAvisosProdutosProximosVencimento(dias);
        
        if (avisos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há produtos próximos do vencimento!");
        } else {
            StringBuilder mensagem = new StringBuilder("Produtos próximos do vencimento:\n\n");
            for (String aviso : avisos) {
                mensagem.append(aviso).append("\n");
            }
            JOptionPane.showMessageDialog(this, mensagem.toString());
        }
    }
    
    private void pesquisarProdutos() {
        String termoPesquisa = txtPesquisa.getText().trim().toLowerCase();
        if (termoPesquisa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um termo para pesquisa");
            return;
        }
        
        String tipoPesquisa = (String) comboPesquisa.getSelectedItem();
        List<Produto> resultados = new ArrayList<>();
        
        if ("Nome".equals(tipoPesquisa)) {
            resultados = estoque.pesquisarPorNome(termoPesquisa);
        } else if ("Código".equals(tipoPesquisa)) {
            resultados = estoque.pesquisarPorCodigo(termoPesquisa);
        }
        
        // Atualizar a tabela com os resultados
        tableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum produto encontrado para a pesquisa: " + termoPesquisa);
        } else {
            for (Produto produto : resultados) {
                Object[] row = {
                    produto.getNome(),
                    produto.getCodigo(),
                    produto.getQuantidade(),
                    produto.getDataVencimento().format(formatter)
                };
                tableModel.addRow(row);
            }
            setTitle("Gerenciamento de Estoque com FIFO - Resultados para: " + termoPesquisa);
        }
    }
    
    private void limparPesquisa() {
        txtPesquisa.setText("");
        setTitle("Gerenciamento de Estoque com FIFO");
        atualizarTabela();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EstoqueGUI gui = new EstoqueGUI();
                gui.setVisible(true);
            }
        });
    }
}