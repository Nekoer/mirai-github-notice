package com.hcyacg.initial

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.hcyacg.GithubNotice
import com.hcyacg.config.Config.admin
import com.hcyacg.config.Config.groups
import com.hcyacg.config.Config.logger
import com.hcyacg.config.Config.num
import com.hcyacg.config.Config.project
import com.hcyacg.config.Config.sha
import com.hcyacg.config.Config.token
import net.mamoe.mirai.utils.MiraiLogger
import java.io.File
import java.io.InputStream

class Configuration {
    companion object {
        private val systemPath: String = System.getProperty("user.dir")
        private val fileDirectory: File =
            File(systemPath + File.separator + "config" + File.separator + "com.hcyacg.github-notice")
        private val file: File = File(fileDirectory.path + File.separator + "setting.json")
        var projectJson: JSONObject = JSONObject.parseObject("{}")
        val path: String = Configuration::class.java.protectionDomain.codeSource.location.path

        /**
         * 初始化插件各项配置
         */
        fun init() {
            /**
             * 不存在配置文件将自动创建
             */

            if (!fileDirectory.exists() || !file.exists()) {
                fileDirectory.mkdirs()
                file.createNewFile()
                val resourceAsStream: InputStream? =
                    Configuration::class.java.classLoader.getResourceAsStream("setting.json")
                resourceAsStream?.let { file.writeBytes(it.readAllBytes()) }
                logger.warning("初始化配置文件,请在config/com.hcyacg.github-notice/setting.json配置相关参数")
            } else {
                load()
            }
        }

        /**
         * 重载配置文件
         */
        fun reload() {
            load()
            num = 0
            logger.info("配置文件已重载")
        }

        /**
         * 加载配置文件
         */
        fun load(){
            projectJson = JSONObject.parseObject(file.readText())
            project = JSON.parseArray(projectJson.getString("project"))
            for ((index, e) in project.withIndex()) {
                sha[JSONObject.parseObject(e.toString()).getString("name")] = ""
            }
            groups = JSON.parseArray(projectJson.getString("group"))
            for ((index, e) in groups.withIndex()) {
                groups[index] = e.toString()
            }
            admin = JSON.parseArray(projectJson.getString("admin"))
            for ((index, e) in admin.withIndex()) {
                admin[index] = e.toString()
            }
            token = projectJson.getString("token")
        }
    }
}
