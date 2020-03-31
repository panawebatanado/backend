package com.ipnetinstitute.csc394.backend.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ipnetinstitute.csc394.backend.entity.BaseEntity;

@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity> extends JpaRepository<T,Long> {

}
