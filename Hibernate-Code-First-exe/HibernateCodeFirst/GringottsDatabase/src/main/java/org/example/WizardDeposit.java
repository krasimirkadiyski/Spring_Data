package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "wizard_deposits")
public class WizardDeposit {
    @Id
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50)
    private String first_name;
    @Column(length = 60, nullable = false)
    private String last_name;
    @Column(length = 1000)
    private String notes;
    @Column(nullable = false)
    private int age;
    @Column(length = 100)
    private String magic_wand_creator;
    @Column
    private int magic_wand_size;
    @Column(length = 20)
    private String deposit_group;
    @Column
    private LocalDate deposit_start_date;
    @Column
    private double deposit_amount;
    @Column
    private double deposit_interest;
    @Column
    private double deposit_charge;
    @Column
    private LocalDate deposit_expiration_date;
    @Column
    private boolean is_deposit_expired;

}
