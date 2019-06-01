<template>
  <el-dialog
    @open="init"
    title="修改课程信息"
    :visible.sync="EditCourseFormVisible"
    :before-close="cancelEditCourse"
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
          maxlength="10"
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
    <el-button @click="cancelEditCourse">取 消</el-button>
    <el-button
      type="primary"
      @click="confirmEditCourse"
    >确 定</el-button>
  </el-dialog>
</template>
<script>
export default {
  name: 'EditCourse',
  props: {
    EditCourseFormVisible: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      form: {
        CourseID: this.$store.state.CourseData.courseidentifier,
        CourseName: this.$store.state.CourseData.coursename,
        Teacher: this.$store.state.CourseData.teacher,
        Lesson: this.$store.state.CourseData.lesson
      }
    }
  },
  methods: {
    init () {
      this.$set(this.form, 'CourseID', this.$store.state.CourseData.courseidentifier)
      this.$set(this.form, 'CourseName', this.$store.state.CourseData.coursename)
      this.$set(this.form, 'Teacher', this.$store.state.CourseData.teacher)
      this.$set(this.form, 'Lesson', this.$store.state.CourseData.lesson)
    },
    cancelEditCourse: function () {
      this.form = {
        CourseID: '',
        CourseName: '',
        Teacher: '',
        Lesson: ''
      }
      this.$emit('CloseEditCourse')
    },
    confirmEditCourse: function () {
      this.form = {
        CourseID: '',
        CourseName: '',
        Teacher: '',
        Lesson: ''
      }
      this.$emit('CloseEditCourse')
    }
  },
  computed: {
    getCourseData () {
      return this.$store.state.CourseData
    }
  }
}
</script>
<style>
</style>
