package com.wynnblevins.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wynnblevins.springboot.entity.WikimediaData;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long>{

}
