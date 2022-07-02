import React, {Component} from 'react';
import ReactMarkdown from "react-markdown";
import {BlogObj} from "../types";

interface BlogProps {
    blog: BlogObj
}

class Blog extends Component<BlogProps, {}> {

    render() {
        return (
            <div className="flex col blog">
                <ReactMarkdown className="blog-title" children={this.props.blog.title.slice(1)}/>
                <ReactMarkdown className="blog-content" children={this.getAbstractContent()}/>
                <ReactMarkdown className="blog-info" children={this.props.blog.author +
                                                                "&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;" +
                                                                this.props.blog.date}/>
            </div>
        );
    }

    getAbstractContent() {
        let content = this.props.blog.content;
        let end = content.indexOf("\n");
        return content.substring(0, end) + " ...";
    }
}

export default Blog;