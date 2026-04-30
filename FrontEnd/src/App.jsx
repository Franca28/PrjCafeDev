import { Routes, Route, Link } from "react-router-dom";
import { useState } from "react";

import Home from "./pages/Home.jsx";
import Cliente from "./pages/Cliente.jsx";
import Pedido from "./pages/Pedido.jsx";

import seta from "./assets/seta.png";
import home from "./assets/home.png";
import cliente from "./assets/cliente.png";
import pedido from "./assets/pedido.png";
import api from "./assets/api.png";

import "./App.css";

export default function App() {
  const [isNavExpanded, setIsNavExpanded] = useState(true);

  const swaggerUrl = "http://localhost:8080/swagger-ui/index.html";

  const toggleNav = () => {
    setIsNavExpanded(!isNavExpanded);
  };

  const expandNav = () => {
    if (!isNavExpanded) {
      setIsNavExpanded(true);
    }
  };

  return (
    <div className="container">
      <nav className={isNavExpanded ? "" : "nav-collapsed"}>
        <ul>
          <li title="Ínicio" onClick={expandNav}>
            <img src={home} alt="Botão de início" className="icon"/>
            <Link to="/">Ínicio</Link>
          </li>
          <li title="Gestão de clientes" onClick={expandNav}>
            <img src={cliente} alt="Botão de gestão de clientes" className="icon"/>
            <Link to="/clientes">Gestão de clientes</Link>
          </li>
          <li title="Gestão de pedidos" onClick={expandNav}>
            <img src={pedido} alt="Botão de gestão de pedidos" className="icon"/>
            <Link to="/pedidos">Gestão de pedidos</Link>
          </li>
          <li title="Documentação da API" onClick={expandNav}>
            <img src={api} alt="Botão de documentação da API" className="icon"/>
            <a 
              href={swaggerUrl} 
              target="_blank" 
              rel="noopener noreferrer"
            >Documentação da API</a>
          </li>
        </ul>

        <button onClick={toggleNav} title={isNavExpanded ? "Recolher" : "Expandir"}>
          <img src={seta} alt="botão para diminuir a página de navegação" className="icon" />
        </button>
      </nav>

      <main>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/clientes" element={<Cliente />} />
          <Route path="/pedidos" element={<Pedido />} />
        </Routes>
      </main>

      <footer>
        <p>&copy; 2026 Cafeteria DEV. Todos os direitos reservados.</p>
        <p>
          Github:{" "}
          <a href="https://github.com/Franca28" target="_blank">
            Felipe França
          </a>
        </p>
      </footer>
    </div>
  );
}
