import React, {Component} from 'react';
import './App.css';
import Main from "./components/Main";
import {Projects} from "./components/Projects";
import {
    Routes,
    Route,
} from "react-router-dom";
import {Post} from "./components/Post";
import Layout from "./Layout";
import CampusPaths from "./components/projects/campuspaths/CampusPaths";


class App extends Component<{}, {}> {
    render() {
        return (
            <Routes>
                <Route element={<Layout/>}>
                    <Route index element={<Main/>}/>
                    <Route path="projects" element={<Projects/>}/>
                    <Route path="post" element={<Post/>}/>
                    <Route path="projects/campus-paths" element={<CampusPaths/>}/>
                </Route>
            </Routes>
        );
    }
}

export default App;
