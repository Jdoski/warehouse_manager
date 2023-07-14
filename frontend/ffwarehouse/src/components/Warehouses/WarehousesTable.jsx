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

  //Gets the information from the Modal and populates the variables to be saved
  const handleEdit = (item) => {
    console.log(item);
    setEditedItem(item);
    setSelectedItem(item);
    setShowModal(true);
  };

  //Recieves the warehouse ID from the event on the delete button which is used to specify which inventory is used to delete
  const handleDelete = (warehouse_id) => {
    fetch(url + `/warehouse/${warehouse_id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (response.ok) {
          window.location.reload();
        } else {
          // Error occurred during deletion
          // Handle the error appropriately
        }
      })
      .catch((error) => {
        // Handle network or other errors
      });
  };

  const handleCancel = () => {
    setShowModal(false);
  };

  //Displays the table as well as the modal for the edit button (defaulted to hidden)
  //Modal sends the data to the Edit form
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
                  <Button
                    variant="danger"
                    onClick={() => handleDelete(warehouse.id)}
                  >
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
