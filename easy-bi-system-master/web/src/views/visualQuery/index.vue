<template>
  <div class="visualQueryContent">
    <div class="sideBar">
      <div class="datasetBox">
        <div class="topBox">
          <div class="topBoxTitle">数据集</div>
          <el-tooltip class="item" effect="dark" content="切换数据集" placement="top">
            <div class="topBoxIcon" @click="changeSetList">
              <i class="el-icon-sort" />
            </div>
          </el-tooltip>
        </div>
        <div class="datasetInfoBox">{{ setName }}</div>
      </div>
      <div class="sideBarInfoContent">
        <div class="sideBarInfoBox dimensionBox">
          <div class="sideBarboxTitle">维度</div>
          <el-scrollbar style="height: 39vh; overflow-x: hidden;">
            <draggable
              v-model="dimensionList"
              :options="{group:{name: 'dimension',pull:'clone'},sort: true}"
              animation="300"
              @end="dimensionEnd1"
              @start="dimensionStart"
            >
              <div v-for="(item,index) in dimensionList" :key="index" class="infoItems">
                <i class="el-icon-rank dimensionIcon" />
                <div class="itemsText">{{ item.descr }}</div>
              </div>
            </draggable>
          </el-scrollbar>
          <div class="sideBarInfoBox targetBox">
            <div class="sideBarboxTitle">指标</div>
            <el-scrollbar style="height: 35vh; overflow-x: hidden;">
              <draggable
                v-model="targetList"
                :options="{group:{name: 'target',pull:'clone'},sort: true}"
                animation="300"
                @end="targetEnd1"
                @start="targetStart"
              >
                <div v-for="(item,index) in targetList" :key="index" class="infoItems">
                  <i class="el-icon-data-line targetIcon" />
                  <div class="itemsText">{{ item.descr }}</div>
                </div>
              </draggable>
            </el-scrollbar>
          </div>
        </div>
      </div>
    </div>
    <div class="charsBar">
      <el-tabs v-model="tabsActiveName">
        <el-tab-pane label="图表" name="tables">
          <div class="tablesStyleListBox">
            <div
              v-for="(item,index) in chars"
              :key="index"
              class="iconBox"
              :class="charsIndexFlag == index ? 'iconBoxBorder': ''"
              @click="charsSelect(index)"
            >
              <SvgIcon :icon-class="item" style="width: 20px;height: 20px;" />
            </div>
          </div>
          <div class="chooseBySelf">
            <div class="chooseBySelfTitle">客制化</div>
            <div v-show="charsIndexFlag == 0" id="barcontainer" class="tablesBySelf">
              <div class="tablesBySelfBox">
                <div class="tablesBySelfTitle">是否显示表头</div>
                <el-switch v-model="isShowTablesHeader" />
              </div>
              <div class="tablesBySelfBox">
                <div class="tablesBySelfTitle">是否显示外边框</div>
                <el-switch v-model="isShowTablesBorder" />
              </div>
            </div>
            <div v-show="charsIndexFlag == 1" id="barcontainer" class="tablesBySelf">
              <div class="tablesBySelfBox">
                <div class="tablesBySelfTitle">是否描边</div>
                <el-switch v-model="isShowBorder" @change="isShowBorderChange" />
              </div>
              <div class="tablesBySelfBox">
                <div class="tablesBySelfTitle">是否显示背景</div>
                <el-switch v-model="isShowBgc" @change="isShowBgcChange" />
              </div>
            </div>
            <div v-show="charsIndexFlag == 2" id="barcontainer" class="tablesBySelf">
              <div class="tablesBySelfBox">
                <div class="tablesBySelfTitle">是否光滑</div>
                <el-switch v-model="isSmooth" @change="smoothChange" />
              </div>
              <div class="tablesBySelfBox">
                <div class="tablesBySelfTitle">是否显示数值</div>
                <el-switch v-model="isShowNum" @change="isShowNumChange" />
              </div>
            </div>
          </div>

        </el-tab-pane>
      </el-tabs>
    </div>
    <div class="visualBar">
      <div class="dimenTargetContent">
        <div class="SelectBox">
          <draggable
            v-model="dimenSelectList"
            :options="{group:{name: 'dimension',pull:'clone'},sort: true}"
            animation="300"
          >
            <div class="SelectListBox">
              <div class="dimenTargetTitle">维度</div>
              <div v-show="dimenSelectList.length == 0" style="font-size: 12px; color: #9ea6b2;">请从左侧维度栏拖拽</div>
              <div v-for="(item,index) in dimenSelectList" :key="index" class="SelectList">
                <i class="el-icon-caret-bottom dimenSelectListIcon" />
                <div class="dimenSelectListText">{{ item.descr }}</div>
                <i class="el-icon-close closeIncon" @click="dimenBtnDel(index)" />
              </div>
            </div>
          </draggable>
        </div>
        <div class="SelectBox">
          <draggable
            v-model="targeSelectList"
            :options="{group:{name: 'target',pull:'clone'},sort: true}"
            animation="300"
            @end="targetEnd2"
          >
            <div class="SelectListBox">
              <div class="dimenTargetTitle">指标</div>
              <div v-show="targeSelectList.length == 0" style="font-size: 12px; color: #9ea6b2;">请从左侧指标栏拖拽</div>
              <div v-for="(item,index) in targeSelectList" :key="index" class="SelectList targeSelectList">
                <i class="el-icon-caret-bottom targetSelectListIcon" />
                <el-dropdown trigger="click">
                  <div class="dimenSelectListText">
                    {{ item.funName == undefined ? '':item.funName }}{{ '(' + item.descr + ')' }}
                  </div>
                  <el-dropdown-menu slot="dropdown">
                    <div v-for="(info,id) in functionList" :key="id">
                      <el-dropdown-item @click.native="dropDownClick(info,index)">{{ info.name }}</el-dropdown-item>
                    </div>
                  </el-dropdown-menu>
                </el-dropdown>
                <i class="el-icon-close closeIncon" @click="targetBtnDel(index)" />
              </div>
            </div>
          </draggable>
        </div>
      </div>
      <div class="visualContent">
        <div class="runBtnBox">
          <el-button type="primary" icon="el-icon-video-play" size="medium" @click="queryBtn">查询</el-button>
        </div>
        <div class="visualBox">
          <div v-show="charsIndexFlag == 0">
            <el-scrollbar style="height: 70vh;">
              <div class="tablesInfoBox">
                <div v-for="(item,index) in queryResList" :key="index" class="tablesInfo">
                  <div v-show="isShowTablesHeader" class="tablesHead" :class="isShowTablesBorder ? 'ShowTablesBorder' : ''">{{ item.key }}</div>
                  <div v-for="(info,id) in item.value" :key="id" class="tablesBody" :class="isShowTablesBorder ? 'ShowTablesBorder' : ''">{{ info }}</div>
                </div>
              </div>
            </el-scrollbar>
          </div>
          <div v-show="charsIndexFlag == 1 || 2" id="linecontainer" class="lineInfoBox" :style="{width: '100%', height: '100%'}" />
        </div>
      </div>
    </div>
    <el-dialog title="选择数据集" :visible.sync="selectSetDialog">
      <div class="setBox">
        <div class="setBoxTitle">全部</div>
        <div class="setListBox">
          <div
            v-for="(item, index) in setList"
            :key="index"
            class="setListItemBox"
            :class="activeclass == index ? 'setListItemBoxActive' : ''"
            @click="selectSet(index,item.id,item.name)"
          >
            <div class="setListTitle">{{ item.name }}</div>
            <div class="setListInfo">
              <div class="descr">描述：{{ item.descr }}</div>
              <div class="createUser">创建者：{{ item.createUser }}</div>
            </div>
          </div>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="selectSetDialog = false">
          取消
        </el-button>
        <el-button type="primary" @click="comfirmSet">
          确定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getAllSetList, getSetInfo, queryDataInfo } from '@/api/visualquery'
