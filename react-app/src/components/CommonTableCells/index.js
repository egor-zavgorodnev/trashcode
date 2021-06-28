import React, { Component } from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import data from './my-statement-data'

export default class CommonTableCells extends Component {

    render() {
        return (
            data.map((record) =>
                <tr key={record.id}>
                    <td>{this.formatDate(new Date(record.date))}</td>
                    <td>{this.formatTime(new Date(record.date))}</td>
                    <td>{record.type}</td>
                    {record.amount > 0 ? <td>{record.amount}</td> : <td></td>}
                    {record.amount < 0 ? <td>{record.amount}</td> : <td></td>}
                </tr>
            )
        )
    }

    formatDate(date) {

        var dd = date.getDate();
        if (dd < 10) dd = '0' + dd;

        var mm = date.getMonth() + 1;
        if (mm < 10) mm = '0' + mm;

        var yy = date.getFullYear() % 100;
        if (yy < 10) yy = '0' + yy;

        return dd + '.' + mm + '.' + yy;
    }

    formatTime(date) {

        var hh = date.getHours();
        if (hh < 10) hh = '0' + hh;

        var mm = date.getMinutes();
        if (mm < 10) mm = '0' + mm;

        var ss = date.getSeconds();
        if (ss < 10) ss = '0' + ss;

        return hh + ':' + mm + ':' + ss;
    }
}