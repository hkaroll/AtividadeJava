# Sistema de Gerenciamento de Estoque com FIFO

Esta é uma aplicação Java para gerenciamento de estoque com foco em datas de vencimento e implementando o princípio FIFO (First In, First Out - Primeiro a Entrar, Primeiro a Sair). O sistema permite aos usuários adicionar, remover, pesquisar e acompanhar produtos com datas de vencimento próximas.

## Funcionalidades

- Adicionar novos produtos com nome, código, quantidade e data de vencimento
- Remover produtos usando a ordem FIFO (data de vencimento mais antiga primeiro)
- Pesquisar produtos por nome ou código
- Acompanhar produtos próximos da data de vencimento
- Ordenar e exibir produtos por data de vencimento
- Interface gráfica para fácil interação

## Componentes

A aplicação consiste em quatro classes Java principais:

### Produto

Representa um produto com os seguintes atributos:
- Nome
- Código
- Quantidade
- Data de vencimento

### Estoque

Implementa a funcionalidade principal de gerenciamento de estoque:
- Adicionar produtos ao estoque
- Remover produtos usando o princípio FIFO
- Listar produtos ordenados por data de vencimento
- Pesquisar produtos por nome ou código
- Identificar produtos próximos do vencimento

### EstoqueGUI

Fornece uma interface gráfica com:
- Formulário para adicionar novos produtos
- Funcionalidade de pesquisa
- Tabela exibindo produtos
- Botões para operações de gerenciamento de estoque
- Avisos de data de vencimento

### Main

Ponto de entrada para a aplicação que inicia a interface gráfica.

## Como Usar

1. **Adicionar Produtos**:
   - Preencha o nome do produto, código, quantidade e data de vencimento
   - Clique no botão "Adicionar"

2. **Remover Produtos**:
   - Clique no botão "Remover Produto" para remover o produto mais antigo

3. **Pesquisar Produtos**:
   - Selecione o tipo de pesquisa (por nome ou código)
   - Digite o termo de busca
   - Clique no botão "Pesquisar"
   - Use "Limpar Pesquisa" para retornar à visualização completa do estoque

4. **Verificar Vencimentos**:
   - Defina o número de dias para avisos de vencimento
   - Clique em "Verificar Vencimentos" para ver produtos que vencem em breve

5. **Atualizar Lista**:
   - Clique em "Atualizar Lista" para atualizar a exibição de produtos

## Implementação Técnica

- Usa Java Swing para a interface gráfica
- Implementa a estrutura de dados Queue (LinkedList) para operações FIFO
- Usa Java Streams para operações de filtragem e ordenação
- LocalDate para manipulação de datas e cálculos de vencimento

## Dados de Exemplo

A aplicação vem pré-carregada com produtos de exemplo para fins de demonstração:
- Leite: Código 001, Quantidade 10, Vence em 15/02/2025
- Pão: Código 002, Quantidade 5, Vence em 10/03/2025
- Queijo: Código 003, Quantidade 20, Vence em 01/04/2025
- Ovos: Código 004, Quantidade 30, Vence em 20/04/2025
- Manteiga: Código 005, Quantidade 15, Vence em 25/02/2025

## Melhorias Futuras Possíveis

1. **Persistência de Dados**:
   - Implementar banco de dados para armazenamento permanente dos produtos
   - Adicionar funcionalidade de exportação/importação de dados

2. **Gestão de Categorias**:
   - Criar categorias para produtos (alimentos, bebidas, limpeza, etc.)
   - Filtrar produtos por categoria na interface

3. **Histórico de Movimentações**:
   - Registrar todas as entradas e saídas de produtos
   - Relatórios de movimentação por período

4. **Gestão de Fornecedores**:
   - Cadastro de fornecedores vinculados aos produtos
   - Histórico de compras por fornecedor

5. **Alertas Automáticos**:
   - Notificações por email ou pop-up para produtos próximos do vencimento
   - Alertas para produtos com estoque baixo

6. **Interface Responsiva**:
   - Adaptar a interface para diferentes tamanhos de tela
   - Versão mobile da aplicação

7. **Controle de Acesso**:
   - Sistema de login para diferentes usuários
   - Níveis de permissão (administrador, operador, etc.)

8. **Funcionalidades Avançadas**:
   - Previsão de demanda baseada no histórico
   - Sugestão automática de reposição
   - Geração de etiquetas com códigos de barras

9. **Integração com Outros Sistemas**:
   - API para integração com sistemas de vendas
   - Integração com leitor de código de barras

## Requisitos

- Java Runtime Environment (JRE) 8 ou superior
- Java Development Kit (JDK) 8 ou superior (para desenvolvimento)

## Compilação e Execução

1. Compile todos os arquivos Java:
   ```
   javac *.java
   ```

2. Execute a aplicação:
   ```
   java Main
   ```

## Autor
Karoll Reis

## Licença
Este projeto é de uso livre. Sinta-se à vontade para modificar e adaptar conforme necessário.
