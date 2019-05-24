<template>
  <div class="item">
    <el-col span=10 offset=1 type="flex" style="margin-top:20px">
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <el-row>
            <el-col span=19>
              <span>{{item.title}}</span>
            </el-col>
            <el-col span=5 type="flex" justify="right">
              <el-button type="primary" icon="el-icon-edit" circle @click="dialogVisible=true"></el-button>
              <el-button type="success" icon="el-icon-check" circle @click="onFinishItem"></el-button>
              <el-button type="danger" icon="el-icon-delete" circle @click="onDeleteItem"></el-button>
              <el-dialog title="EditItem" :visible.sync="dialogVisible" :before-close="handleClose">
                <el-form :model="form" :label-position="'top'" ref="form" label-width="80px">
                  <el-form-item label="标题:">
                    <el-input v-model="form.newTodoTitle"></el-input>
                  </el-form-item>
                  <el-form-item label="任务内容:">
                    <el-input type="textarea" :autosize="{minRows:4}" v-model="form.newTodoContent"></el-input>
                  </el-form-item>
                  <el-form-item label="任务完成度描述:">
                    <el-input
                      type="textarea"
                      :autosize="{minRows:3}"
                      v-model="form.newTodoCompleteDesc"
                    ></el-input>
                  </el-form-item>
                  <el-form-item>
                    <span>起始时间:</span>
                    <el-date-picker
                      v-model="form.newTodoBeginDate"
                      align="right"
                      type="date"
                      placeholder="选择日期"
                      format="yyyy 年 MM 月 dd 日"
                      :picker-options="pickerOptions1"
                    ></el-date-picker>
                    <span>截止时间:</span>
                    <el-date-picker
                      v-model="form.newTodoDeadline"
                      align="right"
                      type="date"
                      placeholder="选择日期"
                      format="yyyy 年 MM 月 dd 日"
                      :picker-options="pickerOptions2"
                    ></el-date-picker>
                  </el-form-item>
                  <el-form-item label="紧急程度:">
                    <el-select v-model="form.newTodoProperty">
                      <el-option
                        v-for="item in PropertyOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="完成度:">
                    <el-slider v-model="form.newTodoComplete" show-input></el-slider>
                  </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                  <el-button @click="dialogVisible = false">取 消</el-button>
                  <el-button type="primary" @click="dialogVisible = false;onEditItem">确 定</el-button>
                </span>
              </el-dialog>
            </el-col>
          </el-row>
        </div>
        <el-row>
          <el-col span=18>
            <div>内容：{{item.content}}</div>
            <div>From {{item.beginDate}} to {{item.deadline}}</div>
            <div>紧急度：{{item.property}}</div>
            <div>完成情况说明：{{item.completeDesc}}</div>
          </el-col>
          <el-col span=6>
            <el-progress type="circle" :percentage="item.complete"></el-progress>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </div>
</template>
<script>
/* eslint-disable */
export default {
  name: "TodoItem",
  props: {
    item: Object
  },
  data() {
    return {
      dialogVisible: false,
      visible: false,
      form: {
        todoItems: [],
        newTodoTitle: this.item.title,
        newTodoContent: this.item.content,
        newTodoBeginDate: this.item.beginDate,
        newTodoDeadline: this.item.deadline,
        newTodoProperty: this.item.property,
        newTodoComplete: this.item.complete,
        newTodoCompleteDesc: this.item.completeDesc
      },
      PropertyOptions: [
        {
          value: "1",
          label: "非常紧急"
        },
        {
          value: "2",
          label: "正常完成"
        },
        {
          value: "3",
          label: "不用着急"
        }
      ],
      pickerOptions1: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            }
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
      },
      pickerOptions2: {
        disabledDate: time => {
          return time.getTime() < this.beginDate;
        },
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            }
          },
          {
            text: "明天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "一周后",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
      },
      searchTitle: ""
    };
  },
  methods: {
    onDeleteItem() {
      //  触发事件
      this.$emit("onDeleteItem", this.item.id);
    },
    onFinishItem() {
      this.$emit("onFinishItem", this.item.id);
    },
    onEditItem() {
      this.$emit("onEditItem", this.item.id);
    }
  }
};
</script>
<style scoped>
.boxcard {
  text-align: left;
  font-size: 16px;
}

.itemTitle {
  font-size: 60px;
  display: block;
  text-align: center;
  align-content: center;
}
</style>
