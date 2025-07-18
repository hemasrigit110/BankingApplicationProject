import React, { useState } from 'react';

const AccountDashboard = () => {
  const [balance, setBalance] = useState(50000);
  const [transactions, setTransactions] = useState([]);
  const [form, setForm] = useState({
    type: 'debit',
    recipientAcc: '',
    ifscCode: '',
    amount: '',
    description: '',
  });
  const [error, setError] = useState('');

    const handleChange = (e) => {
      setForm({ ...form, [e.target.name]: e.target.value });
    };
   const handleTransfer = () => {
       const { type, recipientAcc, ifscCode, amount, description } = form;
       const amt = parseFloat(amount);

       if (!recipientAcc || !ifscCode || !description || isNaN(amt) || amt <= 0) {
         setError(' Please fill all fields correctly.');
         return;
       }
    if (type === 'debit' && amt > balance) {
          setError(' Insufficient balance.');
          return;
        }
    const newBalance = type === 'debit' ? balance - amt : balance + amt;
        setBalance(newBalance);
        setTransactions([
          {
            date: new Date().toLocaleString(),
            type: type === 'debit' ? 'Transfer' : 'Deposit',
            recipientAcc,
            ifscCode: ifscCode.toUpperCase(),
            amount: amt.toFixed(2),
            description,
          },
          ...transactions,
        ]);
    setForm({ type: 'debit', recipientAcc: '', ifscCode: '', amount: '', description: '' });
        setError('');
      };

      const logout = () => {
        alert('Logged out!');
        // You can redirect using router or window.location
        window.location.href = '/login';
      };
  return (
  <div style={styles.container}>
  <div style={styles.header}>
  <h2>Account Dashboard</h2>
  <button onClick={logout} style={styles.logoutBtn}>Logout</button>
  </div>
  <div style={styles.info}>
  <p><strong>Account Holder Name:</strong> Vikas MS</p>
  <p><strong>Account Number:</strong> 1234567890</p>
  <p><strong>Account Type:</strong> Savings</p>
  <p><strong>Balance:</strong> ₹{balance.toFixed(2)}</p>
  </div>
    <div style={styles.form}>
  <h3>Fund Transfer / Deposit</h3>
  <label>Transaction Type:
  <select name="type" value={form.type} onChange={handleChange}>
  <option value="debit">Transfer (Debit)</option>
  <option value="credit">Deposit (Credit)</option>
  </select>
  </label>
  <label>Recipient Account Number:
  <input name="recipientAcc" value={form.recipientAcc} onChange={handleChange} />
  </label>
  <label>IFSC Code:
  <input name="ifscCode" value={form.ifscCode} onChange={handleChange} />
  </label>
  <label>Amount:
  <input type="number" name="amount" value={form.amount} onChange={handleChange} />
  </label>
  <label>Description:
  <input name="description" value={form.description} onChange={handleChange} />
  </label>
          {error && <p style={styles.error}>{error}</p>}
  <button onClick={handleTransfer}>Submit</button>
  </div>
   <div style={styles.tableSection}>
   <h3>Transaction History</h3>
   <table style={styles.table}>
   <thead>
   <tr>
   <th>Date</th>
   <th>Type</th>
   <th>Description</th>
   <th>To Account</th>
   <th>IFSC</th>
   <th>Amount (₹)</th>
   </tr>
   </thead>
   <tbody>
               {transactions.map((txn, index) => (
   <tr key={index}>
   <td>{txn.date}</td>
   <td>{txn.type}</td>
   <td>{txn.description}</td>
   <td>{txn.recipientAcc}</td>
   <td>{txn.ifscCode}</td>
   <td>{txn.amount}</td>
   </tr>
               ))}
   </tbody>
   </table>
   </div>
   </div>
     );
   };

   //  Styles
   const styles = {
     container: {
       maxWidth: '800px',
       margin: 'auto',
       padding: '25px',
       backgroundColor: '#fefefe',
       borderRadius: '10px',
       fontFamily: 'Arial, sans-serif',
       boxShadow: '0 0 10px rgba(0,0,0,0.1)',
     },
     header: {
       display: 'flex',
       justifyContent: 'space-between',
       alignItems: 'center',
     },
     logoutBtn: {
       backgroundColor: '#d9534f',
       color: '#fff',
       padding: '8px 14px',
       border: 'none',
       borderRadius: '5px',
       cursor: 'pointer',
     },
     info: {
       marginTop: '20px',
     },
     form: {
       marginTop: '30px',
     },
     error: {
       color: 'red',
       marginTop: '10px',
     },
     tableSection: {
       marginTop: '30px',
     },
     table: {
       width: '100%',
       borderCollapse: 'collapse',
     },
     tableCell: {
       border: '1px solid #ccc',
       padding: '8px',
       textAlign: 'center',
     }
   };

   export default AccountDashboard;