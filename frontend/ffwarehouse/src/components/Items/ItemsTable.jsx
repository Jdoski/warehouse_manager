import Table from "react-bootstrap/Table";
import ItemsEditForm from "./ItemsEditForm";
import { useState } from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";

export default function WarehousesTable({ tableData, handleNewItem }) {
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
  const handleDelete = (item_id) => {
    fetch(url + `/item/${item_id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (response.ok) {
          window.location.reload();
        } else {
        }
      })
      .catch((error) => {
        console.error(error);
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
            <th>Name</th>
            <th>Type</th>
            <th>Cost</th>
          </tr>
        </thead>
        <tbody>
          {tableData.map((item) => {
            return (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td>{item.type}</td>
                <td>{item.cost}</td>
                <td>
                  <Button variant="warning" onClick={() => handleEdit(item)}>
                    Edit
                  </Button>
                  <Button
                    variant="danger"
                    onClick={() => handleDelete(item.id)}
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
        <ItemsEditForm handleNewItem={handleNewItem}></ItemsEditForm>
      </Modal>
    </>
  );
}
