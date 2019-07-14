package com.laesunk.webservice.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByMsrl(long msrl);
    public User findByUid(String uid);
    public void deleteByMsrl(long msrl);
}
