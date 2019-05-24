package com.lenway.objbox.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * id必须使用使用注解 @Id，表示自增长; 如需手动管理应该用 @Id(assignable = true)
 * 若哪个字段与数据库无关则添加注解: @Transient
 * 不需要写get、set方法，否则会报错
 * 变量用public修饰符，总之我用private 时Build会报错
 */
@Entity
public class Person
{
    @Id
    public long boxId;

    public String id;
    public String name; // 姓名
    public int gender; // 性别 0 男，1 女
    public String birthday; // 生日
    public String familyId; // 家庭编号
}
