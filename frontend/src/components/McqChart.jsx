import React from 'react';
import { Pie } from 'react-chartjs-2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';

ChartJS.register(ArcElement, Tooltip, Legend);

const McqChart = ({ summary }) => {
    const mcqCharts = [];

    for (const question in summary) {
        if (!question.toLowerCase().includes('rate')) {
            const counts = summary[question];
            const data = {
                labels: Object.keys(counts),
                datasets: [{
                    label: question,
                    data: Object.values(counts),
                    backgroundColor: [
                        '#60a5fa', '#facc15', '#f87171', '#34d399', '#a78bfa', '#f472b6'
                    ],
                }]
            };

            mcqCharts.push(
                <div key={question} className="bg-white p-4 rounded-xl shadow w-full max-w-md mx-auto">
                    <h3 className="text-md font-semibold mb-2">{question}</h3>
                    <div className="relative h-[250px] w-full">
                        <Pie data={data} options={{ responsive: true, maintainAspectRatio: false }} />
                    </div>
                </div>

            );
        }
    }

    return <div className="grid grid-cols-1 md:grid-cols-2 gap-6">{mcqCharts}</div>;
};

export default McqChart;
