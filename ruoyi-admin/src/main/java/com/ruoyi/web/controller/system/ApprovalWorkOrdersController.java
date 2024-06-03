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
import com.ruoyi.system.domain.ApprovalWorkOrders;
import com.ruoyi.system.service.IApprovalWorkOrdersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工单审批Controller
 * 
 * @author ruoyi
 * @date 2024-06-01
 */
@RestController
@RequestMapping("/system/approvalOrders")
public class ApprovalWorkOrdersController extends BaseController
{
    @Autowired
    private IApprovalWorkOrdersService approvalWorkOrdersService;

    /**
     * 查询工单审批列表
     */
    @PreAuthorize("@ss.hasPermi('system:approvalOrders:list')")
    @GetMapping("/list")
    public TableDataInfo list(ApprovalWorkOrders approvalWorkOrders)
    {
        if (SecurityUtils.getDeptId() != 100)
            approvalWorkOrders.setApprovalRoleId(SecurityUtils.getLoginUser().getUser().getDept().getParentId());
        startPage();
        List<ApprovalWorkOrders> list = approvalWorkOrdersService.selectApprovalWorkOrdersList(approvalWorkOrders);
        return getDataTable(list);
    }

    /**
     * 导出工单审批列表
     */
    @PreAuthorize("@ss.hasPermi('system:approvalOrders:export')")
    @Log(title = "工单审批", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApprovalWorkOrders approvalWorkOrders)
    {
        List<ApprovalWorkOrders> list = approvalWorkOrdersService.selectApprovalWorkOrdersList(approvalWorkOrders);
        ExcelUtil<ApprovalWorkOrders> util = new ExcelUtil<ApprovalWorkOrders>(ApprovalWorkOrders.class);
        util.exportExcel(response, list, "工单审批数据");
    }

    /**
     * 获取工单审批详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:approvalOrders:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(approvalWorkOrdersService.selectApprovalWorkOrdersByOrderId(orderId));
    }

    /**
     * 新增工单审批
     */
    @PreAuthorize("@ss.hasPermi('system:approvalOrders:add')")
    @Log(title = "工单审批", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ApprovalWorkOrders approvalWorkOrders)
    {
        return toAjax(approvalWorkOrdersService.insertApprovalWorkOrders(approvalWorkOrders));
    }

    /**
     * 修改工单审批
     */
    @PreAuthorize("@ss.hasPermi('system:approvalOrders:edit')")
    @Log(title = "工单审批", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ApprovalWorkOrders approvalWorkOrders)
    {
        return toAjax(approvalWorkOrdersService.updateApprovalWorkOrders(approvalWorkOrders));
    }

    /**
     * 删除工单审批
     */
    @PreAuthorize("@ss.hasPermi('system:approvalOrders:remove')")
    @Log(title = "工单审批", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(approvalWorkOrdersService.deleteApprovalWorkOrdersByOrderIds(orderIds));
    }
}
