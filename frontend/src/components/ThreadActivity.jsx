import React from 'react';
import './ThreadActivity.css';

const ThreadActivity = ({ recentTransactions }) => {
  return (
    <div className="thread-activity-container">
      <h3>Recent Thread Activity</h3>
      <div className="thread-activity-list">
        {recentTransactions.length === 0 ? (
          <div className="no-activity">No recent activity</div>
        ) : (
          recentTransactions.slice(-10).reverse().map((transaction, index) => (
            <div key={index} className="thread-activity-item">
              <span className="thread-name">{transaction.threadName}</span>
              <span className="activity-type">{transaction.type.toLowerCase()}</span>
              <span className="activity-amount">₹{transaction.amount.toFixed(2)}</span>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default ThreadActivity;
