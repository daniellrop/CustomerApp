// Home.js
import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import "../styles/Home.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSortUp,
  faSortDown,
  faUserPlus,
  faEdit,
  faTrash,
} from "@fortawesome/free-solid-svg-icons";
import AddUserForm from "./AddUserForm";
import { Link } from "react-router-dom";


type User = {
  id: number;
  customerId: string;
  firstname: string;
  lastname: string;
  company: {
    companyId: number;
    companyName: string;
  };
  city: {
    cityId: number;
    cityName: string;
    country: {
      countryId: number;
      countryName: string;
    };
  };
};

const Home = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [isError, setIsError] = useState<string | null>(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [usersPerPage] = useState(10);
  const [searchTerm, setSearchTerm] = useState<string>("");
  const [sortOrder, setSortOrder] = useState<"asc" | "desc">("asc");
  const [showAddUserForm, setShowAddUserForm] = useState(false);
  const [showMainPage, setShowMainPage] = useState(true);
  const tableRef = useRef<HTMLTableElement>(null);
  const [isLoading, setIsLoading] = useState(true); 



  useEffect(() => {
    axios
      .get<User[]>("http://localhost:8080/customers",  {auth: {
        username: 'user3',
        password: 'password'
      }})
      .then((response) => {
        setUsers(response.data);
        setIsLoading(false);
      })
      .catch((error) => {
        setIsError(error.message);
         setIsLoading(false);
      });
  }, []);

  const indexOfLastUser = currentPage * usersPerPage;
  const indexOfFirstUser = indexOfLastUser - usersPerPage;

  const toggleSortOrder = () => {
    setSortOrder(sortOrder === "asc" ? "desc" : "asc");
  };

  const filteredUsers = users.filter((user) => {
    const fullName = user.firstname + " " + user.lastname;
    return (
      fullName.toLowerCase().includes(searchTerm.toLowerCase()) ||
      user.id.toString().toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  const sortedUsers = [...filteredUsers].sort((a, b) => {
    if (sortOrder === "asc") {
      return a.id - b.id;
    } else {
      return b.id - a.id;
    }
  });

  const nextPage = () => {
    if (currentPage < Math.ceil(sortedUsers.length / usersPerPage)) {
      setCurrentPage(currentPage + 1);
      scrollToTop();
    }
  };

  const prevPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
      scrollToTop();
    }
  };

  const scrollToTop = () => {
    if (tableRef.current) {
      tableRef.current.scrollIntoView({ behavior: "auto", block: "start" });
    }
  };

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
    setCurrentPage(1);
  };

  const addUser = (newUser: User) => {
    axios
      .post<User[]>("http://localhost:8080/customers",  newUser,  {auth: {
        username: 'user3',
        password: 'password'
      }})
      .then((response) => {
        if (response.status === 200) {
          alert(`Customer ${newUser.id} added!`);
          // Refresh the user list
          axios.get<User[]>("http://localhost:8080/customers").then((response) => {
            setUsers(response.data);
          });
        } else {
          alert("Failed to add the user.");
        }
      })
      .catch((error) => {
        alert("Failed to add the user.");
        console.error(error);
      });

    setShowAddUserForm(false);
    setShowMainPage(true);
  };

  const toggleAddUserForm = () => {
    setShowAddUserForm(!showAddUserForm);
    if (!showAddUserForm) {
      setShowMainPage(false);
    }
  };

 
  const deleteUser = (userId: number) => {
    axios
      .delete(`http://localhost:8080/customers/${userId}`,  {withCredentials: true})
      .then((response) => {
        if (response.status === 200) {
          alert(`Customer ${userId} deleted!`);
          // Refresh the user list
          axios.get<User[]>("http://localhost:8080/customers",  {auth: {
            username: 'user3',
            password: 'password'
          }}).then((response) => {
            setUsers(response.data);
          });
        } else {
          alert("Failed to delete the user.");
        }
      })
      .catch((error) => {
        alert("Failed to delete the user.");
        console.error(error);
      });
  };

  const renderTableRows = () => {
    const displayedUsers = sortedUsers.slice(indexOfFirstUser, indexOfLastUser);

    return displayedUsers.map((user) => (
      <tr key={user.id}>
        <td>{user.id}</td>
        <td>{user.customerId}</td>
        <td>{user.firstname}</td>
        <td>{user.lastname}</td>
        <td>{user.company.companyName}</td>
        <td>{user.city.cityName}</td>
        <td>{user.city.country.countryName}</td>
        <td>
          <Link to={`/customers/${user.id}`}>
            <button><FontAwesomeIcon icon={faEdit} /></button> 
          </Link>
          <button onClick={() => deleteUser(user.id)}>
            <FontAwesomeIcon icon={faTrash} />
          </button>
        </td>
      </tr>
    ));
  };

  return (
    <>
      {isLoading ? ( // Display loading message while data is being fetched
        <div className="center-bold">Loading Customer Data...</div>
      ) : isError ? ( // Display error message if there's an error
        <div className="error">Error: {isError}</div>
      ) : (
        <>
      <h1 className="center1">Customer Data</h1>
      <div className="search-bar">
        <div className="toggle-button">
          <button onClick={toggleSortOrder}>
            {sortOrder === "asc" ? (
              <FontAwesomeIcon icon={faSortUp} />
            ) : (
              <FontAwesomeIcon icon={faSortDown} />
            )}
          </button>
        </div>
        <input
          type="text"
          placeholder="Search by FirstName or LastName"
          value={searchTerm}
          onChange={handleSearchChange}
        />
        <button onClick={toggleAddUserForm}>
          <FontAwesomeIcon icon={faUserPlus} />
        </button>
      </div>
      {showAddUserForm && (
        <AddUserForm addUser={addUser} onClose={toggleAddUserForm} />
      )}
      {showMainPage && (
        <table ref={tableRef}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Customer ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Company Name</th>
              <th>City Name</th>
              <th>Country Name</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>{renderTableRows()}</tbody>
        </table>
      )}
      <div className="pagination">
        {currentPage > 1 && <button onClick={prevPage}>&#8249; Prev</button>}
        {currentPage < Math.ceil(sortedUsers.length / usersPerPage) && (
          <button onClick={nextPage}>Next &#8250;</button>
        )}
      </div>
    </>
 )}
 </>
);
};

export default Home;
