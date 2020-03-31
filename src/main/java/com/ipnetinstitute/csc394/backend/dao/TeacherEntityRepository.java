package com.ipnetinstitute.csc394.backend.dao;
import javax.transaction.Transactional;
import com.ipnetinstitute.csc394.backend.entity.Teacher;

//@NoRepositoryBean
@Transactional
public interface TeacherEntityRepository extends BaseEntityRepository<Teacher> {

}
