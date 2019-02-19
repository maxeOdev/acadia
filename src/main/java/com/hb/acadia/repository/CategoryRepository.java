package com.hb.acadia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.acadia.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
