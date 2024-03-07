import React, { useState } from "react";
import "../Styles/Login.css";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import swal from "sweetalert";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";
import { useAuth } from "../Context/AuthContext";

const Login = () => {
  const { login } = useAuth();
  const [credentials, setCredentials] = useState({
    email: "",
    password: "",
  });
  const [hide, setHide] = useState(true);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setCredentials({
      ...credentials,
      [e.target.name]: e.target.value,
    });
  };

  const isEmailValid = (email) => {
    // Regular expression for basic email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (!isEmailValid(credentials.email)) {
        toast.error("Invalid Email format", { autoClose: 3000 });
        return;
      }

      const response = await axios.post(
        "http://localhost:8080/api/gxHospital/users/authenticate",
        credentials
      );
      const { token} = response.data;
      login({ token });
      console.log(response.data);

      swal({
        title: "Login successful",
        text: "You are successfully logged in!",
        icon: "success",
        timer: 3000,
      });

      navigate("/");
    } catch (error) {
      console.error("Login failed", error);
      toast.error("Incorrect Credentials", { autoClose: 3000 });
    }

  };
  const forgotPassword = () => {
    navigate("/forgot-password");
  };
  const togglePasswordVisibility = () => {
    setHide(!hide);
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
        <h1>Welcome back!</h1>
        <div className="login-form">
          <form onSubmit={handleSubmit}>
            <div className="input-box">
              <input
                placeholder="Email/Phone Number"
                type="text"
                name="email"
                value={credentials.email}
                onChange={handleChange}
                required
              />
            </div>
            <div className="input-box">
              <input
                placeholder="Password"
                type={hide ? "password" : "text"}
                name="password"
                value={credentials.password}
                onChange={handleChange}
                required
              />

              <button
                onClick={togglePasswordVisibility}
                type="button"
                className="toggle-password"
              >
                {hide ? (
                  <FontAwesomeIcon icon={faEye} />
                ) : (
                  <FontAwesomeIcon icon={faEyeSlash} />
                )}
              </button>
            </div>
            <button onClick={forgotPassword} className="forgot-password">
              Forgot your password?
            </button>
            <Link to="https://docs.spring.io/spring-framework/reference/index.html">
              <p className="help">Need help?</p>
            </Link>
            <button className="login-button" type="submit">
              Sign In
            </button>
          </form>
        </div>
      </main>
    </div>
  );
};

export default Login;
