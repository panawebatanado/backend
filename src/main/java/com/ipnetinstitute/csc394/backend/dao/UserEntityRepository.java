package com.ipnetinstitute.csc394.backend.dao;
import javax.transaction.Transactional;
import com.ipnetinstitute.csc394.backend.entity.User;

//@NoRepositoryBean
@Transactional
public interface UserEntityRepository extends BaseEntityRepository<User> {

}
