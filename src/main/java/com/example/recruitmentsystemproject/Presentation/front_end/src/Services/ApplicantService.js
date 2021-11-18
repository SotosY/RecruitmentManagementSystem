import React from 'react';
import httpClient from '../httpClient'

export const getApplicantDashboard = (data) => {
    return httpClient.get("/applicant/dashboard");
}