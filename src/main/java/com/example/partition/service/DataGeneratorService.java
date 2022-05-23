package com.example.partition.service;

import com.example.partition.entity.Column;
import com.example.partition.entity.ColumnPK;
import com.example.partition.entity.Schema;
import com.example.partition.entity.Table;
import com.example.partition.repository.ColumnRepository;
import com.example.partition.repository.SchemaRepository;
import com.example.partition.repository.TableRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class DataGeneratorService {
    private SchemaRepository schemaRepository;
    private ColumnRepository columnRepository;
    private TableRepository tableRepository;
    public DataGeneratorService(SchemaRepository schemaRepository, ColumnRepository columnRepository, TableRepository tableRepository) {
        this.schemaRepository = schemaRepository;
        this.columnRepository = columnRepository;
        this.tableRepository = tableRepository;
    }

    public Iterable<Schema> createData(int schemaCount, int tableCount, int columnCount) {
        String r1 = RandomStringUtils.random(10, true, true);
        List<Schema> schemas = new ArrayList<>();

        for(int i = 1; i <= schemaCount; i++) {
            List<Table> tables = new ArrayList<>();
            for(int j = 1; j <= tableCount; j++) {
                List<Column> columns = new ArrayList<>();
                for(int k = 1; k <= columnCount; k++) {
                    columns.add(createColumn(r1));
                }
                tables.add(createTable(r1, columns));
            }
            schemas.add(createSchema(r1, tables));
        }
        Iterable<Schema> results = schemaRepository.saveAll(schemas);
        return results;
    }

    public Schema createSchema(String r1, List<Table> tables) {
        Schema schema = new Schema();
        schema.setName("schema" + r1);
        schema.setDescription("schema" + r1);
        schema.setTables(tables);
        tables.stream().forEach(table -> table.setSchema(schema));
        return schema;
    }

    public Table createTable(String r1, List<Column> columns) {
        Table table = new Table();
        table.setName("table" + r1);
        table.setDescription("table" + r1);
        table.setColumns(columns);
        columns.stream().forEach(column -> column.setTable(table));
        return table;
    }

    public Column createColumn(String r1) {
        Column column = new Column();
        column.setName("column" + r1);
        column.setDescription("column" + r1);
        return column;
    }

    public Column getColumn(UUID id) {
        return columnRepository.findColumnById(id);
    }

    public Column getColumn(UUID tableId, UUID columnId) {
        System.out.println("step 1");
        Table table = tableRepository.findById(tableId).get();
        System.out.println("step 2");
        ColumnPK columnPK = new ColumnPK();
        columnPK.setId(columnId);
        columnPK.setTable(table);
        System.out.println("step 3");
        Column column = columnRepository.findById(columnPK).get();
//        Column column = columnRepository.findColumnByIdAndTableId(columnId, tableId);
        System.out.println("step 4");
        return column;
    }

    public Column updateColumn(String id, Column updatedColumn) {
//        Column existingColumn = columnRepository.findById(id).get();
//        existingColumn.setDescription(updatedColumn.getDescription());
//        existingColumn.setName(updatedColumn.getName());
        return columnRepository.save(updatedColumn);
    }
}
