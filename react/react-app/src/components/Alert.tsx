import { ReactNode } from "react";

interface Props {
  children: ReactNode;
}

const Alert = ({ children }: Props) => {
  return (
    <div className="alert alert-primary alert-dismissible" role="alert">
      {children}
      <button
        type="button"
        className="close"
        data-dismiss="alert"
        aria-label="Close"
      >
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
  );
};

export default Alert;
