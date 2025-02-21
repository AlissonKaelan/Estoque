# Projeto de Gerenciamento de Estoque em Java

Este projeto é uma aplicação Java que utiliza a biblioteca JFrame para criar uma interface gráfica de usuário (GUI) para gerenciamento de estoque. O sistema permite que os usuários façam login, se registrem e gerenciem produtos em um estoque.

## Estrutura do Projeto

### Conexão com o Banco de Dados
- **Classe `ConnectionFactory`**: Gerencia a conexão com o banco de dados MySQL.
  - **Métodos**:
    - `getConnection()`: Estabelece uma conexão com o banco de dados.
    - `closeConnection(Connection con)`: Fecha a conexão.
    - `closeConnection(Connection con, PreparedStatement stmt)`: Fecha a conexão e o PreparedStatement.
    - `closeConnection(Connection con, PreparedStatement stmt, ResultSet rs)`: Fecha a conexão, PreparedStatement e ResultSet.

### Modelos de Dados
- **Classe `Cadastro`**: Representa um usuário com atributos como `id`, `usuario`, `data_nascimento`, `cpf` e `senha`.
- **Classe `Login`**: Representa as credenciais de login de um usuário.
- **Classe `Produto`**: Representa um produto com atributos como `id`, `descricao`, `quantidade` e `preco`.

### Acesso a Dados
- **Classe `CadastroDAO`**: Realiza operações de CRUD para a tabela de cadastro de usuários.
- **Classe `LoginDAO`**: Verifica as credenciais de login de um usuário.
- **Classe `ProdutoDAO`**: Realiza operações de CRUD para a tabela de produtos.

### Interface Gráfica
- **Classe `Login`**: Tela de login onde os usuários podem inserir suas credenciais.
- **Classe `Registrar_se`**: Tela de registro para novos usuários.
- **Classe `Estoque`**: Tela para gerenciar produtos, permitindo adicionar, atualizar, excluir e buscar produtos.

## Funcionalidades
- **Login**: Usuários podem se autenticar no sistema.
- **Registro**: Novos usuários podem se cadastrar.
- **Gerenciamento de Produtos**: Usuários podem adicionar, atualizar, excluir e buscar produtos no estoque.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação principal.
- **JFrame**: Para a construção da interface gráfica.
- **MySQL**: Sistema de gerenciamento de banco de dados utilizado para persistência de dados.

## Como Executar o Projeto
1. Certifique-se de ter o Java e o MySQL instalados em sua máquina.
2. Configure o banco de dados MySQL com as tabelas necessárias. O dump do banco de dados está disponível na pasta `Dump database` dentro do projeto.
3. Compile e execute a aplicação Java.

