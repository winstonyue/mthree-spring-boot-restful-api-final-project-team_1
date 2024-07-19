import React from "react";
import "./CustomModal.css";
import { NewExchangeType } from "../Types";

interface Props {
  onSave: (exchange: NewExchangeType) => void;
  onExit: () => void;
  data: NewExchangeType;
}

function ConfirmExchangeModal({ onSave, onExit, data }: Props) {
  if (data != null) {
    return (
      <div className="modalBackground">
        <div className="modalContainer">
          <button onClick={onExit} className="btn-close"></button>
          <div className="title">
            <h3> Confirm Exchange Details? </h3>
          </div>
          <div className="body">
            <dl className="row">
              <dt className="col-sm-3 fs-4">Date</dt>
              <dd className="col-sm-9 fs-4 text-truncate">{data.date}</dd>

              <dt className="col-sm-3 fs-4">User</dt>
              <dd className="col-sm-9 fs-4">{data.username}</dd>

              <dt className="col-sm-3 fs-4">Exchange</dt>
              <dd className="col-sm-9 fs-4">
                {data.baseAmount}
                {data.fromCurrencyCode} to {data.toCurrencyCode} at rate{" "}
                {data.exchangeRate}
              </dd>

              <dt className="col-sm-3 fs-4">Total</dt>
              <dd className="col-sm-9 fs-4">
                {data.finalAmount}
                {data.toCurrencyCode}
              </dd>
            </dl>
          </div>
          <div className="footer">
            <button className="btn btn-outline-danger" onClick={onExit}>
              Cancel
            </button>
            <button
              className="btn btn-outline-primary"
              onClick={() => onSave(data)}
            >
              Confirm
            </button>
          </div>
        </div>
      </div>
    );
  } else {
    <div>No Content</div>;
  }
}

export default ConfirmExchangeModal;
