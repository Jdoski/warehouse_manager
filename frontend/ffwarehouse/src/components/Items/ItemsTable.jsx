import Table from "react-bootstrap/Table";

export default function WarehousesTable({ tableData }) {
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
              </tr>
            );
          })}
        </tbody>
      </Table>
    </>
  );
}
