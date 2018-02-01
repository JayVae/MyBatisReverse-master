# MyBatisReverse
MyBatis逆向工程入门Demo
MyBatis逆向工程比较简单，可以直接做为模板工程进行应用，只需要改一下数据库名，表名和包的路径就行了。 

注意：每次会覆盖，但是里面的内容会重复，因此每一次生成前都要将包清理下。

GeneratorSqlmap是运行文件，运行完成后将对应的包拷到需要的地方即可。
db.properties中修改数据库相关的配置；
generatorConfig.xml是主要修改的配置文件，主要是修改生成文件的路径，其中修改如下：
<!-- targetProject:生成PO类的位置 -->
<!-- targetProject:mapper映射文件生成的位置 -->
<!--targetPackage：mapper接口生成的位置，遵循MyBatis规范，让mapper.xml
和mapper.java在同一目录下，或者单独存放-->

