package com.emids.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.emids.sms.model.Writer;
import org.springframework.stereotype.Repository;
@Repository
public interface WriterRepository extends JpaRepository<Writer, Integer> {
    Writer findByName(String email);
}
