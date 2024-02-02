package com.marufh.localizeapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "PRODUCT")
public class Product {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;

    private String name;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @MapKeyColumn(name = "lang", length = 2)
    private Map<String, ProductLocal> productLocals;
}
