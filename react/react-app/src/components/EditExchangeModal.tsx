import React from "react";

interface Props {
  closeModal: () => void;
  currencies: string[];
  onPreview: () => void;
}

function EditExchangeModal({ closeModal, currencies, onPreview }: Props) {
  return (
    <div className="modalBackground">
      <div className="modalContainer">
        <button onClick={closeModal} className="btn-close"></button>
        <div className="title">
          <h3>Edit Exchange</h3>
        </div>
        <div className="body"></div>
        <div className="footer">
          <button className="btn btn-outline-primary" onClick={onPreview}>
            Preview
          </button>
        </div>
      </div>
    </div>
  );
}

export default EditExchangeModal;
