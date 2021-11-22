import React from 'react';
import httpClient from '../httpClient'

export const getApplicantDashboard = (data) => {
    return httpClient.get("/applicant/dashboard");
}

export const getApplicantDetails = (data) => {
    return httpClient.get("/applicant/profile", data);
}

export const getJobDetails = (id) => {
    return httpClient.get(`/applicant/application/job/${id}`);
}

export const saveApplicantProfileDetails = (data) => {
    return httpClient.post("/applicant/profile/save",data);
}