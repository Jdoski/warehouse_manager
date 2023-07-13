import InventoriesTable from "../components/Inventories/InventoriesTable";
import { useEffect, useRef, useState } from "react";
import InventoriesForm from "../components/Inventories/InventoriesForm";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

export default function Inventories() {
  const url = "http://localhost:8080/inventories";

  // state for our list of Warehouses

  const [inventory, setInventory] = useState([]);
  const [show, setShow] = useState(false);

  const handleShow = () => setShow(true);
  const handleClose = () => setShow(false);

  // make this get request when the componennt is mounted to dom
  useEffect(() => {
    // fetch will default to a GET request
    fetch(url)
      .then((data) => data.json())
      .then((returnedData) => {
        setInventory(returnedData);
      })
      .catch((error) => console.error(error));
  }, []); // need to add empty dependency list so it runs on mount only

  function handleNewInventory(newInvetory) {
    setInventory((oldState) => {
      return [...oldState, newInvetory];
    });
  }

  return (
    <>
      <Container>
        <Row>
          <Col>
            <h1 className="text-centered">All Invetories</h1>
          </Col>
          <Col>
            <Button variant="primary" onClick={handleShow}>
              New Inventory
            </Button>
          </Col>
        </Row>
        <Row>
          <Col>
            <InventoriesTable tableData={inventory} />
          </Col>
        </Row>
      </Container>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <InventoriesForm
          handleNewInventory={handleNewInventory}
        ></InventoriesForm>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
