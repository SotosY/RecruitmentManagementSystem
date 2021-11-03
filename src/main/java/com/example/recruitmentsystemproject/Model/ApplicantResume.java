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

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "applicantId")
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
    private File cv;

    /**
     * The cover letter of the applicant.
     */
    @Column(name = "cover_letter")
    private File coverLetter;
}
