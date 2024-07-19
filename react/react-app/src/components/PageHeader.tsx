import { Fragment } from "react";

interface Props {
  clickExchangeLink: () => void;
  clickHistoryLink: () => void;
  clickUsersLink: () => void;
}

function PageHeader({
  clickExchangeLink,
  clickHistoryLink,
  clickUsersLink,
}: Props) {
  return (
    <Fragment>
      <nav className="navbar navbar-expand-lg bg-body-tertiary">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">
            ForexSphere
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <a
                  className="nav-link"
                  aria-current="page"
                  onClick={clickExchangeLink}
                >
                  New Exchange
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" onClick={clickHistoryLink}>
                  See History
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" onClick={clickUsersLink}>
                  Users
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </Fragment>
  );
}

export default PageHeader;
