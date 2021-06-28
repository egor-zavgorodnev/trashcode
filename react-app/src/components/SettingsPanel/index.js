import React, { Component } from 'react'
import './style.css'
import 'bootstrap/dist/css/bootstrap.css'


export default class SettingPanel extends Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        console.log(event.target.value)
        const { onTableChange } = this.props
        onTableChange(event.target.value)
    }

    render() {
        return (
            <section className="panel">
                <div className="container">
                    <div className="row">
                        <div className="col-12">
                            <div className="panel-group">
                                <label>Группировка</label>
                                <select id="panel-group__type-select" onChange={this.handleChange}>
                                    <option value={1}>Без группировки</option>
                                    <option value={2}>По дате</option>
                                </select>
                                <a id="show_link" href="#">Показать все колонки</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        )
    }
}