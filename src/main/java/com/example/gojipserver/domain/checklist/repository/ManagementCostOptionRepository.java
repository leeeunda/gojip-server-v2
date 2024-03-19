package com.example.gojipserver.domain.checklist.repository;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.entity.cost.ManagementCostOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagementCostOptionRepository extends JpaRepository<ManagementCostOption, Long> {

    void deleteByCheckList(CheckList checkList);
}
