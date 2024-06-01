package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工单审批对象 work_orders
 * 
 * @author hao
 * @date 2024-06-01
 */
public class ApprovalWorkOrders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单ID */
    @Excel(name = "工单ID")
    private Long orderId;

    /** 工单标识符 */
    @Excel(name = "工单标识符")
    private String orderNumber;

    /** 工单类型 */
    @Excel(name = "工单类型")
    private String businessType;

    /** 工单标题 */
    @Excel(name = "工单标题")
    private String title;

    /** 工单描述 */
    @Excel(name = "工单描述")
    private String description;

    /** 工单状态 */
    @Excel(name = "工单状态")
    private String status;

    /** 提交者 */
    @Excel(name = "提交者")
    private Long submitterId;

    /** 接受者 */
    private Long assigneeId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setOrderNumber(String orderNumber) 
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() 
    {
        return orderNumber;
    }
    public void setBusinessType(String businessType) 
    {
        this.businessType = businessType;
    }

    public String getBusinessType() 
    {
        return businessType;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSubmitterId(Long submitterId) 
    {
        this.submitterId = submitterId;
    }

    public Long getSubmitterId() 
    {
        return submitterId;
    }
    public void setAssigneeId(Long assigneeId) 
    {
        this.assigneeId = assigneeId;
    }

    public Long getAssigneeId() 
    {
        return assigneeId;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("orderNumber", getOrderNumber())
            .append("businessType", getBusinessType())
            .append("title", getTitle())
            .append("description", getDescription())
            .append("status", getStatus())
            .append("submitterId", getSubmitterId())
            .append("assigneeId", getAssigneeId())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
