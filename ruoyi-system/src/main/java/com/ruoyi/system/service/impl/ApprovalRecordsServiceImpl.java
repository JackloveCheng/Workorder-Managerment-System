package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ApprovalRecordsMapper;
import com.ruoyi.system.domain.ApprovalRecords;
import com.ruoyi.system.service.IApprovalRecordsService;

/**
 * 审批记录Service业务层处理
 * 
 * @author myself
 * @date 2024-06-01
 */
@Service
public class ApprovalRecordsServiceImpl implements IApprovalRecordsService 
{
    @Autowired
    private ApprovalRecordsMapper approvalRecordsMapper;

    /**
     * 查询审批记录
     * 
     * @param approvalId 审批记录主键
     * @return 审批记录
     */
    @Override
    public ApprovalRecords selectApprovalRecordsByApprovalId(Long approvalId)
    {
        return approvalRecordsMapper.selectApprovalRecordsByApprovalId(approvalId);
    }

    /**
     * 查询审批记录列表
     * 
     * @param approvalRecords 审批记录
     * @return 审批记录
     */
    @Override
    public List<ApprovalRecords> selectApprovalRecordsList(ApprovalRecords approvalRecords)
    {
        return approvalRecordsMapper.selectApprovalRecordsList(approvalRecords);
    }

    /**
     * 新增审批记录
     * 
     * @param approvalRecords 审批记录
     * @return 结果
     */
    @Override
    public int insertApprovalRecords(ApprovalRecords approvalRecords)
    {
        return approvalRecordsMapper.insertApprovalRecords(approvalRecords);
    }

    /**
     * 修改审批记录
     * 
     * @param approvalRecords 审批记录
     * @return 结果
     */
    @Override
    public int updateApprovalRecords(ApprovalRecords approvalRecords)
    {
        return approvalRecordsMapper.updateApprovalRecords(approvalRecords);
    }

    /**
     * 批量删除审批记录
     * 
     * @param approvalIds 需要删除的审批记录主键
     * @return 结果
     */
    @Override
    public int deleteApprovalRecordsByApprovalIds(Long[] approvalIds)
    {
        return approvalRecordsMapper.deleteApprovalRecordsByApprovalIds(approvalIds);
    }

    /**
     * 删除审批记录信息
     * 
     * @param approvalId 审批记录主键
     * @return 结果
     */
    @Override
    public int deleteApprovalRecordsByApprovalId(Long approvalId)
    {
        return approvalRecordsMapper.deleteApprovalRecordsByApprovalId(approvalId);
    }
}
