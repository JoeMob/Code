<template>
  <el-dialog
    title="新增成绩"
    :visible.sync="NewScoreFormVisible"
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
          maxlength="6"
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
    <el-button @click="cancelNewScore">取 消</el-button>
    <el-button
      type="primary"
      @click="confirmNewScore"
    >确 定</el-button>
  </el-dialog>
</template>
<script>
export default {
  name: 'NewScore',
  props: {
    NewScoreFormVisible: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      labelpostion: 'left',
      form: {
        CourseID: '',
        StudentID: '',
        CourseName: '',
        Score: ''
      }
    }
  },
  methods: {
    cancelNewScore: function () {
      this.$emit('CloseNewScore')
    },
    confirmNewScore: function () {
      this.$emit('CloseNewScore')
    }
  }
}
</script>
<style>
</style>
