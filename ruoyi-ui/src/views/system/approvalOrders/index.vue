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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="approvalOrdersList" @selection-change="handleSelectionChange">
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
      <el-table-column label="提交者" align="center" prop="submitterId" />
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
            @click="handleDetailInfo(scope.row)"
            v-hasPermi="['system:approvalOrders:edit']"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:approvalOrders:edit']"
          >审批</el-button>
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

    <!-- 审批对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="评论" prop="comments">
          <el-input v-model="form.comments" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">通 过</el-button>
        <el-button @click="cancel">拒 绝</el-button>
      </div>
    </el-dialog>
    <!-- 详情对话框 -->
    <el-dialog :title="title" :visible.sync="open_detail" width="500px" append-to-body>
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
          <el-input v-model="form.title" v-bind:disabled="isReadOnly" placeholder="请输入工单标题" />
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
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listApprovalOrders, getApprovalOrders, delApprovalOrders, addApprovalOrders, updateApprovalOrders } from "@/api/system/approvalOrders";
import { listApprovalRecords, getApprovalRecords, delApprovalRecords, addApprovalRecords, updateApprovalRecords } from "@/api/system/approvalRecords";


export default {
  name: "ApprovalOrders",
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
      // 工单审批表格数据
      approvalOrdersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示详情
      open_detail: false,
      // 详情是否只读
      isReadOnly: true,
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
    /** 查询工单审批列表 */
    getList() {
      this.loading = true;
      listApprovalOrders(this.queryParams).then(response => {
        this.approvalOrdersList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          addApprovalRecords(this.form).then(response => {
            this.$modal.msgSuccess("审批成功");
            this.open = false;
          });
          this.form_1.status = "refused";
          updateApprovalOrders(this.form_1).then(response => {
            this.open = true;
            this.getList();
          });
        }
      });

    },
    // 表单重置
    reset() {
      this.form = {
        approvalId: null,
        orderId: null,
        approverId: null,
        status: null,
        comments: null,
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
      this.title = "添加工单审批";
      dict.type.system
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids;
      getApprovalOrders(orderId).then(response => {
        this.form_1 = response.data;
        this.open = true;
        this.title = "修改工单审批";
        this.form.orderId = orderId;
      });
    },
    /** 提交按钮 */
    submitForm() {
      //增加审批记录
      this.$refs["form"].validate(valid => {
        if (valid) {
          addApprovalRecords(this.form).then(response => {
            this.$modal.msgSuccess("审批成功");
            this.open = true;
          });
          var length = this.$store.state.dict.dict.at(0).value.length;
          var status = this.form_1.status;
          for (let i = 0; i < length; i++) {
            var item = this.$store.state.dict.dict.at(0).value[i];
            if (item.dictValue === status) {
              this.form_1.status = this.$store.state.dict.dict.at(0).value[i + 1].dictValue;
            }
          }
          updateApprovalOrders(this.form_1).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = true;
            this.getList();
          });
        }
      });


      //改变工单状态

    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除工单审批编号为"' + orderIds + '"的数据项？').then(function() {
        return delApprovalOrders(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/approvalOrders/export', {
        ...this.queryParams
      }, `approvalOrders_${new Date().getTime()}.xlsx`)
    },
    /** 详情按钮操作 */
    handleDetailInfo(row){
      const orderId = row.orderId || this.ids
      getApprovalOrders(orderId).then(response => {
        this.form_1 = response.data;
        this.open_detail = true;
        this.isReadOnly = true;
        this.title = "工单详情";
      });
    }
  }
};
</script>
