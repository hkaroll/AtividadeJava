# Gerenciamento de Estoques com Controle de Validade e FIFO

## Descrição do Projeto
Este sistema foi desenvolvido como parte da disciplina de Estrutura de Dados para implementar um gerenciador de estoque que organiza produtos com base em suas datas de vencimento, aplicando o método FIFO (First In, First Out). O objetivo principal é criar uma solução eficiente para controlar produtos com prazos de validade, garantindo que os itens próximos ao vencimento sejam priorizados para venda.

## Estrutura do Projeto
O projeto está organizado em três classes principais:
- `Produto`: Define a estrutura de dados para armazenar informações dos produtos
- `Estoque`: Implementa a lógica de gerenciamento usando fila e streams
- `Main`: Classe executável para demonstração das funcionalidades

## Funcionalidades Implementadas
- Adição de produtos com nome, código, quantidade e data de vencimento
- Organização automática dos produtos por data de vencimento 
- Listagem de produtos ordenados por proximidade de vencimento
- Remoção de produtos do estoque (implementando FIFO)
- Alerta para produtos próximos da data de vencimento (configurável em dias)

## Tecnologias Utilizadas
- Linguagem Java
- Estrutura de dados: Queue (implementada com LinkedList)
- API de data: LocalDate do Java 8+
- Streams para operações de filtragem e ordenação

## Como Executar o Projeto
1. Certifique-se de ter o JDK instalado (versão 8 ou superior)
2. Compile os três arquivos Java:
   ```
   javac Produto.java Estoque.java Main.java
   ```
3. Execute a classe principal:
   ```
   java Main
   ```

## Detalhes da Implementação

### Classe Produto
- Armazena as informações básicas de cada produto (nome, código, quantidade, data de vencimento)
- Inclui métodos para atualizar a quantidade e obter informações do produto
- Implementa toString() para facilitar a exibição dos dados

### Classe Estoque
- Gerencia uma fila de produtos usando a interface Queue
- Implementa os métodos:
  - `adicionarProduto()`: Adiciona produtos à fila
  - `listarProdutos()`: Exibe produtos ordenados por data de vencimento
  - `removerProduto()`: Remove o primeiro produto da fila (FIFO)
  - `avisarProdutosProximosVencimento()`: Alerta sobre produtos próximos da validade

### Classe Main
- Demonstra o uso das funcionalidades do sistema
- Cria um estoque e adiciona produtos de exemplo
- Exibe a listagem de produtos, demonstra a remoção e os avisos de vencimento

## Melhorias Futuras Possíveis
- Implementar persistência de dados
- Adicionar interface gráfica
- Incluir relatórios de produtos vencidos
- Implementar funcionalidade para atualização de quantidade após vendas parciais
- Melhorar a ordenação FIFO para considerar tanto a data de inserção quanto a data de vencimento

## Autor
Karoll Reis

## Licença
Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
