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

  const handleEdit = (item) => {
    console.log(item);
    setEditedItem(item);
    setSelectedItem(item);
    setShowModal(true);
  };

  function handleSave(event) {
    const data = new FormData(event.target);
    const newItem = {
      id: data.get("id"),
      name: data.get("name"),
      type: data.get("type"),
      cost: Number(data.get("cost")),
    };
    // sending the post request
    fetch(url + "/warehouse", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newItem),
    })
      .then((data) => data.json())
      .then((returnedData) => {
        // calling handleNewWarehouse from Warehouses.jsx to add the Warehouse to the table
        handleNewInventory(returnedData);

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
          <Modal.Title>Edit Item</Modal.Title>
        </Modal.Header>
        <InventoriesEditForm
          handleNewInventory={handleNewInventory}
        ></InventoriesEditForm>
      </Modal>
    </>
  );
}
