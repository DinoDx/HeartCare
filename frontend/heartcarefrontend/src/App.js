import "./App.css";
import "./css/style.css";
import Menu from "./components/Menu";
import MainContent from "./components/MainContent";
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";

import Home from "./pages/Home.js";
import Profilo from "./pages/Profilo.js";
import About from "./pages/About.js";
import Pazienti from "./pages/Pazienti.js";
import Schedules from "./pages/Schedules.js";

function App() {
  return (
    <div>
      {/* nella pagina di login SI DEVE aggiungere la classe contenitorePaginaLogin */}
      <div className="sfondoPagina contenitorePaginaHomePage">
        <Router>
          <Menu />
          <MainContent />
          <Routes>
            <Route path="Home" element={<Home />} />
            <Route path="Schedules" element={<Schedules />} />
            <Route path="Pazienti" element={<Pazienti />} />
            <Route path="Profilo" element={<Profilo />} />
            <Route path="About" element={<About />} />
          </Routes>
        </Router>
      </div>
    </div>
  );
}

export default App;
