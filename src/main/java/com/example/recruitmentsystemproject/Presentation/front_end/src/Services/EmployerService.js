import React from 'react';
import httpClient from '../httpClient'

export const getEmployerDetails = (data) => {
    return httpClient.get("/employer/profile", data);
}

export const saveEmployerProfileDetails = (data) => {
    return httpClient.post("/employer/profile/save",data);
}