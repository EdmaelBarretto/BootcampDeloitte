# 🚀 Bootcamp Deloitte  

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)  
![Spring](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)  
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)  
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)  
![H2 Database](https://img.shields.io/badge/H2-007396?style=for-the-badge&logo=h2-database&logoColor=white)  

---

# 🛒 Produtos do Ed

## 📖 Descrição

O **Produtos do Ed** é uma **API RESTful** desenvolvida em **Java** utilizando **Spring Boot**.  
O projeto implementa um sistema de **CRUD (Create, Read, Update, Delete)** para gerenciamento de produtos, permitindo **criar, listar, atualizar e deletar produtos**, com validações, envio de email e testes automatizados.

O objetivo do projeto é aplicar conceitos de:

* **Programação Orientada a Objetos (POO)**  
* **Desenvolvimento de APIs REST**  
* **Persistência de dados com JPA/Hibernate**  
* **Arquitetura em camadas (Controller, Service, Repository, Model, Validation, EmailService)**  
* **Testes unitários e de controller com JUnit + Mockito + MockMvc**  

---

## 🚀 Tecnologias Utilizadas

* **Java 17**  
* **Spring Boot**  
* **Spring Web**  
* **Spring Data JPA**  
* **Hibernate**  
* **H2 Database (in-memory)**  
* **JUnit 5 + Mockito + MockMvc**  
* **Maven**  
* **IntelliJ IDEA**


## 🧩 Função das camadas

| Camada     | Função                                                      |
| ---------- | ----------------------------------------------------------- |
| Controller | Recebe as requisições HTTP da API                           |
| Service    | Contém a lógica de negócio                                  |
| Repository | Responsável pelo acesso ao banco de dados                  |
| Model      | Representa a entidade do sistema                            |
| Validation | Contém validações de dados (nome, preço, estoque, etc.)    |
| Email      | Serviço de envio de emails (simulação ou integração real)  |
| Test       | Valida o comportamento do Controller e Service             |

---

## 📌 Funcionalidades

A API permite:

* ➕ Cadastrar produtos  
* 📋 Listar produtos  
* ✏️ Atualizar produtos  
* ❌ Deletar produtos  
* 🧪 Testes unitários e de controller automatizados  

---

## 🔗 Endpoints da API

| Método HTTP | Endpoint       | Descrição               |
| ----------- | -------------- | ----------------------- |
| GET         | /produtos      | Lista todos os produtos |
| POST        | /produtos      | Cria um novo produto    |
| PUT         | /produtos/{id} | Atualiza um produto     |
| DELETE      | /produtos/{id} | Remove um produto       |

## 🧪 Testes Automatizados

O projeto inclui **testes unitários e de controller**:

### Testes de Controller (`ProdutoControllerTest.java`)

* Simulam requisições HTTP (GET, POST, PUT, DELETE) usando **MockMvc**  
* **@WebMvcTest(ProdutoController.class)** → Roda apenas a camada de Controller  
* **@MockBean ProdutoService** → O service é mockado (não acessa banco real)  
* Verifica se todos os endpoints funcionam corretamente  

### Testes de Service

* Valida regras de negócio implementadas no **ProdutoService**  
* Garante que métodos como `salvar`, `listar`, `atualizar` e `deletar` retornem os resultados esperados  

---

## 🗄 Banco de Dados

O projeto utiliza **H2 Database**, um banco em memória usado para testes.

### Acesse o console do banco

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

### Na tela de login

- **JDBC URL:** `jdbc:h2:mem:produtosdb`  
- **User:** `sa`  
- **Password:** *(deixe em branco)*  

Clique em **Connect**.  

---

## 👨‍💻 Autor

Edmael Barreto
Estudante de Análise e Desenvolvimento de Sistemas

🎯 Foco em: Java • QA • Cloud • APIs REST
