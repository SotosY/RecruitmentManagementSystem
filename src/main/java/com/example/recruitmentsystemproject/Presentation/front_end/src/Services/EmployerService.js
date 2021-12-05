import React from 'react';
import httpClient from '../httpClient'

export const getEmployerDetails = (data) => {
    return httpClient.get("/employer/profile", data);
}

export const saveEmployerProfileDetails = (data) => {
    return httpClient.post("/employer/profile/save",data);
}

export const getVacancyPage = (data) => {
    return httpClient.get("/employer/vacancies", data);
}


export const saveJobDetails = (data) => {
    return httpClient.post("/employer/vacancy/save",data);
}

export const getVacancyHistoryPage = (data) => {
    return httpClient.get("/employer/vacancy-history", data);
}