import logoPath from './images/LogoHeartCare.png';
import './App.css';
import './css/style.css'
import LoginForm from './components/LoginForm';



function App() {

  return (
      <div>
        <div className='sfondoPagina'>
          <div className='contenitoreLogin'>
            <img src={logoPath} alt="Logo" className='logo'/><br></br>
            <LoginForm/>
          </div>
        </div>
      </div>
  );
}

export default App;