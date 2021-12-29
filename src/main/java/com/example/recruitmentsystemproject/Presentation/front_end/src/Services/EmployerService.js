import React from 'react';
import httpClient from './HTTPCreateService'

// GET Request - Get Employer Profile details
export const getEmployerDetails = (data) => {
    return httpClient.get("/employer/profile", data);
}

// POST Request - Save Employer Profile details
export const saveEmployerProfileDetails = (data) => {
    return httpClient.post("/employer/profile/save",data);
}

// GET Request - Get Employer Vacancy form
export const getVacancyPage = (data) => {
    return httpClient.get("/employer/vacancies", data);
}

// POST Request - Save Vacancy details
export const saveJobDetails = (data) => {
    return httpClient.post("/employer/vacancy/save",data);
}

// GET Request - Get Vacancy History
export const getVacancyHistoryPage = (data) => {
    return httpClient.get("/employer/vacancy-history", data);
}

// GET Request - Get Vacancy applications
export const getApplicationDetails = (id) => {
    return httpClient.get(`/employer/vacancy-history/${id}`);
}

// POST Request - Accept an Applicant
export const acceptAnApplicant = (id) => {
    return httpClient.post("/employer/vacancy-history/accept",id);
}

// POST Request - Reject an Applicant
export const rejectAnApplicant = (id) => {
    return httpClient.post("/employer/vacancy-history/reject",id);
}