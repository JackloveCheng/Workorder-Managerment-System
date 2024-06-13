package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工单协助对象 assist_order
 * 
 * @author lin
 * @date 2024-06-13
 */
public class AssistOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 协助id */
    private Long assistanceID;

    /** 工单id */
    @Excel(name = "工单id")
    private Long workOrderID;

    /** 部门id */
    @Excel(name = "部门id")
    private Long assistingDepartment;

    /** 协助信息 */
    @Excel(name = "协助信息")
    private String assistInfo;

    public void setAssistanceID(Long assistanceID) 
    {
        this.assistanceID = assistanceID;
    }

    public Long getAssistanceID() 
    {
        return assistanceID;
    }
    public void setWorkOrderID(Long workOrderID) 
    {
        this.workOrderID = workOrderID;
    }

    public Long getWorkOrderID() 
    {
        return workOrderID;
    }
    public void setAssistingDepartment(Long assistingDepartment) 
    {
        this.assistingDepartment = assistingDepartment;
    }

    public Long getAssistingDepartment() 
    {
        return assistingDepartment;
    }
    public void setAssistInfo(String assistInfo) 
    {
        this.assistInfo = assistInfo;
    }

    public String getAssistInfo() 
    {
        return assistInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("assistanceID", getAssistanceID())
            .append("workOrderID", getWorkOrderID())
            .append("assistingDepartment", getAssistingDepartment())
            .append("assistInfo", getAssistInfo())
            .toString();
    }
}
