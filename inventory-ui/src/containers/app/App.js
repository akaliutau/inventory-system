import React, {Component} from "react";
import ReactTable from "react-table";
import "react-table/react-table.css";
import './css/App.css';
import axios from "axios";
import Snackbar from '@material-ui/core/Snackbar';
import Alert from './Alert.js';
import {SERVER_URL} from './constants.js';
import {INVENTORY_COLUMNS} from './columns.js';
import SearchAppBar from './AppBar';


class App extends Component {
    constructor(props) {
        super(props);
        //Initial data from API
        this.state = {
            data: []
        };
        axios.get(SERVER_URL + 'api/v1/items?page=0')
            .then(res => {
                // Update react-table
                this.setState({
                    posts: res.data,
                    data: res.data.content,
                    pages: res.data.totalPages,
                    loading: false
                });
            })
            .catch(err => {
                this.setState({open: true, message: 'Error when connecting to server. Original message: ' + err});
                console.error('Error: ' + err)
            });
    }

    handleClose = (event, reason) => {
        this.setState({open: false});
    };


    render() {

        return (
            <div className="App">
                <SearchAppBar/>

                <ReactTable
                    columns={INVENTORY_COLUMNS}
                    data={this.state.data}
                    pages={this.state.pages}
                    loading={this.state.loading}
                    filterable
                    onPageChange={pageIndex => {
                        console.log("requesting page: " + pageIndex);
                        axios.get(SERVER_URL + 'api/v1/items?page=' + pageIndex)
                            .then(res => {
                                // Update react-table
                                this.setState({
                                    posts: res.data,
                                    data: res.data.content,
                                    pages: res.data.totalPages,
                                    loading: false
                                });
                            });
                    }}
                    defaultPageSize={5}
                    noDataText={"Loading..."}
                    manual // server-side sorting and pagination
                    pageSizeOptions={[5]}
                    showPageSizeOptions={true}

                />
                <Snackbar
                    open={this.state.open} onClose={this.handleClose}
                    autoHideDuration={3500}>
                    <Alert onClose={this.handleClose} severity="error">{this.state.message}</Alert>
                </Snackbar>

            </div>
        );
    }

}


export default App;
