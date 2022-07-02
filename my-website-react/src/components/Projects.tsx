import React, {Component} from 'react';
import {NavLink} from "react-router-dom";

// class Projects extends Component {
//     render() {
//         return (
//             <>
//                 <div>
//                     <h1>Project List</h1>
//                 </div>
//                 <table>
//                     <thead>
//                         <tr>
//                             <th>Project Name</th>
//                             <th>Creation Date</th>
//                         </tr>
//                     </thead>
//                     <tbody>
//                     <tr>
//                         <td>
//                             <NavLink className="link" to="/projects">
//                                 UW campus map
//                             </NavLink>
//                         </td>
//                         <td>06/03/2022</td>
//                     </tr>
//                     </tbody>
//                 </table>
//             </>
//         );
//     }
// }

function Projects() {
    return (
        <>
            <div>
                <h1 className="pPage-title">Projects of Mine</h1>
            </div>
            <table>
                <colgroup>
                    <col className="pName"/>
                    <col/>
                </colgroup>

                <thead>
                    <tr>
                        <th>Project Name</th>
                        <th>Creation Date</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <NavLink className="link" to="/projects/campus-paths">
                                Shortest path finder between two UW buildings
                            </NavLink>
                        </td>
                        <td>06/03/2022</td>
                    </tr>
                    <tr>
                        <td>
                            <NavLink className="link" to="/projects">
                                Online Bookstore
                            </NavLink>
                        </td>
                        <td>06/30/2022</td>
                    </tr>
                </tbody>
            </table>
        </>
    );
}

export {Projects};