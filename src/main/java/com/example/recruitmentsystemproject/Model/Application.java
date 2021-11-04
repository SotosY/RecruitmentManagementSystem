package com.example.recruitmentsystemproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *Represents Applicant's resume.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "application")
public class Application {

    /**
     * An ID that will identify applicant's resume.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "applicantId")
    private Applicant applicant;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "jobId")
    private Job job;

    /**
     * The date that the applicant applied.
     */
    @Column(name = "apply_date")
    private String applyDate;

    /**
     * The status of the application.
     */
    @Column(name = "application_status")
    private String applicationStatus;

}
