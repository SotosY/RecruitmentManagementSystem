package com.example.recruitmentsystemproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *Represents a Job.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
public class Job {

    /**
     * An ID that will identify the job.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "employer_id")
    private Employer employer;

    /**
     * The status of the job (Entered, Active, Expired, Cancelled).
     */
    @Column(name = "status")
    private String status;

    /**
     * The title of the job.
     */
    @Column(name = "title")
    private String title;

    /**
     * The department of the job.
     */
    @Column(name = "department")
    private String department;

    /**
     * The person who manages the job.
     */
    @Column(name = "managed_by")
    private String managedBy;

    /**
     * The location of the job.
     */
    @Column(name = "location")
    private String location;

    /**
     * The salary of the job.
     */
    @Column(name = "salary")
    private String salary;

    /**
     * The post date of the job.
     */
    @Column(name = "post_date")
    private String postDate;

    /**
     * The active date of the job.
     */
    @Column(name = "active_date")
    private String activeDate;

    /**
     * The expiry date of the job.
     */
    @Column(name = "expiry_date")
    private String expiryDate;

    /**
     * The starting date of the job.
     */
    @Column(name = "starting_date")
    private String startingDate;

    /**
     * The description of the job.
     */
    @Column(name = "description")
    private String description;

    /**
     * The requirements of the job.
     */
    @Column(name = "requirements")
    private String requirements;

    /**
     * The essential criteria of the job.
     */
    @Column(name = "essential_criteria")
    private String essentialCriteria;

    /**
     * The desirable criteria of the job.
     */
    @Column(name = "desirable_criteria")
    private String desirableCriteria;

    /**
     * The salary and other benefits of the job.
     */
    @Column(name = "salary_and_benefits")
    private String salaryAndBenefits;


}
