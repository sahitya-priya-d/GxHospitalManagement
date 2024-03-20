import React, { useEffect, useState } from 'react'
import {  Tab, Tabs } from "react-bootstrap";
import "../Styles/AdminDashboard.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Card, CardGroup } from "react-bootstrap";

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

import axios from "axios";
import ConfirmationModal from '../Components/ConfirmationModal';
import { useNavigate } from 'react-router-dom';
import SidePanel from '../Components/SidePanel';
import { ToastContainer, toast } from 'react-toastify';
const DepartmentsAdmin = () => {
    const navigate=useNavigate();
    const [departments,setDepartments]=useState([]);
    const [deptName, setDeptName] = useState('');
    const [image, setImage] = useState('');
    const handleLogout=()=>{
        navigate('/login');
    }
  
    useEffect(() => {
        const fetchDepartments = async () => {
          try {
            const response = await axios.get('http://localhost:8080/api/gxHospital/department/all');
            setDepartments(response.data);
          } catch (error) {
            console.error('Error fetching departments:', error);
          }
        };
fetchDepartments()},[])    
const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        await axios.post('http://localhost:8080/api/gxHospital/department/add', {
            deptName: deptName,
            image: image
        });
        console.log('Department added successfully.');
        toast.success("Department added",{autoClose:3000})
    } catch (error) {
        console.error('Error adding department:', error);
    }
};
  return (
    <div className="dashboard">
    <ToastContainer/>
   <SidePanel/>
    <div className="admindashboard">
      <div className="admin-header">
        <FontAwesomeIcon icon={faUserTie} />
        &nbsp;&nbsp;<strong>ADMIN</strong>
        <button className="logout-button" onClick={handleLogout}>Logout</button>
        <Tabs
      defaultActiveKey="profile"
      id="uncontrolled-tab-example"
      className="mb-3"
    >
      <Tab eventKey="home" title="Departments">
      <div
      className="item-card-container"
      style={{ margin: "25px", display: "flex", flexWrap: "wrap" }}
    >
      {departments.map((item) => (
        <CardGroup
          key={item.deptId}
          style={{ margin: "15px", flexBasis: "calc(25% - 40px)" }}
        >
          <Card className="data-card" key={item.deptId}>
            <Card.Img
              variant="top"
              src={item.image}
              style={{ width: "195.33px", height: "137.55px" }}
            />

            <Card.Body className="cards-body">
              <Card.Title>{item.deptName}</Card.Title>
            </Card.Body>
          </Card>
        </CardGroup>
      ))}
    </div>
      </Tab>
      <Tab eventKey="profile" title="Add Department">
      <div className="doctor-form">
      <br></br><h5>Add Department</h5>
      <hr></hr>
                        <form onSubmit={handleSubmit}>
                           <div className='input-box'>
                               
                                <input
                                    type="text"
                                    placeholder="Department name"
                                    value={deptName}
                                    onChange={(e) => setDeptName(e.target.value) }
                                required />
                                </div>
                        <div className='input-box'>
                                <input
                                    type="text"
                                    placeholder="Department Image"
                                    value={image}
                                    onChange={(e) => setImage(e.target.value)} required
                                />
                                </div>
                                <br></br>
    
                                <button type="submit" className="login-button" style={{marginLeft: "11rem",
                                    marginBottom: "2rem"}}>Add Department</button>
                        </form>
                    </div>
      </Tab>
      
    </Tabs>
        
      </div>
      
      </div>
   
      
            <br></br><br></br>
    </div>
  )
}

export default DepartmentsAdmin

