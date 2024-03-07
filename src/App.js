
import './App.css';
import Login from './Pages/Login';
import ForgotPassword from './Pages/ForgotPassword';

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ResetPassword from './Pages/ResetPassword';
import WelcomePage from './Pages/WelcomePage';
function App() {
  return (
    <div className="App">
      <Router>
      <Routes>
      <Route path='/' element={<WelcomePage/>}/>
      <Route path='/forgot-password' element={<ForgotPassword/>}/>
      <Route path='/forgot-password/:id' element={<ResetPassword/>}/>
      <Route path='/login' element={<Login/>}/>
      </Routes>
      </Router>
    </div>
  );
}

export default App;
