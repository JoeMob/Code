<template>
  <el-dialog
    @open="init"
    title="修改学生信息"
    :visible.sync="EditStudentFormVisible"
    :before-close="cancelEditStu"
  >
    <el-form
      :model="form"
      inline="true"
      ref="refform"
    >
      <el-form-item label="StudentID:">
        <el-input
          v-model="form.StudentID"
          autocomplete="off"
          maxlength="10"
          show-word-limit
        ></el-input>
      </el-form-item>
      <el-form-item label="Name:">
        <el-input
          v-model="form.Name"
          autocomplete="off"
          maxlength="6"
          show-word-limit
        ></el-input>
      </el-form-item>
    </el-form>
    <el-form
      :model="form"
      inline="true"
      ref="refform"
    >
      <el-form-item label="Age:">
        <el-input-number
          v-model="form.Age"
          :min="1"
          :max="1000"
        ></el-input-number>
      </el-form-item>
      <el-form-item label="Gender:">
        <el-select v-model="form.Gender">
          <el-option
            v-for="item in form.Option"
            :key="item.value"
            :label="item.value"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>

    <el-button @click="cancelEditStu">取 消</el-button>
    <el-button
      type="primary"
      @click="confirmEditStu"
    >确 定</el-button>
  </el-dialog>
</template>
<script>
export default {
  name: 'EditStudent',
  props: {
    EditStudentFormVisible: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      Data: '',
      form: {
        StudentID: '',
        Name: '',
        Age: '',
        Gender: '',
        Option: [{
          value: 'Male'
        }, {
          value: 'Female'
        }]
      }
    }
  },
  methods: {
    init () {
      this.$set(this.form, 'id', this.$store.state.StudentData.id)
      this.$set(this.form, 'StudentID', this.$store.state.StudentData.studentId)
      this.$set(this.form, 'Name', this.$store.state.StudentData.studentName)
      this.$set(this.form, 'Age', this.$store.state.StudentData.age)
      this.$set(this.form, 'Gender', this.$store.state.StudentData.gender)
    },
    cancelEditStu: function () {
      this.form = {
        id: '',
        StudentID: '',
        Name: '',
        Age: '',
        Gender: ''
      }
      this.$emit('CloseEditStu')
    },
    confirmEditStu: function () {
      this.$axios('http://localhost:8080/student/id', {
        method: 'put',
        headers: {
          'Content-type': 'application/json'
        },
        data: {
          'id': this.form.id,
          'studentId': this.form.StudentID,
          'studentName': this.form.Name,
          'age': this.form.Age,
          'gender': this.form.Gender
        }
      })
        .then(function (response) {
          console.log(response)
        })
        .catch(function (error) {
          console.log(error)
        })
      this.form = {
        StudentID: '',
        Name: '',
        Age: '',
        Gender: ''
      }
      this.$emit('CloseEditStu')
    }
  },
  computed: {
  }
}
</script>
<style>
</style>
