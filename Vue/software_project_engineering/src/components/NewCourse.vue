<template>
  <el-dialog
    @open=init
    title="新增课程"
    :visible.sync="NewCourseFormVisible"
    :before-close="cancelNewCourse"
  >
    <el-form
      :model="form"
      inline="true"
    >
      <el-form-item label="CourseID:">
        <el-input
          v-model="form.CourseID"
          autocomplete="off"
          maxlength="10"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item label="CourseName:">
        <el-input
          v-model="form.CourseName"
          autocomplete="off"
          maxlength="10"
          show-word-limit
        ></el-input>
      </el-form-item>
    </el-form>
    <el-form
      :model="form"
      inline="true"
    >
      <el-form-item label="Teacher:">
        <el-input
          v-model="form.Teacher"
          autocomplete="off"
          maxlength="6"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item label="Lesson:">
        <el-input-number
          v-model="form.Lesson"
          :min="1"
          :max="100"
        >
        </el-input-number>
      </el-form-item>
    </el-form>
    <el-button @click="cancelNewCourse">取 消</el-button>
    <el-button
      type="primary"
      @click="confirmNewCourse"
    >确 定</el-button>
  </el-dialog>
</template>
<script>
export default {
  name: 'NewCourse',
  props: {
    NewCourseFormVisible: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      form: {
        CourseID: '',
        CourseName: '',
        Teacher: '',
        Lesson: ''
      }
    }
  },
  methods: {
    init () {
      this.form = {
        CourseID: '',
        CourseName: '',
        Teacher: '',
        Lesson: ''
      }
    },
    cancelNewCourse: function () {
      this.form = {
        CourseID: '',
        CourseName: '',
        Teacher: '',
        Lesson: ''
      }
      this.$emit('CloseNewCourse')
    },
    confirmNewCourse: function () {
      this.form = {
        CourseID: '',
        CourseName: '',
        Teacher: '',
        Lesson: ''
      }
      this.$emit('CloseNewCourse')
    }
  }
}
</script>
<style>
</style>
