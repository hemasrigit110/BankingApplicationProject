// services/auth.js

export const register = async (data) => {
  return new Promise((resolve, reject) => {
    const users = JSON.parse(localStorage.getItem('users') || '[]');

    const existingUser = users.find((u) => u.username === data.username);
    if (existingUser) {
      const error = new Error('Username already exists');
      error.response = { data: { message: 'Username already exists' } };
      return reject(error);
    }

    users.push(data);
    localStorage.setItem('users', JSON.stringify(users));
    setTimeout(() => resolve({ message: 'User registered successfully' }), 300);
  });
};

export const login = async (username, password) => {
  return new Promise((resolve, reject) => {
    const users = JSON.parse(localStorage.getItem('users') || '[]');
    const user = users.find(
      u => u.username === username && u.password === password
    );

    if (!user) {
      const err = new Error('Invalid credentials');
      err.response = { data: { message: 'Invalid credentials' } };
      return reject(err);
    }

    localStorage.setItem('user', JSON.stringify(user)); // Save full user
    localStorage.setItem('token', 'mock-jwt-token');     // Save mock token
    resolve({ token: 'mock-jwt-token', user });
  });
};
