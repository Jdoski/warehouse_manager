import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

export default function InventoryEditForm({ handleNewInventory }) {
  const url = "http://localhost:8080/inventories";

  function handleSubmit(event) {
    // will grab all of the data from our form
    const data = new FormData(event.target);
    console.log(data);

    const newInventory = {
      warehouse_id: data.get("warehouse_id"),
      item_id: data.get("item_id"),
      quantity: Number(data.get("quantity")),
    };
    // sending the put request with proper URL endpoint
    fetch(
      url +
        `/inventory/updateInventory/${data.get("warehouse_id")}/${data.get(
          "item_id"
        )}/${data.get("quantity")}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newInventory),
      }
    )
      .then((data) => data.json())
      .then((returnedData) => {
        handleNewInventory(returnedData);
        // resetting the form
        event.target.reset();
      })
      .catch((error) => console.error(error));
  }
  // layout for the form in the modal for the edit form
  return (
    <>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label htmlFor="warehouse-id">Warehouse Id</Form.Label>
          <Form.Control type="text" id="warehouse_id" name="warehouse_id" />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label htmlFor="item-id">Item Id</Form.Label>
          <Form.Control type="number" id="item_id" name="item_id" />
        </Form.Group>
        <Form.Group>
          <Form.Label htmlFor="quantities">Quantity</Form.Label>
          <Form.Control id="quantity" name="quantity" type="number" />
        </Form.Group>
        <Button type="submit" data-close-modal="true">
          Submit
        </Button>
      </Form>
    </>
  );
}
