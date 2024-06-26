package com.ruoyi.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.WorkOrders;

/**
 * 工单信息Service接口
 * 
 * @author ruoyi
 * @date 2024-06-01
 */
public interface IWorkOrdersService 
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
     * 批量删除工单信息
     * 
     * @param orderIds 需要删除的工单信息主键集合
     * @return 结果
     */
    public int deleteWorkOrdersByOrderIds(Long[] orderIds);

    /**
     * 删除工单信息信息
     * 
     * @param orderId 工单信息主键
     * @return 结果
     */
    public int deleteWorkOrdersByOrderId(Long orderId);

    /**
     * 判断工单是否超时
     *
     * @param date 工单信息主键
     * @return 工单信息
     */
    public int updateOverTimeOrders(Date date);

    /**
     * 判断工单是否超时
     *
     * @param  工单信息主键
     * @return 工单信息
     */
    public List<Map<String,Object>> getWorkOrderCounts();
}
