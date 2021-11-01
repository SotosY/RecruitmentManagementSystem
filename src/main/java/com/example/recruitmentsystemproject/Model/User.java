package com.example.recruitmentsystemproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
/**
 *Represents a user.
 */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * An ID that will identify the user.
     */
    @Column(name = "user_id")
    private Long userID;


    /**
     * The email of the user.
     */
    @Column(name = "email")
    private String email;


    /**
     * The password of the user.
     */
    @Column(name = "password")
    private String password;


    /**
     * Is this user active?
     */
    @Column(name = "is_active")
    private boolean isActive = true;


    /**
     * The role of the user (applicant/employer)
     */
    @Column(name = "roles")
    private String roles;
}
