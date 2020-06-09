import request from '@/utils/request'

export default{

  list() {
    return request({
      url: '/admin/edu/teacher/list',
      method: 'get'
    })
  },

  pageList(page, limit, searchObj) {
    return request({
      url: `/admin/edu/teacher/list/${page}/${limit}`,
      method: 'get',
      params: searchObj
    })
  },

  removeById(id) {
    return request({
      url: `/admin/edu/teacher/remove/${id}`,
      method: 'delete'
    })
  },

  save(teacher) {
    return request({
      url: '/admin/edu/teacher/save',
      method: 'post',
      data: teacher
    })
  },

  getById(id) {
    return request({
      url: `/admin/edu/teacher/get/${id}`,
      method: 'get'
    })
  },

  updateById(teacher) {
    return request({
      url: `/admin/edu/teacher/update`,
      method: 'put',
      data: teacher
    })
  }
}
