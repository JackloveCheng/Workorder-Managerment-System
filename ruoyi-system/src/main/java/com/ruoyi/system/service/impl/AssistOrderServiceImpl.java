package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AssistOrderMapper;
import com.ruoyi.system.domain.AssistOrder;
import com.ruoyi.system.service.IAssistOrderService;

/**
 * 工单协助Service业务层处理
 * 
 * @author lin
 * @date 2024-06-13
 */
@Service
public class AssistOrderServiceImpl implements IAssistOrderService 
{
    @Autowired
    private AssistOrderMapper assistOrderMapper;

    /**
     * 查询工单协助
     * 
     * @param assistanceID 工单协助主键
     * @return 工单协助
     */
    @Override
    public AssistOrder selectAssistOrderByAssistanceID(Long assistanceID)
    {
        return assistOrderMapper.selectAssistOrderByAssistanceID(assistanceID);
    }

    /**
     * 查询工单协助列表
     * 
     * @param assistOrder 工单协助
     * @return 工单协助
     */
    @Override
    public List<AssistOrder> selectAssistOrderList(AssistOrder assistOrder)
    {
        return assistOrderMapper.selectAssistOrderList(assistOrder);
    }

    /**
     * 新增工单协助
     * 
     * @param assistOrder 工单协助
     * @return 结果
     */
    @Override
    public int insertAssistOrder(AssistOrder assistOrder)
    {
        return assistOrderMapper.insertAssistOrder(assistOrder);
    }

    /**
     * 修改工单协助
     * 
     * @param assistOrder 工单协助
     * @return 结果
     */
    @Override
    public int updateAssistOrder(AssistOrder assistOrder)
    {
        return assistOrderMapper.updateAssistOrder(assistOrder);
    }

    /**
     * 批量删除工单协助
     * 
     * @param assistanceIDs 需要删除的工单协助主键
     * @return 结果
     */
    @Override
    public int deleteAssistOrderByAssistanceIDs(Long[] assistanceIDs)
    {
        return assistOrderMapper.deleteAssistOrderByAssistanceIDs(assistanceIDs);
    }

    /**
     * 删除工单协助信息
     * 
     * @param assistanceID 工单协助主键
     * @return 结果
     */
    @Override
    public int deleteAssistOrderByAssistanceID(Long assistanceID)
    {
        return assistOrderMapper.deleteAssistOrderByAssistanceID(assistanceID);
    }
}
