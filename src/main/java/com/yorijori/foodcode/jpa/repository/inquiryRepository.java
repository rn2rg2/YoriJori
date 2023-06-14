package com.yorijori.foodcode.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yorijori.foodcode.jpa.entity.Ingredients;
import com.yorijori.foodcode.jpa.entity.Inquiry;

public interface inquiryRepository extends JpaRepository<Inquiry, Integer>{

}
