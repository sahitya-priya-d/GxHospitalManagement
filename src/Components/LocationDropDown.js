import React, { useState, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLocationDot } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import axios from "axios";
import "../Styles/LocationDropDown.css";

const LocationDropDown = () => {
  const [isDropdownOpen, setDropdownOpen] = useState(false);
  const [locations, setLocations] = useState([]);

  useEffect(() => {
    setDropdownOpen(false);
    fetchLocations();
  }, []);

  const toggleDropdown = () => {
    setDropdownOpen(!isDropdownOpen);
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

  return (
    <div className="dropdown-container">
      <button className="dropdown-button" onClick={toggleDropdown}>
        <FontAwesomeIcon icon={faLocationDot} size="lg" />
      </button>

      {isDropdownOpen && (
        <>
          <div className="overlay" onClick={() => setDropdownOpen(false)}></div>
          <div className="dropdown-content">
            {locations.map((location) => (
              <Link
                key={location.locationId}
                to={`/location/${location.locationId}`}
              >
                <button>
                  <FontAwesomeIcon icon={faLocationDot} size="sm" />
                  &nbsp;&nbsp;&nbsp;{location.locationName}
                </button>
              </Link>
            ))}
          </div>
        </>
      )}
    </div>
  );
};

export default LocationDropDown;
