import "./Home.css";

export default function Home() {
  return (
    <div className="home-container">
      <section className="hero">
        <h1>🎯 Bem-vindo ao Cafeteria DEV</h1>
        <p className="subtitle">Sua plataforma completa para gerenciar cafeteria e pedidos</p>
      </section>

      <section className="features">
        <div className="feature-card">
          <div className="feature-icon">📍</div>
          <h3>Gestão de Clientes</h3>
          <p>Cadastre, consulte e gerencie informações de todos os seus clientes de forma simples e intuitiva.</p>
        </div>

        <div className="feature-card">
          <div className="feature-icon">📦</div>
          <h3>Gestão de Pedidos</h3>
          <p>Organize e acompanhe todos os pedidos em tempo real, controlando status e entrega.</p>
        </div>

        <div className="feature-card">
          <div className="feature-icon">📚</div>
          <h3>Documentação da API</h3>
          <p>Acesse a documentação completa da nossa API para integração com seus sistemas.</p>
        </div>
      </section>

      <section className="guide">
        <h2>📖 Como Usar</h2>
        
        <div className="steps">
          <div className="step">
            <div className="step-number">1</div>
            <h4>Navegação</h4>
            <p>Use a barra lateral (navbar) para navegar entre as diferentes seções da plataforma. Você pode recolhê-la clicando no botão com a seta para ganhar mais espaço.</p>
          </div>

          <div className="step">
            <div className="step-number">2</div>
            <h4>Gestão de Clientes</h4>
            <p>Na seção de clientes, você pode adicionar novos clientes, atualizar informações e visualizar o histórico de cada um.</p>
          </div>

          <div className="step">
            <div className="step-number">3</div>
            <h4>Gestão de Pedidos</h4>
            <p>Crie e acompanhe pedidos dos clientes. Marque o status de cada pedido conforme ele avança no processo.</p>
          </div>

          <div className="step">
            <div className="step-number">4</div>
            <h4>Integração com API</h4>
            <p>Consulte a documentação da API para integrar a plataforma com suas aplicações externas.</p>
          </div>
        </div>
      </section>

      <section className="tips">
        <h2>💡 Dicas Úteis</h2>
        <ul className="tips-list">
          <li>
            <strong>Barra de Navegação Colapsável:</strong> Clique em qualquer item da navbar para expandir automaticamente ao navegar.
          </li>
          <li>
            <strong>Mobile Friendly:</strong> A plataforma se adapta perfeitamente a dispositivos móveis e tablets.
          </li>
          <li>
            <strong>Busca Rápida:</strong> Use a função de busca para localizar clientes e pedidos rapidamente.
          </li>
          <li>
            <strong>Relatórios:</strong> Gere relatórios detalhados sobre vendas e clientes.
          </li>
        </ul>
      </section>

      <section className="support">
        <h2>🤝 Precisa de Ajuda?</h2>
        <p>Entre em contato conosco através do GitHub ou verifique a documentação da API para mais informações técnicas.</p>
        <p className="contact-info">
          👨‍💻 Desenvolvedor: <strong>Felipe França</strong> | 
          🔗 <a href="https://github.com/Franca28" target="_blank" rel="noopener noreferrer">GitHub</a>
        </p>
      </section>
    </div>
  );
}
