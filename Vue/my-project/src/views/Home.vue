<template>
  <div class='all'>
    <img src='./../assets/E=mc² 355x94 white background.jpeg'>
    <h1>学生成绩管理</h1>
    <el-col
      span="12"
      offset="6"
    >
      <el-tabs
        style="height: 200px;"
        stretch=true
      >
        <el-tab-pane label="Students">
          <new-student
            :NewStudentFormVisible="NewStuVisible"
            @CloseNewStu="closeNewStu"
          ></new-student>
          <edit-student
            :EditStudentFormVisible="EditStuVisible"
            @CloseEditStu="closeEditStu"
          ></edit-student>
          <el-col>
            <el-table
              :data="StudentData"
              style="width: 100%"
              :default-sort="{prop: 'studentidentifier', order:'ascending'}"
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

              <el-table-column
                align="center"
                width="40"
              >
                <template
                  slot="header"
                  slot-scope="scope"
                >
                  <el-button
                    size="mini"
                    type="success"
                    icon="el-icon-plus"
                    circle
                    @click="handleNewStudent()"
                  ></el-button>
                </template>
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
                    clearable
                  />
                </template>
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="primary"
                    icon="el-icon-edit"
                    circle
                    @click="handleEditStudent(scope.$index, scope.row)"
                  ></el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    @click="handleDeleteStudent(scope.$index, scope.row)"
                  ></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-tab-pane>
        <el-tab-pane label="Score">
          <new-score
            :NewScoreFormVisible="NewScoreVisible"
            @CloseNewScore="closeNewScore"
          ></new-score>
          <edit-score
            :EditScoreFormVisible="EditScoreVisible"
            @CloseEditScore="closeEditScore"
          ></edit-score>
          <el-col>
            <el-table
              :data="ScoreData"
              style="width: 100%"
              :default-sort="{prop: 'courseidentifier', order:'ascending'}"
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
              <el-table-column
                align="center"
                width="40"
              >
                <template
                  slot="header"
                  slot-scope="scope"
                >
                  <el-button
                    size="mini"
                    type="success"
                    icon="el-icon-plus"
                    circle
                    @click="handleNewScore()"
                  ></el-button>
                </template>
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
                    clearable
                  />
                </template>
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="primary"
                    icon="el-icon-edit"
                    circle
                    @click="handleEditScore(scope.$index, scope.row)"
                  ></el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    @click="handleDeleteScore(scope.$index, scope.row)"
                  ></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-tab-pane>
        <el-tab-pane label="Course">
          <new-Course
            :NewCourseFormVisible="NewCourseVisible"
            @CloseNewCourse="closeNewCourse"
          ></new-Course>
          <edit-Course
            :EditCourseFormVisible="EditCourseVisible"
            @CloseEditCourse="closeEditCourse"
          ></edit-Course>
          <el-col>
            <el-table
              :data="CourseData"
              style="width: 100%"
              :default-sort="{prop: 'courseidentifier', order: 'ascending'}"
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
              <el-table-column
                align="center"
                width="40"
              >
                <template
                  slot="header"
                  slot-scope="scope"
                >
                  <el-button
                    size="mini"
                    type="success"
                    icon="el-icon-plus"
                    circle
                    @click="handleNewCourse(scope.$index, scope.row)"
                  ></el-button>
                </template>
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
                    clearable
                  />
                </template>
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="primary"
                    icon="el-icon-edit"
                    circle
                    @click="handleEditCourse(scope.$index, scope.row)"
                  ></el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    @click="handleDeleteCourse(scope.$index, scope.row)"
                  ></el-button>
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
import NewStudent from '@/components/NewStudent.vue'
import NewScore from '@/components/NewScore.vue'
import NewCourse from '@/components/NewCourse.vue'
import EditStudent from '@/components/EditStudent.vue'
import EditScore from '@/components/EditScore.vue'
import EditCourse from '@/components/EditCourse.vue'
export default {
  name: 'Home',
  components: {
    NewStudent,
    NewScore,
    NewCourse,
    EditStudent,
    EditScore,
    EditCourse
  },
  data () {
    return {
      StudentData: [{
        studentidentifier: '1',
        name: 'A',
        age: '18',
        gender: 'Male',
        createdtime: '2019-5-25',
        editedtime: '2019-5-25'
      }, {
        studentidentifier: '2',
        name: 'B',
        age: '20',
        gender: 'Female',
        createdtime: '2019-5-25',
        editedtime: '2019-5-25'
      }],
      ScoreData: [{
        courseidentifier: '1',
        studentidentifier: '1',
        coursename: 'Math',
        score: '100',
        createdtime: '2019-5-25',
        editedtime: '2019-5-25'
      }, {
        courseidentifier: '2',
        studentidentifier: '1',
        coursename: 'Compilier',
        score: '100',
        createdtime: '2019-5-25',
        editedtime: '2019-5-25'
      }],
      CourseData: [{
        courseidentifier: '1',
        coursename: 'Math',
        teacher: 'JoJo',
        lesson: '48',
        createdtime: '2019-5-25',
        editedtime: '2019-5-25'
      }, {
        courseidentifier: '2',
        coursename: 'Compiler',
        teacher: 'Dio',
        lesson: '48',
        createdtime: '2019-5-25',
        editedtime: '2019-5-25'
      }],
      search: '',
      NewStuVisible: false,
      EditStuVisible: false,
      NewScoreVisible: false,
      EditScoreVisible: false,
      NewCourseVisible: false,
      EditCourseVisible: false
    }
  },
  methods: {
    handleNewStudent () {
      this.NewStuVisible = true
    },
    handleEditStudent (index, row) {
      this.EditStuVisible = true
      this.$store.commit('changeStudent', this.StudentData[index])
    },
    handleDeleteStudent (index, row) {
    },
    handleNewScore () {
      this.NewScoreVisible = true
    },
    handleEditScore (index, row) {
      this.EditScoreVisible = true
      this.$store.commit('changeScore', this.ScoreData[index])
    },
    handleDeleteScore (index, row) {
    },
    handleNewCourse () {
      this.NewCourseVisible = true
    },
    handleEditCourse (index, row) {
      this.EditCourseVisible = true
      this.$store.commit('changeCourse', this.CourseData[index])
    },
    handleDeleteCourse (index, row) {
    },
    closeNewStu () {
      this.NewStuVisible = false
    },
    closeEditStu () {
      this.EditStuVisible = false
    },
    closeNewScore () {
      this.NewScoreVisible = false
    },
    closeEditScore () {
      this.EditScoreVisible = false
    },
    closeNewCourse () {
      this.NewCourseVisible = false
    },
    closeEditCourse () {
      this.EditCourseVisible = false
    }
  }
}
</script>
<style>
.all {
  font-family: Sarasa Mono Slab SC;
  font-size: 24px;
  text-align: center;
}
</style>
