package com.ruoyi.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
@RequestMapping("/system/acceptOrders")
public class AcceptOrdersController extends BaseController
{
    @Autowired
    private IWorkOrdersService workOrdersService;
    Map<Long, String> map = new HashMap<>();
    /**
     * 查询接收工单列表
     */
    @PreAuthorize("@ss.hasPermi('system:acceptOrders:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkOrders workOrders)
    {
        map.clear();
        map.put(208L, "net_wrong");
        map.put(209L, "device_fix");
        map.put(210L, "purchase");
        workOrders.setStatus("completed");
        workOrders.setBusinessType(map.get(SecurityUtils.getDeptId()));
        startPage();
        List<WorkOrders> list = workOrdersService.selectWorkOrdersList(workOrders);
        return getDataTable(list);
    }

    /**
     * 导出接收工单列表
     */
    @PreAuthorize("@ss.hasPermi('system:acceptOrders:export')")
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
    @PreAuthorize("@ss.hasPermi('system:acceptOrders:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(workOrdersService.selectWorkOrdersByOrderId(orderId));
    }

    /**
     * 新增接收工单
     */
    @PreAuthorize("@ss.hasPermi('system:acceptOrders:add')")
    @Log(title = "接收工单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkOrders workOrders)
    {
        workOrders.setAssigneeId(SecurityUtils.getUserId());
        return toAjax(workOrdersService.insertWorkOrders(workOrders));
    }

    /**
     * 修改接收工单
     */
    @PreAuthorize("@ss.hasPermi('system:acceptOrders:edit')")
    @Log(title = "接收工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkOrders workOrders)
    {
        return toAjax(workOrdersService.updateWorkOrders(workOrders));
    }

    /**
     * 删除接收工单
     */
    @PreAuthorize("@ss.hasPermi('system:acceptOrders:remove')")
    @Log(title = "接收工单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(workOrdersService.deleteWorkOrdersByOrderIds(orderIds));
    }
}
