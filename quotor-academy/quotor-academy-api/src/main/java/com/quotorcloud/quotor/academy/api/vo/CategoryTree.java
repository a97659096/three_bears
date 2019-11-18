package com.quotorcloud.quotor.academy.api.vo;

import com.quotorcloud.quotor.academy.api.entity.ConditionPro;
import com.quotorcloud.quotor.common.core.util.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryTree extends TreeNode {


    private Integer type;

    private String shopId;

    private Boolean disabled;

    private List<ConditionPro> conditionPros;

}
