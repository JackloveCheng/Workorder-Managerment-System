package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WorkOrders;

/**
 * 工单信息Mapper接口
 * 
 * @author hao
 * @date 2024-06-01
 */
public interface WorkOrdersMapper 
{
    /**
     * 查询工单信息
     * 
     * @param orderId 工单信息主键
     * @return 工单信息
     */
    public WorkOrders selectWorkOrdersByOrderId(Long orderId);

    /**
     * 查询工单信息列表
     * 
     * @param workOrders 工单信息
     * @return 工单信息集合
     */
    public List<WorkOrders> selectWorkOrdersList(WorkOrders workOrders);

    /**
     * 新增工单信息
     * 
     * @param workOrders 工单信息
     * @return 结果
     */
    public int insertWorkOrders(WorkOrders workOrders);

    /**
     * 修改工单信息
     * 
     * @param workOrders 工单信息
     * @return 结果
     */
    public int updateWorkOrders(WorkOrders workOrders);

    /**
     * 删除工单信息
     * 
     * @param orderId 工单信息主键
     * @return 结果
     */
    public int deleteWorkOrdersByOrderId(Long orderId);

    /**
     * 批量删除工单信息
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkOrdersByOrderIds(Long[] orderIds);
}
