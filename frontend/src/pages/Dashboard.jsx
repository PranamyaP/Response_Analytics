import React, { useEffect, useState } from 'react';
import { getSessions } from '../services/api';
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
  const [sessions, setSessions] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    getSessions()
      .then(res => setSessions(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Training Sessions</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {sessions.map(session => (
          <div key={session.id} className="bg-white shadow-md rounded-2xl p-4 flex justify-between items-center">
            <div>
              <h2 className="text-lg font-semibold">Session ID: {session.id}</h2>
              <p className="text-gray-500">Trainer: {session.trainerName}</p>
            </div>
            <button
              onClick={() => navigate(`/analytics/${session.id}`)}
              className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-xl"
            >
              View Analytics
            </button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Dashboard;
