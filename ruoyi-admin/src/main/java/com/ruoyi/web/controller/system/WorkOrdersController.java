package com.ruoyi.web.controller.system;

import java.util.List;
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
 * 工单信息Controller
 * 
 * @author hao
 * @date 2024-06-01
 */
@RestController
@RequestMapping("/system/orders")
public class WorkOrdersController extends BaseController
{
    @Autowired
    private IWorkOrdersService workOrdersService;

    /**
     * 查询工单信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:orders:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkOrders workOrders)
    {
        SecurityUtils.getLoginUser().getUser().getDept().getDeptId();
        workOrders.setSubmitterId(SecurityUtils.getLoginUser().getUser().getUserId());
        startPage();
        List<WorkOrders> list = workOrdersService.selectWorkOrdersList(workOrders);
        return getDataTable(list);
    }

    /**
     * 导出工单信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:orders:export')")
    @Log(title = "工单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkOrders workOrders)
    {
        List<WorkOrders> list = workOrdersService.selectWorkOrdersList(workOrders);
        ExcelUtil<WorkOrders> util = new ExcelUtil<WorkOrders>(WorkOrders.class);
        util.exportExcel(response, list, "工单信息数据");
    }

    /**
     * 获取工单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(workOrdersService.selectWorkOrdersByOrderId(orderId));
    }

    /**
     * 新增工单信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:add')")
    @Log(title = "工单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkOrders workOrders)
    {
        workOrders.setSubmitterId(SecurityUtils.getUserId());
        Long tmp = 103L;
        workOrders.setApprovalRoleId(tmp);
        String str = "pending_area";
        workOrders.setStatus(str);
        return toAjax(workOrdersService.insertWorkOrders(workOrders));
    }

    /**
     * 修改工单信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:edit')")
    @Log(title = "工单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkOrders workOrders)
    {
        return toAjax(workOrdersService.updateWorkOrders(workOrders));
    }

    /**
     * 删除工单信息
     */
    @PreAuthorize("@ss.hasPermi('system:orders:remove')")
    @Log(title = "工单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(workOrdersService.deleteWorkOrdersByOrderIds(orderIds));
    }
}
