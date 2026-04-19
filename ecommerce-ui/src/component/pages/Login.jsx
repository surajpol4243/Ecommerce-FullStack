import "bootstrap/dist/css/bootstrap.min.css";
import React, { useState } from "react";

const LoginPage = () => {
 const[ formData, setFormData] = useState({
    email: "",
    password: ""
 });

 const handleChange = (e) =>{
    const {name, value} = e.target;
    setFormData({...formData, [name]: value})

 }

  const handleLogin = (e) =>{
    const {name, value} = e.target;
    setFormData({...formData, [name]: value })
  }

  return (
    <div className="container d-flex justify-content-center align-items-center vh-100">
      <div className="card p-4 shadow" style={{ width: "350px" }}>
        <h3 className="text-center mb-3">Login</h3>
        <form onSubmit={handleLogin}>
          <div className="mb-3">
            <label className="form-label">Email: </label>
            <input
              type="email"
              className="form-control"
              placeholder="Enter email"
              required
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Password</label>
            <input
              type="password"
              className="form-control"
              placeholder="Enter password"
              required
              onChange={handleChange}
            />
          </div>

          <button className="btn btn-primary w-100">Login</button>
        </form>
        <div className="text-center mt-3">
            <a href="#" className="text-decoration-none">
                Forgot Password?
            </a>
            <a href="#" className="text-decoration-none">
                Rregister
            </a>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
