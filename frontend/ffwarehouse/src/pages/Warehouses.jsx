import WarehousesTable from "../components/Warehouses/WarehousesTable";
import { useEffect, useRef, useState } from "react";
import WarehousesForm from "../components/Warehouses/WarehousesForm";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

export default function Warehouses() {
  const url = "http://localhost:8080/warehouses";

  // state for our list of Warehouses

  const [warehouse, setWarehouse] = useState([]);
  const [show, setShow] = useState(false);

  const handleShow = () => setShow(true);
  const handleClose = () => setShow(false);

  // make this get request when the componennt is mounted to dom
  useEffect(() => {
    // fetch will default to a GET request
    fetch(url)
      .then((data) => data.json())
      .then((returnedData) => {
        setWarehouse(returnedData);
      })
      .catch((error) => console.error(error));
  }, []); // need to add empty dependency list so it runs on mount only

  function handleNewWarehouse(newWarehouse) {
    setWarehouse((oldState) => {
      return [...oldState, newWarehouse];
    });
  }

  return (
    <>
      <Container>
        <Row>
          <Col>
            <h1 className="text-centered">All Warehouses</h1>
          </Col>
          <Col>
            <Button variant="primary" onClick={handleShow}>
              New Warehouse
            </Button>
          </Col>
        </Row>
        <Row>
          <Col>
            <WarehousesTable tableData={warehouse} />
          </Col>
        </Row>
      </Container>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Create Warehouse</Modal.Title>
        </Modal.Header>
        <WarehousesForm
          handleNewWarehouse={handleNewWarehouse}
        ></WarehousesForm>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
