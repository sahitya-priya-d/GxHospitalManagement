import React, { useState } from "react";
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
import jsonData from "../jsonFile/Data.json";
import { Card, CardGroup } from "react-bootstrap";
import { Link, Navigate, useNavigate } from "react-router-dom";
import SidePanel from "../Components/SidePanel";
const AdminDashboard = () => {
    const navigate=useNavigate();
    

    const handleLogout=()=>{
        navigate('/login');
    }
    
  return (
    <div className="dashboard">
     <SidePanel/>
      <div className="admindashboard">
        <div className="admin-header">
          <FontAwesomeIcon icon={faUserTie} />
          &nbsp;&nbsp;<strong>ADMIN</strong>
          <button className="logout-button" onClick={handleLogout}>Logout</button>
        </div>
        <div
          className="item-card-container"
          style={{ margin: "25px", display: "flex", flexWrap: "wrap" }}
        >
          {jsonData.map((item, index) => (
            <CardGroup
              key={index}
              style={{ margin: "15px", flexBasis: "calc(25% - 40px)" }}
            >
              <Link style={{
                textDecoration: "none"}} to={item.endpoint}><Card className="data-card" key={index}>
                <Card.Img
                  variant="top"
                  src={item.image}
                  style={{ width: "206.33px", height: "137.55px" }}
                />

                <Card.Body className="cards-body">
                  <Card.Title>{item.category}</Card.Title>
                </Card.Body>
              </Card></Link>
            </CardGroup>
          ))}
        </div>
      </div>
    </div>
  );
};

export default AdminDashboard;
