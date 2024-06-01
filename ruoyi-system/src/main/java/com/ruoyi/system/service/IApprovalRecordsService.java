package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ApprovalRecords;

/**
 * 审批记录Service接口
 * 
 * @author myself
 * @date 2024-06-01
 */
public interface IApprovalRecordsService 
{
    /**
     * 查询审批记录
     * 
     * @param approvalId 审批记录主键
     * @return 审批记录
     */
    public ApprovalRecords selectApprovalRecordsByApprovalId(Long approvalId);

    /**
     * 查询审批记录列表
     * 
     * @param approvalRecords 审批记录
     * @return 审批记录集合
     */
    public List<ApprovalRecords> selectApprovalRecordsList(ApprovalRecords approvalRecords);

    /**
     * 新增审批记录
     * 
     * @param approvalRecords 审批记录
     * @return 结果
     */
    public int insertApprovalRecords(ApprovalRecords approvalRecords);

    /**
     * 修改审批记录
     * 
     * @param approvalRecords 审批记录
     * @return 结果
     */
    public int updateApprovalRecords(ApprovalRecords approvalRecords);

    /**
     * 批量删除审批记录
     * 
     * @param approvalIds 需要删除的审批记录主键集合
     * @return 结果
     */
    public int deleteApprovalRecordsByApprovalIds(Long[] approvalIds);

    /**
     * 删除审批记录信息
     * 
     * @param approvalId 审批记录主键
     * @return 结果
     */
    public int deleteApprovalRecordsByApprovalId(Long approvalId);
}
