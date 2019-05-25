import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    StudentData: '',
    ScoreData: '',
    CourseData: '',
    NewStudentFormVisible: 0
  },
  mutations: {
    changeStudent (state, Data) {
      state.StudentData = Data
    },
    changeScore (state, Data) {
      state.ScoreData = Data
    },
    changeCourse (state, Data) {
      state.CourseData = Data
    },
    changeStuForm (state, bool) {
      state.NewStudentFormVisible = bool
    }
  }
})
