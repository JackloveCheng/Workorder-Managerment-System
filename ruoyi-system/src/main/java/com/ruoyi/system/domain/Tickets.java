package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工单系统对象 tickets
 * 
 * @author hao
 * @date 2024-05-30
 */
public class Tickets extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  工单号 */
    private Long ticketId;

    /** 业务性质 */
    @Excel(name = "业务性质")
    private String businessType;

    /** 工单标题 */
    @Excel(name = "工单标题")
    private String title;

    /** 进度 */
    @Excel(name = "进度")
    private String progress;

    /** 提交者 */
    @Excel(name = "提交者")
    private String submitter;

    /** 受理人员 */
    @Excel(name = "受理人员")
    private String handler;

    /** 工单状态 */
    @Excel(name = "工单状态")
    private String status;

    public void setTicketId(Long ticketId) 
    {
        this.ticketId = ticketId;
    }

    public Long getTicketId() 
    {
        return ticketId;
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
    public void setProgress(String progress) 
    {
        this.progress = progress;
    }

    public String getProgress() 
    {
        return progress;
    }
    public void setSubmitter(String submitter) 
    {
        this.submitter = submitter;
    }

    public String getSubmitter() 
    {
        return submitter;
    }
    public void setHandler(String handler) 
    {
        this.handler = handler;
    }

    public String getHandler() 
    {
        return handler;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ticketId", getTicketId())
            .append("businessType", getBusinessType())
            .append("title", getTitle())
            .append("progress", getProgress())
            .append("submitter", getSubmitter())
            .append("handler", getHandler())
            .append("status", getStatus())
            .toString();
    }
}
