import React, { Fragment } from "react";
import { UserInputType } from "../Types";

interface Props {
  currencies: string[];
  onSubmitHandler: (input: UserInputType) => void;
}

function ExchangeModalBody({ currencies, onSubmitHandler }: Props) {
  const handleFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const form = event.currentTarget;
    const username = form.elements.namedItem("user") as HTMLInputElement;
    const fromCurrency = form.elements.namedItem(
      "fromCurrency"
    ) as HTMLInputElement;
    const toCurrency = form.elements.namedItem(
      "toCurrency"
    ) as HTMLInputElement;
    const amount = form.elements.namedItem("amount") as HTMLInputElement;

    const input = {
      date: new Date().toISOString().substring(0, 10),
      username: username.value,
      currencyFrom: fromCurrency.value.toLowerCase(),
      baseAmount: amount.valueAsNumber,
      currencyTo: toCurrency.value.toLowerCase(),
    } as UserInputType;

    onSubmitHandler(input);
  };

  return (
    <Fragment>
      <form onSubmit={handleFormSubmit}>
        <div className="input-group mb-3">
          <input
            name="user"
            type="text"
            required={true}
            className="form-control"
            maxLength={10}
            placeholder="Username"
            aria-label="Username"
            aria-describedby="basic-addon1"
          />
        </div>
        <div className="selectCurrencies">
          <div className="input-group mb-3">
            <select
              className="form-select"
              aria-label="Default select example"
              id="fromCurrency"
              name="fromCurrency"
            >
              {currencies.map((item) => (
                <option key={item}>{item}</option>
              ))}
            </select>
            <span className="input-group-text">&#8594;</span>
            <select
              className="form-select"
              aria-label="Default select example"
              id="toCurrency"
              name="toCurrency"
            >
              {currencies.map((item) => (
                <option key={item}>{item}</option>
              ))}
            </select>
          </div>
        </div>
        <div className="input-group mb-3" id="exchangeAmount">
          <span className="input-group-text">$</span>
          <input
            name="amount"
            type="number"
            step=".01"
            min="0.01"
            required={true}
            className="form-control"
            placeholder="000000.00"
            aria-label="Dollar amount (with dot and two decimal places)"
          />
        </div>
        <button className="btn btn-outline-primary" type="submit">
          Preview
        </button>
      </form>
    </Fragment>
  );
}

export default ExchangeModalBody;
