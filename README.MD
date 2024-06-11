# Controle Acadêmico - README

## Descrição:

Este é um projeto de uma aplicação de controle acadêmico desenvolvida em Java com o framework Spring Boot. A aplicação é voltada para gerenciamento de alunos em uma academia, permitindo a realização de operações como inserção, alteração, exclusão e filtragem de alunos, além de autenticação de usuários e controle de sessão.

## Funcionalidades:

1. **Cadastro de Alunos:**
    - Permite inserir novos alunos no sistema, fornecendo um formulário para preenchimento dos dados do aluno, como nome, idade, turno, etc.
2. **Listagem de Alunos:**
    - Exibe uma lista dos alunos cadastrados, permitindo visualizar detalhes de cada aluno, como nome, idade, turno, etc.
3. **Edição de Alunos:**

    - Permite alterar os dados de um aluno já cadastrado no sistema, fornecendo um formulário preenchido com os dados atuais do aluno para edição.

4. **Exclusão de Alunos:**

    - Permite excluir um aluno cadastrado no sistema.

5. **Filtragem de Alunos:**

    - Oferece opções para filtrar os alunos por diferentes critérios, como status (ativo, cancelado, inativo, trancado) e pesquisa por nome.

6. **Autenticação de Usuários:**

    - Permite que usuários se autentiquem na aplicação através de um sistema de login.

7. **Controle de Sessão:**
    - Mantém o usuário autenticado durante a sessão, permitindo o acesso a áreas restritas da aplicação apenas para usuários autenticados.

## Tecnologias Utilizadas:

-   Java
-   Spring Boot
-   Spring MVC
-   Spring Data JPA
-   Hibernate
-   Thymeleaf (para a criação de templates HTML)
-   MySQL
-   Bootstrap (para o design e estilo do frontend)
-   Validador de Formulários do Spring (para validação de dados)
-   MD5 (para criptografia de senhas)
-   HttpSession (para controle de sessão)

## Requisitos Necessários:

-   **JDK (Java Development Kit):** Certifique-se de ter o JDK instalado em sua máquina. Você pode baixar e instalar o JDK a partir do site oficial da Oracle ou de outras fontes confiáveis.

```bash
    $ sudo apt update
    $ sudo apt install openjdk-17-jdk
```

-   **Maven:** O Maven é uma ferramenta de automação de compilação utilizada principalmente para projetos Java. Certifique-se de ter o Maven instalado em sua máquina para compilar e executar a aplicação.

```bash
    $ sudo apt install -y maven
```

-   **MySQL:** Este projeto utiliza o MySQL como banco de dados. Certifique-se de ter o MySQL instalado e em execução em sua máquina. Você pode baixar e instalar o MySQL a partir do site oficial ou de outras fontes confiáveis. E recomendo utilizar o Mysql Workbench para gerenciamento e visualização do banco.

```bash
    $ sudo apt install mysql-server
```

-   **Conexão com a Internet (para baixar dependências):** Durante o processo de compilação e execução do projeto, o Maven fará o download de todas as dependências necessárias para a aplicação. Certifique-se de ter uma conexão com a internet para que o Maven possa baixar essas dependências.

## Como Executar:

1. Clone este repositório para o seu ambiente local.

```bash
    $ git clone https://github.com/GiovaniMurakami/academyProjetoPOO.git
```

2. Crie um banco de dados para ser utilizado pelo projeto.

```bash
    $ sudo mysql -u root -p
```

3. Entre com a senha do usuário root do seu Mysql-server, e copie o comando abaixo:

```Mysql
    $ CREATE SCHEMA `academy`;
```

4. Configure as informações do banco de dados no arquivo `application.properties`.

```java
    spring.application.name=academy

    #thymeleaf config
    spring.thymeleaf.mode=HTML
    spring.thymeleaf.cache=FALSE

    #mysql config
    spring.datasource.url=jdbc:mysql://localhost:3306/academy
    spring.datasource.username=<USER_NAME>
    spring.datasource.password=<USER_PASSWORD>
    spring.jpa.hibernate.ddl_auto=update
```

5. Execute a aplicação utilizando o Maven.

```bash
    $ cd diretorio_projeto/
    $ mvn spring-boot:run
```

6. Acesse a aplicação através do navegador, utilizando o endereço `http://localhost:8080`.

## Observações:

-   Certifique-se de que o banco de dados configurado está em execução antes de iniciar a aplicação.
-   Este projeto pode ser expandido com novas funcionalidades, como relatórios, integração com sistemas de pagamento, entre outros.
-   Abaixo existe um link que te redireciona para o TUTORIAL.MD.
    (https://github.com/GiovaniMurakami/academyProjetoPOO/blob/master/docs/TUTORIAL.MD).

## Autores:

Este projeto foi desenvolvido por Giovani Murakami e João Detore
