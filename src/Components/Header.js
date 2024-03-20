import React, { useEffect } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import LocationDropDown from "./LocationDropDown";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLocationDot } from "@fortawesome/free-solid-svg-icons";

import "../Styles/header.css";
import { Link } from "react-router-dom";
const Header = ({ locationName }) => {
  useEffect(() => {
    console.log(locationName);
  }, [locationName]);
  const role=sessionStorage.getItem('role');
  return (
    (role==='ADMIN')?<><Navbar expand="lg" className="bg-body-tertiary"><div className="admin-dashboard">Admin Dashboard</div></Navbar></>:
    <div>
      <Navbar expand="lg" className="bg-body-tertiary">
        <Container>
          {locationName ? (
            <div style={{ fontFamily:"domaine-display-medium, Georgia, serif" }}><FontAwesomeIcon icon={faLocationDot} size="lg" />&nbsp;{locationName}</div>
          ) : (
            <>
            <LocationDropDown /><p className="location">Location</p>
            </>
          )}

          <Navbar.Brand>
           <Link to='/home-page'><img
              className="logo-header-img"
              src="https://www.outsourcetopk.com/public/assets/images/portfolio/logo/Hospital-1.jpg"
              alt=""
            ></img></Link> 
            <Link className='header-name' to='/home-page'> <h3>Gx Hospitals</h3></Link> 
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="#home">Contact Us</Nav.Link>
              <Nav.Link href="#link">About Us</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </div>
  );
};

export default Header;
