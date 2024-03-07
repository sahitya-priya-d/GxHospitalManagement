import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import axios from "axios";

const ForgotPassword = () => {
  const [email, setEmail] = useState("");
  const navigate = useNavigate();

  const handleReset = async (e) => {
    e.preventDefault();

    // Validate email format
    if (!isValidEmail(email)) {
      toast.error("Please enter a valid email", { autoClose: 3000 });
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/api/gxHospital/users/send-reset-password",
        null,
        {
          params: {
            email: email,
          },
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      console.log(response.data);

      if (response.status===200) {
        toast.success("An email has been sent to you.", {
          autoClose: 3000,
        });
        setTimeout(() => {
          navigate("/login");
        }, 4000);
      }
    } catch (error) {
      console.error(error);

      if (error.response) {
        toast.error("Your account is not registered with us", { autoClose: 3000 });
      } else if (error.request) {
        toast.error("Error in sending mail", { autoClose: 3000 });
      } else {
        toast.error("An unexpected error occurred", { autoClose: 3000 });
      }
    }
  };

  const cancelForgotPassword = () => {
    navigate("/login");
  };

  // Email validation function
  const isValidEmail = (email) => {
    // Simple email validation regex
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  return (
    <div>
      <ToastContainer />
      <main className="login-page">
        <img
          className="logo-img"
          src="https://www.outsourcetopk.com/public/assets/images/portfolio/logo/Hospital-1.jpg"
          alt=""
        />
        <h1>Forgot your password?</h1>
        <div className="login-form">
          <p className="text">Enter the email address you registered with us:</p>
          <div className="input-box">
            <input
              placeholder="Email"
              type="email"
              name="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              pattern="[^\s@]+@[^\s@]+\.[^\s@]+"
              title="Enter a valid email address"
              required
            />
          </div>
          <Link to="https://docs.spring.io/spring-framework/reference/index.html">
            <p style={{ marginTop: "1rem" }} className="help">
              Need help?
            </p>
          </Link>

          <button className="login-button" onClick={handleReset}>
            Submit
          </button>
          <br></br>
          <button className="cancel-button" onClick={cancelForgotPassword}>
            Cancel
          </button>
        </div>
      </main>
    </div>
  );
};

export default ForgotPassword;
