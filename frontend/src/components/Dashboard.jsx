import React, { useState, useEffect } from 'react';
import TransactionForm from './TransactionForm';
import TransferForm from './TransferForm';
import TransactionTable from './TransactionTable';
import ThreadActivity from './ThreadActivity';
import { getBalance, getTransactions, simulateTransactions } from '../services/api';
import './Dashboard.css';

const Dashboard = () => {
  const [balance, setBalance] = useState(0);
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(false);
  const [simulating, setSimulating] = useState(false);

  const fetchBalance = async () => {
    try {
      const data = await getBalance();
      setBalance(data.balance);
    } catch (error) {
      console.error('Error fetching balance:', error);
    }
  };

  const fetchTransactions = async () => {
    try {
      const data = await getTransactions();
      setTransactions(data);
    } catch (error) {
      console.error('Error fetching transactions:', error);
    }
  };

  useEffect(() => {
    fetchBalance();
    fetchTransactions();
    
    const interval = setInterval(() => {
      fetchBalance();
      fetchTransactions();
    }, 2000);

    return () => clearInterval(interval);
  }, []);

  const handleTransactionComplete = () => {
    fetchBalance();
    fetchTransactions();
  };

  const handleSimulate = async () => {
    setSimulating(true);
    try {
      await simulateTransactions();
      fetchBalance();
      fetchTransactions();
    } catch (error) {
      console.error('Error simulating transactions:', error);
    } finally {
      setSimulating(false);
    }
  };

  return (
    <div className="dashboard">
      <header className="dashboard-header">
        <h1>Banking Transaction Simulator</h1>
        <p className="subtitle">Multithreaded Concurrent Banking System</p>
      </header>

      <div className="dashboard-content">
        <div className="balance-card">
          <h2>Account Balance</h2>
          <div className="balance-amount">₹{balance.toFixed(2)}</div>
        </div>

        <div className="transaction-forms">
          <TransactionForm 
            type="deposit" 
            onComplete={handleTransactionComplete}
            setLoading={setLoading}
          />
          <TransactionForm 
            type="withdraw" 
            onComplete={handleTransactionComplete}
            setLoading={setLoading}
          />
          <TransferForm 
            onComplete={handleTransactionComplete}
            setLoading={setLoading}
          />
        </div>

        <div className="simulate-section">
          <button 
            className="btn-simulate"
            onClick={handleSimulate}
            disabled={simulating}
          >
            {simulating ? 'Simulating...' : 'Simulate 20 Concurrent Transactions'}
          </button>
        </div>

        <ThreadActivity recentTransactions={transactions} />

        <TransactionTable transactions={transactions} />
      </div>
    </div>
  );
};

export default Dashboard;
