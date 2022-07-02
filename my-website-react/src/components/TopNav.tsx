import React, {Component} from 'react';
import {
    NavLink
} from "react-router-dom";

class TopNav extends Component<{}, {}> {

    render() {
        return (
            <>
                <nav>
                    <ul className="menu">
                        <li>
                            <NavLink to="/">Home</NavLink>
                        </li>
                        <li>
                            <NavLink to="/projects">Projects</NavLink>
                        </li>
                    </ul>
                </nav>
            </>
        );
    }
}

export default TopNav;