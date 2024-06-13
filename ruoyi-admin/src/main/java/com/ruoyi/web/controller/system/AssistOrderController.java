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
import com.ruoyi.system.domain.AssistOrder;
import com.ruoyi.system.service.IAssistOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工单协助Controller
 * 
 * @author lin
 * @date 2024-06-13
 */
@RestController
@RequestMapping("/system/assistOrder")
public class AssistOrderController extends BaseController
{
    @Autowired
    private IAssistOrderService assistOrderService;

    /**
     * 查询工单协助列表
     */
    @PreAuthorize("@ss.hasPermi('system:assistOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssistOrder assistOrder)
    {
        if (SecurityUtils.getDeptId() != 100L)
            assistOrder.setAssistingDepartment(SecurityUtils.getDeptId());
        startPage();
        List<AssistOrder> list = assistOrderService.selectAssistOrderList(assistOrder);
        return getDataTable(list);
    }

    /**
     * 导出工单协助列表
     */
    @PreAuthorize("@ss.hasPermi('system:assistOrder:export')")
    @Log(title = "工单协助", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssistOrder assistOrder)
    {
        List<AssistOrder> list = assistOrderService.selectAssistOrderList(assistOrder);
        ExcelUtil<AssistOrder> util = new ExcelUtil<AssistOrder>(AssistOrder.class);
        util.exportExcel(response, list, "工单协助数据");
    }

    /**
     * 获取工单协助详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:assistOrder:query')")
    @GetMapping(value = "/{assistanceID}")
    public AjaxResult getInfo(@PathVariable("assistanceID") Long assistanceID)
    {
        return success(assistOrderService.selectAssistOrderByAssistanceID(assistanceID));
    }

    /**
     * 新增工单协助
     */
    @PreAuthorize("@ss.hasPermi('system:assistOrder:add')")
    @Log(title = "工单协助", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssistOrder assistOrder)
    {
        return toAjax(assistOrderService.insertAssistOrder(assistOrder));
    }

    /**
     * 修改工单协助
     */
    @PreAuthorize("@ss.hasPermi('system:assistOrder:edit')")
    @Log(title = "工单协助", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssistOrder assistOrder)
    {
        return toAjax(assistOrderService.updateAssistOrder(assistOrder));
    }

    /**
     * 删除工单协助
     */
    @PreAuthorize("@ss.hasPermi('system:assistOrder:remove')")
    @Log(title = "工单协助", businessType = BusinessType.DELETE)
	@DeleteMapping("/{assistanceIDs}")
    public AjaxResult remove(@PathVariable Long assistanceIDs)
    {
        return toAjax(assistOrderService.deleteAssistOrderByAssistanceID(assistanceIDs));
    }
}
