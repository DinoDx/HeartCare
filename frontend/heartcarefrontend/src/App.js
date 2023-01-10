import './App.css';
import './css/style.css'
import Menu from './components/Menu';
import MainContent from "./components/MainContent";



function App() {

  return (
      <div>
            {/* nella pagina di login SI DEVE aggiungere la classe contenitorePaginaLogin */}
            <div className='sfondoPagina contenitorePaginaHomePage'>
                <Menu/>
                <MainContent/>
            </div>
      </div>
  );
}

export default App;