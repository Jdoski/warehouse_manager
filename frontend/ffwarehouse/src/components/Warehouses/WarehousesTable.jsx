import { useState } from "react";
import Button from "react-bootstrap/Button";
import Table from "react-bootstrap/Table";
import Modal from "react-bootstrap/Modal";
import WarehousesEditForm from "./WarehousesEditForm";

export default function WarehousesTable({ tableData, handleNewWarehouse }) {
  const url = "http://localhost:8080/warehouses";

  const [showModal, setShowModal] = useState(false);
  const [selectedItem, setSelectedItem] = useState(null);
  const [editedItem, setEditedItem] = useState({
    id: "",
    location: "",
    max_items: "",
  });

  const handleEdit = (item) => {
    console.log(item);
    setEditedItem(item);
    setSelectedItem(item);
    setShowModal(true);
  };

  function handleSave(event) {
    const data = new FormData(event.target);
    const newWarehouse = {
      location: data.get("location"),
      max_items: data.get("max_items"),
    };
    // sending the post request
    fetch(url + "/warehouse", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newWarehouse),
    })
      .then((data) => data.json())
      .then((returnedData) => {
        // calling handleNewWarehouse from Warehouses.jsx to add the Warehouse to the table
        handleNewWarehouse(returnedData);

        // resetting the form
        event.target.reset();
      })
      .catch((error) => console.error(error));

    setShowModal(false);
  }

  const handleCancel = () => {
    setShowModal(false);
  };

  return (
    <>
      <Table striped bordered className="bg-primary-lighter">
        <thead>
          <tr>
            <th>ID</th>
            <th>Location</th>
            <th>Max_Items</th>
          </tr>
        </thead>
        <tbody>
          {tableData.map((warehouse) => {
            return (
              <tr key={warehouse.id}>
                <td>{warehouse.id}</td>
                <td>{warehouse.location}</td>
                <td>{warehouse.max_items}</td>
                <td>
                  <Button
                    variant="warning"
                    onClick={() => handleEdit(warehouse)}
                  >
                    Edit
                  </Button>
                  <Button variant="danger" onClick={() => onDelete(item.id)}>
                    Delete
                  </Button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </Table>
      <Modal show={showModal} onHide={handleCancel}>
        <Modal.Header closeButton>
          <Modal.Title>Edit Warehouse</Modal.Title>
        </Modal.Header>
        <WarehousesEditForm
          handleNewWarehouse={handleNewWarehouse}
        ></WarehousesEditForm>
      </Modal>
    </>
  );
}
