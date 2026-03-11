import React, { useState } from 'react';
import { transfer } from '../services/api';
import './TransactionForm.css';

const TransferForm = ({ onComplete, setLoading }) => {
  const [fromAccount, setFromAccount] = useState('1');
  const [toAccount, setToAccount] = useState('2');
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

    if (fromAccount === toAccount) {
      setError('Cannot transfer to the same account');
      return;
    }

    setIsProcessing(true);
    setLoading(true);
    setError('');
    setMessage('');

    try {
      const amountValue = parseFloat(amount);
      const response = await transfer(parseInt(fromAccount), parseInt(toAccount), amountValue);
      
      if (response.error) {
        setError(response.error);
      } else {
        setMessage(`Transferred ₹${amountValue.toFixed(2)} successfully!`);
      }
      
      setAmount('');
      onComplete();
      
      setTimeout(() => {
        setMessage('');
        setError('');
      }, 3000);
    } catch (err) {
      setError(err.response?.data?.error || 'Transfer failed');
    } finally {
      setIsProcessing(false);
      setLoading(false);
    }
  };

  return (
    <div className="transaction-form-card">
      <h3>Transfer</h3>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>From Account</label>
          <select
            value={fromAccount}
            onChange={(e) => setFromAccount(e.target.value)}
            disabled={isProcessing}
          >
            <option value="1">Account 1</option>
            <option value="2">Account 2</option>
          </select>
        </div>
        <div className="form-group">
          <label>To Account</label>
          <select
            value={toAccount}
            onChange={(e) => setToAccount(e.target.value)}
            disabled={isProcessing}
          >
            <option value="1">Account 1</option>
            <option value="2">Account 2</option>
          </select>
        </div>
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
          className="btn btn-transfer"
          disabled={isProcessing}
        >
          {isProcessing ? 'Processing...' : 'Transfer'}
        </button>
      </form>
      {message && <div className="success-message">{message}</div>}
      {error && <div className="error-message">{error}</div>}
    </div>
  );
};

export default TransferForm;
