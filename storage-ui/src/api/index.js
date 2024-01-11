import http from '../utils/request'

// 请求首页数据
export const getData = () => {
    return http.get('/home/getData')
}

export const getMenu = (data) => {
    // return http.post('/permission/getMenu', data)
    return http.post('http://localhost:8090/storage/v1/user/login', data)
}
