package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ApprovalWorkOrdersMapper;
import com.ruoyi.system.domain.ApprovalWorkOrders;
import com.ruoyi.system.service.IApprovalWorkOrdersService;

/**
 * 工单审批Service业务层处理
 * 
 * @author hao
 * @date 2024-06-01
 */
@Service
public class ApprovalWorkOrdersServiceImpl implements IApprovalWorkOrdersService 
{
    @Autowired
    private ApprovalWorkOrdersMapper approvalWorkOrdersMapper;

    /**
     * 查询工单审批
     * 
     * @param orderId 工单审批主键
     * @return 工单审批
     */
    @Override
    public ApprovalWorkOrders selectApprovalWorkOrdersByOrderId(Long orderId)
    {
        return approvalWorkOrdersMapper.selectApprovalWorkOrdersByOrderId(orderId);
    }

    /**
     * 查询工单审批列表
     * 
     * @param approvalWorkOrders 工单审批
     * @return 工单审批
     */
    @Override
    public List<ApprovalWorkOrders> selectApprovalWorkOrdersList(ApprovalWorkOrders approvalWorkOrders)
    {
        return approvalWorkOrdersMapper.selectApprovalWorkOrdersList(approvalWorkOrders);
    }

    /**
     * 新增工单审批
     * 
     * @param approvalWorkOrders 工单审批
     * @return 结果
     */
    @Override
    public int insertApprovalWorkOrders(ApprovalWorkOrders approvalWorkOrders)
    {
        return approvalWorkOrdersMapper.insertApprovalWorkOrders(approvalWorkOrders);
    }

    /**
     * 修改工单审批
     * 
     * @param approvalWorkOrders 工单审批
     * @return 结果
     */
    @Override
    public int updateApprovalWorkOrders(ApprovalWorkOrders approvalWorkOrders)
    {
        return approvalWorkOrdersMapper.updateApprovalWorkOrders(approvalWorkOrders);
    }

    /**
     * 批量删除工单审批
     * 
     * @param orderIds 需要删除的工单审批主键
     * @return 结果
     */
    @Override
    public int deleteApprovalWorkOrdersByOrderIds(Long[] orderIds)
    {
        return approvalWorkOrdersMapper.deleteApprovalWorkOrdersByOrderIds(orderIds);
    }

    /**
     * 删除工单审批信息
     * 
     * @param orderId 工单审批主键
     * @return 结果
     */
    @Override
    public int deleteApprovalWorkOrdersByOrderId(Long orderId)
    {
        return approvalWorkOrdersMapper.deleteApprovalWorkOrdersByOrderId(orderId);
    }
}
