import { useState } from 'react'

export default function App() {
  // 1. Estados para armazenar os dados do formulário
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [mensagem, setMensagem] = useState('');

  // 2. Função para enviar os dados para o Spring Boot
  const handleSubmit = async (e) => {
    e.preventDefault(); // Impede a página de recarregar
    setMensagem("A enviar...");

    const novoCliente = { nome, email };

    try {
      const resposta = await fetch('/cliente/salvar', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoCliente)
      });

      if (resposta.ok) {
        const dados = await resposta.json();
        setMensagem(`Sucesso! Cliente ${dados.nome} criado.`);
        setNome(''); // Limpa os campos
        setEmail('');
      } else {
        setMensagem("Erro ao salvar: " + resposta.status);
      }
    } catch (error) {
      setMensagem("Erro de conexão: " + error.message);
    }
  };

  return (
    <div style={{ padding: '40px', maxWidth: '400px', margin: '0 auto', fontFamily: 'Arial' }}>
      <h2>Criar Novo Cliente</h2>
      
      <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
        <input 
          type="text" 
          placeholder="Nome do Cliente" 
          value={nome}
          onChange={(e) => setNome(e.target.value)}
          required 
        />
        <input 
          type="email" 
          placeholder="Email" 
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required 
        />
        <button type="submit" style={{ cursor: 'pointer', background: '#007bff', color: 'white', border: 'none', padding: '10px' }}>
          Salvar no Java
        </button>
      </form>

      {mensagem && (
        <div style={{ marginTop: '20px', padding: '10px', background: '#f0f0f0', border: '1px solid #ccc' }}>
          {mensagem}
        </div>
      )}
    </div>
  );
}