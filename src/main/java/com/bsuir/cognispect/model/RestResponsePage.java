package com.bsuir.cognispect.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class RestResponsePage<T> {
    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int numberOfElements;
    private int pageNumber;
    private int pageSize;

    public RestResponsePage(Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.numberOfElements = page.getNumberOfElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
    }
}
