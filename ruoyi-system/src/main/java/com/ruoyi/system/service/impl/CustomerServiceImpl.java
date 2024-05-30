package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CustomerMapper;
import com.ruoyi.system.domain.Customer;
import com.ruoyi.system.service.ICustomerService;

/**
 * 客户管理Service业务层处理
 * 
 * @author hao
 * @date 2024-05-28
 */
@Service
public class CustomerServiceImpl implements ICustomerService 
{
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    @Override
    public Customer selectCustomerById(Long id)
    {
        return customerMapper.selectCustomerById(id);
    }

    /**
     * 查询客户管理列表
     * 
     * @param customer 客户管理
     * @return 客户管理
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer)
    {
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer)
    {
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改客户管理
     * 
     * @param customer 客户管理
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer)
    {
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的客户管理主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(Long[] ids)
    {
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    @Override
    public int deleteCustomerById(Long id)
    {
        return customerMapper.deleteCustomerById(id);
    }
}
