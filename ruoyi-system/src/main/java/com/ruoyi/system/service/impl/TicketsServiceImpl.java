package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TicketsMapper;
import com.ruoyi.system.domain.Tickets;
import com.ruoyi.system.service.ITicketsService;

/**
 * 工单系统Service业务层处理
 * 
 * @author hao
 * @date 2024-05-30
 */
@Service
public class TicketsServiceImpl implements ITicketsService 
{
    @Autowired
    private TicketsMapper ticketsMapper;

    /**
     * 查询工单系统
     * 
     * @param ticketId 工单系统主键
     * @return 工单系统
     */
    @Override
    public Tickets selectTicketsByTicketId(Long ticketId)
    {
        return ticketsMapper.selectTicketsByTicketId(ticketId);
    }

    /**
     * 查询工单系统列表
     * 
     * @param tickets 工单系统
     * @return 工单系统
     */
    @Override
    public List<Tickets> selectTicketsList(Tickets tickets)
    {
        return ticketsMapper.selectTicketsList(tickets);
    }

    /**
     * 新增工单系统
     * 
     * @param tickets 工单系统
     * @return 结果
     */
    @Override
    public int insertTickets(Tickets tickets)
    {
        return ticketsMapper.insertTickets(tickets);
    }

    /**
     * 修改工单系统
     * 
     * @param tickets 工单系统
     * @return 结果
     */
    @Override
    public int updateTickets(Tickets tickets)
    {
        return ticketsMapper.updateTickets(tickets);
    }

    /**
     * 批量删除工单系统
     * 
     * @param ticketIds 需要删除的工单系统主键
     * @return 结果
     */
    @Override
    public int deleteTicketsByTicketIds(Long[] ticketIds)
    {
        return ticketsMapper.deleteTicketsByTicketIds(ticketIds);
    }

    /**
     * 删除工单系统信息
     * 
     * @param ticketId 工单系统主键
     * @return 结果
     */
    @Override
    public int deleteTicketsByTicketId(Long ticketId)
    {
        return ticketsMapper.deleteTicketsByTicketId(ticketId);
    }
}
