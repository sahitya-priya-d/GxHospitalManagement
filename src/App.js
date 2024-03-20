import './App.css';
import Login from './Pages/Login';
import ForgotPassword from './Pages/ForgotPassword';
import 'bootstrap/dist/css/bootstrap.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ResetPassword from './Pages/ResetPassword';
import WelcomePage from './Pages/WelcomePage';
import HomePage from './Pages/HomePage';
import Branches from './Pages/Branches';
import Doctors from './Pages/Doctors';
import ProtectedRoute from './ProtectedRoutes/ProtectedRoute';
import AddDoctor from './Pages/AddDoctor';
import AdminDashboard from './Pages/AdminDashboard';
import DoctorsAdminPage from './Pages/DoctorsAdminPage';
import DepartmentsAdmin from './Pages/DepartmentsAdmin';
import Hospitals from './Pages/Hospitals';

function App() {
  
  return (
    <div className="App">
      <Router>
      <Routes>
      <Route path='/' element={<WelcomePage/>}/>
      <Route path='/forgot-password' element={<ForgotPassword/>}/>
      <Route path='/forgot-password/:id' element={<ResetPassword/>}/>
      <Route path='/login' element={<Login/>}/>
      <Route path='/home-page' element={<HomePage/>}/>
      <Route path='/location/:locationId' element={<Branches/>}/>
      <Route path='/branch/:branchId' element={<Doctors/>}/>
      <Route path='/admin-dashboard' element={<AdminDashboard/>}/>
      <Route path='/admin/add-doctor' element={<AddDoctor/>}/>
      <Route path='admin-dashboard/doctors' element={<DoctorsAdminPage/>}/>
      <Route path='admin-dashboard/department' element={<DepartmentsAdmin/>}/>
      <Route path='admin-dashboard/hospitals' element={<Hospitals/>}/>
      </Routes>
      </Router>
    </div>
  );
}

export default App;
