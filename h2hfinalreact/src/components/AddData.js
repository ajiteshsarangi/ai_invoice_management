import React, { useState } from "react";

import Button from "@material-ui/core/Button";

function AddData() {
  const [reload, setReload] = useState(false);

  // Function to trigger the reload
  const handleReload = () => {
    setReload(!reload);
  };
  return (
    <div>
      <form action="http://localhost:8081/h2h_milestone_3/add" method="POST">
        <div className="super-container">
          <div className="container">
            <div className="subc flex justifySpace">
              <div>
                <input
                  type="number"
                  className="item"
                  placeholder="CUSTOMER ORDER ID"
                  name="customerOrderId"
                  id="customerOrderId"
                  key={reload.toString()}
                />
              </div>
              <div>
                <input
                  type="number"
                  className="item"
                  placeholder="SALES ORG"
                  name="salesOrg"
                  id="salesOrg"
                  key={reload.toString()}
                />
              </div>
            </div>
            <div className="subc flex justifySpace">
              <div>
                <input
                  type="text"
                  className="item"
                  placeholder="CUSTOMER NUMBER"
                  name="customerNumber"
                  id="customerNumber"
                  key={reload.toString()}
                />
              </div>
              <div>
                <input
                  type="number"
                  className="item"
                  placeholder="COMPANY CODE"
                  name="companyCode"
                  id="companyCode"
                  key={reload.toString()}
                />
              </div>
            </div>
            <div>
              <Button
                type="submit"
                variant="contained"
                className="item5"
                style={{ backgroundColor: "#fc7500" }}
              >
                ADD
              </Button>
            </div>
          </div>
          <div className="container2">
            <div>
              <input
                type="text"
                className="item4"
                placeholder="DISTRIBUTION CHANNEL"
                name="distributionChannel"
                id="distributionChannel"
                key={reload.toString()}
              />
            </div>

            <div className="subc flex justifySpace">
              <div>
                <input
                  type="text"
                  className="item3"
                  placeholder="ORDER CURRENCY"
                  name="orderCurrency"
                  id="orderCurrency"
                  key={reload.toString()}
                />
              </div>
              <div>
                <input
                  type="number"
                  className="item3"
                  placeholder="AMOUNT IN USD"
                  name="amountInUSD"
                  id="amountInUSD"
                  key={reload.toString()}
                />
              </div>
              <div>
                <input
                  type="date"
                  className="item3"
                  placeholder="ORDER CREATION DATE"
                  name="orderCreationDate"
                  id="orderCreationDate"
                  key={reload.toString()}
                />
              </div>
            </div>

            <div>
              <Button
                onClick={handleReload}
                variant="contained"
                className="item5"
                style={{ backgroundColor: "#db4437" }}
              >
                CLEAR DATA
              </Button>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
}

export default AddData;
