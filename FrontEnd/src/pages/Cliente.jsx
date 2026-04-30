import { useState, useEffect } from "react";
import "./Cliente.css";

export default function Cliente() {
  const [clientes, setClientes] = useState([]);
  const [loading, setLoading] = useState(false);
  const [formData, setFormData] = useState({ id: "", nome: "", email: "" });
  const [searchTerm, setSearchTerm] = useState("");
  const [editingId, setEditingId] = useState(null);

  // Carregar clientes ao montar o componente
  useEffect(() => {
    carregarClientes();
  }, []);

  // TODO: Método GET para carregar todos os clientes
  const carregarClientes = async () => {
    setLoading(true);
    try {
      const response = await fetch("/cliente/listar", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        }
      });

      if (!response.ok) {
        throw new Error("Erro ao carregar clientes");
      }

      const dados = await response.json();
      setClientes(dados);
    } catch (error) {
      console.error("Erro ao carregar clientes:", error);
    } finally {
      setLoading(false);
    }
  };

  // TODO: Método GET para buscar cliente por ID ou Nome
  const buscarCliente = async () => {
    if (!searchTerm.trim()) {
      carregarClientes();
      return;
    }

    setLoading(true);
    try {
      const response = await fetch(`/cliente/buscar?termo=${encodeURIComponent(searchTerm)}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        }
      });

      if (!response.ok) {
        throw new Error("Erro ao buscar cliente");
      }

      const dados = await response.json();
      setClientes(dados);
    } catch (error) {
      console.error("Erro ao buscar cliente:", error);
    } finally {
      setLoading(false);
    }
  };

  // Filtrar clientes baseado na busca (local filtering)
  const clientesFiltrados = clientes;

  // Lidar com mudanças no formulário
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // Adicionar novo cliente
  const handleCadastro = async (e) => {
  e.preventDefault();

  // 1. Validação básica
  if (!formData.nome.trim() || !formData.email.trim()) {
    alert("Por favor, preencha todos os campos!");
    return;
  }

  if (editingId) {
    // --- LÓGICA DE ATUALIZAÇÃO (PUT) ---
    try {
      const response = await fetch(`/cliente/atualizar/${editingId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          nome: formData.nome,
          email: formData.email,
        }),
      });

      if (!response.ok) {
        throw new Error("Erro ao atualizar cliente");
      }

      const clienteAtualizado = await response.json();

      // Atualiza o estado local com os dados que vieram do servidor
      setClientes((prev) =>
        prev.map((cliente) =>
          cliente.id === editingId ? clienteAtualizado : cliente
        )
      );
      
      setEditingId(null); // Sai do modo de edição
    } catch (error) {
      console.error("Erro ao atualizar cliente:", error);
      alert("Não foi possível atualizar o cliente.");
    }

  } else {
    // --- LÓGICA DE CRIAÇÃO (POST) ---
    const novoCliente = {
      nome: formData.nome,
      email: formData.email,
    };

    try {
      const response = await fetch("/cliente/salvar", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(novoCliente),
      });

      if (!response.ok) {
        throw new Error("Erro ao salvar cliente");
      }

      const clienteSalvo = await response.json();
      setClientes((prev) => [...prev, clienteSalvo]);
    } catch (error) {
      console.error("Erro ao salvar cliente:", error);
    }
  }

  // Limpa o formulário após sucesso (POST ou PUT)
  setFormData({ id: "", nome: "", email: "" });
};

  // Editar cliente
  const handleEditar = (cliente) => {
    setFormData({ id: cliente.id, nome: cliente.nome, email: cliente.email });
    setEditingId(cliente.id);
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  // Deletar cliente
  const handleDeletar = (id) => {
    if (window.confirm("Tem certeza que deseja deletar este cliente?")) {
      setClientes((prev) => prev.filter((cliente) => cliente.id !== id));
      async function deletarCliente() {
        try {
          const response = await fetch(`/cliente/deletar/${id}`, {
            method: "DELETE",
            headers: {
              "Content-Type": "application/json",
            }
          });

          if (!response.ok) {
            throw new Error("Erro ao deletar cliente");
          }
        } catch (error) {
          console.error("Erro ao deletar cliente:", error);
        }
      }
      deletarCliente();
    }
  };

  // Cancelar edição
  const handleCancelar = () => {
    setFormData({ id: "", nome: "", email: "" });
    setEditingId(null);
  };

  return (
    <div className="cliente-container">
      <h1>Gestão de Clientes</h1>

      {/* Seção de Cadastro/Atualização */}
      <section className="cadastro-section">
        <h2>{editingId ? "Atualizar Cliente" : "Cadastrar Novo Cliente"}</h2>
        <form onSubmit={handleCadastro} className="form-cadastro">
          <div className="form-group">
            <label htmlFor="nome">Nome:</label>
            <input
              type="text"
              id="nome"
              name="nome"
              value={formData.nome}
              onChange={handleInputChange}
              placeholder="Digite o nome do cliente"
              autoFocus
            />
          </div>

          <div className="form-group">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleInputChange}
              placeholder="Digite o email do cliente"
            />
          </div>

          <div className="form-buttons">
            <button type="submit" className="btn btn-primary">
              {editingId ? "Atualizar" : "Cadastrar"}
            </button>
            {editingId && (
              <button
                type="button"
                className="btn btn-secondary"
                onClick={handleCancelar}
              >
                Cancelar
              </button>
            )}
          </div>
        </form>
      </section>

      {/* Seção de Busca */}
      <section className="busca-section">
        <h2>Buscar Cliente</h2>
        <div className="busca-container">
          <input
            type="text"
            className="search-input"
            placeholder="Buscar por ID ou Nome..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
          <button
            className="btn btn-search"
            onClick={buscarCliente}
            disabled={loading}
            title="Buscar cliente"
          >
            🔍 Buscar
          </button>
          {searchTerm && (
            <button
              className="btn-clear"
              onClick={() => {
                setSearchTerm("");
                carregarClientes();
              }}
              title="Limpar busca"
            >
              ✕
            </button>
          )}
        </div>
        {searchTerm && (
          <p className="search-info">
            {clientesFiltrados.length} cliente(s) encontrado(s)
          </p>
        )}
      </section>

      {/* Seção de Lista de Clientes */}
      <section className="lista-section">
        <h2>Lista de Clientes ({clientesFiltrados.length})</h2>

        {loading && (
          <div className="loading-state">
            <p>Carregando clientes...</p>
          </div>
        )}

        {!loading && clientesFiltrados.length === 0 ? (
          <div className="empty-state">
            <p>
              {clientes.length === 0
                ? "Nenhum cliente cadastrado ainda."
                : "Nenhum cliente encontrado com esse critério."}
            </p>
          </div>
        ) : (
          <div className="clientes-grid">
            {clientesFiltrados.map((cliente) => (
              <div key={cliente.id} className="cliente-card">
                <div className="card-header">
                  <h3>{cliente.nome}</h3>
                  <span className="cliente-id">ID: {cliente.id}</span>
                </div>

                <div className="card-body">
                  <p>
                    <strong>Email:</strong> <a href={`mailto:${cliente.email}`}>{cliente.email}</a>
                  </p>
                </div>

                <div className="card-footer">
                  <button
                    className="btn btn-edit"
                    onClick={() => handleEditar(cliente)}
                    title="Editar cliente"
                  >
                    ✏️ Atualizar
                  </button>
                  <button
                    className="btn btn-delete"
                    onClick={() => handleDeletar(cliente.id)}
                    title="Deletar cliente"
                  >
                    🗑️ Deletar
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </section>
    </div>
  );
}
