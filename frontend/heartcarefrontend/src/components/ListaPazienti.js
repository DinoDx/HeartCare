import React from "react";
import "../css/style.css";
import "../css/PazientiCss.css";
import axios from "axios";
import {useState, useEffect} from "react";

function ListaPazienti(){
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState([])

    useEffect(() => {
        const fetchData = async () =>{
            setLoading(true);
            try {
                const response = await axios.post('http://localhost:8080/getTuttiPazienti');
                setData(response.data);
            } catch (error) {
                console.error(error.message);
            }
            setLoading(false);
        }
        fetchData();
    }, []);

    return(
        <div>
            {data.map(function(paziente, idx){
                return (<li key={idx}>{paziente.id}</li>)
            })}
        </div>
    );
}

export default ListaPazienti;