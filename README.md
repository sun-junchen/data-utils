# data-utils
## data 工具，目前实现了拷贝 

## 项目中有测试方法

1. 实现对象深拷贝
2. 实现对象Collection 深拷贝（List Set ...）

## 实现步骤

  1. dto 实现 BaseData接口
  2. dto.asViewObject(Target.class);
  3. 如果 Target 还有其他字段 也可以自定义，例如测试用例中的genderNum(只是简单举的例子，按照项目实际来)

```java
 AccountVO accountVO = accountDTO.asTargetObject(AccountVO.class,v->{
            v.setGenderNum(Objects.equals(accountDTO.getGender(), "男") ? "1" : "0");
        });
```

## <font color=red>注意</font>
<font color=red>两个类 相同的字段名的字段类型 必须完全一样!!!</font>

