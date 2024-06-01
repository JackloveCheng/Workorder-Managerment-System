<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工单ID" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入工单ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工单标识符" prop="orderNumber">
        <el-input
          v-model="queryParams.orderNumber"
          placeholder="请输入工单标识符"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工单类型" prop="businessType">
        <el-select v-model="queryParams.businessType" placeholder="请选择工单类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_ticket_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="工单标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入工单标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工单状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择工单状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_ticket_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:orders:add']"
        >添加工单</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:orders:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:orders:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ordersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工单ID" align="center" prop="orderId" />
      <el-table-column label="工单标识符" align="center" prop="orderNumber" />
      <el-table-column label="工单类型" align="center" prop="businessType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_ticket_type" :value="scope.row.businessType"/>
        </template>
      </el-table-column>
      <el-table-column label="工单标题" align="center" prop="title" />
      <el-table-column label="工单描述" align="center" prop="description" />
      <el-table-column label="工单状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_ticket_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDetailInfo"
            v-hasPermi="['system:orders:remove']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:orders:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改工单信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工单标识符" prop="orderNumber">
          <el-input v-model="form.orderNumber" placeholder="请输入工单标识符" />
        </el-form-item>
        <el-form-item label="工单类型" prop="businessType">
          <el-select v-model="form.businessType" placeholder="请选择工单类型">
            <el-option
              v-for="dict in dict.type.sys_ticket_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工单标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入工单标题" />
        </el-form-item>
        <el-form-item label="工单描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="工单状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择工单状态">
            <el-option
              v-for="dict in dict.type.sys_ticket_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOrders, getOrders, delOrders, addOrders, updateOrders } from "@/api/system/orders";

export default {
  name: "Orders",
  dicts: ['sys_ticket_status', 'sys_ticket_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 工单信息表格数据
      ordersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        orderNumber: null,
        businessType: null,
        title: null,
        description: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderNumber: [
          { required: true, message: "工单标识符不能为空", trigger: "blur" }
        ],
        businessType: [
          { required: true, message: "工单类型不能为空", trigger: "change" }
        ],
        title: [
          { required: true, message: "工单标题不能为空", trigger: "blur" }
        ],
        description: [
          { required: true, message: "工单描述不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "工单状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工单信息列表 */
    getList() {
      this.loading = true;
      console.log(this.queryParams.submitterId);
      listOrders(this.queryParams).then(response => {
        this.ordersList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        orderId: null,
        orderNumber: null,
        businessType: null,
        title: null,
        description: null,
        status: null,
        submitterId: null,
        assigneeId: null,
        createdAt: null,
        updatedAt: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工单信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getOrders(orderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工单信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateOrders(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrders(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除工单信息编号为"' + orderIds + '"的数据项？').then(function() {
        return delOrders(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/orders/export', {
        ...this.queryParams
      }, `orders_${new Date().getTime()}.xlsx`)
    },
    /** 详情按钮操作 */
    handleDetailInfo(){
      this.reset();
      this.open = true;
      this.title = "添加工单信息";
    }
  }
};
</script>
