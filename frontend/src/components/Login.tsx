// Login.tsx

import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "../styles/login.scss";

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = () => {
    // Replace 'user3' and 'password' with your actual valid credentials
    if (username === 'user' && password === 'password1234') {
      // Redirect to the home page when the login is successful
      navigate('/');
    } else {
      // Show an error message if login fails
      alert('Invalid username or password. Please try again.');
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <div className="form-group">
        <label htmlFor="username">Username:</label>
        <input
          type="text"
          id="username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="form-control"
        />
      </div>
      <div className="form-group">
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="form-control"
        />
      </div>
      <button onClick={handleLogin} className="login-button">Login</button>
    </div>
  );
};

export default Login;
