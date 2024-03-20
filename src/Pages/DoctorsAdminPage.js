import React, { useEffect, useState } from "react";
import { Button, Col, Container, Row, Tab, Tabs } from "react-bootstrap";
import "../Styles/AdminDashboard.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Modal } from "react-bootstrap";

import {
  faUserTie,
} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import ConfirmationModal from "../Components/ConfirmationModal";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import SidePanel from "../Components/SidePanel";

const DoctorsAdminPage = () => {
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [doctors, setDoctors] = useState([]);
  const [doctorId, setDoctorId] = useState();
  const navigate = useNavigate();
  const [currentPage, setCurrentPage] = useState(1);
  const doctorsPerPage = 5;
  const [deptType, setDeptType] = useState("All");
  const [doctorData, setDoctorData] = useState({
    doctorName: "",
    image: "",
    experience: "",
    fee: "",
    department: {
      deptId: "",
    },
    branch: {
      branchId: "",
      location: {
        locationId: "",
      },
    },
  });
  const [deptId, setDeptId] = useState();
  const [branchId, setBranchId] = useState();
  const [departments, setDepartments] = useState([]);
  const [locations, setLocations] = useState([]);
  const [branches, setBranches] = useState([]);
  const [locationId, setLocationId] = useState();
  const [location, setLocation] = useState("All");
  const [editedDoctorData, setEditedDoctorData] = useState({
    doctorId:"",
    doctorName: "",
  image: "",
  experience: "",
  fee: "",
  department: {
    deptId: "",
  },
  branch: {
    branchId: "",
    location:{
      locationId:"",
    },
  },
});
  const [fee,setFee]=useState();
  const [experience,setExperience]=useState();
  useEffect(() => {
    const fetchDepartments = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/gxHospital/department/all"
        );
        setDepartments(response.data);
      } catch (error) {
        console.error("Error fetching departments:", error);
      }
    };

    const fetchLocations = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/gxHospital/location/get-all-location"
        );
        setLocations(response.data);
      } catch (error) {
        console.error("Error fetching locations:", error);
      }
    };

    fetchDoctors();
    fetchDepartments();
    fetchLocations();
  }, []);

  const fetchDoctors = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/api/gxHospital/doctors/get-all"
      );
      setDoctors(response.data);
      
    } catch (error) {
      console.error("Error fetching doctors:", error);
    }
  };

  const handleLocationChange = async (event) => {
    const locationId = event.target.value;
    setLocationId(event.target.value);
    try {
      const response = await axios.get(
        `http://localhost:8080/api/gxHospital/branch/byLocation/${locationId}`
      );
      setBranches(response.data);
    } catch (error) {
      console.error("Error fetching branches:", error);
    }
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setDoctorData({ ...doctorData, [name]: value });
  };

  useEffect(() => {
    const fetchDataAndFilter = async () => {
      if (deptType === "All" && location === "All") {
        await fetchDoctors();
      } else if (deptType !== "All" && location === "All") {
        await fetchDoctorsByDepartment();
      } else if (deptType === "All" && location !== "All") {
        await fetchDoctorsByLocation();
      } else {
        const filteredDoctors = doctors.filter(
          doctor =>
            doctor.department.deptName === deptType &&
            doctor.branch.location.locationName === location
        );
        setDoctors(filteredDoctors);
      }
    };

    const delayDebounceFn = setTimeout(() => {
      fetchDataAndFilter();
    }, 300);
    return () => clearTimeout(delayDebounceFn);
  }, [branchId, deptType, location]);

  const handleDeptTypeChange = (event) => {
    setDeptType(event.target.value);
    setCurrentPage(1);
  };

  const handleLocationTypeChange = (event) => {
    setLocation(event.target.value);
    setCurrentPage(1);
  };

  const fetchDoctorsByDepartment = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/gxHospital/doctors/getByDept?deptName=${deptType}`
      );
      setDoctors(response.data);
    } catch (error) {
      console.error("Error fetching doctors:", error);
    }
  };

  const handleReset = () => {
    setDeptType("All");
    setLocation("All");
  };

  const fetchDoctorsByLocation = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/gxHospital/doctors/getByLocation?locationName=${location}`
      );
      setDoctors(response.data);
    } catch (error) {
      console.error("Error fetching doctors:", error);
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
     
        const dataToSend = {
          ...doctorData,
          department: {
            deptId: deptId,
          },
          branch: {
            branchId: branchId,
            location: {
              locationId: locationId,
            },
          },
        };
        const response = await axios.post(
          "http://localhost:8080/api/gxHospital/doctors/add-doctor",
          dataToSend
        );
        console.log("Doctor added successfully:", response.data);
        setDoctorData({
          doctorName: "",
          image: "",
          experience: "",
          fee: "",
          department: {
            deptId: "",
          },
          branch: {
            branchId: "",
          },
        });
        toast.success("Doctor added successfully", {
          autoClose: 3000,
        })
      }
    catch (error) {
      console.error("Error adding/updating doctor:", error);
    }
  };

  const handleEdit = (doctor) => {
    setEditedDoctorData({
      doctorId:doctor.doctorId,
      doctorName: doctor.doctorName,
      image: doctor.image,
      experience: doctor.experience,
      fee: doctor.fee,
      department: {
        deptId: doctor.department.deptId,
      },
      branch: {
        branchId: doctor.branch.branchId,
        location: {
          locationId: doctor.branch.location.locationId,
        },
      },
    })
    setShowEditModal(true);
  };

  const handleEditSubmit = async (event) => {
    event.preventDefault();
    const dataToSend={
      ...editedDoctorData,
      fee:fee,
      experience:experience,
    }
    try {
      const response = await axios.put(
        "http://localhost:8080/api/gxHospital/doctors/update",
       dataToSend
      );
      console.log("Doctor updated successfully:", response.data);
      setDoctors((prevDoctors) =>
        prevDoctors.map((doctor) =>
          doctor.doctorId === editedDoctorData.doctorId
            ? response.data
            : doctor
        )
      );
      toast.success("Doctor updated successfully", {
        autoClose: 3000,
      })
      setShowEditModal(false);
    } catch (error) {
      console.error("Error updating doctor:", error);
    }
  };

  const handleLogout = () => {
    navigate("/login");
  };

  const handleDashboard = () => {
    navigate("/admin-dashboard");
  };
