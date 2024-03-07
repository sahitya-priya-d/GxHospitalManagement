import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";

const ResetPassword = () => {
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const { id } = useParams();
  const navigate = useNavigate();
  const [hide, setHide] = useState(true);

  const handleUpdatePassword = async (e) => {
    e.preventDefault();

    const passwordRegex =
      /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/;

    if (!passwordRegex.test(newPassword)) {
      console.log("error");
      toast.error(
        "Password must be at least 8 characters and contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace",
        { autoClose: 3000 }
      );
      setNewPassword("");
      setConfirmPassword("");
      return;
    }

    if (newPassword !== confirmPassword) {
      toast.error("Passwords do not match!", { autoClose: 3000 });
      setNewPassword("");
      setConfirmPassword("");
      return;
    }

    try {
      const response = await axios.post(
        `http://localhost:8080/api/gxHospital/users/updatepassword?userId=${id}`,
        {
          newPassword,
          confirmPassword,
        }
      );
      toast.success("Password updated successfully!", { autoClose: 3000 });

      if (response.data === "Password updated successfully") {
        setTimeout(() => {
          navigate("/login");
        }, 4000);
        // if(response.data==="New password cannot be same as past passwords")
        // {
        //     toast.error(response.data,{autoClose:3000});
        // }
      }
    } catch (error) {
        console.error(error);
  
        if (error.response) {
         
          toast.error(error.response.data, { autoClose: 3000 });
        } else if (error.request) {
         
          toast.error("Failed to send request", { autoClose: 3000 });
        } else {
          
          toast.error("An unexpected error occurred", { autoClose: 3000 });
        }
      }
    };

  const togglePasswordVisibility = () => {
    setHide(!hide);
  };

  return (
    <>
      <ToastContainer />
      <main className="login-page">
        <img
          className="logo-img"
          src="https://www.outsourcetopk.com/public/assets/images/portfolio/logo/Hospital-1.jpg"
          alt=""
        />
        <h1>Reset Your Account</h1>
        <div className="login-form">
          <form onSubmit={handleUpdatePassword}>
            <div className="input-box">
              <input
                placeholder="New Password"
                type={hide ? "password" : "text"}
                value={newPassword}
                onChange={(e) => setNewPassword(e.target.value)}
              />
            </div>

            <div className="input-box">
              <input
                placeholder="Confirm Password"
                type={hide ? "password" : "text"}
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
              />{" "}
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
            <br></br>
            <br></br>
            <br></br>
            <Link to="https://docs.spring.io/spring-framework/reference/index.html">
              <p className="help">Need help?</p>
            </Link>
            <button className="login-button" type="submit">
              Reset Password
            </button>
          </form>
        </div>
      </main>
    </>
  );
};

export default ResetPassword;
