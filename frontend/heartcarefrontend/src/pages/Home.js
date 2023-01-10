import React from "react";
import RouterMedico from "../components/router-menu/RouterMedico";
import MainContent from "../components/MainContent";
import "../css/style.css";
import { useNavigate } from "react-router";

function Home() {
  let nav = useNavigate();
  return (
    <div className="contenitoreMainContent">
      <MainContent />
    </div>
  );
}

export default Home;
