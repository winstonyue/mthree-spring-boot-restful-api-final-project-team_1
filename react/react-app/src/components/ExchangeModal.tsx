import React, { useState } from "react";
import "./CustomModal.css";
import ExchangeModalBody from "./ExchangeModalBody";
import { UserInputType } from "../Types";
interface Props {
  closeModal: () => void;
  currencies: string[];
  onPreview: (input: UserInputType | null) => void;
}
function ExchangeModal({ closeModal, currencies, onPreview }: Props) {
  return (
    <div className="modalBackground">
      <div className="modalContainer">
        <button onClick={closeModal} className="btn-close"></button>
        <div className="title">
          <h3> Create New Exchange </h3>
        </div>
        <div className="body">
          <ExchangeModalBody
            currencies={currencies}
            onSubmitHandler={(userinput: UserInputType) => {
              onPreview(userinput);
            }}
          />
        </div>
        <div className="footer"></div>
      </div>
    </div>
  );
}

export default ExchangeModal;
