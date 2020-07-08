import React from "react";
import ReactDOM from "react-dom";
import App from "./App/App";
import serviceWorker from './utils/serviceWorker';

ReactDOM.render(<App/>, document.getElementById("root"));
serviceWorker();