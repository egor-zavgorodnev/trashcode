import React from 'react'
import 'bootstrap/dist/css/bootstrap.css'
import profileImg from './images/profile.png'
import './header.css'

export default function Header() {
    return (
        <header className="header">
            <div className="container">
                <div className="row header-content">
                    <div className="col-md-3">
                        <h1 className="header-content__title">Моя выписка</h1>
                    </div>
                    <div className="col-md-3 offset-6">
                        <div className="header-content__profile">
                            <img src={profileImg} alt="Фото пользователя"/><span>Иван Иванов</span>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    )
}