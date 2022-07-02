import React, {Component} from 'react';
import Blog from "./Blog";
import {BlogObj} from "../types";
import {NavLink} from "react-router-dom";
import 'animate.css';

interface MainState {
    blogList: BlogObj[]
}

const numLatestBlogs = 5;

class Main extends Component<{}, MainState> {
    constructor(props: any) {
        super(props);
        this.state = {
            blogList: []
        }
    }

    async componentDidMount() {
        try {
            let response = await fetch("http://localhost:4567/readBlogs")
            let blogs = (await response.json()) as BlogObj[];
            console.log(blogs);
            this.setState({
                blogList: blogs
            })
        } catch (e) {
            alert("There was an error contacting the server");
            console.log(e);
        }
    }

    render() {
        return (
            <div className="flex1 flex col">
                {/*introduction*/}
                <div id="introduction">
                    <p>Hi, I'm Zhihao Li a.k.a Leo.</p>
                    <p>Welcome to my page!</p>
                </div>
                <nav>
                    {this.renderLatestBlogs()}
                </nav>

            </div>
        );
    }

    // Render Blogs to DOM. Each Blog is inside a NavLink.
    renderLatestBlogs() {
        let toRender: JSX.Element[] = []; // need to render a list of Blogs
        for (let i = 0; i < numLatestBlogs; i++) {
            if (this.state.blogList[i]) {
                toRender.push(
                    <NavLink className="link" to={"/post/" + this.state.blogList[i].title}
                             state={{blog: this.state.blogList[i]}} key={this.state.blogList[i].title}>
                        <Blog blog={this.state.blogList[i]}/>
                    </NavLink>
                )
            }
        }
        return toRender;
    }
}

export default Main;