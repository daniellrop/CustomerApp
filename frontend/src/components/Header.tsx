import React, { useState } from 'react'
import { Link } from "react-router-dom";
import { HashLink } from "react-router-hash-link";
import enigma from "../assets/enigma-gold.png";
import user from "../assets/default-user.png";

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';

const Header = () => {

    const navigateToHomepage = () => {
        // To navigate back to the homepage
        window.location.href = "/";
    };

    const [showProfileMenu, setShowProfileMenu] = useState(false);

  const toggleProfileMenu = () => {
    setShowProfileMenu(!showProfileMenu);
  };

  const signOut = () => {
    // Perform sign-out logic here
    // For example, clear user session, cookies, or JWT tokens
    // Redirect the user to the login page if needed
    setTimeout(() => {
      window.location.href = "/login";
    }, 2000); 
  };
    

  return (
    <nav>
      <img onClick={navigateToHomepage} style={{ cursor: "pointer" }}src={enigma} alt="Enigma" />
      <div className = "white-box">
        <h1>Version 1</h1>
      </div>
      <main>
      {/* { <Link to={"/matches"}>Matches</Link> } */}
      <HashLink className= "contact" to="/#contact">Contact</HashLink>

      {/* User Profile Icon */}
      <div className="user-profile">
        <button onClick={toggleProfileMenu}>
          <img src={user} alt="User" /> {/* Replace with your user image */}
        </button>
        {showProfileMenu && (
          <div className="profile-menu">
            <button onClick={signOut}>Sign Out</button>
          </div>
        )}
      </div>
    </main>
    </nav>
  )
}

export default Header