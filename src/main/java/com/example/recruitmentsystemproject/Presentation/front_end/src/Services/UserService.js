import React from 'react';
import httpClient from '../httpClient'

export const registerEmployer = (data) => {
    return httpClient.post("/register/e",data);
}

export const registerApplicant = (data) => {
    return httpClient.post("/register/a",data);
}



