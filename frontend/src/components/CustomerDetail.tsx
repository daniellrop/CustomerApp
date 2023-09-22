// CustomerDetail.tsx
import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/CustomerDetails.scss";
import { useParams } from "react-router-dom";

type Customer = {
  id: number;
  customerId: string;
  firstname: string;
  lastname: string;
  email: string;
  subscriptionDate: number;
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
  phonenumber2: string;
  phonenumber1: string;
};

const CustomerDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [customer, setCustomer] = useState<Customer | null>(null);
  const [editedData, setEditedData] = useState<Partial<Customer>>({});

  useEffect(() => {
    // Fetch customer data based on customerId using an API request
    axios
      .get<Customer>(`http://localhost:8080/customers/${id}`)
      .then((response) => {
        setCustomer(response.data);
        setEditedData(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [id]);

  const handleInputChange = (field: string, value: string) => {
    // Update the edited data when input fields change
    setEditedData({
      ...editedData,
      [field]: value,
    });
  };

  const handleSaveClick = () => {
    if (editedData) {
      // Send a PUT request to update the edited data
      axios
        .put(`http://localhost:8080/customers`, editedData, {
          auth: {
            username: 'user3',
            password: 'password',
          },
        })
        .then((response) => {
          if (response.status === 200) {
            // Update the customer data with edited data
            setCustomer((prevCustomer) => ({
              ...prevCustomer!,
              ...editedData,
            }));
          } else {
            alert("Failed to save changes.");
          }
        })
        .catch((error) => {
          alert("Failed to save changes.");
          console.error(error);
        });
    }
  };

  function formatDate(date: number | undefined): string {
    if (date) {
      const formattedDate = new Date(date).toLocaleDateString();
      return formattedDate;
    }
    return "";
  }


  return (
    <div className="customer-detail">
      {customer ? (
        <>
          <h2 style={{ borderBottom: '2px solid #000', fontWeight: 'bold' }}>Customer Details</h2>
          <div>
            <label>ID:</label>
            <input
              type="text"
              value={editedData.id || ""}
              onChange={(e) => handleInputChange("id", e.target.value)}
            />
          </div>
          <div>
            <label>Customer ID:</label>
            <input
              type="text"
              value={editedData.customerId || ""}
              onChange={(e) => handleInputChange("customerId", e.target.value)}
            />
          </div>
          <div>
            <label>First Name:</label>
            <input
              type="text"
              value={editedData.firstname || ""}
              onChange={(e) => handleInputChange("firstname", e.target.value)}
            />
          </div>
          <div>
            <label>Last Name:</label>
            <input
              type="text"
              value={editedData.lastname || ""}
              onChange={(e) => handleInputChange("lastname", e.target.value)}
            />
          </div>
          <div>
            <label>Email:</label>
            <input
              type="text"
              value={editedData.email || ""}
              onChange={(e) => handleInputChange("email", e.target.value)}
            />
          </div>
          <div>
            <label>Subscription Date:</label>
            <input
              type="text"
              value={editedData.subscriptionDate ||   ""}
              onChange={(e) =>
                handleInputChange("subscriptionDate", e.target.value)
              }
            />
          </div>
          <div>
            <label>Company Name:</label>
            <input
              type="text"
              value={editedData.company?.companyName || ""}
              onChange={(e) =>
                handleInputChange("company.companyName", e.target.value)
              }
            />
          </div>
          <div>
            <label>City Name:</label>
            <input
              type="text"
              value={editedData.city?.cityName || ""}
              onChange={(e) =>
                handleInputChange("city.cityName", e.target.value)
              }
            />
          </div>
          <div>
            <label>Country Name:</label>
            <input
              type="text"
              value={editedData.city?.country?.countryName || ""}
              onChange={(e) =>
                handleInputChange("city.country.countryName", e.target.value)
              }
            />
          </div>
          <div>
            <label>Phone Number 1:</label>
            <input
              type="text"
              value={editedData.phonenumber1 || ""}
              onChange={(e) => handleInputChange("phonenumber1", e.target.value)}
            />
          </div>
          <div>
            <label>Phone Number 2:</label>
            <input
              type="text"
              value={editedData.phonenumber2 || ""}
              onChange={(e) => handleInputChange("phonenumber2", e.target.value)}
            />
          </div>
          <div>
            <button onClick={handleSaveClick}>Save</button>
          </div>
        </>
      ) : (
        <p>Loading customer data...</p>
      )}
    </div>
  );
};

export default CustomerDetail;




// setCustomer((prevCustomer) => ({
//   ...prevCustomer!,
//   ...editedData,
// }));