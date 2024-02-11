package com.example.gojipserver.domain.collection.controller;

import com.example.gojipserver.domain.collection.dto.CollectionSaveDto;
import com.example.gojipserver.domain.collection.service.CollectionService;
import com.example.gojipserver.global.oauth2.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping
    public Long saveCollection(@AuthenticationPrincipal UserPrincipal userPrincipal, CollectionSaveDto collectionSaveDto) {
        return collectionService.saveCollection(userPrincipal, collectionSaveDto);
    }

}
