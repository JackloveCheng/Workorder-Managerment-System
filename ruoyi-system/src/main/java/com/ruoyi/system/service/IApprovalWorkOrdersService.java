package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ApprovalWorkOrders;

/**
 * 工单审批Service接口
 * 
 * @author hao
 * @date 2024-06-01
 */
public interface IApprovalWorkOrdersService 
{
    /**
     * 查询工单审批
     * 
     * @param orderId 工单审批主键
     * @return 工单审批
     */
    public ApprovalWorkOrders selectApprovalWorkOrdersByOrderId(Long orderId);

    /**
     * 查询工单审批列表
     * 
     * @param approvalWorkOrders 工单审批
     * @return 工单审批集合
     */
    public List<ApprovalWorkOrders> selectApprovalWorkOrdersList(ApprovalWorkOrders approvalWorkOrders);

    /**
     * 新增工单审批
     * 
     * @param approvalWorkOrders 工单审批
     * @return 结果
     */
    public int insertApprovalWorkOrders(ApprovalWorkOrders approvalWorkOrders);

    /**
     * 修改工单审批
     * 
     * @param approvalWorkOrders 工单审批
     * @return 结果
     */
    public int updateApprovalWorkOrders(ApprovalWorkOrders approvalWorkOrders);

    /**
     * 批量删除工单审批
     * 
     * @param orderIds 需要删除的工单审批主键集合
     * @return 结果
     */
    public int deleteApprovalWorkOrdersByOrderIds(Long[] orderIds);

    /**
     * 删除工单审批信息
     * 
     * @param orderId 工单审批主键
     * @return 结果
     */
    public int deleteApprovalWorkOrdersByOrderId(Long orderId);
}
