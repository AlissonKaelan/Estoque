# Documentação do Projeto Estoque

Este projeto é um sistema de controle de estoque desenvolvido em Java, utilizando JDBC para interação com um banco de dados MySQL. O sistema permite o registro de usuários, login e gerenciamento de produtos no estoque.

## Estrutura do Projeto

O projeto é dividido em várias camadas:

- **Connection**: Gerencia a conexão com o banco de dados.
- **Model**: Contém as classes que representam as entidades do sistema (Cadastro, Login, Produto).
- **DAO (Data Access Object)**: Contém as classes responsáveis pela interação com o banco de dados para cada entidade.
- **View**: Contém as classes que representam a interface gráfica do usuário.

## Dependências

- Java Development Kit (JDK)
- MySQL Connector/J (para conexão com o MySQL)
- MySQL Server

## Configuração do Banco de Dados

1. Crie um banco de dados chamado `estoque`.
2. Execute os seguintes comandos SQL para criar as tabelas necessárias:

  ```sql
CREATE TABLE cadastro (
 id INT AUTO_INCREMENT PRIMARY KEY,
usuario VARCHAR(255) NOT NULL,
data_nascimento DATE NOT NULL,
cpf VARCHAR(11) NOT NULL,
senha VARCHAR(255) NOT NULL
 );
CREATE TABLE produtos (
id INT AUTO_INCREMENT PRIMARY KEY,
 descricao VARCHAR(255) NOT NULL,
 quantidade INT NOT NULL,
 preco DOUBLE NOT NULL
);
```
### Dump do Banco de Dados

Um dump do banco de dados está disponível na pasta `Dump database` do projeto. Você pode usar este arquivo para restaurar o banco de dados rapidamente.

## Configuração do Projeto

1. Clone o repositório:

   git clone https://github.com/AlissonKaelan/Estoque

2. Abra o projeto em sua IDE favorita (como IntelliJ IDEA ou Eclipse).
3. Configure a conexão com o banco de dados na classe `ConnectionFactory`:

   private static final String URL = "jdbc:mysql://localhost:3306/estoque";<br>
   private static final String USER = "root";<br>
   private static final String PASS = "admin";<br>

## Funcionalidades

### Cadastro de Usuário

- O usuário pode se registrar fornecendo um nome de usuário, data de nascimento, CPF e senha.
- O sistema valida se o CPF e a data de nascimento são válidos.

### Login

- O usuário pode fazer login utilizando seu nome de usuário e senha.
- O sistema verifica se as credenciais estão corretas.

### Gerenciamento de Produtos

- O usuário pode adicionar, atualizar, excluir e buscar produtos no estoque.
- Cada produto possui uma descrição, quantidade e preço.

## Como Executar

1. Compile o projeto.
2. Execute a classe `Login` para iniciar o sistema.
3. Siga as instruções na interface gráfica para registrar-se ou fazer login.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir um "issue" ou enviar um "pull request".

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
