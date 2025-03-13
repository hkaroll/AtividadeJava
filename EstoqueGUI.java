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
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Estoque estoque;
    private JTable tableProdutos;
    private DefaultTableModel tableModel;
    private JTextField txtNome, txtCodigo, txtQuantidade, txtDataVencimento;
    private JTextField txtPesquisa;
    private JComboBox<String> comboPesquisa;
    private JComboBox<String> comboCategoria;
    private JComboBox<String> comboFiltroCategoria;
    private JButton btnAdicionar, btnRemover, btnEditar, btnListar, btnAvisar, btnPesquisar, btnLimparPesquisa;
    private JButton btnFiltrarCategoria, btnLimparFiltro;
    private JSpinner spinnerDias;
    
    // Categorias pré-definidas
    private final String[] CATEGORIAS = {"Alimentos", "Bebidas", "Limpeza", "Higiene", "Outros"};
    private final String TODAS_CATEGORIAS = "Todas as Categorias";

    public EstoqueGUI() {
        estoque = new Estoque();
        
        // Configuração da janela
        setTitle("Gerenciamento de Estoque");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Painel de formulário
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Adicionar/Editar Produto"));
        
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
        
        panelForm.add(new JLabel("Categoria:"));
        comboCategoria = new JComboBox<>(CATEGORIAS);
        panelForm.add(comboCategoria);
        
        panelForm.add(new JLabel("Dias para aviso de vencimento:"));
        spinnerDias = new JSpinner(new SpinnerNumberModel(7, 1, 100, 1));
        panelForm.add(spinnerDias);
        
        // Painel de pesquisa e filtro
        JPanel panelPesquisaFiltro = new JPanel(new BorderLayout());
        
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
        
        // Painel de filtro por categoria
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.setBorder(BorderFactory.createTitledBorder("Filtrar por Categoria"));
        
        String[] opcoesCategoria = new String[CATEGORIAS.length + 1];
        opcoesCategoria[0] = TODAS_CATEGORIAS;
        System.arraycopy(CATEGORIAS, 0, opcoesCategoria, 1, CATEGORIAS.length);
        
        comboFiltroCategoria = new JComboBox<>(opcoesCategoria);
        btnFiltrarCategoria = new JButton("Filtrar");
        btnLimparFiltro = new JButton("Mostrar Todos");
        
        panelFiltro.add(new JLabel("Categoria:"));
        panelFiltro.add(comboFiltroCategoria);
        panelFiltro.add(btnFiltrarCategoria);
        panelFiltro.add(btnLimparFiltro);
        
        panelPesquisaFiltro.add(panelPesquisa, BorderLayout.NORTH);
        panelPesquisaFiltro.add(panelFiltro, BorderLayout.SOUTH);
        
        // Painel de botões
        JPanel panelButtons = new JPanel(new FlowLayout());
        
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar Produto");
        btnRemover = new JButton("Remover Produto");
        btnListar = new JButton("Atualizar Lista");
        btnAvisar = new JButton("Verificar Vencimentos");
        
        panelButtons.add(btnAdicionar);
        panelButtons.add(btnEditar);
        panelButtons.add(btnRemover);
        panelButtons.add(btnListar);
        panelButtons.add(btnAvisar);
        
        // Tabela de produtos
        String[] colunas = {"Nome", "Código", "Quantidade", "Data de Vencimento", "Categoria", "Status"};
        tableModel = new DefaultTableModel(colunas, 0);
        tableProdutos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableProdutos);
        
        // Adicionar componentes à janela
        JPanel panelNorth = new JPanel(new BorderLayout());
        panelNorth.add(panelForm, BorderLayout.NORTH);
        panelNorth.add(panelPesquisaFiltro, BorderLayout.SOUTH);
        
        add(panelNorth, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
        
        // Eventos
        configurarEventos();
        
        // Preencher com alguns dados de exemplo
        adicionarDadosExemplo();
        
        // Verificar produtos vencidos automaticamente
        verificarProdutosVencidos();
    }
    
    private void adicionarDadosExemplo() {
        estoque.adicionarProduto(new Produto("Leite", "001", 10, LocalDate.of(2025, 2, 15), "Alimentos"));
        estoque.adicionarProduto(new Produto("Pão", "002", 5, LocalDate.of(2025, 3, 10), "Alimentos"));
        estoque.adicionarProduto(new Produto("Queijo", "003", 20, LocalDate.of(2025, 4, 1), "Alimentos"));
        estoque.adicionarProduto(new Produto("Água Mineral", "004", 30, LocalDate.of(2025, 4, 20), "Bebidas"));
        estoque.adicionarProduto(new Produto("Detergente", "005", 15, LocalDate.of(2025, 2, 25), "Limpeza"));
        // Adicionar um produto já vencido para demonstração
        estoque.adicionarProduto(new Produto("Iogurte", "006", 8, LocalDate.of(2024, 3, 10), "Alimentos"));
        atualizarTabela();
    }
    
    private void configurarEventos() {
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProduto();
            }
        });
        
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProduto();
            }
        });
        
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerProdutoSelecionado();
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
        
        btnFiltrarCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarPorCategoria();
            }
        });
        
        btnLimparFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparFiltros();
            }
        });
        
        // Adicionar evento de clique duplo na tabela para editar o produto
        tableProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    carregarProdutoParaEdicao();
                }
            }
        });
    }
    
    private void adicionarProduto() {
        try {
            String nome = txtNome.getText();
            String codigo = txtCodigo.getText();
            int quantidade = Integer.parseInt(txtQuantidade.getText());
            LocalDate dataVencimento = LocalDate.parse(txtDataVencimento.getText());
            String categoria = (String) comboCategoria.getSelectedItem();
            
            if (nome.isEmpty() || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }
            
            // Verificar se o código já existe
            if (estoque.codigoExiste(codigo)) {
                JOptionPane.showMessageDialog(this, 
                    "Erro: Código '" + codigo + "' já existe no estoque!\n" +
                    "Por favor, escolha um código diferente.", 
                    "Código Duplicado", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Produto produto = new Produto(nome, codigo, quantidade, dataVencimento, categoria);
            estoque.adicionarProduto(produto);
            
            // Limpar campos
            txtNome.setText("");
            txtCodigo.setText("");
            txtQuantidade.setText("");
            txtDataVencimento.setText("");
            
            JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
            atualizarTabela();
            verificarProdutosVencidos();
            
            // Atualizar lista de categorias no filtro
            atualizarListaCategorias();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!");
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido! Use yyyy-MM-dd");
        }
    }
    
    private void editarProduto() {
        try {
            String nome = txtNome.getText();
            String codigo = txtCodigo.getText();
            int quantidade = Integer.parseInt(txtQuantidade.getText());
            LocalDate dataVencimento = LocalDate.parse(txtDataVencimento.getText());
            String categoria = (String) comboCategoria.getSelectedItem();
            
            if (nome.isEmpty() || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }
            
            // Verificar se o código existe para edição
            if (!estoque.codigoExiste(codigo)) {
                JOptionPane.showMessageDialog(this, 
                    "Erro: Código '" + codigo + "' não existe no estoque!\n" +
                    "Impossível editar produto inexistente.", 
                    "Código Não Encontrado", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Produto produtoAtualizado = new Produto(nome, codigo, quantidade, dataVencimento, categoria);
            boolean sucesso = estoque.atualizarProduto(codigo, produtoAtualizado);
            
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");
                // Limpar campos
                txtNome.setText("");
                txtCodigo.setText("");
                txtQuantidade.setText("");
                txtDataVencimento.setText("");
                txtCodigo.setEditable(true); // Reativar a edição do código
                
                atualizarTabela();
                verificarProdutosVencidos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar produto!");
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida!");
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido! Use yyyy-MM-dd");
        }
    }
    
    private void carregarProdutoParaEdicao() {
        int selectedRow = tableProdutos.getSelectedRow();
        if (selectedRow >= 0) {
            String codigo = (String) tableModel.getValueAt(selectedRow, 1);
            Produto produto = estoque.buscarProdutoPorCodigo(codigo);
            
            if (produto != null) {
                txtNome.setText(produto.getNome());
                txtCodigo.setText(produto.getCodigo());
                txtCodigo.setEditable(false); // Impedir a edição do código que é usado como identificador
                txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
                txtDataVencimento.setText(produto.getDataVencimento().toString());
                comboCategoria.setSelectedItem(produto.getCategoria());
                
                JOptionPane.showMessageDialog(this, 
                    "Produto carregado para edição.\n" +
                    "O código não pode ser alterado pois é usado como identificador único.", 
                    "Editar Produto", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto na tabela para editar!");
        }
    }
    
    private void removerProdutoSelecionado() {
        int selectedRow = tableProdutos.getSelectedRow();
        if (selectedRow >= 0) {
            String codigo = (String) tableModel.getValueAt(selectedRow, 1);
            String nome = (String) tableModel.getValueAt(selectedRow, 0);
            
            int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja remover o produto:\n" +
                "Nome: " + nome + "\n" +
                "Código: " + codigo,
                "Confirmar Remoção",
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirmacao == JOptionPane.YES_OPTION) {
                boolean sucesso = estoque.removerProdutoPorCodigo(codigo);
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao remover produto!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto na tabela para remover!");
        }
    }
    
    private void removerProduto() {
        if (estoque.isProdutosVazio()) {
            JOptionPane.showMessageDialog(this, "Estoque vazio!");
        } else {
            Produto produtoRemovido = estoque.removerProdutoComRetorno();
            JOptionPane.showMessageDialog(this, "Produto removido: " + produtoRemovido.getNome());
            atualizarTabela();
            
            // Atualizar lista de categorias no filtro
            atualizarListaCategorias();
        }
    }
    
    private void atualizarTabela() {
        // Limpar a tabela
        tableModel.setRowCount(0);
        
        // Obter produtos ordenados por data
        List<Produto> produtosOrdenados = estoque.getProdutosOrdenadosPorData();
        
        // Preencher a tabela
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate hoje = LocalDate.now();
        
        for (Produto produto : produtosOrdenados) {
            String status = "Normal";
            
            // Verificar se o produto está vencido
            if (produto.getDataVencimento().isBefore(hoje)) {
                status = "VENCIDO";
            }
            
            Object[] row = {
                produto.getNome(),
                produto.getCodigo(),
                produto.getQuantidade(),
                produto.getDataVencimento().format(formatter),
                produto.getCategoria(),
                status
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
    
    private void verificarProdutosVencidos() {
        List<String> vencidos = estoque.obterProdutosVencidos();
        
        if (!vencidos.isEmpty()) {
            StringBuilder mensagem = new StringBuilder("ATENÇÃO! Produtos VENCIDOS:\n\n");
            for (String produto : vencidos) {
                mensagem.append(produto).append("\n");
            }
            JOptionPane.showMessageDialog(
                this, 
                mensagem.toString(),
                "Produtos Vencidos",
                JOptionPane.WARNING_MESSAGE
            );
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
        LocalDate hoje = LocalDate.now();
        
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum produto encontrado para a pesquisa: " + termoPesquisa);
        } else {
            for (Produto produto : resultados) {
                String status = "Normal";
                
                // Verificar se o produto está vencido
                if (produto.getDataVencimento().isBefore(hoje)) {
                    status = "VENCIDO";
                }
                
                Object[] row = {
                    produto.getNome(),
                    produto.getCodigo(),
                    produto.getQuantidade(),
                    produto.getDataVencimento().format(formatter),
                    produto.getCategoria(),
                    status
                };
                tableModel.addRow(row);
            }
            setTitle("Gerenciamento de Estoque - Resultados para: " + termoPesquisa);
        }
    }
    
    private void limparPesquisa() {
        txtPesquisa.setText("");
        setTitle("Gerenciamento de Estoque");
        atualizarTabela();
    }
    
    private void filtrarPorCategoria() {
        String categoriaFiltro = (String) comboFiltroCategoria.getSelectedItem();
        
        if (TODAS_CATEGORIAS.equals(categoriaFiltro)) {
            atualizarTabela();
            return;
        }
        
        List<Produto> produtosFiltrados = estoque.filtrarPorCategoria(categoriaFiltro);
        
        // Atualizar tabela com produtos filtrados
        tableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate hoje = LocalDate.now();
        
        if (produtosFiltrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum produto encontrado na categoria: " + categoriaFiltro);
        } else {
            for (Produto produto : produtosFiltrados) {
                String status = "Normal";
                
                // Verificar se o produto está vencido
                if (produto.getDataVencimento().isBefore(hoje)) {
                    status = "VENCIDO";
                }
                
                Object[] row = {
                    produto.getNome(),
                    produto.getCodigo(),
                    produto.getQuantidade(),
                    produto.getDataVencimento().format(formatter),
                    produto.getCategoria(),
                    status
                };
                tableModel.addRow(row);
            }
            setTitle("Gerenciamento de Estoque - Categoria: " + categoriaFiltro);
        }
    }
    
    private void limparFiltros() {
        comboFiltroCategoria.setSelectedItem(TODAS_CATEGORIAS);
        setTitle("Gerenciamento de Estoque");
        atualizarTabela();
    }
    
    private void atualizarListaCategorias() {
        // Esta função atualiza dinamicamente a lista de categorias no filtro
        // Mantendo as categorias pré-definidas por simplicidade neste exemplo
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