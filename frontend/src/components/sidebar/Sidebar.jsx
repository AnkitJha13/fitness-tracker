import React from 'react';
import { NavLink } from 'react-router-dom';
import './Sidebar.css'; // Import custom CSS
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTachometerAlt, faDumbbell, faRunning, faBullseye } from '@fortawesome/free-solid-svg-icons';

const Sidebar = () => {
    return (
        <div className="sidebar">
            <div className="sidebar-header text-center">
                <h2 className="sidebar-title">Fitness Tracker</h2>
            </div>
            <ul className="sidebar-list">
                <li className="sidebar-item">
                    <NavLink to="/" className="sidebar-link">
                        <FontAwesomeIcon icon={faTachometerAlt} /> Dashboard
                    </NavLink>
                </li>
                <li className="sidebar-item">
                    <NavLink to="/workout" className="sidebar-link">
                        <FontAwesomeIcon icon={faDumbbell} /> Workout
                    </NavLink>
                </li>
                <li className="sidebar-item">
                    <NavLink to="/activity-post" className="sidebar-link">
                        <FontAwesomeIcon icon={faRunning} /> Activity
                    </NavLink>
                </li>
                <li className="sidebar-item">
                    <NavLink to="/goal" className="sidebar-link">
                        <FontAwesomeIcon icon={faBullseye} /> Goal
                    </NavLink>
                </li>
            </ul>
        </div>
    );
};

export default Sidebar;
