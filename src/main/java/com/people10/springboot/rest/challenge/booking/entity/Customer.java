package com.people10.springboot.rest.challenge.booking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;

    @Column(length = 50, unique = true, nullable = false)
    @Email(message = "Email should be valid")
    @NotEmpty @NotNull
    private String email;

    @Column(length = 10, nullable = false)
    @Size(min = 8, max = 10, message = "Password should be between 8 to 10 characters")
    @NotEmpty @NotNull
    private String password;

}