import draggable from 'vuedraggable'
import SvgIcon from '@/components/SvgIcon/index.vue'
import Charts from '@jiaminghi/charts'

export default {
  components: {
    draggable,
    SvgIcon
  },
  data() {
    return {
      selectSetDialog: false,
      activeclass: -1,
      setList: [],
      selectSetId: '',
      dimensionList: [],
      dimenSelectList: [],
      targetList: [],
      targeSelectList: [],
      setName: '',
      dimenListFlag: false,
      targetListFlag: false,
      functionList: [],
      tabsActiveName: 'tables',
      charsIndexFlag: 0,
      queryResList: [],
      queryData: {
        dataSetId: '',
        groupByList: [],
        selectList: [],
        sort: [],
        whereList: []
      },
      chars: ['tables', 'bar', 'line'],
      options: {
        xAxis: {
          name: '',
          data: ''
        },
        yAxis: {
          name: '',
          data: 'value'
        },
        series: [
          {
            data: [],
            type: ''
          }
        ]
      },
      // 客制化定制
      isShowTablesHeader: true,
      isShowTablesBorder: true,
      isSmooth: false,
      isShowNum: false,
      isShowBorder: false,
      isShowBgc: false
    }
  },
  watch: {
    options: {
      handler() {
        this.drawLine()
      },
      deep: true
    }
  },
  created() {
    if (this.setName === '') {
      this.$alert('请先选择数据集', '', {
        confirmButtonText: '确定',
        callback: action => {
          this.changeSetList()
        }
      })
    }
  },
  methods: {
    changeSetList() {
      this.selectSetDialog = true
      getAllSetList().then(res => {
        this.setList = res.data
      })
    },
    selectSet(index, id, setName) {
      this.setName = setName
      this.activeclass = index
      this.selectSetId = id
      this.queryData.dataSetId = id
    },
    comfirmSet() {
      getSetInfo(this.selectSetId).then(res => {
        this.dimensionList = res.data.dimensionList
        this.targetList = res.data.metricList
        this.functionList = res.data.functionList
        this.selectSetDialog = false
        this.resetQueryData()
        this.resetOptions()
        this.dimenSelectList.length = 0
        this.targeSelectList.length = 0
        this.queryResList.length = 0
      }).catch(err => {
        console.log('getSetInfo', err)
      })
    },
    dimensionStart(e) {
      if (this.dimenSelectList.includes(this.dimensionList[e.oldDraggableIndex])) {
        this.dimenListFlag = true
      }
    },
    dimensionEnd1(e) {
      if (this.dimenListFlag) {
        this.dimenSelectList.splice(e.newDraggableIndex, 1)
        this.dimenListFlag = false
      }
    },
    targetStart(e) {
      if (this.targeSelectList.includes(this.targetList[e.oldDraggableIndex])) {
        this.targetListFlag = true
      }
    },
    targetEnd1(e) {
      if (this.targetListFlag) {
        this.targeSelectList.splice(e.newDraggableIndex, 1)
        this.targetListFlag = false
      }
      this.resetQueryData()
    },
    targetEnd2() {
      this.resetQueryData()
    },
    dimenBtnDel(index) {
      this.dimenSelectList.splice(index, 1)
      this.resetQueryData()
    },
    targetBtnDel(index) {
      this.targeSelectList.splice(index, 1)
      this.resetQueryData()
    },
    dropDownClick(info, index) {
      this.$set(this.targeSelectList[index], 'funName', info.name)
      this.$set(this.targeSelectList[index], 'funValue', info.value)
    },
    resetQueryData() {
      this.queryData = {
        dataSetId: this.queryData.dataSetId,
        groupByList: [],
        selectList: [],
        sort: [],
        whereList: []
      }
    },
    queryBtn() {
      this.setQueryData()
      queryDataInfo(this.queryData).then(res => {
        if (this.charsIndexFlag === 0) {
          this.queryResList = res.data.data
        }
        if (this.charsIndexFlag === 1) {
          this.options.xAxis.data = res.data.data[0].value.slice(0, 6)
          this.options.series[0].data = res.data.data[1].value.slice(0, 6)
          this.options.series[0].type = 'bar'
          this.drawLine()
        }
        if (this.charsIndexFlag === 2) {
          this.options.xAxis.data = res.data.data[0].value.slice(0, 6)
          this.options.series[0].data = res.data.data[1].value.slice(0, 6)
          this.options.series[0].type = 'line'
          this.drawLine()
        }
      })
    },
    charsSelect(index) {
      var container = document.getElementById('linecontainer')
      container.innerHTML = ''
      if (this.index === 2) {
        this.options.series[0].type = 'bar'
      }
      this.charsIndexFlag = index
    },
    setQueryData() {
      for (var i = 0; i < this.targeSelectList.length; i++) {
        if (this.targeSelectList[i].funValue === undefined) {
          this.$message('请给指标选择一个操作')
          return
        }
        var selectobj = {
          field: this.targeSelectList[i].name,
          function: this.targeSelectList[i].funValue
        }
        this.queryData.selectList.push(selectobj)
      }
      for (var j = 0; j < this.dimenSelectList.length; j++) {
        this.queryData.groupByList[j] = this.dimenSelectList[j].name
        var sortobj = {
          field: this.dimenSelectList[j].name,
          order: 'desc'
        }
        this.queryData.sort.push(sortobj)
      }
    },
    drawLine() {
      var container = document.getElementById('linecontainer')
      container.innerHTML = ''
      const myChart = new Charts(container)
      myChart.setOption(this.options, true)
    },
    resetOptions() {
      this.options = {
        xAxis: {
          name: '',
          data: ''
        },
        yAxis: {
          name: '',
          data: 'value'
        },
        series: [
          {
            data: [],
            type: ''
          }
        ]
      }
    },
    smoothChange(e) {
      if (e) {
        this.$set(this.options.series[0], 'smooth', true)
      } else {
        this.$delete(this.options.series[0], 'smooth')
      }
    },
    isShowNumChange(e) {
      var obj = {
        show: true,
        formatter: '{value}'
      }
      if (e) {
        this.$set(this.options.series[0], 'label', obj)
      } else {
        this.$delete(this.options.series[0], 'label')
      }
    },
    isShowBorderChange(e) {
      var obj = {
        stroke: '#000'
      }
      if (e) {
        this.$set(this.options.series[0], 'barStyle', obj)
      } else {
        this.$delete(this.options.series[0], 'barStyle')
      }
    },
    isShowBgcChange(e) {
      var obj = {
        show: true
      }
      if (e) {
        this.$set(this.options.series[0], 'backgroundBar', obj)
      } else {
        this.$delete(this.options.series[0], 'backgroundBar')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.visualQueryContent {
  display: flex;
  align-items: center;
  .sideBar {
    height: 100vh;
    width: 12vw;
    padding: 15px;
    border-right: 1px solid #ececec;
    .datasetBox {
      border-bottom: 1px solid #d6d9dd;
      .topBox {
        display: flex;
        justify-content: space-between;
        font-weight: bold;
        .topBoxTitle {
          font-weight: bold;
        }
        .topBoxIcon {
          cursor: pointer;
          transform:rotate(-90deg);
          -webkit-transform:rotate(-90deg);
        }
      }
      .datasetInfoBox {
        font-size: 15px;
        padding: 20px 0 20px 5px;
      }
    }
    .sideBarInfoContent {
      -moz-user-select: none;
      -webkit-user-select: none;
      -ms-user-select: none;
      user-select: none;
      .dimensionBox {
        border-bottom: 1px solid #d6d9dd;
      }
      .sideBarInfoBox {
        padding: 15px 0 35px 0;
        .dimensionIcon {
          color: #3685f2;
        }
        .targetIcon {
          color: #6cd975;
        }
        .sideBarboxTitle {
          font-weight: bold;
          padding-bottom: 20px;
        }
        .infoItems {
          display: flex;
          justify-content: left;
          align-items: center;
          .itemsText {
            font-size: 13px;
            margin-left: 10px;
            flex: 1;
            cursor: pointer;
            padding: 5px 0;
          }
          .itemsText:hover {
            background-color: #e4edfa;
          }
        }
      }
    }
  }
  .charsBar {
    height: 100vh;
    width: 15vw;
    padding: 15px;
    border-right: 1px solid #ececec;
    .tablesStyleListBox {
      display: flex;
      justify-content: space-around;
      padding-bottom: 15px;
      border-bottom: 1px solid #ececec;
    }
    .iconBox {
      padding: 5px;
      cursor: pointer;
    }
    .iconBox:hover {
      border: 2px solid #3073ff;
    }
    .iconBoxBorder {
      border: 2px solid #3073ff;
    }
    // 客制化
    .chooseBySelf {
      padding: 20px;
      .chooseBySelfTitle {
        font-size: 16px;
        font-weight: bold;
      }
      .tablesBySelf {
        .tablesBySelfBox {
          margin: 20px 0;
          .tablesBySelfTitle {
            padding: 10px 0;
          }
        }
      }
    }
  }
  .visualBar {
    height: 100vh;
    padding: 10px;
    flex: 1;
    background-color: #f0f5f8;
    display: flex;
    flex-direction: column;
    .closeIncon {
      font-size: 12px!important;
      margin-left: 8px;
    }
    .dimenTargetContent {
      .dimenTargetTitle {
        margin-right: 10px;
        font-weight: bold;
        font-size: 16px;
      }
      .SelectBox {
        padding: 15px 20px;
        display: flex;
        justify-content: left;
        background-color: #fff;
        margin-bottom: 3px;
        .SelectListBox {
          width: 56vw;
          display: flex;
          justify-content: left;
          align-items: center;
          .SelectList {
            padding: 8px 20px;
            margin-right: 15px;
            border-radius: 15px;
            background-color: #eaf0ff;
            display: flex;
            justify-content: left;
            align-items: center;
            cursor: pointer;
          }
          .targeSelectList {
            background-color: #e6f5e5;
          }
        }
      }
      .dimenSelectListIcon {
        color: #3073ff;
      }
      .dimenSelectListText {
        margin-left: 10px;
      }
      .targetSelectListIcon {
        color: #5eb955;
      }
    }
    .visualContent {
      background-color: #fff;
      margin-top: 10px;
      flex: 1;
      display: flex;
      flex-direction: column;
      padding: 30px 20px;
      .visualBox {
        margin-top: 20px;
        flex: 1;
        .tablesInfoBox {
          display: flex;
          justify-content: left;
          .tablesInfo {
            .tablesHead {
              padding: 16px 30px;
              border-bottom: 1px solid #ececec;
            }
            .tablesBody {
              padding: 16px 30px;
              border-bottom: 1px solid #ececec;
              max-width: 400px;
              white-space: nowrap;  // 设置段落文本不换行(不换行才有可能行溢出)；
              overflow: hidden;  // 关闭滚动条，超出部分隐藏；
              text-overflow:ellipsis;  // 超出部分添加省略号；
            }
            .tablesBody:hover {
              overflow: visible;
              white-space: inherit;
            }
            .ShowTablesBorder {
              border: 1px solid #ececec!important;
            }
          }
        }
      }
    }
  }
  .setBox {
    .setBoxTitle {
      padding: 8px 15px;
      border-left: 4px solid #3574ff;
      font-weight: bold;
      font-size: 15px;
    }
    .setListBox {
      display: flex;
      justify-content: left;
      margin-top: 20px;
      .setListItemBox {
        width: 270px;
        border: 1px solid #ececec;
        cursor: pointer;
        margin-right: 20px;
        .setListTitle {
          color: #3574ff;
          font-weight: bold;
         padding: 10px 25px;
          border-bottom: 1px solid #ececec;
        }
        .setListInfo {
         color: #999999;
         padding: 20px 15px;
         .createUser {
          margin-top: 10px;
         }
       }
      }
      .setListItemBoxActive {
        border: 1px solid #3574ff;
      }
    }
  }
}
</style>
