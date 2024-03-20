import React from 'react'
import "../Styles/AdminDashboard.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faBars,
    faCalendarCheck,
    faHospital,
    faHospitalUser,
    faLocationDot,
    faUserDoctor,
    faUserTie,
    faUsersLine,
  } from "@fortawesome/free-solid-svg-icons";
  import { faSlack } from "@fortawesome/free-brands-svg-icons";
import { useNavigate } from 'react-router-dom';
  
const SidePanel = () => {
    const navigate=useNavigate();
    const handleDashboard = () => {
        navigate("/admin-dashboard");
      };
    const handleDepartment=()=>{
      navigate('/admin-dashboard/department')
    }
    const handleDoctor=()=>{
        navigate('/admin-dashboard/doctors')
    }
    const handleHospital=()=>{
        navigate('/admin-dashboard/hospitals')
    }
  return (
    <>
    <div className="sidebar">
    <div className="side-panel">
      <br></br>
      <span className="icon">
        <FontAwesomeIcon icon={faBars} />
      </span>
      <button className="button" onClick={handleDashboard}>
        &nbsp;
        <FontAwesomeIcon icon={faSlack} /> &nbsp;Dashboard
      </button>
      <button className="button" onClick={handleDoctor}>
        &nbsp;
        <FontAwesomeIcon icon={faUserDoctor} /> &nbsp;Doctors
      </button>
      <button className="button" onClick={handleDepartment}>
        &nbsp;
        <FontAwesomeIcon icon={faUsersLine} /> &nbsp;Departments
      </button>
      <button className="button">
        &nbsp;
        <FontAwesomeIcon icon={faCalendarCheck} /> &nbsp;Appointments
      </button>
      <button className="button">
        &nbsp;
        <FontAwesomeIcon icon={faHospitalUser} /> &nbsp;Patients
      </button>
      <button className="button" onClick={handleHospital}>
        &nbsp;
        <FontAwesomeIcon icon={faHospital} /> &nbsp;Hospitals
      </button>
      <button className="button">
        &nbsp;
        <FontAwesomeIcon icon={faLocationDot} /> &nbsp;Locations
      </button>
      <br></br>
    </div>
  </div>
    </>
  )
}

export default SidePanel
