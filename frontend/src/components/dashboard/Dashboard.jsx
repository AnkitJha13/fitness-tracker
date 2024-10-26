import React, { useEffect, useState } from "react";
import axios from "axios";
import { Line } from "react-chartjs-2";
import { FaRunning, FaWalking, FaFire, FaClock, FaTrophy, FaRegTimesCircle } from "react-icons/fa";
import './Dashboard.css';
import { Chart, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js';

Chart.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const BASE_URL = "https://fitnessapp-4g6c.onrender.com/api";

const Dashboard = () => {
    const [stats, setStats] = useState({});
    const [graphData, setGraphData] = useState({ workouts: [], activities: [] });

    useEffect(() => {
        const fetchData = async () => {
            try {
                const statsResponse = await axios.get(`${BASE_URL}/stats`);
                setStats(statsResponse.data);

                const graphResponse = await axios.get(`${BASE_URL}/graphs`);
                setGraphData(graphResponse.data);
            } catch (error) {
                console.error("Error fetching data:", error);
            }
        };
        fetchData();
    }, []);

    const formatDate = (date) => {
        const options = { day: 'numeric', month: 'long', year: 'numeric' };
        return new Date(date).toLocaleDateString(undefined, options);
    };

    const workoutChartData = {
        labels: graphData.workouts.map((workout) => formatDate(workout.date)),
        datasets: [
            {
                label: 'Calories Burned',
                data: graphData.workouts.map((workout) => workout.caloriesBurned),
                borderColor: '#FF5733',
                backgroundColor: 'rgba(255, 87, 51, 0.2)',
                fill: true,
                tension: 0.4,
            },
            {
                label: 'Duration (mins)',
                data: graphData.workouts.map((workout) => workout.duration),
                borderColor: '#28B463',
                backgroundColor: 'rgba(40, 180, 99, 0.2)',
                fill: true,
                tension: 0.4,
            },
        ],
    };

    const activityChartData = {
        labels: graphData.activities.map((activity) => formatDate(activity.date)),
        datasets: [
            {
                label: 'Steps',
                data: graphData.activities.map((activity) => activity.steps),
                borderColor: '#3498DB',
                backgroundColor: 'rgba(52, 152, 219, 0.2)',
                fill: true,
                tension: 0.4,
            },
            {
                label: 'Calories Burned',
                data: graphData.activities.map((activity) => activity.caloriesBurned),
                borderColor: '#F39C12',
                backgroundColor: 'rgba(243, 156, 18, 0.2)',
                fill: true,
                tension: 0.4,
            },
        ],
    };

    const chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: 'top',
                labels: {
                    color: '#333',
                    font: {
                        size: 14,
                    },
                },
            },
        },
        scales: {
            x: {
                ticks: {
                    color: '#333',
                    font: {
                        size: 12,
                    },
                },
                grid: {
                    color: '#eee',
                },
            },
            y: {
                ticks: {
                    color: '#333',
                    font: {
                        size: 12,
                    },
                },
                grid: {
                    color: '#eee',
                },
            },
        },
    };

    return (
        <div className="dashboard-container">
            <h1 className="dashboard-title">Fitness Tracker Dashboard</h1>

            <div className="cards-container">
                <div className="card card-calories">
                    <FaFire className="card-icon" />
                    <h3>Total Calories Burned</h3>
                    <p>{stats.totalCaloriesBurned} kcal</p>
                </div>
                <div className="card card-distance">
                    <FaRunning className="card-icon" />
                    <h3>Total Distance Covered</h3>
                    <p>{stats.distance} km</p>
                </div>
                <div className="card card-steps">
                    <FaWalking className="card-icon" />
                    <h3>Total Steps Taken</h3>
                    <p>{stats.steps} steps</p>
                </div>
                <div className="card card-time">
                    <FaClock className="card-icon" />
                    <h3>Total Time Spent</h3>
                    <p>{stats.duration} mins</p>
                </div>
                <div className="card card-achieved">
                    <FaTrophy className="card-icon" />
                    <h3>Achieved Goals</h3>
                    <p>{stats.achievedGoals}</p>
                </div>
                <div className="card card-not-achieved">
                    <FaRegTimesCircle className="card-icon" />
                    <h3>Not Achieved Goals</h3>
                    <p>{stats.notAchievedGoals}</p>
                </div>
            </div>

            <div className="charts-row">
                <div className="chart-container">
                    <h3>Workout Progress</h3>
                    <div className="chart-wrapper">
                        <Line data={workoutChartData} options={chartOptions} />
                    </div>
                </div>

                <div className="chart-container">
                    <h3>Activity Progress</h3>
                    <div className="chart-wrapper">
                        <Line data={activityChartData} options={chartOptions} />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
