package com.example.partition.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.util.UUID;

@Data
@Entity
@javax.persistence.Table(name="m_column")
@IdClass(ColumnPK.class)
public class Column {
    @Version
    private Integer version;

    @Id
    @GeneratedValue
    @javax.persistence.Column(name="id")
    private UUID id;

    @javax.persistence.Column(name="name")
    private String name;

    @javax.persistence.Column(name="description")
    private String description;

    @Schema
    @ManyToOne
    @JoinColumn(name="table_id", nullable = false, foreignKey = @ForeignKey(name="fk_column_to_table"))
    @JsonIgnoreProperties(value = {"columns"}, allowSetters = true)
    private Table table;


}
