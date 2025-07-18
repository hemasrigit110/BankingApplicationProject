import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const BankLogin = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate(); // For navigation
  const handleLogin = (e) => {
      e.preventDefault();
          if (username.trim() === '' || password.trim() === '') {
            alert('Please enter both username and password.');
            return;
          }
      // Replace this with your actual login API or logic
          if (username === 'user' && password === 'pass') {
            alert('Login successful!');
            navigate('/dashboard'); // Redirect to dashboard
          } else {
            alert('Invalid credentials.');
          }
        };
    return (
    <div style={styles.container}>
    <form style={styles.form} onSubmit={handleLogin}>
    <h2 style={styles.heading}>Bank Login</h2>

            <label style={styles.label}>Username</label>
    <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              style={styles.input}
              placeholder="Enter username"
            />
     <label style={styles.label}>Password</label>
     <input
               type="password"
               value={password}
               onChange={(e) => setPassword(e.target.value)}
               style={styles.input}
               placeholder="Enter password"
             />
                <button type="submit" style={styles.button}>Login</button>

                     <div style={styles.register}>
             <a href="/register">Register here</a>
             </div>
             </form>
             </div>
               );
             };
         const styles = {
           container: {
             backgroundColor: '#eef2f5',
             height: '100vh',
             display: 'flex',
             justifyContent: 'center',
             alignItems: 'center'
           },
           form: {
             backgroundColor: '#fff',
             padding: 30,
             borderRadius: 12,
             boxShadow: '0 4px 10px rgba(0,0,0,0.1)',
             width: 300
           },
       heading: {
           textAlign: 'center',
           marginBottom: 20
         },
         label: {
           fontWeight: 'bold',
           marginBottom: 6,
           display: 'block'
         },
     input: {
         width: '100%',
         padding: 10,
         marginBottom: 15,
         borderRadius: 6,
         border: '1px solid #ccc'
       },
       button: {
         width: '100%',
         padding: 10,
         backgroundColor: '#0078d7',
         color: '#fff',
         border: 'none',
         borderRadius: 6,
         cursor: 'pointer'
       },
   register: {
       marginTop: 15,
       textAlign: 'center'
     }
   };

   export default BankLogin;