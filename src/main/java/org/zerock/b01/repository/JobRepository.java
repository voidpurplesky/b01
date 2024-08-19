package org.zerock.b01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.b01.domain.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}
