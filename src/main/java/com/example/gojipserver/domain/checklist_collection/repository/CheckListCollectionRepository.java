package com.example.gojipserver.domain.checklist_collection.repository;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist_collection.entity.CheckListCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CheckListCollectionRepository extends JpaRepository<CheckListCollection, Long> {

    void deleteByCheckList(CheckList checkList);

    @Modifying
    @Query("DELETE FROM CheckListCollection cc WHERE cc.checkList.id = :checkListId AND cc.collection.id = :collectionId")
    void deleteByCheckListIdAndCollectionId(Long checkListId, Long collectionId);

    @Query("SELECT cc.collection.id FROM CheckListCollection cc WHERE cc.checkList.id = :checkListId")
    List<Long> findCollectionIdByCheckListId(Long checkListId);
}
