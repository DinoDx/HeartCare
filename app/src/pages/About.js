import React from "react";
import "../css/styleInfo.css"

function About() {
  return (
    <div className="contenitoreAboutContent" >
        <div className="systemGoal">
                <h1 className="titolo">Obiettivo del sistema:</h1>
                <hr className="lineaMenuAbout"/>
            <div className="bloccoTestoSystemGoal">
                <p className="paragrafo">HeartCare è una piattaforma che nasce con lo scopo di fornire uno strumento di telemonitoraggio ai pazienti affetti da <span className="grassetto">malattie cardiache</span> <br/>in modo da poter tenere sotto osservazione i loro valori e notificare loro preventivamente in merito a possibili problemi grazie all'applicazione dell'<span className="grassetto">Intelligenza Artificiale.</span><br/>
                    Il paziente potrà, tramite le misurazioni, aggiornare il proprio <span className="grassetto">Fascicolo Sanitario Elettronico</span> che sarà accessibile al medico.
                    Medico e paziente potranno comunicare tramite dei messaggi asincroni.</p>
            </div>
        </div>
        <div className="systemGoal">
                <h1 className="titolo">Perchè abbiamo sviluppato questa piattaforma:</h1>
                <hr className="lineaMenuAbout"/>
            <div className="bloccoTestoSystemGoal">
                <p className="paragrafo">HeartCare verrà sviluppata per fornire un supporto online alle problematiche delle persone cardiopatiche.<br/>
                    La piattaforma permetterà ad un paziente di accedere al proprio <span className="grassetto">Fascicolo Sanitario Elettronico</span>,
                    di aggiungere delle note destinate al medico, di registrare un nuovo dispositivo di monitoraggio e di eseguire delle <span className="grassetto">misurazioni.</span>
                    La piattaforma permetterà ad un medico di accedere a qualsiasi fascicolo di ogni suo paziente, di aggiungere delle note destinate al paziente e di stabilire il giorno della visita.
                    Inoltre, la piattaforma permetterà ad un amministratore di gestire la presenza di medici e pazienti sulla <span className="grassetto">piattaforma</span> e le relazioni tra questi ultimi.</p>
            </div>
        </div>
        <div className="footer-about">
            <h1 className="footer-element">Cookie Policy</h1>
            <h1 className="footer-element">Privacy Policy</h1>
            <h1 className="footer-element">@Copyright</h1>
        </div>
    </div>
  );
}

export default About;
