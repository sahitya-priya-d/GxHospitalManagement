import React from 'react'
import { Modal, Button } from "react-bootstrap";
const AppointmentModal = ({ showModal, handleCloseModal, dates }) => {
    return (
      <Modal show={showModal} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>Select Appointment Date</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <ul>
            {dates.map((date, index) => (
              <li key={index}>{date}</li>
            ))}
          </ul>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    );
  };
export default AppointmentModal
