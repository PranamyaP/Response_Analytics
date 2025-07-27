import React, { useRef, useEffect } from "react";
import { Chart } from "chart.js/auto";

const CustomChart = ({ config }) => {
  const canvasRef = useRef(null);
  const chartInstanceRef = useRef(null);

  useEffect(() => {
    if (chartInstanceRef.current) {
      chartInstanceRef.current.destroy();
    }

    if (canvasRef.current) {
      chartInstanceRef.current = new Chart(canvasRef.current, config);
    }

    return () => {
      if (chartInstanceRef.current) {
        chartInstanceRef.current.destroy();
      }
    };
  }, [config]);

  return <canvas ref={canvasRef} className="w-full h-full" />;
};

export default CustomChart;
