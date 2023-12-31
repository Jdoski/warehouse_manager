import Table from "react-bootstrap/Table";
import InventoriesEditForm from "./InventoriesEditForm";
import { useState } from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

export default function InventoriesTable({ tableData, handleNewInventory }) {
  const url = "http://localhost:8080/items";

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
  //Recieves the inventory ID from the event on the delete button which is used to specify which inventory is used to delete
  const handleDelete = (inventory_id) => {
    fetch(url + `/inventory/${inventory_id}`, {
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
            <th>Warehouse ID</th>
            <th>Item ID</th>
            <th>Quantity</th>
          </tr>
        </thead>
        <tbody>
          {tableData.map((inventory, index) => {
            return (
              <tr key={index}>
                <td>{inventory.id.warehouse_id}</td>
                <td>{inventory.id.item_id}</td>
                <td>{inventory.quantity}</td>
                <td>
                  <Button
                    variant="warning"
                    onClick={() => handleEdit(inventory)}
                  >
                    Edit
                  </Button>
                  <Button
                    variant="danger"
                    onClick={() => handleDelete(inventory.id)}
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
          <Modal.Title>Edit Item</Modal.Title>
        </Modal.Header>
        <InventoriesEditForm
          handleNewInventory={handleNewInventory}
        ></InventoriesEditForm>
      </Modal>
    </>
  );
}
