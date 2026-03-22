# 🚀 Bootcamp Deloitte  
# 🛒 Produtos do Ed

> CRUD completo de produtos com Spring Boot + H2 + Frontend estático

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=flat-square&logo=springboot)
![Lombok](https://img.shields.io/badge/Lombok-active-pink?style=flat-square)
![H2 Database](https://img.shields.io/badge/H2-Database-blue?style=flat-square)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple?style=flat-square&logo=bootstrap)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=flat-square&logo=apachemaven)

---

## 📋 Sobre o Projeto

Aplicação web full-stack para gerenciamento de produtos, desenvolvida com **Spring Boot** no backend e **HTML/CSS/JS + Bootstrap** no frontend. Utiliza banco de dados **H2** em arquivo para persistência entre reinicializações.

Desenvolvido como projeto de aprendizado para a **Deloitte Porto Digital**.

---

## ✨ Funcionalidades

- ✅ **Cadastrar** produtos (nome, preço com máscara R$, estoque)
- ✅ **Listar** todos os produtos em tabela
- ✅ **Buscar** produto por ID ou por nome
- ✅ **Atualizar** dados de um produto existente
- ✅ **Deletar** produto com confirmação
- ✅ **Validações** de negócio (nome, preço e estoque)
- ✅ **Tratamento global de erros** com mensagens claras no frontend
- ✅ **CORS** configurado centralmente
- ✅ **Logs** estruturados em todos os serviços

---

## 🏗️ Arquitetura

```
produtos-do-ed/
├── pom.xml
└── src/main/
    ├── java/com/deloitteportodigital/produtos/
    │   ├── ProdutosDoEd.java                        # Entry point
    │   ├── config/
    │   │   └── WebConfig.java                       # Configuração de CORS
    │   ├── controller/
    │   │   └── ProdutoController.java               # Endpoints REST
    │   ├── exception/
    │   │   └── GlobalExceptionHandler.java          # Tratamento global de erros
    │   ├── model/
    │   │   └── Produto.java                         # Entidade JPA + Lombok
    │   ├── repository/
    │   │   └── ProdutoRepository.java               # Spring Data JPA
    │   ├── service/
    │   │   ├── ProdutoService.java                  # Regras de negócio
    │   │   └── EmailService.java                    # Notificações via log
    │   └── validation/
    │       ├── ProdutoValidation.java               # Interface de validação
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
git clone https://github.com/seu-usuario/produtos-do-ed.git

# 2. Entre na pasta do projeto
cd produtos-do-ed

# 3. Rode a aplicação
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

> ⚠️ **Importante:** não abra o `index.html` diretamente pelo navegador nem use Live Server.
> Acesse sempre pelo Spring: **http://localhost:8080**

### Acessos

| Serviço | URL |
|--------|-----|
| 🌐 Frontend | http://localhost:8080 |
| 🗄️ Console H2 | http://localhost:8080/h2-console |
| 🔌 API REST | http://localhost:8080/produtos |

> **Console H2:** JDBC URL: `jdbc:h2:file:./data/produtosdb` · User: `sa` · Senha: *(vazio)*

---

## 🔌 Endpoints da API

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| `GET` | `/produtos` | Lista todos os produtos | 200 |
| `GET` | `/produtos/{id}` | Busca produto por ID | 200 / 404 |
| `GET` | `/produtos/buscar?nome=x` | Busca por nome (parcial) | 200 |
| `POST` | `/produtos` | Cria novo produto | 201 |
| `PUT` | `/produtos/{id}` | Atualiza produto existente | 200 |
| `DELETE` | `/produtos/{id}` | Remove produto | 204 |

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

### Exemplo de Resposta (201 Created)

```json
{
  "id": 1,
  "nome": "Teclado Mecânico",
  "preco": 349.90,
  "estoque": 15
}
```

### Exemplo de Resposta de Erro (400 Bad Request)

```json
{
  "timestamp": "2025-06-10T14:32:00",
  "status": 400,
  "erro": "Bad Request",
  "mensagem": "O preço deve ser maior que zero"
}
```

---

## ✅ Validações de Negócio

| Regra | Mensagem de Erro |
|-------|-----------------|
| Nome deve ter no mínimo 3 caracteres | `"Nome do produto inválido — mínimo 3 caracteres"` |
| Preço deve ser maior que zero | `"O preço deve ser maior que zero"` |
| Estoque não pode ser negativo | `"O estoque não pode ser negativo"` |

---

## 🧩 Decisões Técnicas

### Lombok
O model `Produto.java` usa `@Data`, `@Builder`, `@NoArgsConstructor` e `@AllArgsConstructor` para eliminar getters, setters e construtores manuais. Os services e controller usam `@RequiredArgsConstructor` no lugar de `@Autowired`.

### GlobalExceptionHandler *(arquivo novo)*
Classe na pasta `exception/` anotada com `@RestControllerAdvice`. Intercepta qualquer `IllegalArgumentException` lançada pelas validações e devolve um JSON estruturado com status HTTP correto — garantindo que o frontend sempre receba uma mensagem de erro legível em vez de silêncio.

### WebConfig *(arquivo novo)*
Substitui o `@CrossOrigin` espalhado pelo controller. Centraliza a configuração de CORS em um único lugar, com os métodos `GET`, `POST`, `PUT`, `DELETE` e `OPTIONS` permitidos explicitamente. O `OPTIONS` é obrigatório para evitar erro **405** em requisições preflight do navegador.

### Frontend servido pelo Spring
O `index.html` fica em `src/main/resources/static/` e é servido diretamente pelo Spring na porta `8080`. Isso evita problemas de CORS e de rota — `/produtos` aponta para a API correta. **Nunca abrir via `file://` ou Live Server.**

---

## 🛠️ Tecnologias

| Camada | Tecnologia |
|--------|-----------|
| Linguagem | Java 17 |
| Framework | Spring Boot 3.2.5 |
| Redução de boilerplate | Lombok |
| Persistência | Spring Data JPA + H2 |
| Validação | Spring Boot Validation (`@NotBlank`, `@Min`, `@Size`) |
| Build | Maven |
| Frontend | HTML5 + CSS3 + JavaScript |
| UI | Bootstrap 5.3 |

---

## 🐛 Histórico de Correções

| # | Arquivo | Problema | Solução |
|---|---------|----------|---------|
| 1 | `ProdutoService.java` | Faltavam `listar()`, `buscarPorId()`, `deletar()` | Métodos implementados |
| 2 | `EmailService.java` | Sem `package` e sem `import` | Corrigido com pacote e Slf4j |
| 3 | `application.properties` | `datasource.url` duplicado | URL única em arquivo |
| 4 | `pom.xml` | Crases Markdown dentro do XML | XML corrigido; validation adicionado |
| 5 | `app.js` | `addEventListener` antes do DOM estar pronto | Envolvido em `DOMContentLoaded` |
| 6 | `app.js` | Erros sumiam sem mensagem | `.catch()` sempre exibe o erro |
| 7 | `WebConfig.java` | CORS sem `OPTIONS` causava erro 405 | `OPTIONS` adicionado explicitamente |
| 8 | Projeto todo | Abertura via Live Server / `file://` | Deve rodar via `localhost:8080` |

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<p align="center">Feito com ☕ e Spring Boot</p>

---

## 👨‍💻 Autor

Edmael Barreto
Estudante de Análise e Desenvolvimento de Sistemas

🎯 Foco em: Java • QA • Cloud • APIs REST
