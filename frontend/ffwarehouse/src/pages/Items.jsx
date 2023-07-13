import ItemsTable from "../components/Items/ItemsTable";
import { useEffect, useState } from "react";
import ItemsForm from "../components/Items/ItemsForm";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

export default function Items() {
  const url = "http://localhost:8080/items";

  // state for our list of items

  const [item, setItem] = useState([]);
  const [show, setShow] = useState(false);

  const handleShow = () => setShow(true);
  const handleClose = () => setShow(false);

  // make this get request when the componennt is mounted to dom
  useEffect(() => {
    // fetch will default to a GET request
    fetch(url)
      .then((data) => data.json())
      .then((returnedData) => {
        setItem(returnedData);
      })
      .catch((error) => console.error(error));
  }, []); // need to add empty dependency list so it runs on mount only

  function handleNewItem(newItem) {
    setItem((oldState) => {
      return [...oldState, newItem];
    });
  }

  return (
    <>
      <Container>
        <Row>
          <Col>
            <h1 className="text-centered">All Items</h1>
          </Col>
          <Col>
            <Button variant="primary" onClick={handleShow}>
              New Item
            </Button>
          </Col>
        </Row>
        <Row>
          <Col>
            <ItemsTable tableData={item} />
          </Col>
        </Row>
      </Container>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <ItemsForm handleNewItem={handleNewItem}></ItemsForm>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
