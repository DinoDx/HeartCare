import React from "react";
import Menu from "../Menu";
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";

import HomeMedico from "../../pages/HomeMedico.js";
import Profilo from "../../pages/Profilo.js";
import About from "../../pages/About.js";
import Pazienti from "../../pages/Pazienti.js";
import Schedules from "../../pages/Schedules.js";

function RouterMedico() {
  return (
    <Router>
      <Menu />
      <Routes>
        <Route path="HomeMedico" element={<HomeMedico />} />
        <Route path="Schedules" element={<Schedules />} />
        <Route path="Pazienti" element={<Pazienti />} />
        <Route path="Profilo" element={<Profilo />} />
        <Route path="About" element={<About />} />
        {/*<Route path="/" element={<Login />} />*/}
      </Routes>
    </Router>
  );
}

export default RouterMedico;
