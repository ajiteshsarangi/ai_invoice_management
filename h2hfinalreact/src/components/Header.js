import React from "react";
import hrclogo from "../img/hrclogo.svg";
import abclogo from "../img/abclogo.svg";
function Header() {
  return (
    <div className="m-20">
      <div className="header">
        <div>
          <img src={abclogo} alt="ABC Logo" />
        </div>
        <div className="center">
          <img src={hrclogo} alt="HRC Logo" />
        </div>
      </div>
      <div>
        <h1 className="list-color">Invoice List</h1>
      </div>
    </div>
  );
}

export default Header;
