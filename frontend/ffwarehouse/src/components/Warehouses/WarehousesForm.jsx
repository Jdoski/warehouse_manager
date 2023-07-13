import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

export default function WarehousesForm({ handleNewWarehouse }) {
  const url = "http://localhost:8080/warehouses";

  function handleSubmit(event) {
    // preventing page refresh
    event.preventDefault();

    // will grab all of the data from our form
    const data = new FormData(event.target);

    // creating Warehouse object
    const newWarehouse = {
      location: data.get("location"),
      max_items: data.get("maxItems"),
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
  }

  return (
    <>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label htmlFor="warehouse-location">
            Warehouse Location
          </Form.Label>
          <Form.Control type="text" id="warehouse-location" name="location" />
        </Form.Group>
        <Form.Group>
          <Form.Label htmlFor="warehouse-max-items">Max Items</Form.Label>
          <Form.Control
            id="warehouse-max-items"
            name="maxItems"
            type="number"
          />
        </Form.Group>
        <Button type="submit" data-close-modal="true">
          Submit
        </Button>
      </Form>
    </>
  );
}
