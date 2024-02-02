package com.marufh.localizeapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@Entity
@Table(name = "LANGUAGE")
public class Language {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;

    @Column(length = 32, unique = true)
    private String name;

    @Column(length = 2, unique = true)
    private String code;
}
