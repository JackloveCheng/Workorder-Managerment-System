package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OrderReportMapper;
import com.ruoyi.system.domain.OrderReport;
import com.ruoyi.system.service.IOrderReportService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-10
 */
@Service
public class OrderReportServiceImpl implements IOrderReportService 
{
    @Autowired
    private OrderReportMapper orderReportMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param orderId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public OrderReport selectOrderReportByOrderId(Long orderId)
    {
        return orderReportMapper.selectOrderReportByOrderId(orderId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param orderReport 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<OrderReport> selectOrderReportList(OrderReport orderReport)
    {
        return orderReportMapper.selectOrderReportList(orderReport);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param orderReport 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertOrderReport(OrderReport orderReport)
    {
        return orderReportMapper.insertOrderReport(orderReport);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param orderReport 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateOrderReport(OrderReport orderReport)
    {
        return orderReportMapper.updateOrderReport(orderReport);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param orderIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteOrderReportByOrderIds(Long[] orderIds)
    {
        return orderReportMapper.deleteOrderReportByOrderIds(orderIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param orderId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteOrderReportByOrderId(Long orderId)
    {
        return orderReportMapper.deleteOrderReportByOrderId(orderId);
    }
}
