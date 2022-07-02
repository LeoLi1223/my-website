import React from 'react';
import ReactMarkdown from "react-markdown";
import {useLocation} from "react-router-dom";
import {BlogObj} from "../types";

interface LocationState {
    blog: BlogObj
}

function Post () {
    const {blog} = useLocation().state as LocationState;

    return (
        <div>
            <ReactMarkdown children={blog.title}/>
            <div className="blog-info">
                <p>
                    {blog.author + " | " + blog.date}
                </p>
            </div>
            <ReactMarkdown className="blog-content" children={blog.content}/>
        </div>
    )
}

export {Post};