import React from 'react';
import httpClient from '../httpClient'

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

export const getApplicantDetails = (data) => {
    return httpClient.get("/applicant/profile", data);
}

export const getJobDetails = (id) => {
    return httpClient.get(`/applicant/application/job/${id}`);
}

export const saveApplicantProfileDetails = (data) => {
    return httpClient.post("/applicant/profile/save",data);
}


export const getApplicantApply = (id) => {
    return httpClient.get(`/applicant/application/job/${id}/application`);
}

export const applyApplicantApplication = (data) => {
    return httpClient.post("/applicant/application/job/apply",data);
}

export const getApplicationHistory = (data) => {
    return httpClient.get(`/applicant/application-history`);
}

