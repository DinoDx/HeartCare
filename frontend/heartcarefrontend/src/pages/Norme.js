import React from "react";
import "../css/StyleNorme.css"

function Norme() {
    return (
        <div className="contenitoreNormeContent">
            <div className="divNumero">
                <h1>1.</h1>
            </div>
            <div className="divNormeFattori">
                <h1 className="titoloNorme">Fattori di rischio non modificabili:</h1>
                <hr className="lineaMenuNorme"></hr>
                <div className="overflow">
                    <ul>
                        <li className="testoPuntato">Età</li>
                        <ul>
                            <li className="testoPuntato">Il rischio <span className="grassetto">aumenta progressivamente</span> con l'avanzare dell'età.</li>
                        </ul>
                        <li className="testoPuntato">Sesso maschile</li>
                        <ul>
                            <li className="testoPuntato">Gli uomini <span className="grassetto">sono più a rischio delle donne.</span> </li>
                            <li className="testoPuntato">Nella donna il rischio aumenta sensibilmente dopo la <span className="grassetto">menopausa</span>.</li>
                        </ul>
                        <li className="testoPuntato">Familiarità</li>
                        <ul>
                            <li className="testoPuntato">Parenti con eventi cardiologici in età <span className="grassetto">giovanile</span> (meno di 55 anni negli uomini e di 65 nelle donne)</li>
                        </ul>
                    </ul>
                </div>
            </div>
            <div className="divNormeFattori">
                <h1 className="titoloNorme">Fattori di rischio non modificabili:</h1>
                <hr className="lineaMenuNorme"></hr>
                <div className="overflow">
                    <ul>
                        <li className="testoPuntato">Fumo</li>
                        <ul>
                            <li className="testoPuntato">La nicotina accellera il battito cardiaco, <span className="grassetto">favorendo lo sviluppo dell'arterosclerosi</span>.</li>
                        </ul>
                        <li className="testoPuntato">Diabete</li>
                        <ul>
                            <li className="testoPuntato">Il diabete, se non controllato, <span className="grassetto">incrementa il rischio di problemi cardiaci</span>.</li>
                        </ul>
                        <li className="testoPuntato">Colesterolemia totale</li>
                        <ul>
                            <li className="testoPuntato">Il colesterolo, <span className="grassetto">può trovarsi in quantità eccessive nel sangue</span>, maggiore è la sua quantità, maggiore è il rischio che si depositi sulle pareti arteriose.</li>
                        </ul>
                    </ul>
                </div>
            </div>
            <div className="divNumero">
                <h1>2.</h1>
            </div>
            <div className="divNorme">
                <div>
                    <h1 className="titoloNorme">Comportamenti da osservare per ridurre il rischio:</h1>
                    <hr className="lineaMenuNorme"></hr>
                </div>
                <div className="overflow">
                    <ul>
                        <li className="testoPuntato">Smettere di fumare</li>
                        <ul>
                            <li className="testoPuntato">Decidere di fare a meno delle sigarette <span className="grassetto">può ridurre il rischio fino al 21%</span>.</li>
                        </ul>
                        <li className="testoPuntato">Svolgere attività fisica</li>
                        <ul>
                            <li className="testoPuntato">Una giusta attività fisica, <span className="grassetto">riduce il rischio di infarto del 20%</span>, dunque è possibile prevenire un infarto su 5</li>
                        </ul>
                        <li className="testoPuntato">Adottare un alimentazione sana</li>
                        <ul>
                            <li className="testoPuntato">Bisogna evitare di assumere cibi grassi, salumi e latte intero.<br/> <span className="grassetto">Da preferire</span> sono, gli alimenti come cereali, frutta e verdura. <span className="grassetto">Con una possibile riduzione del 16%</span>.</li>
                        </ul>
                        <li className="testoPuntato">Controllare il proprio peso</li>
                        <ul>
                            <li className="testoPuntato"><span className="grassetto">Obesità e sovrappeso sono nemici del cuore</span>, mantenere un peso forma vuol dire ridurre il rischio di infarto del 15%.</li>
                        </ul>
                        <li className="testoPuntato">Moderare il consumo di alcool</li>
                        <ul>
                            <li className="testoPuntato">L'uso di bevande alcoliche hanno un <span className="grassetto"> pessimo effetto sul funzionamento del cuore</span>. Riuscire a contenere il consumo di alcool significa ridurre il rischio di problemi cardiaci <span className="grassetto">almeno del 7%</span>.</li>
                        </ul>
                    </ul>
                </div>
            </div>
            <div className="divNorme">
                <h1 className="titoloNorme">I cibi che fanno bene al cuore:</h1>
                <hr className="lineaMenuNorme"></hr>
                <ul>
                    <li className="testoPuntato">Verdura a foglia verde</li>
                    <li className="testoPuntato">Frutta fresca e secca</li>
                    <li className="testoPuntato">Pesce</li>
                    <li className="testoPuntato">Legumi</li>
                    <li className="testoPuntato">Soia</li>
                    <li className="testoPuntato">Cereali</li>
                    <li className="testoPuntato">Cioccolato fondente</li>
                    <li className="testoPuntato">Oli e grassi vegetali</li>
                    <li className="testoPuntato">Aceto di mele</li>
                </ul>
            </div>
            <div className="footer-norme">
                <h1 className="footer-element-norme">Cookie Policy</h1>
                <h1 className="footer-element-norme">Privacy Policy</h1>
                <h1 className="footer-element-norme">@Copyright</h1>
            </div>
        </div>
    )
}

export default Norme;