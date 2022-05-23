create table m_column (id uuid not null, description varchar(255), name varchar(255), version int4, table_id uuid not null, primary key (id));
create table m_schema (id uuid not null, description varchar(255), name varchar(255), version int4, primary key (id));
create table m_table (id uuid not null, description varchar(255), name varchar(255), version int4, schema_id uuid not null, primary key (id));
alter table m_column add constraint fk_column_to_table foreign key (table_id) references m_table;
alter table m_table add constraint fk_table_to_schema foreign key (schema_id) references m_schema;
