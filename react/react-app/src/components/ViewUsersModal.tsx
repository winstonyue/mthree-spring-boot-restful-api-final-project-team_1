import { useState } from "react";
import "./CustomModal.css";
import { UserType } from "../Types";

interface Props {
  closeModal: () => void;
  users: UserType[];
  handleEditItem: (oldUser: string, newUser: string) => void;
  handleDeleteItem: (user: string) => void;
  handleAddItem: (user: string) => void;
}

function ViewUsersModal({
  closeModal,
  users,
  handleDeleteItem,
  handleEditItem,
  handleAddItem,
}: Props) {
  const [selectedItem, setSelectedItem] = useState<string>("");
  const [selectedIndex, setSelectedIndex] = useState(-1);
  const [showUserBox, setShowUserBox] = useState(false);

  const selectItem = (item: string, index: number) => {
    if (index === selectedIndex) {
      setSelectedIndex(-1);
      setSelectedItem("");
    } else {
      setSelectedIndex(index);
      setSelectedItem(item);
    }
  };

  const confirmDelete = () => {
    if (confirm("Really delete this user? This is irreversible.")) {
      handleDeleteItem(selectedItem);
    }
  };

  const handleFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const form = event.currentTarget;
    const input = form.elements.namedItem("user") as HTMLInputElement;
    if (selectedIndex === -1) {
      handleAddItem(input.value);
    } else {
      handleEditItem(selectedItem, input.value);
    }
    setShowUserBox(false);
  };

  return (
    <div className="modalBackground">
      <div className="modalContainer">
        <button onClick={closeModal} className="btn-close"></button>
        <div className="title">
          <h3> All Users </h3>
        </div>
        <div className="body">
          <div className="userTable">
            <table className="table fs-5">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Username</th>
                </tr>
              </thead>
              <tbody>
                {users != null &&
                  users.map((item, index) => {
                    return (
                      <tr
                        className={
                          selectedIndex === index ? "table-active" : ""
                        }
                        onClick={() => selectItem(item.username, index)}
                      >
                        <th scope="row">{index}</th>
                        <td>{item.username}</td>
                      </tr>
                    );
                  })}
              </tbody>
            </table>
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
          <button
            type="button"
            className="btn btn-outline-warning"
            disabled={selectedIndex === -1}
            onClick={() => setShowUserBox(true)}
          >
            Edit
          </button>
          <button
            type="button"
            className="btn btn-outline-primary"
            disabled={selectedIndex !== -1}
            onClick={() => setShowUserBox(true)}
          >
            Add
          </button>
          {showUserBox && (
            <form onSubmit={handleFormSubmit}>
              <input
                className="form-control me-2"
                type="text"
                required={true}
                maxLength={10}
                placeholder="New Username"
                name="user"
              />
              <button className="btn btn-outline-success" type="submit">
                &#10003;
              </button>
            </form>
          )}
        </div>
      </div>
    </div>
  );
}

export default ViewUsersModal;
