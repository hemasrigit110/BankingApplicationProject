import React, { useState, useEffect } from 'react';
import { getTransactionHistory } from '../../services/transaction';

function TransactionHistory({ accountNumber }) {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const data = await getTransactionHistory(accountNumber);
        setTransactions(data);
        setLoading(false);
      } catch (err) {
        setError('Failed to fetch transaction history');
        setLoading(false);
      }
    };
    fetchTransactions();
  }, [accountNumber]);

  if (loading) return <div>Loading...</div>;
  if (error) return <div className="error">{error}</div>;
  return (
    <div className="transaction-history">
      <h2>Transaction History</h2>
      {transactions.length === 0 ? (
        <p>No transactions found</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>Date</th>
              <th>Description</th>
              <th>From Account</th>
              <th>To Account</th>
              <th>Amount</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map((txn) => (
              <tr key={txn.id}>
                <td>{new Date(txn.transactionDate).toLocaleString()}</td>
                <td>{txn.description}</td>
                <td>{txn.fromAccount}</td>
                <td>{txn.toAccount}</td>
                <td className={txn.fromAccount === accountNumber ? 'debit' : 'credit'}>
                  {txn.fromAccount === accountNumber ? '-' : '+'}₹{txn.amount.toFixed(2)}
                </td>
                <td>{txn.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default TransactionHistory;