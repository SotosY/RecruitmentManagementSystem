package com.example.recruitmentsystemproject.Structurizr;

import com.structurizr.Workspace;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.ReferencedTypesSupportingTypesStrategy;
import com.structurizr.analysis.SpringComponentFinderStrategy;
import com.structurizr.api.StructurizrClient;
import com.structurizr.model.*;
import com.structurizr.view.*;
import org.junit.jupiter.api.Test;

/**
 * Adapted from code example of previous project (Welsh Rowing) [Accessed: 29 December 2021]
 */
public class GenerateModel {

    public void generateModel() throws Exception {

        //set up workspace and model
        //these are the core objects of our
        Workspace workspace = new Workspace("Recruitment system", "Spring Boot Project");
        Model model = workspace.getModel();

        // create the basic model
        SoftwareSystem recruitmentSystem = model.addSoftwareSystem("RecruitmentSystem", "Recruitment system Application");
        Person applicant = model.addPerson("Applicant", "A person who is applying for a job");
        Person employer = model.addPerson("Employer", "A person who opens jobs and manages applicants");
        Person user = model.addPerson("User", "A user who logs in to the system");

        applicant.uses(recruitmentSystem, "Uses");
        employer.uses(recruitmentSystem, "Uses");
        user.uses(recruitmentSystem, "Uses");


        Container webApplication = recruitmentSystem.addContainer(
                "Spring Boot Application", "The web application", "Embedded web container. Tomcat 9.0.39");
        Container relationalDatabase = recruitmentSystem.addContainer(
                "Relational Database", "Stores information regarding the products.", "MySQL");
        applicant.uses(webApplication, "Uses", "HTTP");
        employer.uses(webApplication, "Uses", "HTTP");
        user.uses(webApplication, "Uses", "HTTP");

        webApplication.uses(relationalDatabase, "Reads from and writes to", "JDBC, port 3306");

        // and now automatically find all Spring @Controller, @Component, @Service and @Repository components
        ComponentFinder componentFinder = new ComponentFinder(
                webApplication,
                "com.example.recruitmentsystemproject",
                new SpringComponentFinderStrategy(
                        new ReferencedTypesSupportingTypesStrategy()
                ));

        componentFinder.findComponents();

        // connect the applicant to all of the Spring MVC controllers
        webApplication.getComponents().stream()
                .filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_MVC_CONTROLLER))
                .forEach(c -> applicant.uses(c, "Uses", "HTTP"));

        // connect the employer to all of the Spring MVC controllers
        webApplication.getComponents().stream()
                .filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_MVC_CONTROLLER))
                .forEach(c -> employer.uses(c, "Uses", "HTTP"));

        // connect the user to all of the Spring MVC controllers
        webApplication.getComponents().stream()
                .filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_MVC_CONTROLLER))
                .forEach(c -> user.uses(c, "Uses", "HTTP"));

        // connect all of the repository components to the relational database
        webApplication.getComponents().stream()
                .filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_REPOSITORY))
                .forEach(c -> c.uses(relationalDatabase, "Reads from and writes to", "JPA"));

        //Let's see what is being scanned
        for (Component c : webApplication.getComponents()) {
            System.out.println(c.getRelationships());
        }

        // finally create some views
        ViewSet viewSet = workspace.getViews();
        SystemContextView contextView = viewSet.createSystemContextView(recruitmentSystem, "context", "The System Context diagram for the Recruitment system");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();

        ContainerView containerView = viewSet.createContainerView(recruitmentSystem, "containers", "The Containers diagram for the Recruitment system");
        containerView.addAllPeople();
        containerView.addAllSoftwareSystems();
        containerView.addAllContainers();

        ComponentView componentView = viewSet.createComponentView(webApplication, "components", "The Components diagram for the Recruitment system");
        componentView.addAllComponents();
        componentView.addAllPeople();
        componentView.add(relationalDatabase);

        // link the architecture model with the code
        for (Component component : webApplication.getComponents()) {
            for (CodeElement codeElement : component.getCode()) {
                String sourcePath = codeElement.getUrl();
                if (sourcePath != null) {
                    codeElement.setUrl(
                            "https://git.cardiff.ac.uk/c1847017/recruitement_system/-/tree/main");
                }
            }
        }

        // rather than creating a component model for the database, let's simply link to the DDL
        // (this is really just an example of linking an arbitrary element in the model to an external resource)
        //relationalDatabase.setUrl("https://github.com/spring-projects/spring-petclinic/tree/master/src/main/resources/db/hsqldb");

        // tag and style some elements
        recruitmentSystem.addTags("Recruitment System");
        webApplication.getComponents().stream().filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_MVC_CONTROLLER)).forEach(c -> c.addTags("Spring MVC Controller"));
        webApplication.getComponents().stream().filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_MVC_CONTROLLER)).forEach(c -> c.addTags("Spring REST Controller"));

        webApplication.getComponents().stream().filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_SERVICE)).forEach(c -> c.addTags("Spring Service"));
        webApplication.getComponents().stream().filter(c -> c.getTechnology().equals(SpringComponentFinderStrategy.SPRING_REPOSITORY)).forEach(c -> c.addTags("Spring Repository"));
        relationalDatabase.addTags("Database");


        Styles styles = viewSet.getConfiguration().getStyles();
        styles.addElementStyle("Recruitment system").background("#6CB33E").color("#ffffff");
        styles.addElementStyle(Tags.PERSON).background("#519823").color("#ffffff").shape(Shape.Person);
        styles.addElementStyle(Tags.CONTAINER).background("#91D366").color("#ffffff");
        styles.addElementStyle("Database").shape(Shape.Cylinder);

        styles.addElementStyle("Spring REST Controller").background("#D4FFC0").color("#000000");

        styles.addElementStyle("Spring MVC Controller").background("#D4F3C0").color("#000000");
        styles.addElementStyle("Spring Service").background("#6CB33E").color("#000000");
        styles.addElementStyle("Spring Repository").background("#95D46C").color("#000000");

        StructurizrClient structurizrClient = new StructurizrClient("b9cf2c98-f298-4f3c-ac94-5b91cb58f0c8", "6dcdfc02-ddfb-4808-8116-edf667017897");
        structurizrClient.putWorkspace( 71432, workspace);
    }


}
