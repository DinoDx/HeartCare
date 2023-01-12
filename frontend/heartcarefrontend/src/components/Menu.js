import React from "react";
import logoPath from "../images/LogoHeartCare.png";
import "../css/style.css";
import "../css/style_menu.css";
import { Link } from "react-router-dom";
import { FaHospital } from "react-icons/fa";
import { CiLogout } from "react-icons/ci";
import { SlHome } from "react-icons/sl";
import { BsCalendarEvent } from "react-icons/bs";
import { AiOutlineQuestion, AiOutlineUser } from "react-icons/ai";

function addClassActive(id) {
  let attualmenteSelezionato = document.querySelectorAll(".voceMenuActive");
  [].forEach.call(attualmenteSelezionato, function (el) {
    el.classList.remove("voceMenuActive");
  });
  document.getElementById(id).classList.add("voceMenuActive");
}

function Menu() {
  return (
    <div className="contenitoreMenu">
      <img src={logoPath} className="logoMenu" />

      <div className="contenitoreVociMenu">
        <Link
          to={"/Home"}
          className="voceMenuText"
          onClick={() => addClassActive(1)}
        >
          <div className="voceMenu voceMenuActive" id="1">
            <SlHome className="iconaMenu" />
            <span>Dashboard</span>
          </div>
        </Link>

        <Link
          to={"/Schedule"}
          className="voceMenuText"
          onClick={() => addClassActive(2)}
        >
          <div className="voceMenu" id="2">
            <BsCalendarEvent className="iconaMenu" />
            <span>Schedule</span>
          </div>
        </Link>

        <Link
          to={"/Pazienti"}
          className="voceMenuText"
          onClick={() => addClassActive(3)}
        >
          <div className="voceMenu" id="3">
            <FaHospital className="iconaMenu" />
            <span>Pazienti</span>
          </div>
        </Link>

        <Link
          to={"/Profilo"}
          className="voceMenuText"
          onClick={() => addClassActive(4)}
        >
          <div className="voceMenu" id="4">
            <AiOutlineUser className="iconaMenu" />
            <span>Profilo</span>
          </div>
        </Link>

        <hr className="lineaMenu" />

        <Link
          to={"/About"}
          className="voceMenuText"
          onClick={() => addClassActive(5)}
        >
          <div className="voceMenu" id="5">
            <AiOutlineQuestion className="iconaMenu" />
            <span>Chi siamo?</span>
          </div>
        </Link>

        <Link to={"/About"} className="voceMenuText">
          <div className="voceMenu">
            <CiLogout className="iconaMenu" />
            <span>Logout</span>
          </div>
        </Link>
      </div>
    </div>
  );
}
export default Menu;
