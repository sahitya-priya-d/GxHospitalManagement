import React from 'react'
import { useAuth } from '../Context/AuthContext'
import { Navigate } from 'react-router-dom';
import Admin from '../Pages/Admin';
import AdminDashboard from '../Pages/AdminDashboard';
const ProtectedRoute = (adminRequired) => {
    const {user}=useAuth();
 if(user && (user.role==='ADMIN' || adminRequired))
 {
   return <AdminDashboard/>
 }
 else{
    return <Navigate to="/login" />;
 }
};
export default ProtectedRoute
