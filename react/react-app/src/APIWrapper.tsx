import {
  CurrencyTableRows,
  ExchangeType,
  NewExchangeType,
  UserType,
} from "./Types";

export async function getRatesForSelectedCurrency(
  currencyCode: string
): Promise<CurrencyTableRows> {
  const url =
    "https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/" +
    currencyCode +
    ".json";

  const request: RequestInfo = new Request(url, {
    method: "GET",
  });

  try {
    return await fetch(request)
      .then((res) => res.json())
      .then((res) => {
        let currencyKey = currencyCode as keyof typeof res;
        let exchangeRates = res[currencyKey];
        const result = {
          rows: [
            {
              code: "usd",
              commonName: "US Dollar",
              exchangeRate: exchangeRates["usd"],
            },
            {
              code: "cad",
              commonName: "Canada Dollar",
              exchangeRate: exchangeRates["cad"],
            },
            {
              code: "eur",
              commonName: "Euro",
              exchangeRate: exchangeRates["eur"],
            },
            {
              code: "gbp",
              commonName: "British Pound",
              exchangeRate: exchangeRates["gbp"],
            },
            {
              code: "chf",
              commonName: "Swiss Franc",
              exchangeRate: exchangeRates["chf"],
            },
            {
              code: "jpy",
              commonName: "Japanese Yen",
              exchangeRate: exchangeRates["jpy"],
            },
          ],
        };
        return result as CurrencyTableRows;
      });
  } catch (error: any) {
    return Promise.reject("Could not get data from server");
  }
}

export async function getSingleExchangeRate(
  currencyFrom: string,
  currencyTo: string
): Promise<number> {
  const url =
    "https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/" +
    currencyFrom +
    ".json";

  const request: RequestInfo = new Request(url, {
    method: "GET",
  });

  try {
    return await fetch(request)
      .then((res) => res.json())
      .then((res) => {
        let currencyKeyA = currencyFrom as keyof typeof res;
        let exchangeRates = res[currencyKeyA];

        let currencyKeyB = currencyTo as keyof typeof exchangeRates;
        return exchangeRates[currencyKeyB];
      });
  } catch (error: any) {
    return Promise.reject("Could not get data from server");
  }
}

export async function createExchange(exchange: NewExchangeType) {
  const url = "http://127.0.0.1:8080/exchange/add";

  const request: RequestInfo = new Request(url, {
    method: "POST",
    body: JSON.stringify(exchange),
    headers: { "Content-Type": "application/json" },
  });

  try {
    await fetch(request);
  } catch (error: any) {
    return Promise.reject("API Error creating exchange.");
  }
}

export async function getAllUserExchanges(
  username: string
): Promise<ExchangeType[]> {
  const url = "http://127.0.0.1:8080/exchange/username/" + username;

  const request: RequestInfo = new Request(url, {
    method: "GET",
  });

  try {
    return await fetch(request)
      .then((res) => res.json())
      .then((res) => {
        return res as ExchangeType[];
      });
  } catch (error: any) {
    return Promise.reject("API Error getting user exchanges");
  }
}

export async function deleteExchange(id: number) {
  const url = "http://127.0.0.1:8080/exchange/" + id;

  const request: RequestInfo = new Request(url, {
    method: "DELETE",
  });

  try {
    return await fetch(request);
  } catch (error: any) {
    return Promise.reject("API error deleting exchange");
  }
}

export async function addUser(username: string) {
  const url = "http://127.0.0.1:8080/user/add";

  const newUser = {
    username: username,
  } as UserType;

  const request: RequestInfo = new Request(url, {
    method: "POST",
    body: JSON.stringify(newUser),
    headers: { "Content-Type": "application/json" },
  });

  try {
    return await fetch(request);
  } catch (error: any) {
    return Promise.reject("API error creating user");
  }
}

export async function deleteUser(username: string) {
  const url = "http://127.0.0.1:8080/user/" + username;

  const request: RequestInfo = new Request(url, {
    method: "DELETE",
  });

  try {
    return await fetch(request);
  } catch (error: any) {
    return Promise.reject("API error deleting user");
  }
}

export async function editUser(oldUsername: string, newUsername: string) {
  const url = "http://127.0.0.1:8080/user/" + oldUsername;

  const newUser = {
    username: newUsername,
  } as UserType;

  const request: RequestInfo = new Request(url, {
    method: "PUT",
    body: JSON.stringify(newUser),
    headers: { "Content-Type": "application/json" },
  });

  try {
    return await fetch(request);
  } catch (error: any) {
    return Promise.reject("API error updating user");
  }
}

export async function getAllUsers(): Promise<UserType[]> {
  const url = "http://127.0.0.1:8080/user/all";

  const request: RequestInfo = new Request(url, {
    method: "GET",
  });

  try {
    return await fetch(request)
      .then((res) => res.json())
      .then((res) => {
        return res as UserType[];
      });
  } catch (error: any) {
    return Promise.reject("API error getting users");
  }
}
