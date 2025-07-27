import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getSessionSummary, getQuestions, getAverageSentiment, getResponseCount } from '../services/api';
import SummaryBox from '../components/SummaryBox';
import RatingChart from '../components/RatingChart';
import McqChart from '../components/McqChart';

const SessionAnalytics = () => {
    const { sessionId } = useParams();
    const [summary, setSummary] = useState({});
    const [questions, setQuestions] = useState([]);
    const [sentiment, setSentiment] = useState({ averageSentiment: 0, category: 'Neutral' });
    const [responseCount, setResponseCount] = useState(null);

    useEffect(() => {
        getSessionSummary(sessionId).then(res => setSummary(res.data));
        getQuestions(sessionId).then(res => setQuestions(res.data));
        getAverageSentiment(sessionId).then(res => setSentiment(res.data)).catch((err) => console.error(err));
        getResponseCount(sessionId).then((res) => setResponseCount(res.data.responseCount)).catch((err) => console.error(err));
    }, [sessionId]);

    return (
        <div className="p-6">
            <h1 className="text-2xl font-bold mb-6">Analytics for Session {sessionId}</h1>
            <div className="grid grid-cols-2 md:grid-cols-4 gap-4 mb-8">
                <SummaryBox label="Session ID" value={sessionId} />
                <SummaryBox label="Total Questions" value={questions.length} />
                <SummaryBox label="Total Responses" value={responseCount !== null ? responseCount : 'Loading...'} />
                <SummaryBox label="Avg. Sentiment" value={`${sentiment.category} (${sentiment.averageSentiment.toFixed(2)})`} />
            </div>


            <div className="mb-10">
                <h2 className="text-xl font-semibold mb-2">Rating Questions</h2>
                <RatingChart summary={summary} />
            </div>

            <div>
                <h2 className="text-xl font-semibold mb-2">MCQ Distribution</h2>
                <McqChart summary={summary} />
            </div>
        </div>
    );
};

export default SessionAnalytics;
