package com.marufh.localizeapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@Setter
@Getter
@Entity
@Table(name = "PRODUCT_LOCALIZATION")
public class ProductLocal {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;

    private String name;

    private String description;

    @ManyToOne
    private Language language;

    @JsonManagedReference
    @ManyToOne
    private Product product;
}
