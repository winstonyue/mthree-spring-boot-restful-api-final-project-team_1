import { Fragment, useState } from "react";
import CurrencyTable from "./CurrencyTable";
import { render } from "react-dom";

//{ items: [], heading: string}
interface Props {
  items: string[];
  onSelectItem: (item: string) => void;
}

function ButtonGroup({ items, onSelectItem }: Props) {
  // Hooks
  const [selectedIndex, setSelectedIndex] = useState(0);

  const getMessage = () => {
    return items.length === 0 && <p>No item found</p>;
  };

  return (
    <Fragment>
      {getMessage()}
      <div
        id="buttonGroup"
        className="btn-group"
        role="group"
        aria-label="Basic example"
      >
        {items.map((item, index) => (
          <button
            className={
              selectedIndex === index
                ? "btn btn-outline-primary active"
                : "btn btn-outline-primary"
            }
            key={item}
            onClick={() => {
              setSelectedIndex(index);
              onSelectItem(item.toLowerCase());
            }}
          >
            {item}
          </button>
        ))}
      </div>
    </Fragment>
  );
}

export default ButtonGroup;
