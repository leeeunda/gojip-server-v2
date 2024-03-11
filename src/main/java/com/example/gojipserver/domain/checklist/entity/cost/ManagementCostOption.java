package com.example.gojipserver.domain.checklist.entity.cost;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//관리비 옵션
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ManagementCostOption extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "management_cost_option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_list_id", nullable = false)
    private CheckList checkList;

    @Column(name = "option_name")
    private ManagementCostOptionName optionName;

}
