import React from "react";
import CustomChart from "./CustomChart";

const AnalyticsChart = ({ question, dataMap }) => {
  const labels = Object.keys(dataMap);
  const dataValues = Object.values(dataMap);

  const chartType = (() => {
    const lower = question.toLowerCase();
    if (lower.includes("rate") || lower.includes("rating")) return "bar";
    if (lower.includes("pace") || lower.includes("speed")) return "line";
    if (lower.includes("material") || lower.includes("useful")) return "doughnut";
    return "pie"; // default for MCQ
  })();

  const config = {
    type: chartType,
    data: {
      labels,
      datasets: [
        {
          label: question,
          data: dataValues,
          backgroundColor: [
            "#38bdf8", "#818cf8", "#facc15", "#f87171", "#34d399", "#fb923c"
          ],
          borderWidth: 1,
        },
      ],
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: "bottom",
        },
        title: {
          display: false,
        },
      },
    },
  };

  return (
    <div className="bg-white rounded-2xl p-4 shadow-md w-full md:w-[48%] lg:w-[30%]">
  <h2 className="text-lg font-semibold mb-3">{question}</h2>
  <div className="relative h-[250px]">
    <CustomChart config={config} />
  </div>
</div>
  );
};

export default AnalyticsChart;
