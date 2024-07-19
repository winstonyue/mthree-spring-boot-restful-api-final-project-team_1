import ButtonGroup from "./components/ButtonGroup";
import CurrencyTable from "./components/CurrencyTable";
import PageHeader from "./components/PageHeader";
import { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import ExchangeModal from "./components/ExchangeModal";
import ConfirmExchangeModal from "./components/ConfirmExchangeModal";
import ViewHistoryModal from "./components/ViewHistoryModal";
import ViewUsersModal from "./components/ViewUsersModal";
import "./App.css";

import {
  CurrencyTableRows,
  ExchangeType,
  NewExchangeType,
  UserInputType,
  UserType,
} from "./Types";

import {
  getRatesForSelectedCurrency,
  getSingleExchangeRate,
  createExchange,
  getAllUserExchanges,
  deleteExchange,
  getAllUsers,
  addUser,
  editUser,
  deleteUser,
} from "./APIWrapper";

const App = () => {
  const currencies = ["USD", "CAD", "GBP", "EUR", "CHF", "JPY"];

  const [selectedCurrency, setSelectedCurrency] = useState("usd");
  const [showExchangeModal, setShowExhangeModal] = useState(false);
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
  const [showHistoryModal, setShowHistoryModal] = useState(false);
  const [showUsersModal, setShowUsersModal] = useState(false);
  const [allUsers, setAllUsers] = useState<UserType[] | null>(null);
  const [currentRates, setCurrentRates] = useState<CurrencyTableRows | null>(
    null
  );
  const [previewExchange, setPreviewExchange] =
    useState<NewExchangeType | null>(null);

  useEffect(() => {
    setCurrentRates(null);
    const fetchData = async () => {
      setCurrentRates(
        await getRatesForSelectedCurrency(selectedCurrency).catch(
          (response) => {
            toast.error(response);
            return null;
          }
        )
      );
    };
    fetchData(); // Call the async function inside useEffect
  }, [selectedCurrency, getRatesForSelectedCurrency]);

  useEffect(() => {
    setAllUsers(null);
    const fetchData = async () => {
      setAllUsers(
        await getAllUsers().catch((response) => {
          toast.error(response);
          return null;
        })
      );
    };
    fetchData();
  }, [showUsersModal]);

  // Event Handlers
  const handleSelectItem = (item: string) => {
    var newCurrency = item.toLowerCase();
    setSelectedCurrency(newCurrency);
  };

  const handlePreviewExchange = (input: UserInputType | null) => {
    if (input != null) {
      setPreviewExchange(null);
      const newExchange = {
        username: input.username,
        fromCurrencyCode: input.currencyFrom,
        toCurrencyCode: input.currencyTo,
        date: input.date,
        exchangeRate: 0,
        baseAmount: input.baseAmount,
        finalAmount: 0,
      } as NewExchangeType;

      getSingleExchangeRate(input.currencyFrom, input.currencyTo)
        .then((res) => {
          newExchange.exchangeRate = res;

          newExchange.finalAmount =
            Math.round(
              newExchange.baseAmount * newExchange.exchangeRate * 100
            ) / 100;
          setPreviewExchange(newExchange);
        })
        .catch((response) => {
          toast.error(response);
        });
    }
    setShowExhangeModal(false);
    if (input != null) setShowConfirmationModal(true);
    else toast.error("Error creating exchange data!");
  };

  const handleDeleteExchange = (item: ExchangeType | null) => {
    setShowHistoryModal(false);
    if (item != null)
      deleteExchange(item.id).catch((response) => {
        toast.error(response);
      });
  };

  const handleEditExchange = (item: ExchangeType | null) => {
    setShowHistoryModal(false);
  };

  const handleSaveExchange = (item: NewExchangeType) => {
    setShowConfirmationModal(false);

    createExchange(item).catch((response) => {
      toast.error(response);
    });
  };

  const getUserExchanges = (user: string): Promise<ExchangeType[]> => {
    if (user === null || user.length === 0) {
      return Promise.reject("Username cannot be null");
    }
    return getAllUserExchanges(user).catch((response) => {
      toast.error(response);
      return Promise.reject(response);
    });
  };

  const handleAddUser = (user: string) => {
    addUser(user).catch((response) => {
      toast.error(response);
    });
    setShowUsersModal(false);
  };

  const handleEditUser = (oldUser: string, newUser: string) => {
    editUser(oldUser, newUser).catch((response) => {
      toast.error(response);
    });
    setShowUsersModal(false);
  };

  const handleDeleteUser = (user: string) => {
    deleteUser(user).catch((response) => {
      toast.error(response);
    });
    setShowUsersModal(false);
  };

  return (
    <div>
      {showExchangeModal && (
        <ExchangeModal
          currencies={currencies}
          closeModal={() => setShowExhangeModal(false)}
          onPreview={handlePreviewExchange}
        />
      )}
      {showConfirmationModal && previewExchange != null && (
        <ConfirmExchangeModal
          onExit={() => setShowConfirmationModal(false)}
          onSave={handleSaveExchange}
          data={previewExchange}
        />
      )}
      {showHistoryModal && (
        <ViewHistoryModal
          closeModal={() => setShowHistoryModal(false)}
          searchUserExchanges={getUserExchanges}
          handleDeleteItem={handleDeleteExchange}
          handleEditItem={handleEditExchange}
        />
      )}
      {showUsersModal && allUsers != null && (
        <ViewUsersModal
          closeModal={() => setShowUsersModal(false)}
          users={allUsers}
          handleDeleteItem={handleDeleteUser}
          handleEditItem={handleEditUser}
          handleAddItem={handleAddUser}
        />
      )}
      <PageHeader
        clickExchangeLink={() => setShowExhangeModal(true)}
        clickHistoryLink={() => setShowHistoryModal(true)}
        clickUsersLink={() => setShowUsersModal(true)}
      />
      <div className="landingPageMainContent">
        <ButtonGroup items={currencies} onSelectItem={handleSelectItem} />
        {currentRates != null && (
          <CurrencyTable
            rows={currentRates}
            selectedCurrency={selectedCurrency}
          />
        )}
      </div>
      <ToastContainer position="top-right" autoClose={5000} />
    </div>
  );
};

export default App;
