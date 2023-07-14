import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

export default function WarehousesEditForm({ handleNewWarehouse }) {
  const url = "http://localhost:8080/warehouses";

  function handleSubmit(event) {
    // preventing page refresh
    // will grab all of the data from our form
    const data = new FormData(event.target);
    console.log(data);
    // creating Warehouse object
    const newWarehouse = {
      id: data.get("id"),
      max_items: Number(data.get("max_items")),
      location: data.get("location"),
    };
    // sending the post request
    fetch(
      url +
        `/warehouse/updateWarehouse/${data.get("id")}/${data.get(
          "max_items"
        )}/${data.get("location")}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newWarehouse),
      }
    )
      .then((data) => data.json())
      .then((returnedData) => {
        // calling handleUpdateWarehouse from Warehouses.jsx to add the Warehouse to the table
        handleNewWarehouse(returnedData);

        // resetting the form
        event.target.reset();
      })
      .catch((error) => console.error(error));
  }

  return (
    <>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label htmlFor="warehouse-location">Warehouse Id</Form.Label>
          <Form.Control type="number" id="id" name="id" />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label htmlFor="warehouse-location">
            Warehouse Location
          </Form.Label>
          <Form.Control type="text" id="location" name="location" />
        </Form.Group>
        <Form.Group>
          <Form.Label htmlFor="warehouse-max-items">Max Items</Form.Label>
          <Form.Control id="max_items" name="max_items" type="number" />
        </Form.Group>
        <Button type="submit" data-close-modal="true">
          Submit
        </Button>
      </Form>
    </>
  );
}
