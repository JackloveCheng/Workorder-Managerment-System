package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工单信息对象 work_orders
 * 
 * @author ruoyi
 * @date 2024-06-01
 */
public class WorkOrders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单id */
    private Long orderId;

    /** 工单数量 */
    @Excel(name = "工单数量")
    private String orderNumber;

    /** 工单类型 */
    @Excel(name = "工单类型")
    private String businessType;

    /** 工单本题 */
    @Excel(name = "工单本题")
    private String title;

    /** 工单描述 */
    @Excel(name = "工单描述")
    private String description;

    /** 工单状态 */
    @Excel(name = "工单状态")
    private String status;

    /** 提交人 */
    @Excel(name = "提交人")
    private Long submitterId;

    /** 接受人 */
    @Excel(name = "接受人")
    private Long assigneeId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    /** 审批部门 */
    @Excel(name = "审批部门")
    private Long approvalRoleId;

    /** 审批完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审批完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approvalFinishedTime;

    /** 是否超时 */
    @Excel(name = "是否超时")
    private Integer isOvertime;

    /** 回答信息 */
    @Excel(name = "回答信息")
    private String receiptInfo;

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
    public void setApprovalRoleId(Long approvalRoleId) 
    {
        this.approvalRoleId = approvalRoleId;
    }

    public Long getApprovalRoleId() 
    {
        return approvalRoleId;
    }

    public void setApprovalFinishedTime(Date approvalFinishedTime)
    {
        this.approvalFinishedTime = approvalFinishedTime;
    }

    public Date getApprovalFinishedTime()
    {
        return approvalFinishedTime;
    }
    public void setIsOvertime(Integer isOvertime)
    {
        this.isOvertime = isOvertime;
    }

    public Integer getIsOvertime()
    {
        return isOvertime;
    }
    public void setReceiptInfo(String receiptInfo)
    {
        this.receiptInfo = receiptInfo;
    }

    public String getReceiptInfo()
    {
        return receiptInfo;
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
            .append("approvalRoleId", getApprovalRoleId())
            .append("approvalFinishedTime", getApprovalFinishedTime())
            .append("isOvertime", getIsOvertime())
            .append("receiptInfo", getReceiptInfo())
            .toString();
    }
}
