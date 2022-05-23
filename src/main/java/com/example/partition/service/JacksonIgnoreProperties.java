package com.example.partition.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "keyAsNumber"})
public abstract class JacksonIgnoreProperties {
}
