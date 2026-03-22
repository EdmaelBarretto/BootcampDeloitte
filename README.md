# 🚀 Bootcamp Deloitte  
# 🛒 Produtos do Ed

> CRUD completo de produtos com Spring Boot + H2 + Frontend estático

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=flat-square&logo=springboot)
![H2 Database](https://img.shields.io/badge/H2-Database-blue?style=flat-square)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple?style=flat-square&logo=bootstrap)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=flat-square&logo=apachemaven)

---

## 📋 Sobre o Projeto

Aplicação web full-stack para gerenciamento de produtos, desenvolvida com **Spring Boot** no backend e **HTML/CSS/JS + Bootstrap** no frontend. Utiliza banco de dados **H2** em arquivo para persistência entre reinicializações.

Desenvolvido como projeto de aprendizado para a **Deloitte e Porto Digital**.

---

## ✨ Funcionalidades

- ✅ **Cadastrar** produtos (nome, preço, estoque)
- ✅ **Listar** todos os produtos em tabela
- ✅ **Buscar** produto por ID
- ✅ **Atualizar** dados de um produto
- ✅ **Deletar** produto com confirmação visual
- ✅ **Validações** de negócio (nome, preço e estoque)
- ✅ **Notificação** em console ao cadastrar novo produto

---

## 🏗️ Arquitetura

```
produtos-do-ed/
├── pom.xml
└── src/
    └── main/
        ├── java/com/deloitteportodigital/produtos/
        │   ├── ProdutosDoEd.java               # Entry point
        │   ├── controller/
        │   │   └── ProdutoController.java      # REST endpoints
        │   ├── model/
        │   │   └── Produto.java                # Entidade JPA
        │   ├── repository/
        │   │   └── ProdutoRepository.java      # Spring Data JPA
        │   ├── service/
        │   │   ├── ProdutoService.java         # Regras de negócio
        │   │   └── EmailService.java           # Notificações
        │   └── validation/
        │       ├── ProdutoValidation.java      # Interface
        │       ├── EstoqueValidation.java
        │       ├── NomeProdutoValidation.java
        │       └── PrecoProdutoValidation.java
        └── resources/
            ├── application.properties
            └── static/
                ├── index.html
                ├── css/style.css
                └── js/app.js
```

---

## 🚀 Como Rodar

### Pré-requisitos

- Java 17+
- Maven 3.8+ (ou use o `mvnw` incluso no projeto)

### Passo a passo

```bash
# 1. Clone o repositório
git clone https://github.com/EdmaelBarretto/BootcampDeloitte.git

# 2. Entre na pasta do projeto
cd produtos-do-ed

# 3. Rode a aplicação
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### Acessos

| Serviço | URL |
|--------|-----|
| 🌐 Frontend | http://localhost:8080 |
| 🗄️ Console H2 | http://localhost:8080/h2-console |
| 🔌 API REST | http://localhost:8080/produtos |

> **Console H2:** JDBC URL: `jdbc:h2:file:./data/produtosdb` · User: `sa` · Senha: *(vazio)*

---

## 🔌 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/produtos` | Lista todos os produtos |
| `GET` | `/produtos/{id}` | Busca produto por ID |
| `POST` | `/produtos` | Cria novo produto |
| `PUT` | `/produtos/{id}` | Atualiza produto existente |
| `DELETE` | `/produtos/{id}` | Remove produto |

### Exemplo de Requisição (POST)

```json
POST /produtos
Content-Type: application/json

{
  "nome": "Teclado Mecânico",
  "preco": 349.90,
  "estoque": 15
}
```

### Exemplo de Resposta

```json
{
  "id": 1,
  "nome": "Teclado Mecânico",
  "preco": 349.90,
  "estoque": 15
}
```

---

## ✅ Validações de Negócio

| Regra | Mensagem de Erro |
|-------|-----------------|
| Nome deve ter no mínimo 3 caracteres | `"Nome do produto inválido"` |
| Preço deve ser maior que zero | `"O preço deve ser maior que zero"` |
| Estoque não pode ser negativo | `"O estoque não pode ser negativo"` |

---

## 🛠️ Tecnologias

| Camada | Tecnologia |
|--------|-----------|
| Linguagem | Java 17 |
| Framework | Spring Boot 3.2.5 |
| Persistência | Spring Data JPA + H2 |
| Build | Maven |
| Frontend | HTML5 + CSS3 + JavaScript |
| UI | Bootstrap 5.3 |

---

## 🐛 Bugs Corrigidos

Durante o desenvolvimento, os seguintes problemas foram identificados e corrigidos:

1. **`ProdutoService`** — Métodos `listar()`, `buscarPorId()` e `deletar()` estavam ausentes, quebrando as operações de Read e Delete
2. **`EmailService`** — Declarado sem `package` e sem `import`, impedindo a inicialização do Spring
3. **`application.properties`** — Propriedade `spring.datasource.url` duplicada causava conflito
4. **`pom.xml`** — Blocos de Markdown (` ``` `) dentro do XML tornavam o arquivo inválido para o Maven

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<p align="center">Feito com Java e Spring Boot</p>

---

## 👨‍💻 Autor

Edmael Barreto
Estudante de Análise e Desenvolvimento de Sistemas

🎯 Foco em: Java • QA • Cloud • APIs REST
