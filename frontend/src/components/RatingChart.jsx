import React from 'react';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, BarElement, CategoryScale, LinearScale, Tooltip } from 'chart.js';

ChartJS.register(BarElement, CategoryScale, LinearScale, Tooltip);

const RatingChart = ({ summary }) => {
  const data = {
    labels: [],
    datasets: [{
      label: 'Ratings Count',
      data: [],
      backgroundColor: 'rgba(34, 197, 94, 0.6)',
      borderRadius: 5,
    }],
  };

  for (const question in summary) {
    if (question.toLowerCase().includes('rate')) {
      const counts = summary[question];
      data.labels = Object.keys(counts);
      data.datasets[0].data = Object.values(counts);
      break;
    }
  }

  return (
    <div className="bg-white p-4 rounded-xl shadow">
      <Bar data={data} />
    </div>
  );
};

export default RatingChart;
