import React, { useState } from "react";
import "../styles/AddUserForm.scss";

const AddUserForm = ({ addUser, onClose }: { addUser: Function; onClose: Function }) => {
  const [formData, setFormData] = useState({
    id: "",
    customerId: "",
    firstname: "",
    lastname: "",
    phonenumber1: "",
    phonenumber2: "",
    email: "",
    subscriptionDate: "",
    company: {
      companyId: "",
      companyName: "",
    },
    city: {
      cityId: "",
      cityName: "",
      country: {
        countryId: "",
        countryName: "",
      },
    },
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    // Check for nested properties and update state accordingly
    if (name.includes("company.")) {
      const companyField = name.split(".")[1];
      setFormData((prevData) => ({
        ...prevData,
        company: {
          ...prevData.company,
          [companyField]: value,
        },
      }));
    } else if (name.includes("city.")) {
      const cityField = name.split(".")[1];
      if (name.includes("country.")) {
        const countryField = name.split(".")[2];
        setFormData((prevData) => ({
          ...prevData,
          city: {
            ...prevData.city,
            country: {
              ...prevData.city.country,
              [countryField]: value,
            },
          },
        }));
      } else {
        setFormData((prevData) => ({
          ...prevData,
          city: {
            ...prevData.city,
            [cityField]: value,
          },
        }));
      }
    } else {
      setFormData((prevData) => ({
        ...prevData,
        [name]: value,
      }));
    }
  };


  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    addUser(formData);
    onClose(); // Close the form after submission
    // Reset the form fields
    setFormData({
      id: "",
      customerId: "",
      firstname: "",
      lastname: "",
      phonenumber1: "",
      phonenumber2: "",
      email: "",
      subscriptionDate: "",
      company: {
        companyId: "",
        companyName: "",
      },
      city: {
        cityId: "",
        cityName: "",
        country: {
          countryId: "",
          countryName: "",
        },
      },
    });
  };

  return (
    <div className="add-user-form">
      <h2>Add User</h2>
      <form onSubmit={handleSubmit}>
        {/* ID */}
        { <div className="form-group">
          <label>ID:</label>
          <input
            type="number"
            name="id"
            value={formData.id}
            onChange={handleChange}
            required
          />
        </div> }

        {/* Customer ID */}
        <div className="form-group">
          <label>Customer ID:</label>
          <input
            type="text"
            name="customerId"
            value={formData.customerId}
            onChange={handleChange}
            required
          />
        </div>

        {/* First Name */}
        <div className="form-group">
          <label>First Name:</label>
          <input
            type="text"
            name="firstname"
            value={formData.firstname}
            onChange={handleChange}
            required
          />
        </div>

        {/* Last Name */}
        <div className="form-group">
          <label>Last Name:</label>
          <input
            type="text"
            name="lastname"
            value={formData.lastname}
            onChange={handleChange}
            required
          />
        </div>

        {/* Phone Number 1 */}
        <div className="form-group">
          <label>Phone Number 1:</label>
          <input
            type="text"
            name="phonenumber1"
            value={formData.phonenumber1}
            onChange={handleChange}
            required
          />
        </div>

        {/* Phone Number 2 */}
        <div className="form-group">
          <label>Phone Number 2:</label>
          <input
            type="text"
            name="phonenumber2"
            value={formData.phonenumber2}
            onChange={handleChange}
            required
          />
        </div>

        {/* Email */}
        <div className="form-group">
          <label>Email:</label>
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </div>

        {/* Subscription Date */}
        <div className="form-group">
          <label>Subscription Date:</label>
          <input
            type="date"
            name="subscriptionDate"
            value={formData.subscriptionDate}
            onChange={handleChange}
            required
          />
        </div>

        {/* Company ID */}
        <div className="form-group">
          <label>Company ID:</label>
          <input
            type="number"
            name="company.companyId"
            value={formData.company.companyId}
            onChange={handleChange}
            required
          />
        </div>

        {/* Company Name */}
        <div className="form-group">
          <label>Company Name:</label>
          <input
            type="text"
            name="company.companyName"
            value={formData.company.companyName}
            onChange={handleChange}
            required
          />
        </div>

        {/* City ID */}
        <div className="form-group">
          <label>City ID:</label>
          <input
            type="number"
            name="city.cityId"
            value={formData.city.cityId}
            onChange={handleChange}
            required
          />
        </div>

        {/* City Name */}
        <div className="form-group">
          <label>City Name:</label>
          <input
            type="text"
            name="city.cityName"
            value={formData.city.cityName}
            onChange={handleChange}
            required
          />
        </div>

        {/* Country ID */}
        <div className="form-group">
          <label>Country ID:</label>
          <input
            type="number"
            name="city.country.countryId"
            value={formData.city.country.countryId}
            onChange={handleChange}
            required
          />
        </div>

        {/* Country Name */}
        <div className="form-group">
          <label>Country Name:</label>
          <input
            type="text"
            name="city.country.countryName"
            value={formData.city.country.countryName}
            onChange={handleChange}
            required
          />
        </div>

        <button type="submit">Add</button>
      </form>
    </div>
  );
};

export default AddUserForm;
