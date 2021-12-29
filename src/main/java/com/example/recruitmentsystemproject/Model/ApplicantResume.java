package com.example.recruitmentsystemproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;

/**
 *Represents Applicant's resume.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applicant_resume")
public class ApplicantResume {

    /**
     * An ID that will identify applicant's resume.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long resumeId;

    /**
     * An Applicant ID that will identify the Applicant
     */
    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    /**
     * The experience of the applicant.
     */
    @Column(name = "experience")
    private String experience;

    /**
     * The education of the applicant.
     */
    @Column(name = "education")
    private String education;

    /**
     * The CV of the applicant.
     */
    @Column(name = "cv")
    private String cv;

    /**
     * The cover letter of the applicant.
     */
    @Column(name = "cover_letter")
    private String coverLetter;
}
