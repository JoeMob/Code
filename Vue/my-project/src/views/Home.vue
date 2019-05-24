<template>
  <div class='all'>
    <img src='./../assets/E=mc² 355x94 white background.jpeg'>
    <h1>学生成绩管理</h1>
    <el-col
      span="10"
      offset="7"
    >
      <el-tabs
        :tab-position="top"
        style="height: 200px;"
        stretch="true"
      >
        <el-tab-pane label="Students">
          <el-col>
            <el-table
              :data="StudentData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              style="width: 100%"
              :default-sort="{prop: 'ID', order: 'descending'}"
            >
              <el-table-column
                label="StudentID"
                prop="studentidentifier"
                width="120"
                sortable
              >
              </el-table-column>
              <el-table-column
                label="Name"
                prop="name"
                width="120"
                sortable
              >
              </el-table-column>
              <el-table-column
                label="Age"
                prop="age"
                width="100"
              >
              </el-table-column>
              <el-table-column
                label="Gender"
                prop="gender"
                width="100"
              >
              </el-table-column>
              <el-table-column
                label="CreatedTime"
                prop="createdtime"
                width="100"
              >
              </el-table-column>
              <el-table-column
                label="EditedTime"
                prop="editedtime"
                width="100"
              >
              </el-table-column>
              <el-table-column align="center">
                <template
                  slot="header"
                  slot-scope="scope"
                >
                  <el-input
                    v-model="search"
                    size="mini"
                    placeholder="Search"
                  />
                </template>
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="success"
                    @click="handleDelete(scope.$index, scope.row)"
                  >Details</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-tab-pane>
        <el-tab-pane label="Score">
          <el-col>
            <el-table
              :data="ScoreData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              style="width: 100%"
            >
              <el-table-column
                label="CourseID"
                prop="courseidentifier"
                width="120"
                sortable
              >
              </el-table-column>
              <el-table-column
                label="StudnetID"
                prop="studentidentifier"
                width="120"
                sortable
              >
              </el-table-column>
              <el-table-column
                label="CourseName"
                prop="coursename"
                width="100"
              >
              </el-table-column>
              <el-table-column
                label="Score"
                prop="score"
                width="100"
              >
              </el-table-column>
              <el-table-column
                label="CreatedTime"
                prop="createdtime"
                width="100"
              >
              </el-table-column>
              <el-table-column
                label="EditedTime"
                prop="editedtime"
                width="100"
              >
              </el-table-column>
              <el-table-column align="center">
                <template
                  slot="header"
                  slot-scope="scope"
                >
                  <el-input
                    v-model="search"
                    size="mini"
                    placeholder="Search"
                  />
                </template>
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="success"
                    @click="handleDelete(scope.$index, scope.row)"
                  >Details</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-tab-pane>
        <el-tab-pane label="Course">
          <el-col>
            <el-table
              :data="CourseData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              style="width: 100%"
            >
              <el-table-column
                label="CourseID"
                prop="courseidentifier"
                width="120"
                sortable
              >
              </el-table-column>
              <el-table-column
                label="CourseName"
                prop="coursename"
                width="120"
                sortable
              ></el-table-column>
              <el-table-column
                label="Teacher"
                prop="teacher"
                width="100"
              >
              </el-table-column>
              <el-table-column
                label="Lesson"
                prop="lesson"
                width="100"
              >
              </el-table-column>
              <el-table-column
                label="CreatedTime"
                prop="createdtime"
                width="100"
              >
              </el-table-column>
              <el-table-column
                label="EditedTime"
                prop="editedtime"
                width="100"
              >
              </el-table-column>
              <el-table-column align="center">
                <template
                  slot="header"
                  slot-scope="scope"
                >
                  <el-input
                    v-model="search"
                    size="mini"
                    placeholder="Search"
                  />
                </template>
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="success"
                    @click="handleDelete(scope.$index, scope.row)"
                  >Details</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-tab-pane>
      </el-tabs>
    </el-col>
  </div>
</template>
<script>
export default {
  name: 'Home',
  components: {
  },
  data () {
    return {
      StudentData: [{
        studentidentifier: '1',
        date: '2016-05-03',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        studentidentifier: '2',
        date: '2016-05-02',
        name: 'John',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-04',
        name: 'Morgan',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-01',
        name: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-01',
        name: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-01',
        name: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles'
      }],
      ScoreData: [{
        studentidentifier: '1',
        date: '2016-05-03',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        studentidentifier: '2',
        date: '2016-05-02',
        name: 'John',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-04',
        name: 'Morgan',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-01',
        name: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-01',
        name: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-01',
        name: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles'
      }],
      CourseData: [{
        studentidentifier: '1',
        date: '2016-05-03',
        name: 'Tom',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        studentidentifier: '2',
        date: '2016-05-02',
        name: 'John',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-04',
        name: 'Morgan',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-01',
        name: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-01',
        name: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        date: '2016-05-01',
        name: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles'
      }],
      search: ''
    }
  },
  methods: {
    handleEdit (index, row) {
      console.log(index, row)
    },
    handleDelete (index, row) {
      console.log(index, row)
    }
  }
}
</script>
<style>
* {
  font-family: "Sarasa Mono Slab SC";
  font-size: "12px";
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.bg-purple-dark {
  background: #99a9bf;
}
</style>