const handleDepartment=()=>{
  navigate('/admin-dashboard/department')
}
  const handleDelete = (doctorId) => {
    setDoctorId(doctorId);
    setShowConfirmationModal(true);
  };

  const confirmDelete = async () => {
    try {
      await axios.delete(
        `http://localhost:8080/api/gxHospital/doctors/delete/${doctorId}`
      );
      setDoctors(doctors.filter((doctor) => doctor.doctorId !== doctorId));
      console.log(`Doctor with ID ${doctorId} deleted successfully.`);
    } catch (error) {
      console.error(`Error deleting doctor with ID ${doctorId}:`, error);
    } finally {
      setShowConfirmationModal(false);
    }
  };

  const indexOfLastDoctor = currentPage * doctorsPerPage;
  const indexOfFirstDoctor = indexOfLastDoctor - doctorsPerPage;
  const totalDoctors = doctors.length;
  const currentDoctors = doctors.slice(indexOfFirstDoctor, indexOfLastDoctor);

  const totalPages = Math.ceil(totalDoctors / doctorsPerPage);

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
  };

  const handlePrevPage = () => {
    setCurrentPage((prevPage) => Math.max(prevPage - 1, 1));
  };

  const handleNextPage = () => {
    setCurrentPage((prevPage) => Math.min(prevPage + 1, totalPages));
  };
  return (
    <div className="dashboard">
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
            <Tab eventKey="home" title="Doctors">
            
            <div className="filter-options">
              <div className="deptlist">
                <label className="filter-type">Department :</label>
                <select value={deptType} onChange={handleDeptTypeChange}>
                  <option value="All">All</option>
                  <option value="Cardiology">Cardiology</option>
                  <option value="Dermatology">Dermatology</option>
                  <option value="ENT">ENT</option>
                  <option value="General Surgery">General Surgery</option>
                  <option value="Neurology">Neurology</option>
                  <option value="Orthopaedics">Orthopaedics</option>
                  <option value="Paediatrics">Paediatrics</option>
                  <option value="Plastic Surgery">Plastic Surgery</option>
                </select>
              </div>
              
              <button className="reset-filter" onClick={handleReset}>
                Reset Filter
              </button>

            </div>
            <div className="deptlist">
              <label className="filter-type">Location :</label>
              <select value={location} onChange={handleLocationTypeChange}>
                <option value="All">All</option>
                <option value="Bangalore">Bangalore</option>
                <option value="New Delhi">New Delhi</option>
                <option value="Mumbai">Mumbai</option>
                <option value="Hyderabad">Hyderabad</option>
              
              </select>
            </div>
              <Container>
                <Row className="row-name">
                  <Col xs lg="2">
                    <p className="col-heading">
                      <strong>#</strong>
                    </p>
                  </Col>
                  <Col>
                    <p className="col-heading">
                      <strong>Doctor Name</strong>
                    </p>
                  </Col>
                  <Col>
                    <p className="col-heading">
                      <strong>Department</strong>
                    </p>
                  </Col>
                  <Col>
                    <p className="col-heading">
                      <strong>Fee</strong>
                    </p>
                  </Col>

                  <Col xs={4}>
                    <p className="col-heading" style={{ marginLeft: "4rem" }}>
                      <strong>Options</strong>
                    </p>
                  </Col>
                </Row>

                {currentDoctors?.map((details) => (
                  <Row key={details.doctorId}>
                    <Col xs lg="2">
                      <p className="order-data">#{details.doctorId}</p>
                    </Col>
                    <Col>
                      <p className="order-data">{details.doctorName}</p>
                    </Col>
                    <Col>
                      <p className="order-data">
                        {details.department.deptName}
                      </p>
                    </Col>
                    <Col>
                    <p className="order-data">
                      {details.fee}
                    </p>
                  </Col>
                    <Col xs={4}>
                      <button className="edit" onClick={()=>handleEdit(details)}>Edit</button>&nbsp;&nbsp;
                      <button
                        className="delete"
                        onClick={() => handleDelete(details.doctorId)}
                      >
                        Delete
                      </button>
                    </Col>
                  </Row>
                ))}
              </Container>
              <div className="pagination">
                <button onClick={handlePrevPage}>&lt;</button>
                {Array.from(
                  { length: totalPages },
                  (_, index) => index + 1
                ).map((page) => (
                  <button
                    key={page}
                    onClick={() => handlePageChange(page)}
                    className={currentPage === page ? "active" : ""}
                  >
                    {page}
                  </button>
                ))}
                <button onClick={handleNextPage}>&gt;</button>
              </div>
            </Tab>
            <Tab eventKey="profile" title="Add Doctor">
              <div className="doctor-form">
                <br></br>
                <h5>Add Doctor</h5>
                <hr></hr>
                <form onSubmit={handleSubmit}>
                  <div className="input-box">
                    <input
                      placeholder="Doctor's Name"
                      type="text"
                      name="doctorName"
                      value={doctorData.doctorName}
                      onChange={handleChange}
                      required
                    />
                  </div>

                  <div className="input-box">
                    <input
                      placeholder=" Image"
                      type="text"
                      name="image"
                      value={doctorData.image}
                      onChange={handleChange}
                      required
                    />
                  </div>

                  <div className="input-box">
                    <input
                      placeholder="Experience (in years)"
                      type="number"
                      name="experience"
                      value={doctorData.experience}
                      onChange={handleChange}
                      required
                    />
                  </div>

                  <div className="input-box">
                    <input
                      placeholder="Appointment Fee"
                      className="Fee"
                      type="number"
                      name="fee"
                      value={doctorData.fee}
                      onChange={handleChange}
                      required
                    />
                  </div>

                  <div className="input-box">
                    <select
                      style={{ marginLeft: "-12.5rem !important" }}
                      name="deptId"
                      value={deptId}
                      onChange={(e) => setDeptId(e.target.value)}
                      required
                    >
                      <option value="">Select Department</option>
                      {departments.map((department) => (
                        <option
                          key={department.deptId}
                          value={department.deptId}
                        >
                          {department.deptName}
                        </option>
                      ))}
                    </select>
                  </div>

                  <div className="input-box">
                    <select
                      style={{ marginLeft: "-12.5rem !important" }}
                      onChange={handleLocationChange}
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

                  {branches.length > 0 && (
                    <div className="input-box">
                      <select
                        style={{ marginLeft: "-12.5rem !important" }}
                        name="branchId"
                        value={branchId}
                        onChange={(e) => setBranchId(e.target.value)}
                        required
                      >
                        <option value="">Select Branch</option>
                        {branches.map((branch) => (
                          <option key={branch.branchId} value={branch.branchId}>
                            {branch.branchName}
                          </option>
                        ))}
                      </select>
                    </div>
                  )}
                  <br></br>

                  <button
                    type="submit"
                    className="login-button"
                    style={{ marginLeft: "11rem", marginBottom: "2rem" }}
                  >
                    Add Doctor
                  </button>
                </form>
              </div>
            </Tab>
          </Tabs>
        </div>
      </div>
      {
        <ConfirmationModal
          show={showConfirmationModal}
          onClose={() => setShowConfirmationModal(false)}
          onConfirm={confirmDelete}
        />
      }
      <Modal
  show={showEditModal}
  onHide={() => setShowEditModal(false)}
  backdrop="static"
  keyboard={false}
>
  <Modal.Header closeButton>
    <Modal.Title>Edit Doctor</Modal.Title>
  </Modal.Header>
  <Modal.Body>
    <form onSubmit={handleEditSubmit}>
      
      <div className="input-box">
        <label>Experience (in years)</label>
        <input
          type="number"
          
          onChange={(e) =>
            setExperience(e.target.value
            )
          }
          required
        />
      </div>
      <div className="input-box">
        <label>Appointment Fee</label>
        <input
          type="number"
          
          onChange={(e) =>setFee(e.target.value)
            
          }
          required
        />
      </div>
    </form>
  </Modal.Body>
  <Modal.Footer>
    <Button variant="secondary" onClick={() => setShowEditModal(false)}>
      Close
    </Button>
    <Button variant="primary" type="submit" onClick={handleEditSubmit}>
      Save Changes
    </Button>
  </Modal.Footer>
</Modal>
      <br></br>
      <br></br>
    </div>
  );
};

export default DoctorsAdminPage;
