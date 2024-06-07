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
import com.ruoyi.system.domain.ApprovalRecords;
import com.ruoyi.system.service.IApprovalRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 审批记录Controller
 * 
 * @author myself
 * @date 2024-06-01
 */
@RestController
@RequestMapping("/system/approvalRecords")
public class ApprovalRecordsController extends BaseController
{
    @Autowired
    private IApprovalRecordsService approvalRecordsService;

    /**
     * 查询审批记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:approvalRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(ApprovalRecords approvalRecords)
    {
        startPage();
        List<ApprovalRecords> list = approvalRecordsService.selectApprovalRecordsList(approvalRecords);
        return getDataTable(list);
    }

    /**
     * 导出审批记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:approvalRecords:export')")
    @Log(title = "审批记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApprovalRecords approvalRecords)
    {
        List<ApprovalRecords> list = approvalRecordsService.selectApprovalRecordsList(approvalRecords);
        ExcelUtil<ApprovalRecords> util = new ExcelUtil<ApprovalRecords>(ApprovalRecords.class);
        util.exportExcel(response, list, "审批记录数据");
    }

    /**
     * 获取审批记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:approvalRecords:query')")
    @GetMapping(value = "/{approvalId}")
    public AjaxResult getInfo(@PathVariable("approvalId") Long approvalId)
    {
        return success(approvalRecordsService.selectApprovalRecordsByApprovalId(approvalId));
    }

    /**
     * 新增审批记录
     */
    @PreAuthorize("@ss.hasPermi('system:orders:add')")
    @Log(title = "审批记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ApprovalRecords approvalRecords)
    {
        approvalRecords.setApproverId(SecurityUtils.getUserId());
        return toAjax(approvalRecordsService.insertApprovalRecords(approvalRecords));
    }

    /**
     * 修改审批记录
     */
    @PreAuthorize("@ss.hasPermi('system:approvalRecords:edit')")
    @Log(title = "审批记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ApprovalRecords approvalRecords)
    {
        return toAjax(approvalRecordsService.updateApprovalRecords(approvalRecords));
    }

    /**
     * 删除审批记录
     */
    @PreAuthorize("@ss.hasPermi('system:approvalRecords:remove')")
    @Log(title = "审批记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{approvalIds}")
    public AjaxResult remove(@PathVariable Long[] approvalIds)
    {
        return toAjax(approvalRecordsService.deleteApprovalRecordsByApprovalIds(approvalIds));
    }
}
