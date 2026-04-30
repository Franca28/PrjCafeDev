import { useState, useEffect } from "react";
import "./Pedido.css"; // Assumindo que terás um CSS semelhante ao Cliente.css

export default function Pedido() {
  const [pedidos, setPedidos] = useState([]);
  const [loading, setLoading] = useState(false);
  // 1. Estado inicial atualizado com os novos campos
  const [formData, setFormData] = useState({ 
    id: "", 
    descricao: "", 
    valorTotal: "", 
    dataPedido: "", 
    clienteId: "" 
  });
  const [searchTerm, setSearchTerm] = useState("");
  const [editingId, setEditingId] = useState(null);

  useEffect(() => {
    carregarPedidos();
  }, []);

  // Método GET para carregar todos os pedidos
  const carregarPedidos = async () => {
    setLoading(true);
    try {
      const response = await fetch("/pedido/listar", {
        method: "GET",
        headers: { "Content-Type": "application/json" }
      });

      if (!response.ok) throw new Error("Erro ao carregar pedidos");

      const dados = await response.json();
      setPedidos(dados);
    } catch (error) {
      console.error("Erro ao carregar pedidos:", error);
    } finally {
      setLoading(false);
    }
  };

  // Método GET para buscar pedido
  const buscarPedido = async () => {
    if (!searchTerm.trim()) {
      carregarPedidos();
      return;
    }

    setLoading(true);
    try {
      const response = await fetch(`/pedido/buscar?termo=${encodeURIComponent(searchTerm)}`, {
        method: "GET",
        headers: { "Content-Type": "application/json" }
      });

      if (!response.ok) throw new Error("Erro ao buscar pedido");

      const dados = await response.json();
      setPedidos(dados);
    } catch (error) {
      console.error("Erro ao buscar pedido:", error);
    } finally {
      setLoading(false);
    }
  };

  const pedidosFiltrados = pedidos;

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // Cadastrar ou Atualizar Pedido
  const handleCadastro = async (e) => {
    e.preventDefault();

    // Validação básica para garantir que nenhum campo essencial está vazio
    if (!formData.descricao || !formData.valorTotal || !formData.dataPedido || !formData.clienteId) {
      alert("Por favor, preencha todos os campos!");
      return;
    }

    const payloadPedido = {
      descricao: formData.descricao,
      valorTotal: Number(formData.valorTotal), // Garante que é enviado como número
      dataPedido: formData.dataPedido,
      clienteId: Number(formData.clienteId), // Garante que é enviado como número
    };

    if (editingId) {
      // --- LÓGICA DE ATUALIZAÇÃO (PUT) ---
      try {
        const response = await fetch(`/pedido/atualizar/${editingId}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payloadPedido),
        });

        if (!response.ok) throw new Error("Erro ao atualizar pedido");

        const pedidoAtualizado = await response.json();

        setPedidos((prev) =>
          prev.map((pedido) =>
            pedido.id === editingId ? pedidoAtualizado : pedido
          )
        );
        setEditingId(null);
      } catch (error) {
        console.error("Erro ao atualizar pedido:", error);
        alert("Não foi possível atualizar o pedido.");
      }
    } else {
      // --- LÓGICA DE CRIAÇÃO (POST) ---
      try {
        const response = await fetch("/pedido/salvar", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(payloadPedido),
        });

        if (!response.ok) throw new Error("Erro ao salvar pedido");

        const pedidoSalvo = await response.json();
        setPedidos((prev) => [...prev, pedidoSalvo]);
      } catch (error) {
        console.error("Erro ao salvar pedido:", error);
      }
    }

    // Limpa o formulário
    setFormData({ id: "", descricao: "", valorTotal: "", dataPedido: "", clienteId: "" });
  };

  const handleEditar = (pedido) => {
    setFormData({ 
      id: pedido.id, 
      descricao: pedido.descricao, 
      valorTotal: pedido.valorTotal, 
      dataPedido: pedido.dataPedido, 
      clienteId: pedido.clienteId 
    });
    setEditingId(pedido.id);
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const handleDeletar = (id) => {
    if (window.confirm("Tem certeza que deseja deletar este pedido?")) {
      setPedidos((prev) => prev.filter((pedido) => pedido.id !== id));
      async function deletarPedido() {
        try {
          const response = await fetch(`/pedido/deletar/${id}`, {
            method: "DELETE",
            headers: { "Content-Type": "application/json" }
          });

          if (!response.ok) throw new Error("Erro ao deletar pedido");
        } catch (error) {
          console.error("Erro ao deletar pedido:", error);
        }
      }
      deletarPedido();
    }
  };

  const handleCancelar = () => {
    setFormData({ id: "", descricao: "", valorTotal: "", dataPedido: "", clienteId: "" });
    setEditingId(null);
  };

  return (
    <div className="pedido-container">
      <h1>Gestão de Pedidos</h1>

      {/* Seção de Cadastro/Atualização */}
      <section className="cadastro-section">
        <h2>{editingId ? "Atualizar Pedido" : "Cadastrar Novo Pedido"}</h2>
        <form onSubmit={handleCadastro} className="form-cadastro">
          
          <div className="form-group">
            <label htmlFor="descricao">Descrição:</label>
            <input
              type="text"
              id="descricao"
              name="descricao"
              value={formData.descricao}
              onChange={handleInputChange}
              placeholder="Ex: Compra de material de escritório"
              autoFocus
            />
          </div>

          <div className="form-group">
            <label htmlFor="valorTotal">Valor Total (R$):</label>
            <input
              type="number"
              step="0.01"
              id="valorTotal"
              name="valorTotal"
              value={formData.valorTotal}
              onChange={handleInputChange}
              placeholder="Ex: 150.50"
            />
          </div>

          <div className="form-group">
            <label htmlFor="dataPedido">Data do Pedido:</label>
            <input
              type="date"
              id="dataPedido"
              name="dataPedido"
              value={formData.dataPedido}
              onChange={handleInputChange}
            />
          </div>

          <div className="form-group">
            <label htmlFor="clienteId">ID do Cliente:</label>
            <input
              type="number"
              id="clienteId"
              name="clienteId"
              value={formData.clienteId}
              onChange={handleInputChange}
              placeholder="Digite o ID do cliente"
            />
          </div>

          <div className="form-buttons">
            <button type="submit" className="btn btn-primary">
              {editingId ? "Atualizar" : "Cadastrar"}
            </button>
            {editingId && (
              <button type="button" className="btn btn-secondary" onClick={handleCancelar}>
                Cancelar
              </button>
            )}
          </div>
        </form>
      </section>

      {/* Seção de Busca */}
      <section className="busca-section">
        <h2>Buscar Pedido</h2>
        <div className="busca-container">
          <input
            type="text"
            className="search-input"
            placeholder="Buscar por ID ou Descrição..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
          <button className="btn btn-search" onClick={buscarPedido} disabled={loading}>
            🔍 Buscar
          </button>
          {searchTerm && (
            <button
              className="btn-clear"
              onClick={() => {
                setSearchTerm("");
                carregarPedidos();
              }}
            >
              ✕
            </button>
          )}
        </div>
      </section>

      {/* Seção de Lista de Pedidos */}
      <section className="lista-section">
        <h2>Lista de Pedidos ({pedidosFiltrados.length})</h2>

        {loading && <div className="loading-state"><p>Carregando pedidos...</p></div>}

        {!loading && pedidosFiltrados.length === 0 ? (
          <div className="empty-state">
            <p>Nenhum pedido encontrado.</p>
          </div>
        ) : (
          <div className="pedidos-grid">
            {pedidosFiltrados.map((pedido) => (
              <div key={pedido.id} className="pedido-card">
                <div className="card-header">
                  <h3>{pedido.descricao}</h3>
                  <span className="pedido-id">ID: {pedido.id}</span>
                </div>

                <div className="card-body">
                  <p><strong>Valor Total:</strong> R$ {Number(pedido.valorTotal).toFixed(2)}</p>
                  <p><strong>Data:</strong> {pedido.dataPedido}</p>
                  <p><strong>ID do Cliente:</strong> {pedido.clienteId}</p>
                </div>

                <div className="card-footer">
                  <button className="btn btn-edit" onClick={() => handleEditar(pedido)}>
                    ✏️ Atualizar
                  </button>
                  <button className="btn btn-delete" onClick={() => handleDeletar(pedido.id)}>
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