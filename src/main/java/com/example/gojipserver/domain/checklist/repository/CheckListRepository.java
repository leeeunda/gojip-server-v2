package com.example.gojipserver.domain.checklist.repository;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {
}
