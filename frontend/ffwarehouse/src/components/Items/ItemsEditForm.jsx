import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

export default function ItemsEditForm({ handleNewItem }) {
  const url = "http://localhost:8080/items";

  function handleSubmit(event) {
    // will grab all of the data from our form
    const data = new FormData(event.target);
    console.log(data);
    const newItem = {
      id: data.get("id"),
      name: data.get("name"),
      type: data.get("type"),
      cost: Number(data.get("cost")),
    };
    // sending the put request
    fetch(
      url +
        `/item/updateItem/${data.get("id")}/${data.get("name")}/${data.get(
          "type"
        )}/${data.get("cost")}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newItem),
      }
    )
      .then((data) => data.json())
      .then((returnedData) => {
        handleNewItem(returnedData);
        // resetting the form
        event.target.reset();
      })
      .catch((error) => console.error(error));
  }
  //Form layout for the edit modal in the table
  return (
    <>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label htmlFor="item_id">Item Id</Form.Label>
          <Form.Control type="number" id="id" name="id" />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label htmlFor="Item-Name">Item Name</Form.Label>
          <Form.Control type="text" id="name" name="name" />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Label htmlFor="item-type">Item Type</Form.Label>
          <Form.Control type="text" id="type" name="type" />
        </Form.Group>
        <Form.Group>
          <Form.Label htmlFor="item-cost">Cost</Form.Label>
          <Form.Control id="cost" name="cost" type="number" />
        </Form.Group>
        <Button type="submit" data-close-modal="true">
          Submit
        </Button>
      </Form>
    </>
  );
}
