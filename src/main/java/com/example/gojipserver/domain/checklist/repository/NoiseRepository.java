package com.example.gojipserver.domain.checklist.repository;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.entity.room.Noise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoiseRepository extends JpaRepository<Noise, Long> {

    void deleteByCheckList(CheckList checkList);
}
