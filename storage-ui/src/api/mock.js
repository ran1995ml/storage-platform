import Mock from 'mockjs'
import homeApi from './mockServeData/home'
import permission from "@/api/mockServeData/permission";

Mock.mock('/api/home/getData', homeApi.getStatisticalData())
// Mock.mock(/api\/permission\/getMenu/, 'post', permission.getMenu)
