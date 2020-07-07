import React from "react";
import ReactDOM from "react-dom";
import App from "./containers/app/App";
import serviceWorker from './utils/serviceWorker';

ReactDOM.render(<App/>, document.getElementById("root"));
serviceWorker();