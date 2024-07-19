import { Fragment, useState } from "react";
import TableHead from "./CurrencyTableHead";
import TableBody from "./CurrencyTableBody";
import { CurrencyTableRows } from "../Types";

//{ items: [], heading: string}
interface Props {
  rows: CurrencyTableRows;
  selectedCurrency: String;
}

function CurrencyTable({ rows, selectedCurrency }: Props) {
  // Hooks

  const [tableData, setTableData] = useState(rows.rows);

  const columns = [
    { label: "Currency Code", accessor: "code" },
    { label: "Common Name", accessor: "commonName" },
    { label: "Exchange Rate", accessor: "exchangeRate" },
  ];

  const handleSorting = (sortField: string, sortOrder: string) => {
    if (sortField) {
      let fieldName = sortField as keyof (typeof rows.rows)[0];
      const sorted = [...tableData].sort((a, b) => {
        if (a[fieldName] === null) return 1;
        if (b[fieldName] === null) return -1;
        if (a[fieldName] === null && b[fieldName] === null) return 0;
        return (
          a[fieldName].toString().localeCompare(b[fieldName].toString(), "en", {
            numeric: true,
          }) * (sortOrder === "asc" ? 1 : -1)
        );
      });
      setTableData(sorted);
    }
  };

  return (
    <Fragment>
      <table className="table">
        <TableHead columns={columns} handleSorting={handleSorting} />
        <TableBody
          columns={columns}
          rows={tableData}
          selectedCurrency={selectedCurrency}
        />
      </table>
    </Fragment>
  );
}

export default CurrencyTable;
