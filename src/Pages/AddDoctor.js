import React, { useState, useEffect } from 'react';
import Header from '../Components/Header';
import axios from 'axios';

const AddDoctor = () => {
  const [doctorData, setDoctorData] = useState({
    doctorName: '',
    image: '',
    experience: '',
    fee: '',
    department:{
        deptId:''
    },
    branch:{
        branchId:'',
    location:{
        locationId:'',
    }
    },
  });
  const[deptId,setDeptId]=useState();
  const [branchId,setBranchId]=useState();
  const [departments, setDepartments] = useState([]);
  const [locations, setLocations] = useState([]);
  const [branches, setBranches] = useState([]);
  const [locationId,setLocationId]=useState();

  useEffect(() => {
    const fetchDepartments = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/gxHospital/department/all');
        setDepartments(response.data);
      } catch (error) {
        console.error('Error fetching departments:', error);
      }
    };

    const fetchLocations = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/gxHospital/location/get-all-location');
        setLocations(response.data);
      } catch (error) {
        console.error('Error fetching locations:', error);
      }
    };

    fetchDepartments();
    fetchLocations();
  }, []);

  const handleLocationChange = async (event) => {
    const locationId = event.target.value;
    setLocationId(event.target.value)
    try {
      const response = await axios.get(`http://localhost:8080/api/gxHospital/branch/byLocation/${locationId}`);
      setBranches(response.data);
    } catch (error) {
      console.error('Error fetching branches:', error);
    }
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setDoctorData({ ...doctorData, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
        const dataToSend = {
            ...doctorData,
            department: {
              deptId: deptId
            },
            branch: {
              branchId: branchId,
              location:{
                locationId:locationId,
              }
            }
          };
          const response = await axios.post('http://localhost:8080/api/gxHospital/doctors/add-doctor', dataToSend);
      console.log('Doctor added successfully:', response.data);
      setDoctorData({
        doctorName: '',
        image: '',
        experience: '',
        fee: '',
        department:{
            deptId:''
        },
        branch:{
            branchId:''},
      });
    } catch (error) {
      console.error('Error adding doctor:', error);
    }
  };

  return (
    <div>
      <Header />
      <main className="login-page">
      <img
          className="logo-img"
          src="https://www.outsourcetopk.com/public/assets/images/portfolio/logo/Hospital-1.jpg"
          alt=""
        />
        <div className="login-form">
        <form onSubmit={handleSubmit}>
        <div className='input-box'>
         
            <input placeholder="Doctor's Name" type="text" name="doctorName" value={doctorData.doctorName} onChange={handleChange} required />
          
          </div>
         
          <div className='input-box'>
          
            <input placeholder=' Image' type="text" name="image" value={doctorData.image} onChange={handleChange} required />
         
          </div>
      
          <div className='input-box'>
         
            <input placeholder = 'Experience (in years)' type="number" name="experience" value={doctorData.experience} onChange={handleChange} required />
          </div>
     
          <div className='input-box'>
      
            <input placeholder='Appointment Fee' className='Fee' type="number" name="fee" value={doctorData.fee} onChange={handleChange} required />
         
          </div>
         
          <div className='input-box'>
         
            <select style={{marginLeft:"-12.5rem !important"}} name="deptId" value={deptId} onChange={(e)=>setDeptId(e.target.value)} required>
              <option value="">Select Department</option>
              {departments.map(department => (
                <option key={department.deptId} value={department.deptId}>{department.deptName}</option>
              ))}
            </select>
          
          </div>
     
          <div className='input-box'>
        
            <select style={{marginLeft:"-12.5rem !important"}} onChange={handleLocationChange} required>
              <option value="">Select Location</option>
              {locations.map(location => (
                <option key={location.locationId} value={location.locationId}>{location.locationName}</option>
              ))}
            </select>
          
          </div>
        
          {branches.length > 0 && (
              <div className='input-box'>
         
              <select style={{marginLeft:"-12.5rem !important"}} name="branchId" value={branchId} onChange={(e)=>setBranchId(e.target.value)} required>
                <option value="">Select Branch</option>
                {branches.map(branch => (
                  <option key={branch.branchId} value={branch.branchId}>{branch.branchName}</option>
                ))}
              </select>
          
            </div>
          )}
          <br></br>
      
          <button type="submit" className="login-button" >Add Doctor</button>
        </form>
      <br>
      </br>
      <br></br>
      <br></br>
      </div>
      </main>
    </div>
  );
};

export default AddDoctor;
