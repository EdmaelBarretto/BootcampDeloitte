// ============================================================
//  app.js — Produtos do Ed
//  ✅ Correções:
//    1. DOMContentLoaded garante que o form existe antes de bindar
//    2. Máscara de moeda (R$ 1.299,90) no campo preço
//    3. Estoque aceita só números inteiros
//    4. Feedback visual de erro na tela (não some em silêncio)
//    5. Parse correto do valor monetário antes de enviar
// ============================================================

const API = "/produtos";

// ── Aguarda o DOM carregar completamente antes de qualquer coisa ──
document.addEventListener("DOMContentLoaded", () => {

    // ── Máscara de moeda ──────────────────────────────────────────
    const campoPreco = document.getElementById("preco");

    campoPreco.addEventListener("input", () => {
        // Remove tudo que não for dígito
        let digits = campoPreco.value.replace(/\D/g, "");

        // Sem dígitos → limpa
        if (!digits) {
            campoPreco.value = "";
            return;
        }

        // Converte centavos → reais (sempre 2 casas decimais)
        const centavos = parseInt(digits, 10);
        const reais = centavos / 100;

        campoPreco.value = reais.toLocaleString("pt-BR", {
            style: "currency",
            currency: "BRL"
        });
    });

    // Impede colar texto não numérico no preço
    campoPreco.addEventListener("paste", (e) => {
        e.preventDefault();
        const texto = (e.clipboardData || window.clipboardData).getData("text");
        const somenteNumeros = texto.replace(/\D/g, "");
        campoPreco.value = somenteNumeros;
        campoPreco.dispatchEvent(new Event("input"));
    });

    // ── Estoque: só inteiros não-negativos ────────────────────────
    const campoEstoque = document.getElementById("estoque");

    campoEstoque.addEventListener("input", () => {
        // Remove tudo que não for dígito
        campoEstoque.value = campoEstoque.value.replace(/\D/g, "");
    });

    campoEstoque.addEventListener("keydown", (e) => {
        // Permite: dígitos, Backspace, Delete, Tab, setas, Home, End
        const permitidos = [
            "Backspace","Delete","Tab","ArrowLeft","ArrowRight","Home","End"
        ];
        if (!permitidos.includes(e.key) && !/^\d$/.test(e.key)) {
            e.preventDefault();
        }
    });

    // ── Submit do form ────────────────────────────────────────────
    const form = document.getElementById("produtoForm");

    form.addEventListener("submit", (e) => {
        e.preventDefault();
        e.stopPropagation();

        const nomeVal    = document.getElementById("nome").value.trim();
        const precoRaw   = campoPreco.value;
        const estoqueVal = campoEstoque.value;

        // Converte "R$ 1.299,90" → 1299.90
        const precoNum = parseMoeda(precoRaw);
        const estoqueNum = parseInt(estoqueVal, 10);

        // Validação frontend antes de chamar API
        let valido = true;

        if (nomeVal.length < 3) {
            document.getElementById("nome").classList.add("is-invalid");
            valido = false;
        } else {
            document.getElementById("nome").classList.remove("is-invalid");
        }

        if (isNaN(precoNum) || precoNum <= 0) {
            campoPreco.classList.add("is-invalid");
            valido = false;
        } else {
            campoPreco.classList.remove("is-invalid");
        }

        if (isNaN(estoqueNum) || estoqueNum < 0 || estoqueVal === "") {
            campoEstoque.classList.add("is-invalid");
            valido = false;
        } else {
            campoEstoque.classList.remove("is-invalid");
        }

        if (!valido) {
            mostrarMensagem("⚠️ Corrija os campos destacados em vermelho.", "warning");
            return;
        }

        const produto = {
            nome:    nomeVal,
            preco:   precoNum,
            estoque: estoqueNum
        };

        fetch(API, {
            method:  "POST",
            headers: { "Content-Type": "application/json" },
            body:    JSON.stringify(produto)
        })
        .then(async (res) => {
            if (!res.ok) {
                // ✅ Tenta ler mensagem de erro do backend
                const texto = await res.text();
                throw new Error(texto || `Erro HTTP ${res.status}`);
            }
            return res.json();
        })
        .then(() => {
            mostrarMensagem("✅ Produto cadastrado com sucesso!", "success");
            form.reset();
            campoPreco.value   = "";
            campoEstoque.value = "";
            // Remove classes de validação
            form.querySelectorAll(".is-invalid").forEach(el => el.classList.remove("is-invalid"));
            carregarProdutos();
        })
        .catch((err) => {
            // ✅ Sempre mostra o erro — não some em silêncio
            mostrarMensagem("❌ " + (err.message || "Erro desconhecido ao cadastrar."), "danger");
            console.error("Erro ao cadastrar:", err);
        });
    });

    // Carrega lista ao abrir a página
    carregarProdutos();
});

