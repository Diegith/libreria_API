package com.diego.literatura.model;

import java.util.List;

public record LibroDTO(int id,
        String title,
        List<Author> authors,
        List<String> languages,
        int download_count) {
}