package com.be.repository;

import com.be.common_api.Catogory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CatogoryRepository extends JpaRepository<Catogory, Long>, JpaSpecificationExecutor<Catogory> {
}