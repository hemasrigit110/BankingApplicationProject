import React, { useState, useEffect } from 'react';
import { getAccountDetails } from '../../services/account';

function AccountInfo({ accountNumber }) {
  const [account, setAccount] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchAccount = async () => {
      try {
        const data = await getAccountDetails(accountNumber);
        setAccount(data);
        setLoading(false);
      } catch (err) {
        setError('Failed to fetch account details');
        setLoading(false);
      }
    };
    fetchAccount();
  }, [accountNumber]);

  if (loading) return <div>Loading...</div>;
  if (error) return <div className="error">{error}</div>;

  return (
    <div className="account-info">
      <h2>Account Details</h2>
      <div>
        <p><strong>Account Holder:</strong> {account.accountHolderName}</p>
        <p><strong>Account Number:</strong> {account.accountNumber}</p>
        <p><strong>Account Type:</strong> {account.accountType}</p>
        <p><strong>Balance:</strong> ₹{account.balance.toFixed(2)}</p>
        <p><strong>IFSC Code:</strong> {account.ifscCode}</p>
      </div>
    </div>
  );
}

export default AccountInfo;