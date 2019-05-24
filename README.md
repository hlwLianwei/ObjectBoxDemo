# ObjectBoxDemo
ObjectBox非关系型数据库示例  
  
1.在ObjectBoxDemo文件夹下build.gradle文件添加classpath的引用  
2.在ObjectBoxDemo\app文件夹下build.gradle文件添加implementation、debugImplementation、releaseImplementation的引用，以及在文件最后位置添加apply plugin: 'io.objectbox'  
3.Sync Project with Gradle Files, 同步下载依赖项  
4.Rebuild和Make项目  
5.添加实体类并实体添加注释  
6.在扩展Application类，添加BoxStore创建(可更改存储位置)，实体类表Box的创建  
7.在其他增删改查数据