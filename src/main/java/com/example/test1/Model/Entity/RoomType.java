package com.example.test1.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="RoomType")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="classPrice", nullable=false)
    private Double classPrice;


    @Column(name="className", nullable = false)
    private String className;


    @Column(name="capacity", nullable=false)
    private Integer capacity;

    @Column(name="description" ,nullable=true)
    private String description;



}
