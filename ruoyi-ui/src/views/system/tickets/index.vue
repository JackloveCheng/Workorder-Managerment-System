<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label=" 工单号" prop="ticketId">
        <el-input
          v-model="queryParams.ticketId"
          placeholder="请输入 工单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务性质" prop="businessType">
        <el-select v-model="queryParams.businessType" placeholder="请选择业务性质" clearable>
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
      <el-form-item label="进度" prop="progress">
        <el-input
          v-model="queryParams.progress"
          placeholder="请输入进度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交者" prop="submitter">
        <el-input
          v-model="queryParams.submitter"
          placeholder="请输入提交者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="受理人员" prop="handler">
        <el-input
          v-model="queryParams.handler"
          placeholder="请输入受理人员"
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
          v-hasPermi="['system:tickets:add']"
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
          v-hasPermi="['system:tickets:edit']"
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
          v-hasPermi="['system:tickets:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:tickets:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ticketsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label=" 工单号" align="center" prop="ticketId" />
      <el-table-column label="业务性质" align="center" prop="businessType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_ticket_type" :value="scope.row.businessType"/>
        </template>
      </el-table-column>
      <el-table-column label="工单标题" align="center" prop="title" />
      <el-table-column label="进度" align="center" prop="progress" />
      <el-table-column label="提交者" align="center" prop="submitter" />
      <el-table-column label="受理人员" align="center" prop="handler" />
      <el-table-column label="工单状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_ticket_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:tickets:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:tickets:remove']"
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

    <!-- 添加或修改工单系统对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="业务性质" prop="businessType">
          <el-select v-model="form.businessType" placeholder="请选择业务性质">
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
        <el-form-item label="进度" prop="progress">
          <el-input v-model="form.progress" placeholder="请输入进度" />
        </el-form-item>
        <el-form-item label="提交者" prop="submitter">
          <el-input v-model="form.submitter" placeholder="请输入提交者" />
        </el-form-item>
        <el-form-item label="受理人员" prop="handler">
          <el-input v-model="form.handler" placeholder="请输入受理人员" />
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
  </div>
</template>

<script>
import { listTickets, getTickets, delTickets, addTickets, updateTickets } from "@/api/system/tickets";

export default {
  name: "Tickets",
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
      // 工单系统表格数据
      ticketsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ticketId: null,
        businessType: null,
        title: null,
        progress: null,
        submitter: null,
        handler: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        businessType: [
          { required: true, message: "业务性质不能为空", trigger: "change" }
        ],
        title: [
          { required: true, message: "工单标题不能为空", trigger: "blur" }
        ],
        progress: [
          { required: true, message: "进度不能为空", trigger: "blur" }
        ],
        submitter: [
          { required: true, message: "提交者不能为空", trigger: "blur" }
        ],
        handler: [
          { required: true, message: "受理人员不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "工单状态不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工单系统列表 */
    getList() {
      this.loading = true;
      listTickets(this.queryParams).then(response => {
        this.ticketsList = response.rows;
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
        ticketId: null,
        businessType: null,
        title: null,
        progress: null,
        submitter: null,
        handler: null,
        status: null
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
      this.ids = selection.map(item => item.ticketId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工单系统";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ticketId = row.ticketId || this.ids
      getTickets(ticketId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工单系统";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ticketId != null) {
            updateTickets(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTickets(this.form).then(response => {
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
      const ticketIds = row.ticketId || this.ids;
      this.$modal.confirm('是否确认删除工单系统编号为"' + ticketIds + '"的数据项？').then(function() {
        return delTickets(ticketIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/tickets/export', {
        ...this.queryParams
      }, `tickets_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
