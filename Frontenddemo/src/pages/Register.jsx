import React, { useState } from "react";

const BankRegistration = () => {
  const [form, setForm] = useState({
    username: "",
    password: "",
    accHolder: "",
    accNumber: "",
    ifsc: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    const { username, password, accHolder, accNumber, ifsc } = form;

    if (!username || !password || !accHolder || !accNumber || !ifsc) {
      setError("All fields are required.");
      return false;
    }

    if (accNumber.length < 9 || accNumber.length > 18) {
      setError("Account number must be between 9 and 18 digits.");
      return false;
    }

    const ifscPattern = /^[A-Z]{4}0[A-Z0-9]{6}$/;
    if (!ifscPattern.test(ifsc)) {
      setError("Invalid IFSC code format.");
      return false;
    }

    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    if (!validateForm()) return;

    try {
      const response = await fetch("http://localhost:8081/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });

      if (response.ok) {
        const data = await response.json();
        setSuccess("Registration successful! Please login.");
        setForm({
          username: "",
          password: "",
          accHolder: "",
          accNumber: "",
          ifsc: "",
        });
      } else {
        const err = await response.json();
        setError(err.message || "Registration failed.");
      }
    } catch (error) {
      console.error("Registration error:", error);
      setError("Server error. Please try again later.");
    }
  };

  return (
    <div style={styles.container}>
      <h2>Bank Registration</h2>

      <form onSubmit={handleSubmit} style={styles.form}>
        <input
          type="text"
          name="username"
          placeholder="Username"
          value={form.username}
          onChange={handleChange}
          style={styles.input}
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={form.password}
          onChange={handleChange}
          style={styles.input}
        />
        <input
          type="text"
          name="accHolder"
          placeholder="Account Holder Name"
          value={form.accHolder}
          onChange={handleChange}
          style={styles.input}
        />
        <input
          type="text"
          name="accNumber"
          placeholder="Account Number"
          value={form.accNumber}
          onChange={handleChange}
          style={styles.input}
        />
        <input
          type="text"
          name="ifsc"
          placeholder="IFSC Code"
          value={form.ifsc}
          onChange={handleChange}
          style={styles.input}
        />

        {error && <p style={styles.error}>{error}</p>}
        {success && <p style={styles.success}>{success}</p>}

        <button type="submit" style={styles.button}>
          Register
        </button>
      </form>
    </div>
  );
};

const styles = {
  container: {
    width: "400px",
    margin: "50px auto",
    padding: "30px",
    backgroundColor: "#fff",
    borderRadius: "10px",
    boxShadow: "0 0 12px rgba(0,0,0,0.2)",
    textAlign: "center",
    fontFamily: "Arial",
  },
  form: {
    display: "flex",
    flexDirection: "column",
    gap: "12px",
  },
  input: {
    padding: "10px",
    fontSize: "16px",
    borderRadius: "5px",
    border: "1px solid #ccc",
  },
  button: {
    padding: "12px",
    backgroundColor: "#007bff",
    color: "white",
    border: "none",
    fontSize: "16px",
    borderRadius: "5px",
    cursor: "pointer",
  },
  error: {
    color: "red",
    fontSize: "14px",
  },
  success: {
    color: "green",
    fontSize: "14px",
  },
};

export default BankRegistration;