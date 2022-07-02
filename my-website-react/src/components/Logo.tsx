import React, {Component} from 'react';
import {NavLink} from "react-router-dom";

class Logo extends Component {
    render() {
        return (
            <div className="logo flex2">
                <NavLink to="" className="link">
                    leo's blog
                </NavLink>
            </div>
        );
    }
}

export default Logo;