package com.example.test1.Model.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( nullable = false, length = 50)
    private String firstName;

    @Column( nullable = false, length = 50)
    private String lastName;

    @Column( nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column( nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate profileCreationDate;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

//    @Column(name = "position_id", nullable = false, length = 100)
//    private int position_id ;


    @ManyToOne
    @JoinColumn(name = "brnach_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_branch"))
    private Branch branch;



    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_POSITION"))
    private Position position;
}




//    @Column(name = "position_id", nullable = false)
//    private int positionID;
//
//
//
//    @ManyToOne
//    @JoinColumn(name = "position_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_MANAGER"))
//    private position position;
