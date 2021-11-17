import React from 'react';
import httpClient from '../httpClient'

export const registerEmployer = (data) => {
    return httpClient.post("/register/e",data);
}

export const loginEmployer = (data) => {
    return httpClient.post("/login", data)
}

export const registerApplicant = (data) => {
    return httpClient.post("/register/a",data);
}

export const getJobs = (data) => {
    return httpClient.get("",data);
}

export const getLogin = () => {
    return httpClient.get("/login")
}

export const getErrorLogin = () => {
    return httpClient.get("/login?error")
}



