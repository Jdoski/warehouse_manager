import Table from "react-bootstrap/Table";

export default function WarehousesTable({ tableData }) {
  return (
    <>
      <Table striped bordered className="bg-primary-lighter">
        <thead>
          <tr>
            <th>ID</th>
            <th>Location</th>
            <th>Max_Items</th>
          </tr>
        </thead>
        <tbody>
          {tableData.map((warehouse) => {
            return (
              <tr key={warehouse.id}>
                <td>{warehouse.id}</td>
                <td>{warehouse.location}</td>
                <td>{warehouse.max_items}</td>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </>
  );
}
