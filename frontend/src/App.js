import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import SessionAnalytics from "./pages/SessionAnalytics"; // 👈 import this

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/analytics/:sessionId" element={<SessionAnalytics />} /> {/* 👈 this must match */}
      </Routes>
    </Router>
  );
}

export default App;
