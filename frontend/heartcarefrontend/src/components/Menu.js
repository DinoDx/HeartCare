import React from "react";
import logoPath from "../images/LogoHeartCare.png";
import homeIconPath from "../images/menuIcon/home.png";
import '../css/style.css';
import '../css/style_menu.css';

function Menu(){

    return (
        <div className="contenitoreMenu">
            <img src={logoPath} className="logoMenu"/>
            <div className="contenitoreVociMenu">
                <div className="voceMenu"><img src={homeIconPath} className="iconaMenu"/><li className="voceMenuText">Dashboard</li></div>
                <div className="voceMenu"><img src={homeIconPath} className="iconaMenu"/><li className="voceMenuText">Schedule</li></div>
                <div className="voceMenu"><img src={homeIconPath} className="iconaMenu"/><li className="voceMenuText">Pazienti</li></div>
                <div className="voceMenu"><img src={homeIconPath} className="iconaMenu"/><li className="voceMenuText">Profilo</li></div>
                <hr className="lineaMenu"/>
                <div className="voceMenu"><img src={homeIconPath} className="iconaMenu"/><li className="voceMenuText">Impostazioni</li></div>
                <div className="voceMenu"><img src={homeIconPath} className="iconaMenu"/><li className="voceMenuText">Logout</li></div>
            </div>
        </div>
    );
}
export default Menu;