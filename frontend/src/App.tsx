// App.tsx

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Header from './components/Header';
import Bottom from './components/Bottom';
import Home from './components/Home';
import CustomerDetail from './components/CustomerDetail';
import Login from './components/Login';

import './styles/App.scss';
import './styles/Header.scss';
import './styles/Bottom.scss';

const App = () => {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/customers/:id" element={<CustomerDetail />} />
        <Route path="/login" element={<Login />} />
      </Routes>
      <Bottom />
    </Router>
  );
};

export default App;
