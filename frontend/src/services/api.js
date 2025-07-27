import axios from 'axios';

const BASE_URL = 'http://localhost:8083/api/analytics';

export const getSessions = () => axios.get(`${BASE_URL}/sessions`);
export const getQuestions = (sessionId) => axios.get(`${BASE_URL}/sessions/${sessionId}/questions`);
export const getSessionSummary = (sessionId) => axios.get(`${BASE_URL}/sessions/${sessionId}/summary`);
export const getAverageSentiment = (sessionId) => axios.get(`http://localhost:8083/api/analytics/sentiment/average/${sessionId}`);
export const getResponseCount = (sessionId) =>
  axios.get(`http://localhost:8083/api/analytics/sessions/${sessionId}/responses/count`);
