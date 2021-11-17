import React from 'react';
import httpClient from '../httpClient'

export const getApplicantDashboard = () => {
    return httpClient.get("/applicant/dashboard");
}