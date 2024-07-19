export interface ExchangeType {
  id: number;
  username: string;
  fromCurrencyCode: string;
  toCurrencyCode: string;
  date: string;
  exchangeRate: number;
  baseAmount: number;
  finalAmount: number;
}

export interface NewExchangeType {
  username: string;
  fromCurrencyCode: string;
  toCurrencyCode: string;
  date: string;
  exchangeRate: number;
  baseAmount: number;
  finalAmount: number;
}

export interface UserInputType {
  date: string;
  username: string;
  currencyFrom: string;
  baseAmount: number;
  currencyTo: string;
}

export interface UserType {
  username: string;
}

export interface CurrencyTableRows {
  rows: {
    code: string;
    commonName: string;
    exchangeRate: number;
  }[];
}
