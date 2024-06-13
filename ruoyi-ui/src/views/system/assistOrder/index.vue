<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工单id" prop="workOrderID">
        <el-input
          v-model="queryParams.workOrderID"
          placeholder="请输入工单id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门id" prop="assistingDepartment">
        <el-input
          v-model="queryParams.assistingDepartment"
          placeholder="请输入部门id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="协助信息" prop="assistInfo">
        <el-input
          v-model="queryParams.assistInfo"
          placeholder="请输入协助信息"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:assistOrder:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:assistOrder:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:assistOrder:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:assistOrder:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assistOrderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="协助id" align="center" prop="assistanceID" />
      <el-table-column label="工单id" align="center" prop="workOrderID" />
      <el-table-column label="协助信息" align="center" prop="assistInfo" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:assistOrder:edit']"
          >查看</el-button>
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

    <!-- 添加或修改工单协助对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="工单id" prop="workOrderID">
          <el-input v-model="form.workOrderID" placeholder="请输入工单id" />
        </el-form-item>
        <el-form-item label="部门id" prop="assistingDepartment">
          <el-input v-model="form.assistingDepartment" placeholder="请输入部门id" />
        </el-form-item>
        <el-form-item label="协助信息" prop="assistInfo">
          <el-input v-model="form.assistInfo" placeholder="请输入协助信息" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form_1" :model="form_1" :rules="rules" label-width="80px">
        <el-form-item label="工单标识符" prop="orderNumber">
          <el-input v-model="form_1.orderNumber" v-bind:disabled="isReadOnly" placeholder="请输入工单标识符" />
        </el-form-item>
        <el-form-item label="工单类型" prop="businessType">
          <el-select v-model="form_1.businessType"  v-bind:disabled="isReadOnly" placeholder="请选择工单类型">
            <el-option
              v-for="dict in dict.type.sys_ticket_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工单标题" prop="title">
          <el-input v-model="form_1.title" v-bind:disabled="isReadOnly" placeholder="请输入工单标题" />
        </el-form-item>
        <el-form-item label="描述">
          <editor v-model="form_1.description" :min-height="192" :readOnly="isReadOnly" />
        </el-form-item>
        <el-form-item label="工单状态" prop="status">
          <el-select v-model="form_1.status" v-bind:disabled="isReadOnly" placeholder="请选择工单状态">
            <el-option
              v-for="dict in dict.type.sys_ticket_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="协助信息" prop="assistInfo">
          <el-input v-model="form.assistInfo" v-bind:disabled="isReadOnly" type="textarea"placeholder="请输入协助信息" />
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { listAssistOrder, getAssistOrder, delAssistOrder, addAssistOrder, updateAssistOrder } from "@/api/system/assistOrder";
import {getOrders} from "../../../api/system/orders";

export default {
  name: "AssistOrder",
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
      // 工单协助表格数据
      assistOrderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        workOrderID: null,
        assistingDepartment: null,
        assistInfo: null
      },
      // 表单参数
      form: {},
      form_1:{
        orderId: null,
        orderNumber: null,
        businessType: null,
        title: null,
        description: null,
        status: null,
        submitterId: null,
        assigneeId: null,
        createdAt: null,
        updatedAt: null,
        approvalRoleId:null ,
      },
      isReadOnly:false,
      // 表单校验
      rules: {
        assistInfo: [
          { required: true, message: "协助信息不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工单协助列表 */
    getList() {
      this.loading = true;
      listAssistOrder(this.queryParams).then(response => {
        this.assistOrderList = response.rows;
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
        assistanceID: null,
        workOrderID: null,
        assistingDepartment: null,
        assistInfo: null
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
      this.ids = selection.map(item => item.assistanceID)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工单协助";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const assistanceID = row.assistanceID || this.ids
      getAssistOrder(assistanceID).then(response => {
        this.form = response.data;
        this.open = true;
      });
      getOrders(row.workOrderID).then(response => {
        this.form_1 = response.data;
        this.isReadOnly = true;
        this.title = "工单详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.assistanceID != null) {
            updateAssistOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAssistOrder(this.form).then(response => {
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
      const assistanceIDs = row.assistanceID || this.ids;
      this.$modal.confirm('是否确认删除工单协助编号为"' + assistanceIDs + '"的数据项？').then(function() {
        return delAssistOrder(assistanceIDs);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/assistOrder/export', {
        ...this.queryParams
      }, `assistOrder_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
