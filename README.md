1. pom.xml中添加java版本
<properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>
不添加前，默认版本是jdk5，无法使用try-resource-catch，每次打开项目需要配置
file->Project Structure->Modules->language level    