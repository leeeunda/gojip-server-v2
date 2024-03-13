package com.example.gojipserver.domain.checklist.repository;

import com.example.gojipserver.domain.checklist.entity.option.InnerOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InnerOptionRepository extends JpaRepository<InnerOption, Long> {
}
