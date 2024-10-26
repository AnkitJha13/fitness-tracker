import React, { useState, useEffect } from "react";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import './Goal.css';

const BASE_URL = "https://fitnessapp-4g6c.onrender.com/api";

const Goal = () => {
    const [goalData, setGoalData] = useState({
        description: '',
        from: '',
        to: ''
    });

    const [submittedGoals, setSubmittedGoals] = useState([]);

    const handleChange = (e) => {
        setGoalData({
            ...goalData,
            [e.target.name]: e.target.value
        });
    };

    const fetchGoals = async () => {
        try {
            const response = await axios.get(`${BASE_URL}/goals`);
            setSubmittedGoals(response.data);
        } catch (error) {
            console.error("Error fetching goals:", error);
            toast.error("Error fetching goals!");
        }
    };

    const handlePostGoal = async (e) => {
        e.preventDefault();
        try {
            const formattedGoalData = {
                description: goalData.description,
                startDate: new Date(goalData.from).toISOString().split('T')[0],
                endDate: new Date(goalData.to).toISOString().split('T')[0]
            };

            await axios.post(`${BASE_URL}/goals`, formattedGoalData);
            toast.success("Goal posted successfully!");
            fetchGoals();
            setGoalData({
                description: '',
                from: '',
                to: ''
            });
        } catch (error) {
            console.error("Error posting goal:", error);
            toast.error("Failed to post goal");
        }
    };

    const handleAchieveGoal = async (goalId) => {
        try {
            await axios.get(`${BASE_URL}/goals/${goalId}`);
            toast.success("Goal marked as achieved!");
            fetchGoals();
        } catch (error) {
            console.error("Error updating goal:", error);
            toast.error("Failed to update goal");
        }
    };

    const handleDelete = async (id) => {
        if (window.confirm("Are you sure you want to delete this goal?")) {
            try {
                await axios.delete(`${BASE_URL}/goals/${id}`);
                toast.success("Goal deleted successfully!");
                fetchGoals();
            } catch (error) {
                console.error("Error deleting goal:", error);
                toast.error("Failed to delete goal");
            }
        }
    };

    useEffect(() => {
        fetchGoals();
    }, []);

    const formatDate = (date) => {
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        const dateObj = new Date(date);
        return !isNaN(dateObj.getTime()) ? dateObj.toLocaleDateString(undefined, options) : "Invalid Date";
    };

    return (
        <div className="goal-container">
            <h1 className="goal-header">Goal</h1>
            <div className="content-container">
                <div className="form-container">
                    <h3 className="form-heading">Post New Goal</h3>
                    <form className="goal-form" onSubmit={handlePostGoal}>
                        <div className="form-group">
                            <input
                                type="text"
                                className="form-control"
                                name="description"
                                value={goalData.description}
                                placeholder="Enter description"
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="from">From:</label>
                            <input
                                type="date"
                                className="form-control"
                                name="from"
                                value={goalData.from}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="to">To:</label>
                            <input
                                type="date"
                                className="form-control"
                                name="to"
                                value={goalData.to}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <button className="goal-btn-primary" type="submit">
                            Post Goal
                        </button>
                    </form>
                </div>

                <div className="submitted-data">
                    <h3>Submitted Goals</h3>
                    {submittedGoals.length === 0 ? (
                        <p>No goals available</p>
                    ) : (
                        submittedGoals.map((goal, index) => (
                            <div key={index} className="goal-item">
                                <h3 className="goal-description">{goal.description}</h3>
                                <p><strong>From:</strong> {formatDate(goal.startDate)}</p>
                                <p><strong>To:</strong> {formatDate(goal.endDate)}</p>
                                <p><strong>Achieved:</strong> {goal.achieved ? "Yes" : "No"}</p>
                                <div className="goal-buttons" style={{ display: 'flex', gap: '10px' }}>
                                    <button className="btn-secondary" onClick={() => handleAchieveGoal(goal.id)}>Achieve</button>
                                    <button className="btn-danger" onClick={() => handleDelete(goal.id)}>Delete</button>
                                </div>
                            </div>
                        ))
                    )}
                </div>
            </div>
            <ToastContainer />
        </div>
    );
};

export default Goal;
