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

## Alterações Realizadas
1. **Validação de Dados Pessoais**
   - **Classe `ValidadorCPF`**: Adicionada para validar a data de nascimento e o CPF.
     - **Método `validarDataNascimento(String data_nascimento)`**: Verifica se a data de nascimento é válida e se a pessoa é maior de idade.
     - **Método `validarCPF(String cpf)`**: Verifica se o CPF é válido, considerando os dígitos verificadores.

2. **Integração de Validações no Cadastro**
   - **Classe `CadastroDAO`**: Modificado o método `create(Cadastro c)` para incluir validações antes de inserir os dados no banco de dados.
     - Mensagens de erro são exibidas se a data de nascimento ou o CPF forem inválidos, utilizando `JOptionPane`.

3. **Atualização da Tela de Registro**
   - **Classe `Registrar_se`**: Modificado o método `btnRegistrarActionPerformed` para manter os dados na tela caso alguma informação esteja incorreta.
     - Validações adicionais foram implementadas para garantir que os campos não estejam vazios e que os dados sejam válidos antes de prosseguir com o registro.

## Conclusão
O projeto de gerenciamento de estoque foi aprimorado com a adição de validações para dados pessoais, garantindo que as informações inseridas pelos usuários sejam válidas antes de serem armazenadas. Isso melhora a integridade dos dados e a experiência do usuário ao interagir com o sistema.
