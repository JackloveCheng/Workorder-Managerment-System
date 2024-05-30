package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Tickets;

/**
 * 工单系统Service接口
 * 
 * @author hao
 * @date 2024-05-30
 */
public interface ITicketsService 
{
    /**
     * 查询工单系统
     * 
     * @param ticketId 工单系统主键
     * @return 工单系统
     */
    public Tickets selectTicketsByTicketId(Long ticketId);

    /**
     * 查询工单系统列表
     * 
     * @param tickets 工单系统
     * @return 工单系统集合
     */
    public List<Tickets> selectTicketsList(Tickets tickets);

    /**
     * 新增工单系统
     * 
     * @param tickets 工单系统
     * @return 结果
     */
    public int insertTickets(Tickets tickets);

    /**
     * 修改工单系统
     * 
     * @param tickets 工单系统
     * @return 结果
     */
    public int updateTickets(Tickets tickets);

    /**
     * 批量删除工单系统
     * 
     * @param ticketIds 需要删除的工单系统主键集合
     * @return 结果
     */
    public int deleteTicketsByTicketIds(Long[] ticketIds);

    /**
     * 删除工单系统信息
     * 
     * @param ticketId 工单系统主键
     * @return 结果
     */
    public int deleteTicketsByTicketId(Long ticketId);
}