import React, { useState, useEffect } from "react";
import axios from "axios";
import { DataGrid } from "@mui/x-data-grid";
import { makeStyles } from "@material-ui/core";
const columns = [
  { field: "slNo", headerName: "Sl No", width: 70, type: "number" },
  { field: "customerOrderId", headerName: "Customer Order ID", width: 160 },
  { field: "salesOrg", headerName: "Sales Org", width: 160 },
  { field: "distributionChannel", headerName: "Distribution Channel", width: 160 },
  { field: "companyCode", headerName: "Company Code", width: 160 },
  { field: "orderCreationDate", headerName: "Order Creation Date", width: 160 },
  { field: "orderCurrency", headerName: "Order Currency", width: 160 },
  { field: "customerNumber", headerName: "Customer Number", width: 160 },
  { field: "amountUSD", headerName: "Amount USD", width: 160 },
  { field: "orderAmount", headerName: "Order Amount", width: 160 },
];
const useStyles = makeStyles(() => ({
  columnHeader: {
    color: "#ffffff",
    fontWeight: "bold",
    whiteSpace: "normal",
    alignItems: "left",
  },
  dataGrid: {
    border: "0px",
    color: "#ffffff",
    fontWeight: 400,
    fontSize: "14px",
    padding: "10px",
    "& .MuiDataGrid-window": {
      scrollbarWidth: "thin",
      scrollbarColor: "#595959 #666666",
      "&::-webkit-scrollbar": {
        width: "10px",
      },
      "&::-webkit-scrollbar-track": {
        backgroundColor: "#666666",
        borderRadius: "4px",
      },
      "&::-webkit-scrollbar-thumb": {
        backgroundColor: "#595959",
        borderRadius: "20px",
      },
      "&::-webkit-scrollbar-thumb:hover": {
        backgroundColor: "#595959",
      },
    },
    "& .MuiDataGrid-pagination": {
      "& button.Mui-selected": {
        color: "#ffffff",
      },
      "& button.Mui-disabled": {
        color: "#ffffff",
      },
    },
  },
}));
function Results({ searchTerm }) {
  const [info, setInfo] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [filteredData, setFilteredData] = useState([]);
  const classes = useStyles();
  console.log(searchTerm);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8081/h2h_milestone_3/dataloading");
        setInfo(response.data);
        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);
  console.log(searchTerm);
  useEffect(() => {
    if (searchTerm === "") {
      // Reset filtered data when searchTerm is empty
      setFilteredData([]);
    } else {
      // Filter data based on the searchTerm
      const filteredRows = info.filter((row) => row.customerOrderId.toString() === searchTerm);
      setFilteredData(filteredRows);
    }
  }, [searchTerm, info]);
  if (isLoading) {
    return <p>Loading data...</p>;
  }
  console.log(typeof searchTerm);
  console.log(filteredData);
  return (
    <div style={{ height: 500, width: "100%", backgroundColor: "#666666" }}>
      <DataGrid
        classes={{
          columnHeader: classes.columnHeader,
        }}
        className={classes.dataGrid}
        rows={filteredData}
        columns={columns}
        getRowId={(row) => row.customerOrderId}
        pageSize={10}
        checkboxSelection
      />
    </div>
  );
}

export default Results;
