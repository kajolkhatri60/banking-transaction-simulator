import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const getBalance = async () => {
  const response = await api.get('/balance');
  return response.data;
};

export const deposit = async (amount) => {
  const response = await api.post('/deposit', { amount });
  return response.data;
};

export const withdraw = async (amount) => {
  const response = await api.post('/withdraw', { amount });
  return response.data;
};

export const transfer = async (fromAccount, toAccount, amount) => {
  const response = await api.post('/transfer', { fromAccount, toAccount, amount });
  return response.data;
};

export const simulateTransactions = async () => {
  const response = await api.post('/simulate');
  return response.data;
};

export const getTransactions = async () => {
  const response = await api.get('/transactions');
  return response.data;
};

export const getRecentTransactions = async (limit = 10) => {
  const response = await api.get(`/transactions/recent?limit=${limit}`);
  return response.data;
};

export default api;
