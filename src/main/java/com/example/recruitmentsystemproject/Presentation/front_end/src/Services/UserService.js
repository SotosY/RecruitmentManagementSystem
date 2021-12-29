import React from 'react';
import httpClient from './HTTPCreateService'

// POST Request - Register an Employer
export const registerEmployer = (data) => {
    return httpClient.post("/register/e",data);
}

// POST Request - Login a user
export const loginEmployer = (data) => {
    return httpClient.post("/login", data)
}

// POST Request - Register an Applicant
export const registerApplicant = (data) => {
    return httpClient.post("/register/a",data);
}

// GET Request - Get all active Jobs
export const getJobs = (data) => {
    return httpClient.get("",data);
}

// GET Request - Get Login form
export const getLogin = () => {
    return httpClient.get("/login")
}

// GET Request - Get Login Error form
export const getErrorLogin = () => {
    return httpClient.get("/login?error")
}



