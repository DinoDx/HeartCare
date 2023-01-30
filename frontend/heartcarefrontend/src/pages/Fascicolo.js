import "../css/Fascicolo.css"
import {FascicoloBanner} from "../components/FascicoloBanner";
import Grafico from "../components/Grafico";
import React, {useState,useEffect} from "react";
import { FaNotesMedical } from "react-icons/fa";
import jwt from "jwt-decode";

function Fascicolo(){
    const [Categorie,setCategorie] = useState([]);
    const [Misurazioni,setMisurazioni] = useState([]);
    const token = localStorage.getItem("token");

    let config = {
        Accept: "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "*",
        withCredentials: true,
        Authorization: `Bearer ${token}`,
        "Content-Type" : "application/json"
    };

    useEffect(() => {
        const getCategorie = async () =>{
            const response = await fetch("http://localhost:8080/getCategorie",{
                method : "POST",
                headers : config,
                body : JSON.stringify({
                    id : jwt(token).id
                })
            }).then(response => {
                return response.json()
            })
                setCategorie(response);
            }

        const fetchData = async() => {
            const response = await fetch("http://localhost:8080/getAllMisurazioniByPaziente",{
                method : "POST",
                headers : config,
                body : JSON.stringify({
                    id: jwt(token).id
                })
            }).then(response => {
                return response.json()
            })
                setMisurazioni(response);
            }
        getCategorie();
        fetchData();
    }, []);

    const returnByCategoria = (categoria) => {
        return Misurazioni.filter( mis =>
            mis["categoria"] == categoria
        )
    }
        return (
        <div className="contenitoreFascicoloContent">
            <div className="contenitoreSinistra" style={{
                width: "90%"
            }}>
                <span className="testo-bentornat TwoPercentMargin" style={{marginLeft:"0px"}}>Il tuo Fasciscolo Sanitario Elettronico 📊</span>
                <FascicoloBanner categorie={Categorie} misurazioni = {Misurazioni}/>
                <Grafico categorie={Categorie} misurazioni = {Misurazioni}/>
                <span className="testo-bentornat TwoPercentMargin" style={{marginLeft:"0px"}}>Storico delle tue Misurazioni 📊</span>
                <div className="contenitoreMisurazioni">
                    {Categorie.map( cat => {
                        return(
                            <div>
                                <h2>{cat}</h2>
                                <div className="bloccoMisurazione">
                                    {returnByCategoria(cat).map( mis => {
                                        return (
                                            <div className="contenitoreMisurazione">
                                                <div className="contenitoreIcona"><FaNotesMedical className="iconaMisurazione"/></div>
                                                <div className="contenitoreDati">
                                                {
                                                    Object.entries(mis["misurazione"]).map(chiave => {
                                                            if (chiave[0] != "id") {
                                                                return(
                                                                    <div>
                                                                        <span className="nomeCampo">{chiave[0]} :</span> <span className="valoreCampo">{chiave[1]}</span>
                                                                    </div>
                                                                )
                                                            }
                                                        }
                                                    )
                                                }
                                                </div>
                                            </div>
                                        )
                                    })}
                                </div>
                            </div>
                        )
                    })}
                </div>
            </div>
        </div>
    )
}

export default Fascicolo