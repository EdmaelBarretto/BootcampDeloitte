const API = "/produtos"; // 🔥 AGORA SEM localhost!

// MENSAGEM
function mostrarMensagem(msg, tipo="success") {
    document.getElementById("mensagem").innerHTML = `
        <div class="alert alert-${tipo} mt-3">${msg}</div>
    `;
}

// CARREGAR PRODUTOS
function carregarProdutos() {
    fetch(API)
        .then(res => res.json())
        .then(data => {
            const tabela = document.getElementById("tabela");
            tabela.innerHTML = "";

            data.forEach(p => {
                tabela.innerHTML += `
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.nome}</td>
                        <td>${p.preco}</td>
                        <td>${p.estoque}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="deletar(${p.id})">Excluir</button>
                        </td>
                    </tr>
                `;
            });
        });
}

// SALVAR
document.getElementById("produtoForm").addEventListener("submit", function(e) {
    e.preventDefault();

    const produto = {
        nome: document.getElementById("nome").value,
        preco: Number(document.getElementById("preco").value),
        estoque: Number(document.getElementById("estoque").value)
    };

    fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(produto)
    })
    .then(async res => {
        if (!res.ok) {
            const erro = await res.text();
            throw new Error(erro);
        }

        mostrarMensagem("Produto cadastrado!");
        document.getElementById("produtoForm").reset();
        carregarProdutos();
    })
    .catch(err => mostrarMensagem(err.message, "danger"));
});

// DELETAR
function deletar(id) {
    fetch(`${API}/${id}`, {
        method: "DELETE"
    })
    .then(() => {
        mostrarMensagem("Produto deletado!");
        carregarProdutos();
    });
}

// INICIAR
carregarProdutos();