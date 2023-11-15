# REST API - Cliente

Esta documentação fornece informações detalhadas sobre como utilizar a REST API para gerenciar clientes. A API foi desenvolvida utilizando Spring Boot e disponibiliza operações CRUD (Create, Read, Update, Delete) para a manipulação de dados de clientes.

Esta API foi criada como parte do aprendizado inicial em Java e Spring Boot, durante a disciplina de Programação Orientada a Objetos. Para visualizar o projeto final, consulte a branch main.

A seguir, apresentamos os métodos implementados e suas respectivas formas de uso.


## 1. Listar Clientes

**Endpoint:** `GET /cliente`

**Descrição:** Retorna a lista de todos os clientes cadastrados.

**Exemplo de Uso:**

```bash
curl -X GET http://localhost:8080/cliente
```


## 2. Adicionar Cliente

**Endpoint:** POST /cliente

**Descrição:** Adiciona um novo cliente.

**Corpo da Requisição:**

```json
{
  "nome": "Nome do Cliente"
}
```

**Exemplo de Uso:**

```bash
curl -X POST \
    http://localhost:8080/cliente \
    -H 'Content-Type: application/json' \
    -d '{
            "nome": "Novo Cliente"
        }'
```


## 3. Atualizar Cliente
**Endpoint:** PUT /cliente/{id}

**Descrição:** Atualiza as informações de um cliente existente.

**Parâmetros:**

* {id}: O ID do cliente a ser atualizado.

**Corpo da Requisição:**

```json
{
  "nome": "Novo Nome do Cliente"
}
```
**Exemplo de Uso:**

```bash
curl -X PUT \
    http://localhost:8080/cliente/1 \
    -H 'Content-Type: application/json' \
    -d '{
            "nome": "Novo Nome"
        }'
```


## 4. Deletar Cliente

**Endpoint:** DELETE /cliente/{id}

**Descrição:** Deleta um cliente com o ID fornecido.

**Parâmetros:**

* {id}: O ID do cliente a ser deletado.

**Exemplo de Uso:**

```bash
curl -X DELETE \
    http://localhost:8080/cliente/1
```

# Exemplos no Postman
Você pode usar o Postman para testar a API com facilidade. Abaixo estão exemplos de solicitações para cada endpoint mencionado acima:

## 1. Listar Clientes:

listar de clientes:

Método: GET
URL: http://localhost:8080/cliente


Adicionar Cliente:

Método: POST
URL: http://localhost:8080/cliente
Corpo da Requisição (JSON):
json
Copy code
{
  "nome": "Novo Cliente"
}


Atualizar Cliente:

Método: PUT
URL: http://localhost:8080/cliente/1 (substitua 1 pelo ID do cliente desejado)
Corpo da Requisição (JSON):
json
Copy code
{
  "nome": "Novo Nome"
}


Deletar Cliente:

Método: DELETE
URL: http://localhost:8080/cliente/1 (substitua 1 pelo ID do cliente desejado)
Lembre-se de ajustar as URLs conforme necessário para o ambiente em que sua aplicação está sendo executada.

