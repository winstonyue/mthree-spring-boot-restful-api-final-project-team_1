import React, { useState } from "react";
import HistoryResults from "./HistoryResults";
import "./CustomModal.css";
import { ExchangeType } from "../Types";

interface Props {
  closeModal: () => void;
  searchUserExchanges: (user: string) => Promise<ExchangeType[]>;
  handleEditItem: (item: ExchangeType | null) => void;
  handleDeleteItem: (item: ExchangeType | null) => void;
}

function ViewHistoryModal({
  closeModal,
  searchUserExchanges,
  handleDeleteItem,
  handleEditItem,
}: Props) {
  const [historyData, setHistoryData] = useState<ExchangeType[] | null>(null);
  const [selectedItem, setSelectedItem] = useState<ExchangeType | null>(null);
  const [selectedIndex, setSelectedIndex] = useState(-1);

  const selectItem = (item: ExchangeType, index: number) => {
    setSelectedIndex(index);
    setSelectedItem(item);
  };

  const handleFormSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const form = event.currentTarget;
    const input = form.elements.namedItem("user") as HTMLInputElement;
    setHistoryData(
      await searchUserExchanges(input.value).catch(() => {
        return null;
      })
    );
  };

  const resetDataAndClose = () => {
    setHistoryData(null);
    closeModal();
  };

  const confirmDelete = () => {
    if (confirm("Really delete this exchange? This is irreversible.")) {
      handleDeleteItem(selectedItem);
    }
  };

  return (
    <div className="modalBackground">
      <div className="modalContainer">
        <button onClick={resetDataAndClose} className="btn-close"></button>
        <div className="title">
          <h3> Search Exchanges By User </h3>
        </div>
        <div className="body">
          <div className="searchUserForm">
            <form className="d-flex" role="search" onSubmit={handleFormSubmit}>
              <input
                className="form-control me-2"
                type="search"
                placeholder="Search"
                maxLength={10}
                aria-label="Search"
                name="user"
              />
              <button className="btn btn-outline-success" type="submit">
                Search
              </button>
            </form>
          </div>
          <div className="historyResults">
            <HistoryResults
              data={historyData}
              selectItem={selectItem}
              selectedIndex={selectedIndex}
            />
          </div>
        </div>
        <div className="footer">
          <button
            type="button"
            className="btn btn-outline-danger"
            disabled={selectedIndex === -1}
            onClick={confirmDelete}
          >
            Delete
          </button>
          {/* <button
            type="button"
            className="btn btn-outline-warning"
            disabled={selectedIndex === -1}
            onClick={() => handleEditItem(selectedItem)}
          >
            Edit
          </button> */}
        </div>
      </div>
    </div>
  );
}

export default ViewHistoryModal;
