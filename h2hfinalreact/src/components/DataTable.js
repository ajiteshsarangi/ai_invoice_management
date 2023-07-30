import React, { useState, useEffect } from "react";
import { DataGrid } from "@mui/x-data-grid";
import { makeStyles } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import "../Modal.css";
import axios from "axios";

const columns = [
  { field: "slNo", headerName: "Sl No", width: 100 },
  { field: "customerOrderId", headerName: "Customer Order ID", width: 150 },
  { field: "salesOrg", headerName: "Sales Org", width: 130 },
  { field: "distributionChannel", headerName: "Distribution Channel", width: 200 },
  { field: "companyCode", headerName: "Company Code", width: 160 },
  { field: "orderCreationDate", headerName: "Order Creation Date", width: 200 },
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

function DataTable() {
  const [info, setInfo] = useState();
  const [isLoading, setIsLoading] = useState(true);
  const [selectedRows, setSelectedRows] = useState([]);
  const [modal, setModal] = useState(false);
  const [modal2, setModal2] = useState(false);
  const [pageSize, setPageSize] = React.useState(5);
  const classes = useStyles();
  // **************************************************************************************************
  // *  The following is the working code for data fetching from the API endpoint, I have commented   *
  // *  it out here and displayed the static data.                                                    *
  // **************************************************************************************************

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8081/h2h_milestone_3/dataloading");
        const limitedData = response.data.slice(0, 10000);
        setInfo(limitedData);
        // setInfo(response.data);
        setIsLoading(false);
      } catch (error) {
        console.log(error);
      }
    };

    fetchData();
  }, []);
  //************ Modal ************* */
  const toggleModal = () => {
    setModal(!modal);
  };

  if (modal) {
    document.body.classList.add("active-modal");
  } else {
    document.body.classList.remove("active-modal");
  }
  // 2nd modal
  const toggleModal2 = () => {
    setModal2(!modal2);
  };

  if (modal2) {
    document.body.classList.add("active-modal");
  } else {
    document.body.classList.remove("active-modal");
  }
  //************************* */
  const [reload, setReload] = useState(false);

  // Function to trigger the reload
  const handleReload = () => {
    window.location.reload();
    setReload(!reload);
  };
  // **************************
  if (isLoading) {
    return <p>Loading data...</p>;
  }
  const output = selectedRows.join(", ");
  console.log(typeof output);
  console.log(output);
  return (
    <div style={{ height: 500, width: "100%", backgroundColor: "#666666" }}>
      <DataGrid
        classes={{
          columnHeader: classes.columnHeader,
        }}
        className={classes.dataGrid}
        rows={info}
        columns={columns}
        getRowId={(row) => row.customerOrderId}
        pageSize={pageSize}
        onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
        rowsPerPageOptions={[5, 10, 20, 50, 100]}
        pagination
        checkboxSelection
        onSelectionModelChange={(newSelection) => {
          setSelectedRows(newSelection);
        }}
        key={reload.toString()}
      />
      <div className="gridBtnsContainer">
        <Button className="gridBtn" onClick={handleReload} variant="contained">
          Refresh
        </Button>
        {selectedRows.length == 1 ? (
          <Button className="gridBtn" variant="contained" onClick={toggleModal2}>
            edit
          </Button>
        ) : (
          <Button disabled className="gridBtnD" variant="contained">
            edit
          </Button>
        )}
        {selectedRows.length != 0 ? (
          <Button
            variant="contained"
            onClick={toggleModal}
            className="btn-modal gridBtn"
            style={{ marginRight: "10px" }}
          >
            delete
          </Button>
        ) : (
          <Button
            disabled
            variant="contained"
            onClick={toggleModal}
            className="btn-modal"
            style={{ marginRight: "10px" }}
          >
            delete
          </Button>
        )}
        {selectedRows.length == 1 ? (
          <Button className="gridBtn" variant="contained">
            predict
          </Button>
        ) : (
          <Button className="gridBtnD" variant="contained" disabled>
            predict
          </Button>
        )}
      </div>

      {modal && (
        <div className="modal">
          <div onClick={toggleModal} className="overlay"></div>
          <div className="modal-content">
            <h2>Delete Records ?</h2>
            <br />
            <p>Are you sure you want to delete these record[s] ?</p>
            <form action="http://localhost:8081/h2h_milestone_3/DeleteServlet" method="POST">
              <input
                type="text"
                id="customerOrderIds"
                name="customerOrderId"
                multiple
                value={selectedRows}
                style={{ display: "none" }}
              />

              <button type="submit" className="delete-invoice">
                DELETE
              </button>
            </form>
            <button className="close-modal" onClick={toggleModal}>
              CLOSE
            </button>
          </div>
        </div>
      )}
      {/* *************** Edit modal ************** */}
      {modal2 && (
        <div className="modal">
          <div onClick={toggleModal2} className="overlay"></div>
          <div className="modal-content2">
            <h2>Edit</h2>
            <br />

            <form action="http://localhost:8081/h2h_milestone_3/editData" method="POST">
              <input
                type="text"
                id="customerOrderId"
                name="customerOrderId"
                multiple
                value={selectedRows}
                style={{ display: "none" }}
              />
              <div className="flexC">
                <div className="flex">
                  <input
                    type="number"
                    id="customerOrderId"
                    name="customerOrderId"
                    value={selectedRows}
                    style={{ display: "none" }}
                  />
                  <TextField
                    type="text"
                    id="orderCurrency"
                    name="orderCurrency"
                    required
                    label="Order Currency"
                    variant="outlined"
                    style={{ margin: "0 10px 0 0" }}
                  />
                  <TextField
                    type="number"
                    id="companyCode"
                    name="companyCode"
                    required
                    label="Company Code"
                    variant="outlined"
                    style={{ margin: "0 0 0 10px" }}
                  />
                </div>
                <TextField
                  type="text"
                  id="distributionChannel"
                  name="distributionChannel"
                  required
                  label="Distribution Channel"
                  variant="outlined"
                  style={{ margin: "20px 0 0 0" }}
                />
              </div>

              <button type="submit" className="edit-invoice">
                EDIT
              </button>
            </form>
            <button className="cancel-modal" onClick={toggleModal2}>
              CANCEL
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default DataTable;
