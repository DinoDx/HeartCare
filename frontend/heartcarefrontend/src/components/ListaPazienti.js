import React from "react";
import logoPath from "../images/LogoHeartCare.png";
import homeIconPath from "../images/menuIcon/home.png";
import "../css/style.css";
import "../css/PazientiCss.css";
import axios from "axios";

function ListaPazienti(){
    axios.post('http://localhost:8080/getTuttiPazienti')


        .then((response) => {
          console.log(response);
          let utenti = response.data;
        }, (error) => {
          console.error(error.response.data);
        });



  return <div>

  </div>
}

export default ListaPazienti;