// ── Helpers ───────────────────────────────────────────────────────

/**
 * Converte string no formato "R$ 1.299,90" para float 1299.90
 */
function parseMoeda(valor) {
    if (!valor) return NaN;
    // Remove símbolo, espaços e pontos de milhar; troca vírgula por ponto
    const limpo = valor
        .replace(/R\$\s?/g, "")
        .replace(/\./g, "")
        .replace(",", ".")
        .trim();
    return parseFloat(limpo);
}

/**
 * Formata número para moeda BR na tabela
 */
function formatarMoeda(valor) {
    return Number(valor).toLocaleString("pt-BR", {
        style: "currency",
        currency: "BRL"
    });
}

/**
 * Exibe mensagem de alerta no topo e some após 5 segundos
 */
function mostrarMensagem(msg, tipo = "success") {
    const el = document.getElementById("mensagem");
    el.innerHTML = `<div class="alert alert-${tipo} alert-dismissible fade show" role="alert">
        ${msg}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>`;

    // Auto-oculta após 5s
    setTimeout(() => {
        const alerta = el.querySelector(".alert");
        if (alerta) alerta.classList.remove("show");
    }, 5000);
}

/**
 * Busca produtos da API e renderiza na tabela
 */
function carregarProdutos() {
    fetch(API)
        .then((res) => {
            if (!res.ok) throw new Error(`Erro ${res.status} ao carregar produtos`);
            return res.json();
        })
        .then((data) => {
            const tabela = document.getElementById("tabela");

            if (!data || data.length === 0) {
                tabela.innerHTML = `
                    <tr>
                        <td colspan="5" class="text-center text-muted py-4">Nenhum produto cadastrado.</td>
                    </tr>`;
                return;
            }

            tabela.innerHTML = data.map(p => `
                <tr>
                    <td><span class="badge bg-secondary">${p.id}</span></td>
                    <td>${p.nome}</td>
                    <td>${formatarMoeda(p.preco)}</td>
                    <td>${p.estoque}</td>
                    <td class="text-center">
                        <button
                            class="btn btn-danger btn-sm"
                            onclick="deletar(${p.id}, '${p.nome}')"
                        >
                            🗑 Excluir
                        </button>
                    </td>
                </tr>
            `).join("");
        })
        .catch((err) => {
            console.error("Erro ao carregar produtos:", err);
            mostrarMensagem("❌ Não foi possível carregar os produtos. Verifique se o servidor está rodando.", "danger");
        });
}

/**
 * Deleta produto por ID com confirmação
 */
function deletar(id, nome) {
    if (!confirm(`Tem certeza que deseja excluir "${nome}"?`)) return;

    fetch(`${API}/${id}`, { method: "DELETE" })
        .then((res) => {
            if (!res.ok) throw new Error(`Erro ${res.status}`);
            mostrarMensagem(`🗑 Produto "${nome}" excluído com sucesso.`, "success");
            carregarProdutos();
        })
        .catch((err) => {
            mostrarMensagem("❌ Erro ao excluir: " + err.message, "danger");
        });
}