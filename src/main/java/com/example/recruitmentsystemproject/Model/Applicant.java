package com.example.recruitmentsystemproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;

/**
 *Represents an Applicant.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applicant")
public class Applicant {

    /**
     * An ID that will identify the applicant.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id")
    private Long applicantId;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The first name of the applicant.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the applicant.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The phone number of the applicant.
     */
    @Column(name = "phone_number")
    private String phoneNumber;

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

    /**
     * The date of birth of the applicant.
     */
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    /**
     * The gender of the applicant.
     */
    @Column(name = "gender")
    private String gender;

}
