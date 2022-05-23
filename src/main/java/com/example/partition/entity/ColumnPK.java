package com.example.partition.entity;

import lombok.Data;

import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.UUID;

@Data
@IdClass(Column.class)
public class ColumnPK implements Serializable {
        private UUID id;
        private Table table;
}
