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
public class Project
{
    @Id
    public long boxId;

    public String id;
    public String title; // 标题
    public String status; // running(进行中), pause(已暂停), finish(已结束)
    public String quesTitle; // 问卷标题
    public String beginDate; // 开始日期
    public String endDate; // 结束日期
    public String publish; // 发布单位
    public String summary; // 项目简介
    public int quesCount; // 题目总数
    public int recycleCount; // 需要回收多少份
    public int commit; // 已提交的数量
    public int preCommit; // 待提交的数量
    public int saved; // 暂存数量
    public int refused; // 拒绝数量
}
