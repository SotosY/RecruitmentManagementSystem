package com.example.recruitmentsystemproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *Represents an Employer.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employer")
public class Employer {

    /**
     * An ID that will identify the employer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private Long employerId;

    /**
     * A User ID that will identify the user
     */
    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The first name of the employer.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the employer.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The contact name of the employer.
     */
    @Column(name = "contact_name")
    private String contactName;

    /**
     * The company name of the employer.
     */
    @Column(name = "company")
    private String company;

    /**
     * The company email of the employer.
     */
    @Column(name = "company_email")
    private String companyEmail;

    /**
     * The business type of the employer.
     */
    @Column(name = "business_type")
    private String businessType;

    /**
     * The telephone number of the employer.
     */
    @Column(name = "telephone_number")
    private String telephoneNumber;

    /**
     * The company profile of the employer.
     */
    @Column(name = "company_profile")
    private String companyProfile;

    /**
     * The address of the applicant.
     */
    @Column(name = "address")
    private String address;

    /**
     * The country of the applicant.
     */
    @Column(name = "country")
    private String country;

    /**
     * The city of the applicant.
     */
    @Column(name = "city")
    private String city;

    /**
     * The postcode of the applicant.
     */
    @Column(name = "postcode")
    private String postcode;

}
