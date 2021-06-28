import React, { Component } from 'react'
import './style.css'
import 'bootstrap/dist/css/bootstrap.css'
import CommonTableCells from '../CommonTableCells/index'

export default class CommonTable extends Component {
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
                                            <td>Время</td>
                                            <td>Тип</td>
                                            <td>Приход</td>
                                            <td>Расход</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <CommonTableCells />
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        )
    }
}