import React from 'react'
import Header from '../Components/Header'
import { faCirclePlus } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import '../Styles/Admin.css';
import { useNavigate } from 'react-router-dom';

const Admin = () => {
    const navigate=useNavigate();
    const handleAddButton=()=>{
        navigate('/admin/add-doctor');
    }
  return (
    <div>
      <Header/>
      <div><button className='add-doctor' onClick={handleAddButton}>Add Doctor</button></div>
    </div>
  )
}

export default Admin
