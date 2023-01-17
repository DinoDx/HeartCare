import "./App.css";
import "./css/style.css";

import RouterMedico from "./components/router-menu/RouterMedico";

function App() {
  /**
* questa cosa va aggiunta per far si che un admin vede un menu diverso dal paziente
  render() {
  const isLoggedIn = this.state.isLoggedIn;
  return (
    <div>
      {isLoggedIn ? (
        <LogoutButton onClick={this.handleLogoutClick} />
      ) : (
        <LoginButton onClick={this.handleLoginClick} />
      )}
    </div>
  );
}
*/

  return (
    <div className="sfondoPagina contenitorePaginaHomePage">
      {/* nella pagina di login SI DEVE aggiungere la classe contenitorePaginaLogin */}
      <RouterMedico />
    </div>
  );
}

export default App;
