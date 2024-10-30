# Projeto Spring Boot

Este projeto implementa uma API RESTful para um sistema de e-commerce simples, utilizando Spring Boot, JPA e Hibernate.

## Funcionalidades

- **Gerenciamento de Usuários**:
    - Cadastro de novos usuários.
    - Busca de usuários por ID.
    - Listagem de todos os usuários.
    - Atualização de informações de usuários.
    - Exclusão de usuários.

- **Gerenciamento de Produtos**:
    - Cadastro de novos produtos.
    - Busca de produtos por ID.
    - Listagem de todos os produtos.
    - Atualização de informações de produtos.
    - Exclusão de produtos.

- **Gerenciamento de Categorias**:
    - Cadastro de novas categorias.
    - Busca de categorias por ID.
    - Listagem de todas as categorias.
    - Atualização de informações de categorias.
    - Exclusão de categorias.

- **Gerenciamento de Pedidos**:
    - Criação de novos pedidos.
    - Busca de pedidos por ID.
    - Listagem de todos os pedidos de um usuário.
    - Atualização de informações de pedidos.
    - Exclusão de pedidos.

- **Autenticação e Autorização**:
    - Login de usuários com email e senha.
    - Geração de tokens JWT para autenticação.
    - Proteção de rotas com base em token JWT.


## Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de dados H2 (em memória)
- Maven

## Como Executar
1. Clone o repositório: `git clone https://github.com/seu-usuario/nome-do-repositorio.git`
2. Acesse o diretório do projeto: `cd nome-do-repositorio`
3. Execute o projeto Spring Boot: `mvn spring-boot:run`
4. A API estará disponível em `http://localhost:8080`

## Próximos Passos
- Criar uma interface de usuário (ex: Angular, React) para interagir com a API.
- Implementar testes unitários e de integração para garantir a qualidade do código.
- Implementar paginação para endpoints de listagem.
- Implementar filtros de busca para endpoints de listagem.
- Adicionar tratamento de erros mais robusto.

## Contribuindo
Contribuições são bem-vindas! Sinta-se à vontade para abrir um pull request com suas sugestões de melhorias.

## Reconhecimentos

Este projeto foi desenvolvido como parte de um [curso de programação em Java](https://www.udemy.com/course/java-curso-completo/).