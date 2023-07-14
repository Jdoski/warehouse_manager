import "bootstrap/dist/css/bootstrap.min.css";
import Warehouses from "./pages/Warehouses";
import Items from "./pages/Items";
import Inventories from "./pages/Inventories";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";

//Navbar container with links to each page.
//BrowserRouter used to handle the pages and routers
function App() {
  return (
    <>
      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="/warehouses">Final Fantasy</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="/warehouses">Warehouses</Nav.Link>
              <Nav.Link href="/items">Items</Nav.Link>
              <Nav.Link href="/inventories">Inventories</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <BrowserRouter basename="/">
        <Routes>
          <Route path="/warehouses" element={<Warehouses />} />
          <Route path="/warehouses" element={<Warehouses />}></Route>
          <Route path="/items" element={<Items />}></Route>
          <Route path="/inventories" element={<Inventories />}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
