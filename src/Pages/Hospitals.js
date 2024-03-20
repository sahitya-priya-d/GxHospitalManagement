import React, { useEffect, useState } from "react";
import { Tab, Tabs, Toast } from "react-bootstrap";
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
import ConfirmationModal from "../Components/ConfirmationModal";
import { useNavigate } from "react-router-dom";
import SidePanel from "../Components/SidePanel";
import { ToastContainer, toast } from "react-toastify";

const Hospitals = () => {
  const [hospitals, setHospitals] = useState([]);
  const [locations, setLocations] = useState([]);
  const [selectedLocation, setSelectedLocation] = useState(1);
  const [locationId, setLocationId] = useState();
  const [newBranch, setNewBranch] = useState({
    branchName: "",
    img: "",
    addressUrl: "",
    branchAddress: "",
    location: {
      locationId: "",
    },
  });
  const navigate = useNavigate();
  const handleLogout = () => {
    navigate("/login");
  };
  const handleDashboard = () => {
    navigate("/admin-dashboard");
  };
  const handleDoctor = () => {
    navigate("admin-dashboard/doctors");
  };
  const handleDepartment=()=>{
    navigate("admin-dashboard/department");
  }
  const handleLocationChange = (locationId) => {
    setSelectedLocation(locationId);
  };
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setNewBranch((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
        const dataToSend = {
            ...newBranch,
            
              location:{
                locationId:locationId,
              
            }
          };
      await axios.post(
        "http://localhost:8080/api/gxHospital/branch/add-branch",
        dataToSend
      );
    
      fetchHospitals();
     
      setNewBranch({
        branchName: "",
        img: "",
        addressUrl: "",
        branchAddress: "",
        location: {
          locationId:"",
        },
      });
      toast.success("Branch added",{autoClose:3000})
    } catch (error) {
      console.error("Error adding branch:", error);
    }
  };
  useEffect(() => {
    const fetchLocation = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/gxHospital/location/get-all-location"
        );
        setLocations(response.data);
      } catch (error) {
        console.error("Error fetching locations:", error);
      }
    };

    fetchLocation();
  }, []);

  useEffect(() => {
    fetchHospitals();
  }, [selectedLocation]);
  const fetchHospitals = async () => {
    if (!selectedLocation) return;
    try {
      const response = await axios.get(
        `http://localhost:8080/api/gxHospital/branch/byLocation/${selectedLocation}`
      );
      setHospitals(response.data);
    } catch (error) {
      console.error("Error fetching hospitals:", error);
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
          <button className="logout-button" onClick={handleLogout}>
            Logout
          </button>
          <Tabs
            defaultActiveKey="profile"
            id="uncontrolled-tab-example"
            className="mb-3"
          >
            <Tab eventKey="home" title="Hospitals">
              
                {locations.map((location) => (<>
                  <button
                    key={location.locationId}
                    onClick={() => handleLocationChange(location.locationId)} className="location-button"
                  >{location.locationName}
                  </button>&nbsp;&nbsp;</>
                ))}
                <div
                className="item-card-container"
                style={{
                  margin: "30px",
                  display: "flex",
                  flexWrap: "wrap",
                }}
              >
                {
                  hospitals.map((item) => (
                    <CardGroup
                      key={item.branchId}
                      style={{
                        margin: "15px",
                        flexBasis: "calc(25% - 40px)",
                      }}
                    >
                      <Card className="data-card" key={item.branchId}>
                        <Card.Img
                          variant="top"
                          src={item.img}
                          style={{
                            width: "195.33px",
                            height: "137.55px",
                          }}
                        />

                        <Card.Body className="cards-body">
                          <Card.Title>{item.branchName}</Card.Title>
                        </Card.Body>
                      </Card>
                    </CardGroup>
                  ))}
              </div>
              
            </Tab>

            <Tab eventKey="profile" title="Add Hopsital Branch">
              <div className="doctor-form">
                <br />
                <h5>Add Hospital Branch</h5>
                <hr />
                <form onSubmit={handleSubmit}>
                  <div className="input-box">
                    <input
                      type="text"
                      name="branchName"
                      value={newBranch.branchName}
                      onChange={handleInputChange}
                      placeholder="Hospital Name"
                      required
                    />
                  </div>
                  <div className="input-box">
                    <input
                      type="text"
                      name="img"
                      value={newBranch.img}
                      onChange={handleInputChange}
                      placeholder="Hospital Image"
                      required
                    />
                  </div>
                  <div className="input-box">
                    <input
                      type="text"
                      name="addressUrl"
                      value={newBranch.addressUrl}
                      onChange={handleInputChange}
                      placeholder="Hospital Location"
                      required
                    />
                  </div>
                  <div className="input-box">
                    <input
                      type="text"
                      name="branchAddress"
                      value={newBranch.branchAddress}
                      onChange={handleInputChange}
                      placeholder="Hospital Address"
                      required
                    />
                  </div>
                  <div className="input-box">
                    <select
                      name="locationId"
                      value={locationId}
                      onChange={(e) => setLocationId(e.target.value)}
                      required
                    >
                      <option value="">Select Location</option>
                      {locations.map((location) => (
                        <option
                          key={location.locationId}
                          value={location.locationId}
                        >
                          {location.locationName}
                        </option>
                      ))}
                    </select>
                  </div>
                  <br></br>
                  <button
                    type="submit"
                    className="login-button"
                    style={{ marginLeft: "11rem", marginBottom: "2rem" }}
                  >
                    Add Hospital
                  </button>
                </form>
              </div>
            </Tab>
          </Tabs>
        </div>
      </div>

      <br></br>
      <br></br>
    </div>
  );
};

export default Hospitals;
