package com.example.recruitmentsystemproject.Business.ApplicationServices;

import com.example.recruitmentsystemproject.Model.Application;
import com.example.recruitmentsystemproject.Persistence.ApplicationRepositories.ApplicationRepo;
import org.springframework.stereotype.Service;

@Service
public class ApplicationCreateServiceImpl implements ApplicationCreateService {

    private final ApplicationRepo applicationRepo;

    public ApplicationCreateServiceImpl(ApplicationRepo applicationRepo) {
        this.applicationRepo = applicationRepo;
    }

    /**
     * Save Application
     * @param application
     */
    @Override
    public void saveApplication(Application application) {
        applicationRepo.saveApplication(application);
    }
}
