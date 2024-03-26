package com.example.gojipserver.domain.checklist.repository;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.entity.option.OuterOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OuterOptionRepository extends JpaRepository<OuterOption, Long> {
    void deleteByCheckList(CheckList checkList);
}
