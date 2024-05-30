package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Tickets;
import com.ruoyi.system.service.ITicketsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工单系统Controller
 * 
 * @author hao
 * @date 2024-05-30
 */
@RestController
@RequestMapping("/system/tickets")
public class TicketsController extends BaseController
{
    @Autowired
    private ITicketsService ticketsService;

    /**
     * 查询工单系统列表
     */
    @PreAuthorize("@ss.hasPermi('system:tickets:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tickets tickets)
    {
        startPage();
        List<Tickets> list = ticketsService.selectTicketsList(tickets);
        return getDataTable(list);
    }

    /**
     * 导出工单系统列表
     */
    @PreAuthorize("@ss.hasPermi('system:tickets:export')")
    @Log(title = "工单系统", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Tickets tickets)
    {
        List<Tickets> list = ticketsService.selectTicketsList(tickets);
        ExcelUtil<Tickets> util = new ExcelUtil<Tickets>(Tickets.class);
        util.exportExcel(response, list, "工单系统数据");
    }

    /**
     * 获取工单系统详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tickets:query')")
    @GetMapping(value = "/{ticketId}")
    public AjaxResult getInfo(@PathVariable("ticketId") Long ticketId)
    {
        return success(ticketsService.selectTicketsByTicketId(ticketId));
    }

    /**
     * 新增工单系统
     */
    @PreAuthorize("@ss.hasPermi('system:tickets:add')")
    @Log(title = "工单系统", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tickets tickets)
    {
        return toAjax(ticketsService.insertTickets(tickets));
    }

    /**
     * 修改工单系统
     */
    @PreAuthorize("@ss.hasPermi('system:tickets:edit')")
    @Log(title = "工单系统", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tickets tickets)
    {
        return toAjax(ticketsService.updateTickets(tickets));
    }

    /**
     * 删除工单系统
     */
    @PreAuthorize("@ss.hasPermi('system:tickets:remove')")
    @Log(title = "工单系统", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ticketIds}")
    public AjaxResult remove(@PathVariable Long[] ticketIds)
    {
        return toAjax(ticketsService.deleteTicketsByTicketIds(ticketIds));
    }
}
