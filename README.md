# Spring Boot To-Do List App

Esta é uma API desenvolvida em Java utilizando o framework Spring Boot para um aplicativo de lista de tarefas. A API proporciona operações CRUD (Create, Read, Update, Delete) para eficaz gestão de tarefas. O propósito por trás do desenvolvimento é a aplicação prática dos conceitos aprendidos em programação orientada a objetos ao longo do semestre.

* **Aluno:** Bruno Bilhar Karaziack - 202110891

* **Docente:** José Matias Lemes Filho

* **Disciplina:** Progamação Orientada a Objetos

## Índice

- [Configuração do Ambiente de Desenvolvimento](#configuração-do-ambiente-de-desenvolvimento)
- [Início Rápido](#início-rápido)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Endpoints](#endpoints)
  - [Obter Tarefas](#obter-tarefas)
  - [Criar Tarefa](#criar-tarefa)
  - [Atualizar Tarefa](#atualizar-tarefa)
  - [Excluir Tarefa](#excluir-tarefa)
  - [Obter Tarefas Excluídas](#obter-tarefas-excluídas)
  - [Recuperar Tarefa Excluída](#recuperar-tarefa-excluída)
- [Utilizando Collection Postman](#utilizando-collection-postman)
- [Notas](#notas)
- [Dependências](#dependências)
- [Contribuindo](#contribuindo)

## Configuração do Ambiente de Desenvolvimento

Antes de começar, certifique-se de ter o Java e o Maven instalados em seu sistema. Além disso, é necessário que seu ambiente tenha suporte ao Lombok. Algumas IDEs oferecem suporte nativo ao Lombok, mas se necessário, você pode obter mais informações [aqui](https://projectlombok.org/).

Você pode importar o projeto em sua IDE favorita ou executar o aplicativo usando a linha de comando.

## Início Rápido

Para usar a API ToDoList, siga estes passos:

1. Clone o repositório: `git clone https://github.com/bruno-bk/trabalho_poo.git`
2. Importe o projeto em sua IDE de preferência.
3. Execute o projeto.
4. Acesse os endpoints da API usando a URL base: `http://localhost:8080/tasks`

## Estrutura do Projeto

- `src/main/java/com/todolist/ToDoList/ToDoListApplication.java`: Definição da classe de modelo `Task` usando Lombok.
- `src/main/java/com/todolist/ToDoList/controler/TaskController.java`: Controlador Spring Boot para expor os endpoints CRUD.
- `src/main/java/com/todolist/ToDoList/model/Task.java`: Classe para modelo de uma task.
- `src/main/java/com/todolist/ToDoList/repository/TaskRepository.java`: Interface do Spring Data JPA para manipulação de dados.

## Endpoints

### Obter Tarefas

- **URL:** `/tasks`
- **Método:** `GET`
- **Descrição:** Obtenha uma lista de tarefas ativas.
- **Resposta:**
  - Código de Status: `200 OK`
  - Corpo: Lista de tarefas
- **Exemplo de Uso:**
```bash
curl -X GET http://localhost:8080/tasks
```

### Criar Tarefa

- **URL:** `/tasks`
- **Método:** `POST`
- **Descrição:** Crie uma nova tarefa.
- **Requisição:**
  - Corpo: Objeto de Tarefa
- **Resposta:**
  - Código de Status: `201 Created`
  - Corpo: Tarefa criada
  - Código de Status: `400 bad Request` (Se a requisição não estive como esperada).
- **Exemplo de Uso:**
```bash
curl -X POST \
  http://localhost:8080/tasks \
  -H 'Content-Type: application/json' \
  -d '{
        "description": "Teste1"
      }'
```

### Atualizar Tarefa

- **URL:** `/tasks/{id}`
- **Método:** `PUT`
- **Descrição:** Atualize uma tarefa existente.
- **Requisição:**
  - Variável de Caminho: ID da Tarefa
  - Corpo: Objeto de tarefa atualizado
- **Resposta:**
  - Código de Status: `200 OK`
  - Corpo: Tarefa atualizada
  - Código de Status: `400 bad Request` (Se a requisição não estive como esperada).
  - Código de Status: `404 Not Found` (se a tarefa não existir)
- **Exemplo de Uso:**
```bash
curl -X PUT \
  http://localhost:8080/tasks/1 \
  -H 'Content-Type: application/json' \
  -d '{
        "description": "teste update",
        "completed": true
      }'
```

### Excluir Tarefa

- **URL:** `/tasks/{id}`
- **Método:** `DELETE`
- **Descrição:** Marque uma tarefa como excluída.
- **Requisição:**
  - Variável de Caminho: ID da Tarefa
- **Resposta:**
  - Código de Status: `200 OK`
  - Corpo: Tarefa excluída
  - Código de Status: `404 Not Found` (se a tarefa não existir)
- **Exemplo de Uso:**
```bash
curl -X DELETE http://localhost:8080/tasks/1
```

### Obter Tarefas Excluídas

- **URL:** `/tasks/bin`
- **Método:** `GET`
- **Descrição:** Obtenha uma lista de tarefas excluídas.
- **Resposta:**
  - Código de Status: `200 OK`
  - Corpo: Lista de tarefas excluídas
- **Exemplo de Uso:**
```bash
curl -X GET http://localhost:8080/tasks/bin
```

### Recuperar Tarefa Excluída

- **URL:** `/tasks/recover/{id}`
- **Método:** `PUT`
- **Descrição:** Recupere uma tarefa excluída.
- **Requisição:**
  - Variável de Caminho: ID da Tarefa
- **Resposta:**
  - Código de Status: `200 OK`
  - Corpo: Tarefa recuperada
  - Código de Status: `404 Not Found` (se a tarefa não existir)
- **Exemplo de Uso:**
```bash
curl -X PUT http://localhost:8080/tasks/recover/1
```

## Utilizando Collection Postman

Este repositório inclui uma Collection no Postman para simplificar e automatizar o teste de API. Siga os passos abaixo para começar:

**Instalação do Postman:** Certifique-se de ter o Postman instalado em seu sistema. Se ainda não tiver, faça o download e a instalação a partir do site oficial: Postman Download.

**Importando a Collection:** Após a instalação do Postman, abra a aplicação e clique no botão "Import" localizado no canto superior esquerdo. clique em file e selecione o arquivo *Trabalho POO.postman_collection*.

Pronto seu ambiente no Postman está configurado!

## Notas

    * Todas as tarefas excluídas permanecem no banco de dados e podem ser recuperadas no endpoint "bin".

    * As respostas de erro incluem códigos de status apropriados.

    * Certifique-se de lidar com as respostas da API adequadamente em sua aplicação.

## Dependências

Esta API é desenvolvida usando o framework Spring Boot e depende das seguintes bibliotecas:

* **Spring Web** - O Spring Web fornece suporte para desenvolvimento de aplicativos da web, incluindo a criação de controladores usando a arquitetura MVC (Model-View-Controller).
* **spring Boot Devtools** - Spring Boot Devtools é uma coleção de ferramentas que visam melhorar a experiência de desenvolvimento, permitindo a reinicialização automática da aplicação quando os arquivos de código-fonte são modificados.
* **Spring Data JPA** - O Spring Data JPA simplifica o acesso a dados usando a tecnologia JPA (Java Persistence API) e fornece um modelo de programação baseado em repositórios para interagir com o banco de dados.
* **H2 Database** - O H2 Database é um banco de dados relacional em memória, geralmente usado durante o desenvolvimento e teste de aplicativos.
* **Lombok** - Lombok é uma biblioteca que reduz a verbosidade do código Java, gerando automaticamente métodos como getters, setters e construtores, entre outros.

## Contribuindo

Sinta-se à vontade para abrir issues ou enviar pull requests para melhorias e correções.
