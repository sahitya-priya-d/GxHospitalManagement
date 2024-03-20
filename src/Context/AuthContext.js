import React, { createContext, useContext, useState, useEffect } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  const login = ({ token, role }) => {
    sessionStorage.setItem("token", token);
    sessionStorage.setItem("role", role); // Store role in session storage
    setUser({ token, role });
  };

  const logout = () => {
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("role"); // Remove role from session storage
    setUser(null);
  };

  useEffect(() => {
    const storedToken = sessionStorage.getItem("token");
    const storedRole = sessionStorage.getItem("role"); // Retrieve role from session storage
    if (storedToken) {
      setUser({ token: storedToken, role: storedRole }); // Set both token and role in user state
    }
  }, []);

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};
