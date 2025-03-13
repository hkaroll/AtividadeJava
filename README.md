# Sistema de Gestão de Estoque

Um aplicativo Java para gerenciamento de estoque que permite controlar produtos, suas quantidades, datas de vencimento e categorias.

## Descrição

O Sistema de Gestão de Estoque é uma aplicação desktop desenvolvida em Java com interface gráfica Swing que oferece funcionalidades completas para o gerenciamento de produtos em estoque. O sistema foi projetado para facilitar o controle de mercadorias, com ênfase especial no monitoramento de datas de vencimento.

## Funcionalidades

- **Cadastro de Produtos**: Adicione produtos ao estoque com nome, código único, quantidade, data de vencimento e categoria.
- **Edição de Produtos**: Atualize informações de produtos existentes.
- **Remoção de Produtos**: Remova produtos específicos do estoque através da seleção na tabela.
- **Listagem de Produtos**: Visualize todos os produtos em uma tabela organizada por data de vencimento.
- **Pesquisa de Produtos**: Localize rapidamente produtos por nome ou código.
- **Filtro por Categorias**: Filtre a visualização de produtos por categoria.
- **Alerta de Vencimentos**: Receba alertas sobre produtos próximos do vencimento ou já vencidos.
- **Status Visual**: Identificação clara de produtos vencidos na interface.

## Estrutura do Projeto

O projeto é composto por quatro classes principais:

1. **Produto.java**: Define a estrutura de dados para armazenar informações de um produto.
2. **Estoque.java**: Implementa a lógica de gerenciamento do estoque usando uma fila (Queue) e métodos para manipulação dos produtos.
3. **EstoqueGUI.java**: Implementa a interface gráfica do usuário usando Java Swing.
4. **Main.java**: Classe principal que inicia a aplicação.

## Como Utilizar

### Adicionando um Produto

1. Preencha os campos de nome, código, quantidade, data de vencimento e selecione uma categoria.
2. Clique no botão "Adicionar".
3. O produto será adicionado ao estoque e exibido na tabela.

### Editando um Produto

1. Selecione um produto na tabela com duplo clique ou clique no produto e pressione o botão "Editar Produto".
2. Os dados do produto serão carregados nos campos do formulário.
3. Modifique os campos desejados (o código não pode ser alterado, pois é o identificador único).
4. Clique no botão "Editar" para salvar as alterações.

### Removendo um Produto

1. Selecione um produto na tabela.
2. Clique no botão "Remover Produto".
3. Confirme a remoção na caixa de diálogo.

### Pesquisando Produtos

1. Selecione o tipo de pesquisa (Nome ou Código).
2. Digite o termo de pesquisa no campo de texto.
3. Clique no botão "Pesquisar".
4. Os resultados serão exibidos na tabela.

### Filtrando por Categoria

1. Selecione uma categoria na caixa de seleção do painel "Filtrar por Categoria".
2. Clique no botão "Filtrar".
3. Apenas os produtos da categoria selecionada serão exibidos.

### Verificando Vencimentos

1. Ajuste o número de dias no campo "Dias para aviso de vencimento".
2. Clique no botão "Verificar Vencimentos".
3. Uma mensagem será exibida listando os produtos que vencerão dentro do período especificado.

## Características Técnicas

- **Linguagem**: Java
- **Interface Gráfica**: Swing
- **Estrutura de Dados**: Filas (Queue) com LinkedList
- **Manipulação de Datas**: API LocalDate do Java
- **Processamento de Coleções**: Java Stream API

## Recursos de Código

- Utilização de programação funcional com Stream API
- Manipulação de datas com a API moderna do Java (java.time)
- Ordenação e filtragem de dados
- Técnicas de desenvolvimento de interfaces gráficas com Swing

## Requisitos

- Java 8 ou superior
- JDK 1.8 ou superior
- Interface gráfica compatível (para executar a GUI)

## Executando o Sistema

Para executar o sistema, compile as classes Java e execute a classe Main:

```bash
javac *.java
java Main
```

Alternativamente, você pode importar o projeto em uma IDE como Eclipse, NetBeans ou IntelliJ IDEA e executá-lo diretamente no ambiente de desenvolvimento.

## Screenshots

![Tela Principal do Sistema](Imagens/Imagem_1.png)

![Tela Vencidos](Imagens/Imagem_2.png)

![Tela Próximo ao Vencimento](Imagens/Imagem_3.png)


## Melhorias Futuras

- Implementação de persistência de dados em banco de dados ou arquivo
- Exportação de relatórios para formatos como PDF ou Excel
- Envio de notificações por email para produtos próximos do vencimento
- Interface de usuário responsiva ou versão web
- Implementação de controle de usuários e permissões

## Autor
Karoll Reis

## Licença
Este projeto é de uso livre. Sinta-se à vontade para modificar e adaptar conforme necessário.
