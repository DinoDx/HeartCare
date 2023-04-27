import React from "react";
import "../css/style_menu.css";
import { RxHamburgerMenu } from "react-icons/rx";

function apriMenu(mainContentClass){
    // il menu è chiuso
    if(document.getElementsByClassName(mainContentClass)[0].style.display != "none"){
        document.getElementsByClassName(mainContentClass)[0].style.display = "none";
        document.getElementsByClassName("contenitoreMenu")[0].style.display = "block";
    }
}

function chiudiMenu(mainContentClass){
    document.getElementsByClassName(mainContentClass)[0].style.display = "block";
    document.getElementsByClassName("contenitoreMenu")[0].style.display = "none";
}

function HamburgerMenu(props) {
    return <span className="HamburgerMenu" onClick={() => {
            apriMenu(props.nomeClasseMainContent);
        }
    }><RxHamburgerMenu/></span>
}
export default HamburgerMenu;
