### Aplicação FullStack Management System Portfólio 🚀 🔄 🌐

Codificação de Aplicação completa a nível Full Stack Senior este sistema é uma aplicação de Gerenciamento de Projetos de Portfólio desenvolvida em Java com Spring Boot para o Backend e com suporte a operações CRUD para entidades de Projeto, integrando-se a um banco de dados PostgreSQL para persistência de dados juntamente com Hibernate. O Frontend foi implementado em JSP com Javascript e BootStrapp. O Backend foi implementado com Testes Unitários usando Junit com Mockito para testar aplicação. A arquitetura do sistema segue princípios de Orientação a Objetos, SOLID e Domain-Driven Design (DDD) para promover um código limpo, modular e facilmente escalável..


### Defafio Técnico

Desafio Técnico junto a CodGroup de aplicação  que se chama Management System Portfólio é uma aplicação FullStack com backend e frontend desenvolvida JSP, Javascript e Bootstrap para gerenciamento e visualização, edição e delação de projetos, pessoas e membros.

### Tecnologias Utilizadas 🛠️

- ![Java](https://img.shields.io/badge/-Java-007396?style=for-the-badge&logo=java&logoColor=white)
- ![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
- ![JSP](https://img.shields.io/badge/-JSP-007ACC?style=for-the-badge&logo=java&logoColor=white)
- ![JavaScript](https://img.shields.io/badge/-JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
- ![AJAX](https://img.shields.io/badge/-AJAX-007ACC?style=for-the-badge&logo=ajax&logoColor=white)
- ![jQuery](https://img.shields.io/badge/-jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white)
- ![PostgreSQL](https://img.shields.io/badge/-PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
- ![Maven](https://img.shields.io/badge/-Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
- ![Hibernate](https://img.shields.io/badge/-Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
- ![Docker](https://img.shields.io/badge/-Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)


### Estrutura do Projeto 🚀 🔄 🌐

O sistema é organizado em diversas camadas seguindo o padrão de arquitetura MVC (Model-View-Controller), complementado com serviços, repositórios (DAOs) e objetos de transferência de dados (DTOs), cada um com responsabilidades bem definidas:

Controller: Contém os endpoints da API, atuando como a interface entre o cliente e o servidor, direcionando as requisições para os serviços adequados.

Service: Lógica de negócios do sistema, onde regras específicas são processadas. Esta camada interage com a camada de persistência para buscar, salvar ou atualizar dados.

Repository (DAO): Camada de acesso a dados, responsável pela comunicação direta com o banco de dados. Utiliza o Spring Data JPA para abstrair e simplificar operações de banco de dados.

Model: Representa as entidades do domínio do negócio, mapeadas para o banco de dados através de anotações JPA.

DTO: Objetos de Transferência de Dados usados para encapsular dados e simplificar a passagem de múltiplos dados entre camadas.

Servlets: Utilizados para operações de banco de dados que não são diretamente suportadas pelo Spring Data JPA, proporcionando uma camada adicional de manipulação de dados.

### Funcionalidades Implementadas
Listagem de Projetos: GET /api/projeto - Retorna uma lista de todos os projetos cadastrados.

Busca de Projeto por ID: GET /api/projeto/{id} - Retorna os detalhes de um projeto específico baseado no ID.

Criação de Projeto: POST /api/projeto - Cria um novo projeto com os dados fornecidos.
http://localhost:8085/api/projeto
```

{
        "id": 18,
        "nome": "Pc Gamer Rog Strix 18 81",
        "dataInicio": "18-02-2023",
        "dataPrevisaoFim": "30-03-2024",
        "dataFim": "30-03-2024",
        "descricao": "Pc Gamer Rog Strix 18",
        "status": "Concluído",
        "orcamento": 4800.0,
        "risco": "Baixo"
}
```

Atualização de Projeto: PUT /api/projeto/{id} - Atualiza os dados de um projeto existente.
http://localhost:8085/api/projeto/{id}
```
{
        "id": 12,
        "nome": "Pc Gamer Rog Strix 18 81",
        "dataInicio": "18-02-2023",
        "dataPrevisaoFim": "30-03-2024",
        "dataFim": "30-03-2024",
        "descricao": "Pc Gamer Rog Strix 18",
        "status": "Concluído",
        "orcamento": 4800.0,
        "risco": "Baixo"
}
```
Deleção de Projeto: DELETE /api/projeto/{id} - Remove um projeto do sistema.

Associação de Pessoa a Projeto: POST /api/projeto/pessoa-projetos - Associa uma pessoa a um projeto existente.

http://localhost:8085/api/projeto/pessoa-projetos

Necessário ter uma pessoa Cadastrada com ID 18 e um projeto com id 17 no Banco de dados para Associar Projetos a Pessoas. E assim por diante, a partir do momento que cadastra um Projeto é gerado um Id que depois pode ser associado
ao id de uma pessoa cadastrada.
```
{
  "pessoaId": 18,
  "projetoId": 17
}


```
### Princípios e Padrões Adotados
Orientação a Objetos: O sistema utiliza classes e objetos para modelar entidades do domínio e suas interações, promovendo encapsulamento, herança e polimorfismo.

SOLID: Os princípios SOLID são seguidos para garantir que o sistema seja fácil de manter e estender. Exemplo: injeção de dependências é usada para desacoplar as classes.

DDD: Domain-Driven Design é adotado para focar na complexidade do domínio do negócio, modelando o sistema em torno do domínio e desenvolvendo uma linguagem ubíqua.

DAO Pattern: Usado para abstrair e encapsular todos os acessos ao banco de dados, o padrão DAO é utilizado nas operações que o Spring Data JPA não cobre diretamente.

DTO Pattern: Para transferir dados entre a API e os clientes de forma eficiente, evitando o envio de dados desnecessários ou sensíveis.

### Conclusão
Este sistema exemplifica uma aplicação web moderna, seguindo boas práticas de desenvolvimento e arquitetura de software. Ao aderir a padrões e princípios bem estabelecidos, o código resultante é limpo, modular e fácil de entender, facilitando a manutenção e futuras expansões do projeto.



### Desenvolvido por:
Emerson Amorim [@emerson-amorim-dev](https://www.linkedin.com/in/emerson-amorim-dev/)

