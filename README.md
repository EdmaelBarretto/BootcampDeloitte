# 🚀 Bootcamp Deloitte  

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

# 🛒 Produtos do Ed

## 📖 Descrição

O **Produtos do Ed** é uma **API RESTful** desenvolvida em **Java** utilizando **Spring Boot**.
O projeto implementa um sistema simples de **CRUD (Create, Read, Update, Delete)** para gerenciamento de produtos.

O objetivo do projeto é aplicar conceitos fundamentais de:

* **Programação Orientada a Objetos (POO)**
* **Desenvolvimento de APIs REST**
* **Persistência de dados com JPA/Hibernate**
* **Arquitetura em camadas (Controller, Service, Repository, Model)**

---

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **H2 Database**
* **Maven**
* **IntelliJ IDEA**

---

## 📂 Estrutura do Projeto

```text
src/main/java
│
├── controller
│      ProdutoController.java
│
├── service
│      ProdutoService.java
│
├── repository
│      ProdutoRepository.java
│
├── model
│      Produto.java
│
└── ProdutosDoEd.java
```

### Função das camadas

| Camada     | Função                                    |
| ---------- | ----------------------------------------- |
| Controller | Recebe as requisições HTTP da API         |
| Service    | Contém a lógica de negócio                |
| Repository | Responsável pelo acesso ao banco de dados |
| Model      | Representa a entidade do sistema          |

---

## 📌 Funcionalidades

A API permite:

* ➕ Cadastrar produtos
* 📋 Listar produtos
* ✏️ Atualizar produtos
* ❌ Deletar produtos

---

## 🔗 Endpoints da API

| Método HTTP | Endpoint       | Descrição               |
| ----------- | -------------- | ----------------------- |
| GET         | /produtos      | Lista todos os produtos |
| POST        | /produtos      | Cria um novo produto    |
| PUT         | /produtos/{id} | Atualiza um produto     |
| DELETE      | /produtos/{id} | Remove um produto       |

---

## 🧪 Exemplo de requisição

### Criar produto

```http
POST /produtos
```

Body JSON:

```json
{
  "nome": "Camiseta",
  "preco": 50
}
```

Resposta esperada:

```json
{
  "id": 1,
  "nome": "Camiseta",
  "preco": 50
}
```

---

## 🗄 Banco de Dados

O projeto utiliza **H2 Database**, um banco de dados em memória usado para testes.

Acesse o console do banco:

```
http://localhost:8080/h2-console
```

Configuração:

```
JDBC URL: jdbc:h2:mem:produtosdb
User: sa
Password:
```

---

## ▶️ Como executar o projeto

1. Clonar o repositório

```
git clone https://github.com/EdmaelBarretto/BootcampDeloitte.git
```

2. Abrir o projeto no IntelliJ

3. Executar a classe principal:

```
ProdutosDoEd.java
```

4. A aplicação iniciará em:

```
http://localhost:8080
```

---

## 👨‍💻 Autor

**Edmael Barreto**

Estudante de Análise e Desenvolvimento de Sistemas

🎯 Foco em: Java • QA • Cloud
