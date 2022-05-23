package com.example.partition.repository;

import com.example.partition.entity.Column;
import com.example.partition.entity.ColumnPK;
import com.example.partition.entity.Schema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ColumnRepository extends CrudRepository<Column, ColumnPK> {
    Column findColumnById(UUID id);

    @Query(value = "select * from m_column where id = :id and table_id = :tableId", nativeQuery = true)
    Column findColumnByIdAndTableId(UUID id, UUID tableId);
}
