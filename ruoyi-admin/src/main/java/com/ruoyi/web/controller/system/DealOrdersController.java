package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.DictUtils;
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
import com.ruoyi.system.domain.WorkOrders;
import com.ruoyi.system.service.IWorkOrdersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 接收工单Controller
 * 
 * @author hhh
 * @date 2024-06-04
 */
@RestController
@RequestMapping("/system/dealOrders")
public class DealOrdersController extends BaseController
{
    @Autowired
    private IWorkOrdersService workOrdersService;

    /**
     * 查询接收工单列表
     */
    @PreAuthorize("@ss.hasPermi('system:dealOrders:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkOrders workOrders)
    {
        workOrders.setStatus("accepted");
        startPage();
        List<WorkOrders> list = workOrdersService.selectWorkOrdersList(workOrders);
        return getDataTable(list);
    }

    /**
     * 导出接收工单列表
     */
    @PreAuthorize("@ss.hasPermi('system:dealOrders:export')")
    @Log(title = "接收工单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkOrders workOrders)
    {
        List<WorkOrders> list = workOrdersService.selectWorkOrdersList(workOrders);
        ExcelUtil<WorkOrders> util = new ExcelUtil<WorkOrders>(WorkOrders.class);
        util.exportExcel(response, list, "接收工单数据");
    }

    /**
     * 获取接收工单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dealOrders:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(workOrdersService.selectWorkOrdersByOrderId(orderId));
    }

    /**
     * 新增接收工单
     */
    @PreAuthorize("@ss.hasPermi('system:dealOrders:add')")
    @Log(title = "接收工单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkOrders workOrders)
    {
        return toAjax(workOrdersService.insertWorkOrders(workOrders));
    }

    /**
     * 修改接收工单
     */
    @PreAuthorize("@ss.hasPermi('system:dealOrders:edit')")
    @Log(title = "接收工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkOrders workOrders)
    {
        Long sort = DictUtils.getDictSort("sys_ticket_status", workOrders.getStatus());
        String value = DictUtils.getDictValueBySort("sys_ticket_status", sort + 1L);
        workOrders.setStatus(value);
        return toAjax(workOrdersService.updateWorkOrders(workOrders));
    }

    /**
     * 删除接收工单
     */
    @PreAuthorize("@ss.hasPermi('system:dealOrders:remove')")
    @Log(title = "接收工单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(workOrdersService.deleteWorkOrdersByOrderIds(orderIds));
    }
}
