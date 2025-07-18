import React, { useState } from 'react';
import { transferFunds } from '../../services/transaction';

function FundTransfer({ accountNumber }) {
  const [formData, setFormData] = useState({
    toAccountNumber: '',
    ifscCode: '',
    amount: '',
    description: ''
  });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await transferFunds(accountNumber, formData.toAccountNumber,
                        formData.amount, formData.description);
      setSuccess('Fund transfer successful!');
      setError('');
      setFormData({
        toAccountNumber: '',
        ifscCode: '',
        amount: '',
        description: ''
      });
    } catch (err) {
      setError(err.message || 'Fund transfer failed');
      setSuccess('');
    }
  };

  return (
    <div className="fund-transfer">
      <h2>Fund Transfer</h2>
      {error && <div className="error">{error}</div>}
      {success && <div className="success">{success}</div>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>To Account Number:</label>
          <input
            type="text"
            name="toAccountNumber"
            value={formData.toAccountNumber}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>IFSC Code:</label>
          <input
            type="text"
            name="ifscCode"
            value={formData.ifscCode}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Amount:</label>
          <input
            type="number"
            name="amount"
            value={formData.amount}
            onChange={handleChange}
            min="1"
            required
          />
        </div>
        <div>
          <label>Description:</label>
          <input
            type="text"
            name="description"
            value={formData.description}
            onChange={handleChange}
          />
        </div>
        <button type="submit">Transfer</button>
      </form>
    </div>
  );
}

export default FundTransfer;
