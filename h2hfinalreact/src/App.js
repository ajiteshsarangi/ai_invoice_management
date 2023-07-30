import React, { useState } from "react";
import "./App.css";
import DataTable from "./components/DataTable";
import Header from "./components/Header";
import AddData from "./components/AddData";
import { Tabs, Tab } from "@material-ui/core";
import { Route, BrowserRouter, Switch, Link, useHistory } from "react-router-dom";
import Results from "./components/Results";

function App() {
  const routes = ["/datatable", "/adddata", "/results"];
  const [searchTerm, setSearchTerm] = useState("");
  const [showResults, setShowResults] = useState(false);
  const history = useHistory();

  const handleSearch = () => {
    setShowResults(true);
    // Redirect to search results page with the search term as a query parameter
    history.push({
      pathname: routes[2],
      search: `?searchTerm=${encodeURIComponent(searchTerm)}`,
    });
  };

  const handleClear = () => {
    setSearchTerm("");
    setShowResults(false);
    // Redirect to home page
    history.push(routes[0]);
  };

  const handleKeyDown = (event) => {
    if (event.key === "Enter") {
      handleSearch();
    }
  };
  console.log(searchTerm);
  return (
    <div>
      <Header />
      <Route
        path="/"
        render={(history) => (
          <div className="tabs flex justifySpace centerSearch">
            <Tabs value={window.location.pathname !== "/" ? window.location.pathname : false}>
              <Tab value={routes[0]} label="Home Page" component={Link} to={routes[0]} />
              <Tab value={routes[1]} label="add data" component={Link} to={routes[1]} />
              {showResults ? (
                <Tab value={routes[2]} label="Search Results" component={Link} to={routes[2]} />
              ) : (
                ""
              )}
            </Tabs>
            <div className="flex">
              <input
                type="text"
                className="itemSearch"
                placeholder="Search Customer Order ID"
                name="search"
                id="search"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                onKeyDown={handleKeyDown}
              />
              {showResults ? (
                <button type="text" className="itemBtn" onClick={handleClear}>
                  Clear
                </button>
              ) : (
                <button type="text" className="itemBtn" onClick={handleSearch}>
                  Advanced Search
                </button>
              )}
            </div>
          </div>
        )}
      />
      <Switch>
        <Route path="/datatable" component={DataTable} />
        <Route path="/adddata" component={AddData} />
        <Route path="/results" render={() => <Results searchTerm={searchTerm} />} />
      </Switch>
      <div className="flex center" style={{ height: "80px" }}>
        <div>
          <a style={{ textDecoration: "none" }} href="#">
            Privacy Policy
          </a>{" "}
          | © {new Date().getFullYear()} HighRadius Corporation. All rights reserved.
        </div>
      </div>
    </div>
  );
}

export default App;
