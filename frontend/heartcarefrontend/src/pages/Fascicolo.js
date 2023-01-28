import "../css/Fascicolo.css"
import {FascicoloBanner} from "../components/FascicoloBanner";
import Grafico from "../components/Grafico";
import {useState,useEffect} from "react";

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
                    id : 1
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
                    id: 1
                })
            }).then(response => {
                return response.json()
            })
                setMisurazioni(response);
            }
        getCategorie();
        fetchData();
    }, []);

        return (
        <div className="contenitoreFascicoloContent">
            <div className="searchbar">
                <input id="search" type="text" placeholder=" 🔍Cerca Paziente..." />
            </div>
            <span>bentornato, Mario 👋🏻</span>
            <FascicoloBanner categorie={Categorie} misurazioni = {Misurazioni}/>
            <Grafico categorie={Categorie} misurazioni = {Misurazioni}/>
        </div>
    )
}

export default Fascicolo