<template>
  <el-dialog
    @open="init"
    title="修改成绩信息"
    :visible.sync="EditScoreFormVisible"
    :before-close="cancelEditScore"
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
      <el-form-item label="StudentID:">
        <el-input
          v-model="form.StudentID"
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
      <el-form-item label="CourseName:">
        <el-input
          v-model="form.CourseName"
          autocomplete="off"
          maxlength="10"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item label="Score:">
        <el-input-number
          v-model="form.Score"
          :min="0"
          :max="100"
        >
        </el-input-number>
      </el-form-item>
    </el-form>
    <el-button @click="cancelEditScore">取 消</el-button>
    <el-button
      type="primary"
      @click="confirmEditScore"
    >确 定</el-button>
  </el-dialog>
</template>
<script>
export default {
  name: 'EditScore',
  props: {
    EditScoreFormVisible: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      form: {
        CourseID: this.$store.state.ScoreData.courseidentifier,
        StudentID: this.$store.state.ScoreData.studentidentifier,
        CourseName: this.$store.state.ScoreData.coursename,
        Score: this.$store.state.ScoreData.score
      }
    }
  },
  methods: {
    init () {
      this.$set(this.form, 'CourseID', this.$store.state.ScoreData.courseidentifier)
      this.$set(this.form, 'StudentID', this.$store.state.ScoreData.studentidentifier)
      this.$set(this.form, 'CourseName', this.$store.state.ScoreData.coursename)
      this.$set(this.form, 'Score', this.$store.state.ScoreData.score)
    },
    cancelEditScore: function () {
      this.form = {
        CourseID: '',
        StudentID: '',
        CourseName: '',
        Score: ''
      }
      this.$emit('CloseEditScore')
    },
    confirmEditScore: function () {
      this.form = {
        CourseID: '',
        StudentID: '',
        CourseName: '',
        Score: ''
      }
      this.$emit('CloseEditScore')
    }
  }
}
</script>
<style>
</style>
