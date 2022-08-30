<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.keyword" placeholder="搜索" style="width: 200px; margin-right: 10px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.order" placeholder="排序方式" clearable class="filter-item" style="width: 130px;margin-right: 10px;">
        <el-option v-for="item in orderWay" :key="item.key" :label="item.name" :value="item.key" />
      </el-select>
      <el-select v-model="listQuery.orderBy" placeholder="排序字段" clearable class="filter-item" style="width: 130px;margin-right: 10px;">
        <el-option v-for="item in orderByWay" :key="item.key" :label="item.display_name" :value="item.key" />
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-plus" @click="$router.push('/addDataSet/addDataSet')">
        新建数据集
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%; margin-top: 15px;"
    >
      <el-table-column label="名称" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建者" width="200px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.createUser }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="300px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="描述" width="450px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.descr }}</span>
        </template>
      </el-table-column>
      <el-table-column label="数据库名" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.dbName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="表名" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.tableName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="handleDelete(row,$index)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.pageSize" @pagination="getList" />

  </div>
</template>

<script>
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { getSetList, deleteDataset } from '@/api/dataset'

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      orderWay: [{
        key: 'desc',
        name: '降序'
      }, {
        key: 'asc',
        name: '升序'
      }],
      orderByWay: [{
        key: 'name',
        display_name: '名称'
      }, {
        key: 'create_user',
        display_name: '创建者'
      }, {
        key: 'create_time',
        display_name: '创建时间'
      }],
      listQuery: {
        page: 1,
        pageSize: 20,
        createUser: '',
        keyword: '',
        order: '',
        orderBy: 'create_time'
      }
    }
  },
  created() {
    this.listQuery.createUser = localStorage.getItem('name')
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getSetList(this.listQuery).then(res => {
        this.total = res.data.totalCount
        this.list = res.data.dataSetList
      }).catch(err => {
        console.log('getSetList', err)
      })
      this.listLoading = false
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleDelete(row, index) {
      deleteDataset(row.id).then(res => {
        this.list.splice(index, 1)
        this.$notify({
          title: 'Success',
          message: '删除成功',
          type: 'success',
          duration: 2000
        })
      })
    }
  }
}
</script>
