package com.example.partition.repository;

import com.example.partition.entity.Schema;
import com.example.partition.entity.Table;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TableRepository extends CrudRepository<Table, UUID> {
}
