package com.example.gojipserver.domain.checklist.service;

import com.example.gojipserver.domain.checklist.dto.CheckListSaveDto;
import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.repository.CheckListRepository;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CheckListService {

    private final CheckListRepository checkListRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveCheckList(Long userId, CheckListSaveDto checkListSaveDto) {

        // 엔티티 조회
        Optional<User> findUser = userRepository.findById(userId);

        CheckList entity = checkListSaveDto.toEntity();

        return entity.getId();
    }
}
