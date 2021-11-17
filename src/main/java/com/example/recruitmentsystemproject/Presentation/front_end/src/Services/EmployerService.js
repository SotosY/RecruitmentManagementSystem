import React from 'react';
import httpClient from '../httpClient'

export const getEmployerDetails = (data) => {
    return httpClient.get("/employer/profile",data);
}