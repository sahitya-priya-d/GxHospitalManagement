import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import Header from "../Components/Header";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faLocationDot } from "@fortawesome/free-solid-svg-icons";
import { Card, CardGroup } from "react-bootstrap";
import "../Styles/branch.css";

const Branches = () => {
  const [branches, setBranches] = useState([]);
  const { locationId } = useParams();
  const [locationName, setLocationName] = useState("");

  useEffect(() => {
    // Fetch branches based on locationId
    fetchBranchesByLocation(locationId);
  }, [locationId]);

  const fetchBranchesByLocation = async (locationId) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/gxHospital/branch/byLocation/${locationId}`
      );
      setBranches(response.data);
      if (response.data.length > 0) {
        setLocationName(response.data[0].location.locationName);
        console.log(response.data[0].location.locationName);
      }
    } catch (error) {
      console.error("Error fetching branches:", error);
    }
  };

  return (
    <div>
      <Header locationName={locationName} />
      <div
        className="item-card-container"
        style={{ margin: "10px", display: "flex", flexWrap: "wrap" }}
      >
        {branches?.map((branch) => (
          <CardGroup
            key={branch.branchId}
            style={{ margin: "20px", flexBasis: "calc(25% - 40px)" }}
          >
            <Card className="branch-card" key={branch.branchId}>
              <Link to={`/branch/${branch.branchId}`}>
                <Card.Img variant="top" src={branch.img} />
              </Link>
              <Card.Body>
                <Card.Title>
                  <Link
                    className="branch-name"
                    to={`/branch/${branch.branchId}`}
                  >
                    {branch.branchName}
                  </Link>
                </Card.Title>
                <Card.Text><p className="branch-address">{branch.branchAddress}</p></Card.Text>
                <Card.Footer>
                  <Link className="branch-url" to={branch.addressUrl}>
                    <FontAwesomeIcon icon={faLocationDot} size="sm" />
                    &nbsp;&nbsp;Open in Maps
                  </Link>
                </Card.Footer>
              </Card.Body>
            </Card>
          </CardGroup>
        ))}
      </div>
    </div>
  );
};

export default Branches;
