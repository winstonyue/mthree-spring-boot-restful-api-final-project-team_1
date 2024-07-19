interface Props {
  rows: { code: String; commonName: String; exchangeRate: number }[];
  columns: { label: string; accessor: string }[];
  selectedCurrency: String;
}

function TableBody({ rows, columns, selectedCurrency }: Props) {
  const getMessage = () => {
    return rows.length === 0 && <p>No item found</p>;
  };

  const getRow = (
    item: {
      code: String;
      commonName: String;
      exchangeRate: number;
    },
    index: number
  ) => {
    return (
      item.code != selectedCurrency && (
        <tr key={index}>
          <td key={columns[0].accessor}>{item.code}</td>
          <td key={columns[1].accessor}>{item.commonName}</td>
          <td key={columns[2].accessor}>{item.exchangeRate}</td>
        </tr>
      )
    );
  };

  return (
    <tbody>
      {getMessage()}
      {rows.map((item, index) => getRow(item, index))}
    </tbody>
  );
}

export default TableBody;
