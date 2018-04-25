
<h1 align="center">仓储管理系统</h1>
<p align="center">
  <a href="https://github.com/alibaba/ice"><img src="https://img.shields.io/badge/developing%20with-ICE-2077ff.svg"></a>
</p>

---
基于SSM的前后端分离的仓储管理系统，前后端交互遵循Restful接口原则。

线上地址 :[wms.luvletter.cn](http://wms.luvletter.cn/) 账号：admin 密码：1
## 技术栈
### 后端
* Spring+SpringMVC+MyBatis
* 缓存使用Redis,并使用Spring Cache的缓存注解,可以直接应用到方法上。
---
    @Cacheable(value = WMSConstant.REDIS_CACHE_NAME)
    public ApiResult getComboBox(String pid,String value) {
        ApiResult apiResult = new ApiResult();
        List<Dictionary> dictionaries = dictionaryMapper.selectByParaId(pid, value);
        if(dictionaries == null || dictionaries.isEmpty()){
            apiResult.isFalse();
            return apiResult;
        }
        apiResult.setData(dictionaries.get(0));
        return apiResult;
    }
### 前端  
* 使用阿里巴巴开源的Iceworks构建
###### （前端代码[在这里](https://github.com/J2ephyr/LUVWMS_React)）
### 权限管理
* Spring Security+JWT (可以细化到url的权限管理)
---
#### 关键代码
    //在身份验证前添加token验证
    http.addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
    //替换身份验证过滤器
    http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    //在权限筛选前添加自定义的url方式筛选
    http.addFilterBefore(urlFilterSecurityInterceptor,FilterSecurityInterceptor.class);
## 系统功能图
![系统功能图](http://cdn.luvletter.cn/wms%E5%8A%9F%E8%83%BD%E5%9B%BE1.png)
## 部分系统截图
### 首页
![首页](http://cdn.luvletter.cn/%E9%A6%96%E9%A1%B5.jpg)
### 商品列表
![商品列表](http://cdn.luvletter.cn/%E5%95%86%E5%93%81%E5%88%97%E8%A1%A8.jpg)
### 人员维护
![人员维护](http://cdn.luvletter.cn/%E4%BA%BA%E5%91%98%E7%BB%B4%E6%8A%A4.jpg)
### 角色权限维护
![角色权限维护](http://cdn.luvletter.cn/%E8%A7%92%E8%89%B2%E6%9D%83%E9%99%90%E7%BB%B4%E6%8A%A4.jpg)
## 本地部署项目
1. clone项目到本地
2. 执行mysql脚本
3. 数据库配置文件在luv-server中resources的jdbc.properties和wms-common中resources的jdbc.propertieste
4. 安装Redis,并配置luv-server下resources的redis.properties
4. 用Intellij IDEA打开项目，Maven加入luv-comm、luv-server、luv-wms-parent,并依次执行mvn clean install
5. 配置tomcat，就可以运行了。


