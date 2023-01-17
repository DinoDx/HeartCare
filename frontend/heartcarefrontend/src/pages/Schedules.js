import React from "react";
import HamburgerMenu from "../components/HamburgerMenu";
import ListaVisita from "../components/ListaVisita";
import "../css/scheduleStyle.css";

function Schedules() {
  return (
      <div className="contenitoreScheduleContent">
    <HamburgerMenu className="HamburgerMenu" nomeClasseMainContent="contenitoreScheduleContent"/>
    <div className="searchbar">
      <input id="search" type="text" placeholder=" 🔍Cerca Paziente..." />
    </div>
    <span className="bentornato">Bentornato, Dr. Lambiase 👋🏻</span>
    <span className="iTuoiPazienti">Le tue visite: </span>
        <ListaVisita />
  </div>
  );
}

export default Schedules;
