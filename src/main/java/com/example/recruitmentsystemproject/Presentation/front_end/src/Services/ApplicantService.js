import React from 'react';
import httpClient from './HTTPCreateService'

// GET Request - Get Applicant Dashboard
export const getApplicantDashboard = (data) => {
    let username = 'applicant'
    let password = 'pass'

    let basicAuthHeader = 'Basic ' + window.btoa(username + ":" + password)

    return httpClient.get("/applicant/dashboard", {
        headers: {
            authorization: basicAuthHeader
        }
    });
}

// GET Request - Get Applicant Profile details
export const getApplicantDetails = (data) => {
    return httpClient.get("/applicant/profile", data);
}

// GET Request - Get Job Details
export const getJobDetails = (id) => {
    return httpClient.get(`/applicant/application/job/${id}`);
}

// POST Request - Save Applicant Profile details
export const saveApplicantProfileDetails = (data) => {
    return httpClient.post("/applicant/profile/save",data);
}

// GET Request - Get Applicant Apply form
export const getApplicantApply = (id) => {
    return httpClient.get(`/applicant/application/job/${id}/application`);
}

// POST Request - Apply to application
export const applyApplicantApplication = (data) => {
    return httpClient.post("/applicant/application/job/apply",data);
}

// GET Request - Get Application History
export const getApplicationHistory = (data) => {
    return httpClient.get(`/applicant/application-history`);
}

