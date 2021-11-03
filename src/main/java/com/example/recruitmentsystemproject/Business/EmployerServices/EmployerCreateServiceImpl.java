package com.example.recruitmentsystemproject.Business.EmployerServices;

import com.example.recruitmentsystemproject.Model.Employer;
import com.example.recruitmentsystemproject.Persistence.EmployerRepositories.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerCreateServiceImpl implements EmployerCreateService{

    private final EmployerRepo employerRepo;

    public EmployerCreateServiceImpl(EmployerRepo employerRepo) {
        this.employerRepo = employerRepo;
    }

    @Override
    public void saveEmployer(Employer employer) {
        employerRepo.saveEmployer(employer);
    }
}
