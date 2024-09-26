### 一、逻辑链路

一般使用 Service 作为逻辑实现。但是当 AService 需要调用 BService 的 C 方法时，
需将 BService 的 C 方法移动至 BManager 中，在 AService 中依赖 BManager 调用 C 方法。

```
1、接口入口应为Controller或Api
2、调用链路应为：Controller/Api->Service->Manager->Repository->Mapper
           或：Controller/Api->Service->Repository->Mapper
3、不能同级依赖。如：AService 不能依赖 BService
4、不能越级依赖。如：BRepository 不能依赖 AService
5、可以跨级依赖。如：AService 可以依赖 BManager
6、业务逻辑只能写在Service或Manager中，Repository和Mapper只负责数据访问
```

### 二、禁止

##### 1、禁止定义private和protected的方法

##### 2、禁止使用查询出来的实体直接用于修改

```
UserModel user = this.userRepository.findById(1L);

user.setName("name");
user.setAge(20);

this.userRepository.updateById(user);
```

##### 3、禁止在model上定义除getter和setter之外的方法

```
@Data
@TableName(value = "`user`")
@EqualsAndHashCode(callSuper = true)
public class UserModel extends GenericModel {
    
    @TableField(value = "`name`")
    @ApiModelProperty(name = "名称")
    private String name;
    
    public boolean hasName() {
        return EmptyUtils.isNotEmpty(this.name);
    }
    
    public boolean isJack() {
        return "Jack".equals(this.name);
    }

}
```

##### 4、dto和vo上禁止定义is和get开头的方法(增加字段除外)

```
@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends GenericVO {
    
    @ApiModelProperty(name = "名字")
    private String firstName; 
    
    @ApiModelProperty(name = "姓氏")
    private String lastName;
    
    public String getFullName() {
        return  this.firstName + this.lastName;
    }

}
```
##### 5、禁止使用Date日期类型，改用LocalDateTime,LocalDate,LocalTime