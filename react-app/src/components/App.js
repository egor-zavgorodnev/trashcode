import React, { Component } from 'react'
import Header from './Header/index'
import SettingPanel from './SettingsPanel/index'
import CommonTable from './CommonTable/index'
import TableWithDataGrouping from './TableWithDataGrouping/index'

const Table = Object.freeze({WITHOUT_GROUPING:"1", GROUP_BY_DATE:"2"}) 

export default class App extends Component {

    state = {
        tableForRender: Table.WITHOUT_GROUPING
    } 

    render() {
        console.log("changed to " + this.state.tableForRender)
        return (
            <div>
                <Header />
                <SettingPanel onTableChange={this.changeTable} />
                {this.state.tableForRender === Table.WITHOUT_GROUPING ? <CommonTable /> : <TableWithDataGrouping />}
            </div>
        )
    }

    changeTable = (tableForRender) => this.setState({ tableForRender: tableForRender })

}