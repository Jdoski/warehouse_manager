import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

export default function ItemsForm({ handleNewItem }) {
  const url = "http://localhost:8080/items";

  function handleSubmit(event) {
    // preventing page refresh
    event.preventDefault();

    // will grab all of the data from our form
    const data = new FormData(event.target);

    // creating Warehouse object
    const newItem = {
      name: data.get("name"),
      type: data.get("type"),
      cost: data.get("cost"),
    };
    // sending the post request
    fetch(url + "/item", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newItem),
    })
      .then((data) => data.json())
      .then((returnedData) => {
        // calling handleNewItem from items.jsx to add the Warehouse to the table
        handleNewItem(returnedData);

        // resetting the form
        event.target.reset();
      })
      .catch((error) => console.error(error));
  }
  //form layout for the new items modal
  return (
    <>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label htmlFor="item-name">Item Name</Form.Label>
          <Form.Control type="text" id="item-name" name="name" />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label htmlFor="item-type">Item Type</Form.Label>
          <Form.Control type="text" id="item-type" name="type" />
        </Form.Group>
        <Form.Group>
          <Form.Label htmlFor="item-cost">Item Cost</Form.Label>
          <Form.Control id="item-cost" name="cost" type="number" />
        </Form.Group>
        <Button type="submit" data-close-modal="true">
          Submit
        </Button>
      </Form>
    </>
  );
}
