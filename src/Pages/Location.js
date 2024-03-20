import React from 'react'
import { useNavigate } from 'react-router-dom';

const Location = () => {
    const navigate = useNavigate();
    const [selectedOption, setSelectedOption] = useState("");
  
    const handleContinue = () => {
      if (selectedOption === "login") {
        navigate("/login");
      } else if (selectedOption === "register") {
        navigate("/create-account");
      }
    };
  
    return (
      <>
        <main className="welcome-page">
          <img
            className="logo-img"
            src="https://www.outsourcetopk.com/public/assets/images/portfolio/logo/Hospital-1.jpg"
            alt=""
          />
          <h1>Welcome to GxHealth</h1>
          <div className="radio-group">
            <p>Please choose an option:</p>
  
            <div className="radio-button">
              <label>
                <input
                  type="radio"
                  value="login"
                  checked={selectedOption === "login"}
                  onChange={() => setSelectedOption("login")}
                />
                {"  "} Login
              </label>
            </div>
            <div className="radio-button">
              <label className="register-label">
                <input
                  className="radio-input"
                  type="radio"
                  value="createAccount"
                  checked={selectedOption === "register"}
                  onChange={() => setSelectedOption("register")}
                />
                {"  "} Register
              </label>
            </div>
  
            <button className="continue-button" onClick={handleContinue}>
              Continue &#8594;
            </button>
          </div>
          <Link to="https://docs.spring.io/spring-framework/reference/index.html">
            <p className="help-link">Need help?</p>
          </Link>
        </main>
      </>
    );
  };
  

export default Location
