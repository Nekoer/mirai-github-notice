plugins {
    val kotlinVersion = "1.5.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.7-M2"
}

group = "com.hcyacg"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/gradle-plugin")
    mavenCentral()
}
dependencies {
    implementation("org.wso2.apache.httpcomponents:httpclient:4.3.1.wso2v1")
    implementation("com.squareup.okhttp3:okhttp:4.2.2")
    implementation("com.alibaba:fastjson:1.2.76")
}