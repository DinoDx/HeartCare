import React from 'react'
import { useNavigate } from 'react-router';
import "../css/home-main-content.css";
import "../css/HomePaziente.css";
import "../css/homeMedico_style.css";

function Error404() {
    const nav = useNavigate();

    const redirectToHome = () => {
        nav("/")
    }

  return (
    <div className='contenitoreMainContent'>
        <span className='testo-bentornat'>La pagina a cui stai cercando di accedere non esiste.</span>
        <button onClick={redirectToHome} >Home</button>
        
    </div>
  )
}

export default Error404