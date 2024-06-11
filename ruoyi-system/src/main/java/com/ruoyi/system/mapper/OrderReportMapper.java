package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.OrderReport;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2024-06-10
 */
public interface OrderReportMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param orderId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public OrderReport selectOrderReportByOrderId(Long orderId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param orderReport 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<OrderReport> selectOrderReportList(OrderReport orderReport);

    /**
     * 新增【请填写功能名称】
     * 
     * @param orderReport 【请填写功能名称】
     * @return 结果
     */
    public int insertOrderReport(OrderReport orderReport);

    /**
     * 修改【请填写功能名称】
     * 
     * @param orderReport 【请填写功能名称】
     * @return 结果
     */
    public int updateOrderReport(OrderReport orderReport);

    /**
     * 删除【请填写功能名称】
     * 
     * @param orderId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteOrderReportByOrderId(Long orderId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderReportByOrderIds(Long[] orderIds);
}
