package com.example.gojipserver.domain.checklist_collection.repository;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist_collection.entity.CheckListCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckListCollectionRepository extends JpaRepository<CheckListCollection, Long> {

    void deleteByCheckList(CheckList checkList);
}
