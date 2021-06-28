import React, { Component } from 'react'
import './style.css'
import 'bootstrap/dist/css/bootstrap.css'
import TableWithDataGroupingCells from '../TableWithDataGroupingCells/index'

export default class TableWithDataGrouping extends Component {
    render() {
        return (
            <section className="table">
                <div className="container">
                    <div className="row">
                        <div className="col-12">
                            <div className="table-block">
                                <table id="myTable" width="100%" border="1">
                                    <thead>
                                        <tr>
                                            <td>Дата</td>
                                            <td>Сумма операций</td>
                                        </tr>
                                    </thead>
                                    <TableWithDataGroupingCells/>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        )
    }
}