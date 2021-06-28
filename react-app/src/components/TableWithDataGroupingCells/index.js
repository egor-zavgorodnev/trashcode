import React, { Component } from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import data from './my-statement-data'

export default class TableWithDataGroupingCells extends Component {

    render() {
        var groupedStatementData = data.reduce((acc, n) => {
            let obj = acc.find(
                m => new Date(m.date).toLocaleDateString("ru-RU") === new Date(n.date).toLocaleDateString("ru-RU")
                );
            if (!obj) {  
              acc.push(obj = { _id: n._id, date: n.date, type: n.type, amount: 0 }); 
            }  
            obj.amount += n.amount;    
            return acc; 
          }, []); 
        return (
            groupedStatementData.map((record) =>
                <tr key={record.id}>
                    <td>{this.formatDate(new Date(record.date))}</td>
                    <td>{record.amount}</td>
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