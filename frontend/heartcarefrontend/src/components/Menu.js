import React from "react";
import logoPath from "../images/LogoHeartCare.png";
import homeIconPath from "../images/menuIcon/home.png";
import "../css/style.css";
import "../css/style_menu.css";
import { Link } from "react-router-dom";

function Menu() {
  return (
    <div className="contenitoreMenu">
      <img src={logoPath} className="logoMenu" />
      <div className="contenitoreVociMenu">
        <div className="voceMenu">
          <img src={homeIconPath} className="iconaMenu" />
          <Link to={"/Home"} style={{ textDecoration: "none" }}>
            <li className="voceMenuText">Dashboard</li>
          </Link>
        </div>
        <div className="voceMenu">
          <img src={homeIconPath} className="iconaMenu" />
          <Link to={"/Schedule"} style={{ textDecoration: "none" }}>
            <li className="voceMenuText">Schedule</li>
          </Link>
        </div>
        <div className="voceMenu">
          <img src={homeIconPath} className="iconaMenu" />
          <Link to={"/Pazienti"} style={{ textDecoration: "none" }}>
            <li className="voceMenuText">Pazienti</li>
          </Link>
        </div>
        <div className="voceMenu">
          <img src={homeIconPath} className="iconaMenu" />
          <Link to={"/Profilo"} style={{ textDecoration: "none" }}>
            <li className="voceMenuText">Profilo</li>
          </Link>
        </div>
        <hr className="lineaMenu" />
        <div className="voceMenu">
          <img src={homeIconPath} className="iconaMenu" />
          <Link to={"/About"} style={{ textDecoration: "none" }}>
            <li className="voceMenuText">Chi siamo</li>
          </Link>
        </div>
        <div className="voceMenu">
          <img src={homeIconPath} className="iconaMenu" />
          <li className="voceMenuText">Logout</li>
        </div>
      </div>
    </div>
  );
}
export default Menu;
