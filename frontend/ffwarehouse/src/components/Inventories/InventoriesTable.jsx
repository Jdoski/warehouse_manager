import Table from "react-bootstrap/Table";

export default function InventoriesTable({ tableData }) {
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
              </tr>
            );
          })}
        </tbody>
      </Table>
    </>
  );
}
