package com.example.partition.controller;

import com.example.partition.entity.Column;
import com.example.partition.entity.Schema;
import com.example.partition.service.DataGeneratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DataGeneratorController {
    private DataGeneratorService dataGeneratorService;

    public DataGeneratorController(DataGeneratorService dataGeneratorService) {
        this.dataGeneratorService = dataGeneratorService;
    }

    @GetMapping(value = "/create")
    public Iterable<Schema> create(@RequestParam(defaultValue = "1") int schemaCount,
                         @RequestParam(defaultValue = "1") int tableCount,
                         @RequestParam(defaultValue = "1") int columnCount) {
        Iterable<Schema> schemas = dataGeneratorService.createData(schemaCount, tableCount, columnCount);
        return null;
    }

    @GetMapping(value = "/columns/{id}")
    public Column getColumn(@PathVariable UUID id) {
        Column updatedColumn = dataGeneratorService.getColumn(id);
        return updatedColumn;
    }

    @GetMapping(value = "/tables/{tableId}/columns/{columnId}")
    public Column getColumn(@PathVariable UUID tableId, @PathVariable UUID columnId) {
        Column updatedColumn = dataGeneratorService.getColumn(tableId, columnId);
        return updatedColumn;
    }

    @PutMapping(value = "/columns/{id}")
    public Column updateColumn(@PathVariable String id, @RequestBody Column column) {
        Column updatedColumn = dataGeneratorService.updateColumn(id, column);
        return updatedColumn;
    }

}
