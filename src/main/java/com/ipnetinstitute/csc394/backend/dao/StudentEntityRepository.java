package com.ipnetinstitute.csc394.backend.dao;
import javax.transaction.Transactional;
import com.ipnetinstitute.csc394.backend.entity.Student;
//@NoRepositoryBean
@Transactional
public interface StudentEntityRepository extends BaseEntityRepository<Student> {

}
