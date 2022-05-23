package com.example.partition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@javax.persistence.Table(name="m_table")
public class Table {
    @Id
    @GeneratedValue
    @javax.persistence.Column(name = "id")
    private UUID id;

    @Version
    private Integer version;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"table"}, allowSetters = true)
    private List<com.example.partition.entity.Column> columns = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="schema_id", nullable = false, foreignKey = @ForeignKey(name="fk_table_to_schema"))
    @JsonIgnoreProperties(value = {"tables"})
    private Schema schema;
    
}
