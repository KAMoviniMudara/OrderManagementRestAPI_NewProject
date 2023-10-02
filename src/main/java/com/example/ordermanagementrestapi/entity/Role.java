package com.example.ordermanagementrestapi.entity;

import com.example.ordermanagementrestapi.entity.enums.ERole;
import javassist.bytecode.MethodParametersAttribute;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Enumerated(EnumType.STRING)
   @Column(length = 20)
   private ERole name;
    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }


    public MethodParametersAttribute getName() {
        return null;
    }
}

