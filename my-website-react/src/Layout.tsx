import React, {Component} from 'react';
import TopNav from "./components/TopNav";
import {Outlet} from "react-router-dom";
import Logo from "./components/Logo";

class Layout extends Component {
    render() {
        return (
            <div className="flex col">
                {/*  Top bar  */}
                <div className="flex">
                    {/*logo区域*/}
                    <Logo/>
                    <TopNav/>
                </div>
                <main className="flex1">
                    <Outlet/>
                </main>
            </div>
        );
    }
}

export default Layout;