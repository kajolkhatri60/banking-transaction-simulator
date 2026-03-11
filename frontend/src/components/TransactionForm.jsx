import React, { useState } from 'react';
import { deposit, withdraw } from '../services/api';
import './TransactionForm.css';

const TransactionForm = ({ type, onComplete, setLoading }) => {
  const [amount, setAmount] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [isProcessing, setIsProcessing] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!amount || parseFloat(amount) <= 0) {
      setError('Please enter a valid amount');
      return;
    }

    setIsProcessing(true);
    setLoading(true);
    setError('');
    setMessage('');

    try {
      const amountValue = parseFloat(amount);
      let response;
      
      if (type === 'deposit') {
        response = await deposit(amountValue);
        setMessage(`Deposited ₹${amountValue.toFixed(2)} successfully!`);
      } else {
        response = await withdraw(amountValue);
        if (response.error) {
          setError(response.error);
        } else {
          setMessage(`Withdrew ₹${amountValue.toFixed(2)} successfully!`);
        }
      }
      
      setAmount('');
      onComplete();
      
      setTimeout(() => {
        setMessage('');
        setError('');
      }, 3000);
    } catch (err) {
      setError(err.response?.data?.error || 'Transaction failed');
    } finally {
      setIsProcessing(false);
      setLoading(false);
    }
  };

  return (
    <div className="transaction-form-card">
      <h3>{type === 'deposit' ? 'Deposit' : 'Withdraw'}</h3>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Amount</label>
          <input
            type="number"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            placeholder="Enter amount"
            disabled={isProcessing}
            step="0.01"
            min="0"
          />
        </div>
        <button 
          type="submit" 
          className={`btn btn-${type}`}
          disabled={isProcessing}
        >
          {isProcessing ? 'Processing...' : type === 'deposit' ? 'Deposit' : 'Withdraw'}
        </button>
      </form>
      {message && <div className="success-message">{message}</div>}
      {error && <div className="error-message">{error}</div>}
    </div>
  );
};

export default TransactionForm;
