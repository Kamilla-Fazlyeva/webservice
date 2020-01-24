package com.fazlyeva.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(name = "Advert")
public class Advert {

    private Integer id;
    private Integer person_id;
    private String header;
    private String body;
    private String category;
    private String phone;
    private LocalDateTime dateTime;

    public Advert() {
    }

    public Advert(int id, Integer person_id, String header, String body, String category,
                  String phone, LocalDateTime dateTime) {
        this.id = id;
        this.person_id = person_id;
        this.header = header;
        this.body = body;
        this.category = category;
        this.phone = phone;
        this.dateTime = dateTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Schema(name = "Advert's unique id in database")
    public Integer getId() {
        return id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    @Schema(name = "Person's id in that connected to advert's id")
    public Integer getPerson_id() {
        return person_id;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Schema(name = "Advert's header")
    public String getHeader() {
        return header;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Schema(name = "Advert's body")
    public String getBody() {
        return body;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Schema(name = "Advert's category: is person individual or entity (company)")
    public String getCategory() {
        return category;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Schema(name = "Person's phone for an advert")
    public String getPhone() {
        return phone;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Schema(name = "Local time when advert was created")
    public LocalDateTime getDateTime() {
        dateTime = LocalDateTime.now();
        return dateTime;
    }
}
