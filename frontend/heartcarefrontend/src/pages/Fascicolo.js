import "../css/Fascicolo.css"
import {FascicoloBanner} from "../components/FascicoloBanner";
import Grafico from "../components/Grafico";
import {useState,useEffect} from "react";

function Fascicolo(){
    const [Categorie,setCategorie] = useState([]);
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
                    id : 1
                })
            }).then(response => response.json());
            setCategorie(response);
        }
        getCategorie();
    },[]);

        return (
        <div className="contenitoreFascicoloContent">
            <div className="searchbar">
                <input id="search" type="text" placeholder=" 🔍Cerca Paziente..." />
            </div>
            <span>bentornato, Mario 👋🏻</span>
            <FascicoloBanner categoria="Misuratore di pressione" />
            <Grafico categorie={Categorie}/>
        </div>
    )
}

export default Fascicolo