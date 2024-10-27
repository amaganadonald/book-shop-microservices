package com.luxprojects.onlineBook.dto;

import java.time.LocalDate;

public record AuthorResponseDTO(String firstName,String lastName,LocalDate bornDate, String picture) {

}
