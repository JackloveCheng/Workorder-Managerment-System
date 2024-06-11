package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WorkOrdersMapper;
import com.ruoyi.system.domain.WorkOrders;
import com.ruoyi.system.service.IWorkOrdersService;

/**
 * 工单信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-01
 */
@Service
public class WorkOrdersServiceImpl implements IWorkOrdersService 
{
    @Autowired
    private WorkOrdersMapper workOrdersMapper;

    /**
     * 查询工单信息
     * 
     * @param orderId 工单信息主键
     * @return 工单信息
     */
    @Override
    public WorkOrders selectWorkOrdersByOrderId(Long orderId)
    {
        return workOrdersMapper.selectWorkOrdersByOrderId(orderId);
    }

    /**
     * 查询工单信息列表
     * 
     * @param workOrders 工单信息
     * @return 工单信息
     */
    @Override
    public List<WorkOrders> selectWorkOrdersList(WorkOrders workOrders)
    {
        return workOrdersMapper.selectWorkOrdersList(workOrders);
    }

    /**
     * 新增工单信息
     * 
     * @param workOrders 工单信息
     * @return 结果
     */
    @Override
    public int insertWorkOrders(WorkOrders workOrders)
    {
        return workOrdersMapper.insertWorkOrders(workOrders);
    }

    /**
     * 修改工单信息
     * 
     * @param workOrders 工单信息
     * @return 结果
     */
    @Override
    public int updateWorkOrders(WorkOrders workOrders)
    {
        return workOrdersMapper.updateWorkOrders(workOrders);
    }

    /**
     * 批量删除工单信息
     * 
     * @param orderIds 需要删除的工单信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkOrdersByOrderIds(Long[] orderIds)
    {
        return workOrdersMapper.deleteWorkOrdersByOrderIds(orderIds);
    }

    /**
     * 删除工单信息信息
     * 
     * @param orderId 工单信息主键
     * @return 结果
     */
    @Override
    public int deleteWorkOrdersByOrderId(Long orderId)
    {
        return workOrdersMapper.deleteWorkOrdersByOrderId(orderId);
    }

    /**
     *
     *
     * @param date 工单信息主键
     * @return 结果
     */
    @Override
    public int updateOverTimeOrders(Date date) {return workOrdersMapper.updateOverTimeOrders(date);};

    /**
     *
     *
     * @param  工单信息主键
     * @return 结果
     */
    @Override
    public List<Map<String,Object>> getWorkOrderCounts() {return workOrdersMapper.getWorkOrderCounts();};
}
