import { useState } from "react";

interface Props {
  columns: { label: string; accessor: string }[];
  handleSorting: (accessor: string, label: string) => void;
}

function TableHead({ columns, handleSorting }: Props) {
  const [sortField, setSortField] = useState("");
  const [order, setOrder] = useState("asc");

  const handleSortingChange = (accessor: string) => {
    const sortOrder =
      accessor === sortField && order === "asc" ? "desc" : "asc";
    setSortField(accessor);
    setOrder(sortOrder);
    handleSorting(accessor, sortOrder);
  };

  return (
    <thead>
      <tr>
        {columns.map(({ label, accessor }) => {
          return (
            <th key={accessor} onClick={() => handleSortingChange(accessor)}>
              {label}
            </th>
          );
        })}
      </tr>
    </thead>
  );
}

export default TableHead;
