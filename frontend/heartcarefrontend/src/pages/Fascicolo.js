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

    const returnByCategoria = (categoria) => {
        return Misurazioni.filter( mis =>
            mis["categoria"] == categoria
        )
    }
        return (
        <div className="contenitoreFascicoloContent">
            <div className="mainContentFascicolo">
                <FascicoloBanner categorie={Categorie} misurazioni = {Misurazioni}/>
                <Grafico categorie={Categorie} misurazioni = {Misurazioni}/>
                <div>
                    {Categorie.map( cat => {
                        return(
                            <div>
                                <span>{cat}</span>
                                <div>
                                    {returnByCategoria(cat).map( mis => {
                                        return (
                                            Object.entries(mis["misurazione"]).map(chiave =>
                                                <span>{chiave[0]} : {chiave[1]}</span>
                                            )
                                        )
                                    })}
                                </div>
                            </div>
                        )
                    }) }
                </div>
            </div>
        </div>
    )
}

export default Fascicolo