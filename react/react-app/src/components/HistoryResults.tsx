import React, { useState } from "react";
import { ExchangeType } from "../Types";
interface Props {
  data: ExchangeType[] | null;
  selectItem: (item: ExchangeType, index: number) => void;
  selectedIndex: number;
}

function HistoryResults({ data, selectItem, selectedIndex }: Props) {
  const getRow = (item: ExchangeType, index: number) => {
    return (
      item.date != "" && (
        <tr
          key={index}
          onClick={() => selectItem(item, index)}
          className={selectedIndex === index ? "table-active" : ""}
        >
          <td className="text-truncate">{item.date}</td>
          <td>{item.baseAmount}</td>
          <td>{item.fromCurrencyCode}</td>
          <td>{item.toCurrencyCode}</td>
          <td>{item.exchangeRate}</td>
          <td>{item.finalAmount}</td>
        </tr>
      )
    );
  };

  return (
    <table className="table">
      <thead>
        <tr>
          <th scope="col">Date</th>
          <th scope="col">Amount</th>
          <th scope="col">From</th>
          <th scope="col">To</th>
          <th scope="col">Rate</th>
          <th scope="col">Total</th>
        </tr>
      </thead>

      <tbody>
        {data != null && data.map((item, index) => getRow(item, index))}
      </tbody>
    </table>
  );
}

export default HistoryResults;
