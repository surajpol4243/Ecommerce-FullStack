import './App.css';
import { BrowserRouter,Route,Routes } from 'react-router-dom';
import LoginPage from './component/pages/Login';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<LoginPage/>} />
      </Routes>
    
    </BrowserRouter>
  );
}

export default App;
