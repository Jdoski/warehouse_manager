import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

export default function InventoriesForm({ handleNewInventory }) {
  const url = "http://localhost:8080/inventories";

  function handleSubmit(event) {
    // preventing page refresh
    event.preventDefault();

    // will grab all of the data from our form
    const data = new FormData(event.target);
    const newInventory = {
      id: {
        warehouse_id: data.get("warehouse_id"),
        item_id: data.get("item_id"),
      },
      quantity: data.get("quantity"),
    };
    // sending the post request
    fetch(url + "/inventory", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newInventory),
    })
      .then((data) => data.json())
      .then((returnedData) => {
        // calling handleNewInventory from Inventories.jsx to add the Inventory to the table
        handleNewInventory(returnedData);
        //reloads the data to update the duplicate invetories quantity.
        window.location.reload();
        // resetting the form
        event.target.reset();
      })
      .catch((error) => console.error(error));
  }
  //Form layout for the add inventory button
  return (
    <>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label htmlFor="warehouse_id">Warehouse ID</Form.Label>
          <Form.Control type="number" id="warehouse_id" name="warehouse_id" />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label htmlFor="item_id">Item ID</Form.Label>
          <Form.Control type="number" id="item_id" name="item_id" />
        </Form.Group>
        <Form.Group>
          <Form.Label htmlFor="inventory-quantity">Quantity</Form.Label>
          <Form.Control id="inventory-quantity" name="quantity" type="number" />
        </Form.Group>
        <Button type="submit" data-close-modal="true">
          Submit
        </Button>
      </Form>
    </>
  );
}
