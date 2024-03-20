import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import axios from "axios";
import Header from "../Components/Header";
import { Card, CardGroup } from "react-bootstrap";
import "../Styles/doctors.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCalendarPlus } from "@fortawesome/free-solid-svg-icons";
import AppointmentModal from "../Components/AppointmentModal";

const Doctors = () => {
  const { branchId } = useParams();
  const [doctors, setDoctors] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [noResults, setNoResults] = useState(false);
  const [deptType, setDeptType] = useState("All");
  const [experienceFilter, setExperienceFilter] = useState("All");
  const [currentPage, setCurrentPage] = useState(1);

  const doctorsPerPage = 8;
  const [showModal, setShowModal] = useState(false);
  const [dates, setDates] = useState([]);

  const handleBookAppointment = () => {
    setShowModal(true);
    calculateNextFiveDates();
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const calculateNextFiveDates = () => {
    const today = new Date();
    const nextFiveDates = [];
    for (let i = 0; i < 5; i++) {
      const date = new Date(today);
      date.setDate(today.getDate() + i);
      nextFiveDates.push(date.toLocaleDateString());
    }
    setDates(nextFiveDates);
  };

  useEffect(() => {
    fetchDoctorsByBranch();
  }, [branchId, experienceFilter]);

  const fetchDoctorsByBranch = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/gxHospital/doctors/get-by-branch/${branchId}`
      );
      if (response.data.length === 0) {
        setNoResults(true);
      } else {
        setNoResults(false);
        const filteredDoctors = applyExperienceFilter(response.data);
        setDoctors(filteredDoctors);
      }
    } catch (error) {
      setNoResults(true);
      console.error("Error fetching doctors:", error);
    }
  };

  const applyExperienceFilter = (doctors) => {
    if (experienceFilter === "All") {
      return doctors;
    }

    const minExperience = parseInt(experienceFilter.split("-")[0]) || 0;
    const maxExperience = parseInt(experienceFilter.split("-")[1]) || Infinity;

    return doctors.filter((doctor) => {
      const doctorExperience = parseInt(doctor.experience);
      return (
        doctorExperience >= minExperience && doctorExperience < maxExperience
      );
    });
  };

  const handleSearch = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/gxHospital/doctors/search?doctorName=${searchQuery}&deptName=${searchQuery}&branchId=${branchId}`
      );

      if (response.data.length === 0) {
        setNoResults(true);
      } else {
        setNoResults(false);
        const filteredDoctors = applyExperienceFilter(response.data);
        setDoctors(filteredDoctors);
      }
    } catch (error) {
      setNoResults(true);
      console.error("Error searching data:", error.message);
    }
  };

  const fetchDoctorsByDepartment = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/gxHospital/doctors/searchByDeptName?deptName=${deptType}&branchId=${branchId}`
      );
      if (response.data.length === 0) {
        setNoResults(true);
      } else {
        setNoResults(false);
        const filteredDoctors = applyExperienceFilter(response.data);
        setDoctors(filteredDoctors);
      }
    } catch (error) {
      setNoResults(true);
      console.error("Error fetching doctors by department", error);
    }
  };

  useEffect(() => {
    const fetchDataAndFilter = async () => {
      if (searchQuery && deptType === "All") {
        await handleSearch();
      } else if (deptType === "All") {
        await fetchDoctorsByBranch();
      } else if (deptType !== "All" && searchQuery !== "") {
        await handleSearchByDept(deptType);
      } else if (deptType !== "All") {
        await fetchDoctorsByDepartment();
      } else {
        await fetchDoctorsByBranch();
      }
    };

    const delayDebounceFn = setTimeout(() => {
      fetchDataAndFilter();
    }, 300);
    return () => clearTimeout(delayDebounceFn);
  }, [searchQuery, branchId, deptType]);
  const handleDeptTypeChange = (event) => {
    setDeptType(event.target.value);
    setSearchQuery("");
    setCurrentPage(1);
  };

  const handleExperienceFilterChange = (event) => {
    setExperienceFilter(event.target.value);
    setCurrentPage(1);
    setDeptType("All");
  };

  const handleSearchByDept = async (deptType) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/gxHospital/doctors/searchByNameAndDept?doctorName=${searchQuery}&deptName=${deptType}&branchId=${branchId}`
      );
      if (response.data.length === 0) {
        setNoResults(true);
      } else {
        setNoResults(false);
        const filteredDoctors = applyExperienceFilter(response.data);
        setDoctors(filteredDoctors);
      }
    } catch (error) {
      setNoResults(true);
      console.error("Error searching data:", error.message);
    }
  };

  const handleReset = () => {
    setDeptType("All");
    setExperienceFilter("All");
    setSearchQuery("");
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
    <div>
      <Header />
      <div className="searchbar">
        <input
          type="text"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          placeholder="Search by doctor name or department name"
        />
      </div>
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
        <div className="experience-filter">
          <label className="filter-type">Experience :</label>
          <select
            value={experienceFilter}
            onChange={handleExperienceFilterChange}
          >
            <option value="All">All</option>
            <option value="0-5">0-5 years</option>
            <option value="5-10">5-10 years</option>
            <option value="10-15">10-15 years</option>
          </select>
        </div>
        <button className="reset-filter" onClick={handleReset}>
          Reset Filter
        </button>
      </div>
      {noResults ? (
        <div>No Doctors exist</div>
      ) : (
        <>
          <div
            className="item-card-container"
            style={{ margin: "25px", display: "flex", flexWrap: "wrap" }}
          >
            {currentDoctors.map((doctor) => (
              <CardGroup
                key={doctor.doctorId}
                style={{ margin: "15px", flexBasis: "calc(25% - 40px)" }}
              >
                <Card className="doctor-card" key={doctor.doctorId}>
                  <Link to="">
                    <Card.Img
                      variant="top"
                      src={doctor.image}
                      style={{ width: "270px", height: "300px" }}
                    />
                  </Link>
                  <Card.Body>
                    <Card.Title className="doctor-name">
                      Dr.&nbsp;{doctor.doctorName}
                    </Card.Title>
                    <Card.Text>
                      Speciality&nbsp;:&nbsp;{doctor.department.deptName}
                      <br />
                      Exp: {doctor.experience} years
                      <br />
                      Consultation Fee: ₹{doctor.fee}
                    </Card.Text>
                  </Card.Body>
                  <Card.Footer><button className="book" onClick={handleBookAppointment}>Book Appointment &nbsp;&nbsp;&nbsp;<FontAwesomeIcon icon={faCalendarPlus} /> </button></Card.Footer>
                </Card>
              </CardGroup>
            ))}
          </div>

          <div className="pagination">
            <button onClick={handlePrevPage}>&lt;</button>
            {Array.from({ length: totalPages }, (_, index) => index + 1).map(
              (page) => (
                <button
                  key={page}
                  onClick={() => handlePageChange(page)}
                  className={currentPage === page ? "active" : ""}
                >
                  {page}
                </button>
              )
            )}
            <button onClick={handleNextPage}>&gt;</button>
            <AppointmentModal
        showModal={showModal}
        handleCloseModal={handleCloseModal}
        dates={dates}
      />
          </div>
        </>
      )}
    </div>
  );
};

export default Doctors;
