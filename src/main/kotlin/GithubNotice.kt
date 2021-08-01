package com.hcyacg

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject

import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.MiraiExperimentalApi
import net.mamoe.mirai.utils.MiraiLogger
import net.mamoe.mirai.utils.info
import okhttp3.*
import okhttp3.internal.closeQuietly

import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.lang.RuntimeException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.log


object GithubNotice : KotlinPlugin(
    JvmPluginDescription(
        id = "com.hcyacg.github-notice",
        name = "github更新通知",
        version = "1.0-SNAPSHOT",
    ) {
        author("Nekoer")
        info("""github更新通知""")
    }
) {

//    var sha = mutableListOf<String>()
    var sha = HashMap<String, String>()
    var num: Int = 0
    var projectJson: JSONObject = JSONObject.parseObject("{}")
    var token: String? = null
    var project: JSONArray = JSONArray.parseArray("[]")
    var flag: Boolean = false
    var groups: JSONArray = JSONArray.parseArray("[]")
    var admin: JSONArray = JSONArray.parseArray("[]")
    val client = OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
        .readTimeout(60000, TimeUnit.MILLISECONDS)


    override fun onEnable() {
        logger.info { "Plugin loaded" }
        GlobalEventChannel.subscribeAlways<GroupMessageEvent> { event ->
            val chain = event.message

            if (groups.indexOf(event.group.id.toString()) >= 0) {

                if (chain.content.contentEquals("/github start") && admin.indexOf(event.sender.id.toString()) >= 0 && !flag) {
                    flag = true
                    event.subject.sendMessage("Github更新通知已开启")
                    openPlugin(event, logger)
                }

                if (chain.content.contentEquals("/github stop") && admin.indexOf(event.sender.id.toString()) >= 0 && flag) {
                    flag = false
                    event.subject.sendMessage("Github更新通知已关闭")
                }

                if (chain.content.contentEquals("/github reload") && admin.indexOf(event.sender.id.toString()) >= 0) {
                    reload()
                    event.subject.sendMessage("配置文件已重载")
                }
            }
        }
    }


    /**
     * 开启推送通知,循环仓库
     */
    private var time = Timer()
    var timerTask: TimerTask? = null

    @OptIn(DelicateCoroutinesApi::class)
    private fun openPlugin(event: GroupMessageEvent, logger: MiraiLogger) {
        logger.info("Github推送通知已开启")
        time.purge()

        time.schedule(object : TimerTask() {
            override fun run() {
                for ((index, e) in project.withIndex()) {
                    val j: JSONObject = JSONObject.parseObject(e.toString())
                    launch {
                        checkUpdate(
                            event = event,
                            logger = logger,
                            projects = j["name"],
                            branch = j["branch"],
                            index = index
                        )
                    }
                }
                if (!flag) {
                    this.cancel()
                    logger.info("Github推送通知已关闭")
                }
            }
        }, Date(), 5000)
    }


    /**
     * 初始化配置文件 以及每次开启时读取配置文件
     */
    override fun PluginComponentStorage.onLoad() {
        val path: String = javaClass.protectionDomain.codeSource.location.path
        val systemPath: String = System.getProperty("user.dir")
        val fileDirectory: File =
            File(systemPath + File.separator + "config" + File.separator + "com.hcyacg.github-notice")
        val file: File = File(fileDirectory.path + File.separator + "setting.json")
        if (!fileDirectory.exists() || !file.exists()) {
            fileDirectory.mkdirs()
            file.createNewFile()
            val resourceAsStream: InputStream? =
                GithubNotice::class.java.classLoader.getResourceAsStream("setting.json")
            resourceAsStream?.let { file.writeBytes(it.readAllBytes()) }
            logger.warning("初始化配置文件,请在config/com.hcyacg.github-notice/setting.json配置相关参数")
        } else {
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

    /**
     * 重载配置文件
     */
    fun reload() {
        val systemPath: String = System.getProperty("user.dir")
        val fileDirectory: File =
            File(systemPath + File.separator + "config" + File.separator + "com.hcyacg.github-notice")
        val file: File = File(fileDirectory.path + File.separator + "setting.json")

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
        num = 0
    }

    /**
     * 检查github推送更新
     */
    private suspend fun checkUpdate(
        event: GroupMessageEvent,
        logger: MiraiLogger,
        projects: Any?,
        branch: Any?,
        index: Int
    ) {
        var name: Any? = null
        var time: Any? = null
        var html: Any? = null
        var avatar: Any? = null
        var message: Any? = null
        var stA: String? = null
        var sha1: Any? = null
        var response: Response? = null

        try {
            val request: Request = Request.Builder()
                .url("https://api.github.com/repos/${projects.toString()}/commits/${branch.toString()}")
                .addHeader("Authorization", "token $token")
                .addHeader("Accept", "application/vnd.github.v3+json").build()
            response = client.build().newCall(request).execute()

            if (response.isSuccessful) {
                stA = response.body?.string()
            }

            val jsonObject: JSONObject? = JSONObject.parseObject(stA)

            if (null != jsonObject) {
                val sha1: Any? = jsonObject["sha"]
                if (sha[projects].contentEquals(sha1.toString())) {
                    return
                }
                sha[projects.toString()] = sha1.toString()

                val commit: Any? = jsonObject["commit"]
                val committer: Any? = JSONObject.parseObject(commit.toString())["committer"]
                name = JSONObject.parseObject(committer.toString())["name"]
                val date: Any? = JSONObject.parseObject(committer.toString())["date"]

                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
                val myDate: Date = dateFormat.parse(date.toString().replace("Z", "+0000"))
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                time = sdf.format(Date(java.lang.String.valueOf(myDate.time).toLong()))

                message = JSONObject.parseObject(commit.toString())["message"]
                html = jsonObject["html_url"]

                val committers: Any? = jsonObject["committer"]
                avatar = JSONObject.parseObject(committers.toString())["avatar_url"]

                if (num >= project.size) {
                    for (e in groups) {
                        event.bot.getGroup(e.toString().toLong())?.sendMessage(
                            process(
                                message = message.toString(),
                                html = html.toString(),
                                avatar = avatar.toString(),
                                time = time.toString(),
                                name = name.toString(),
                                event = event
                            )
                        )
//                        event.bot.getGroup(e.toString().toLong())?.sendMessage("${name}推送了代码\n${message}\n${time}\n${html}")
                    }
                } else {
                    num += 1
                }
            }
            response.closeQuietly()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 返回卡片
     */
    @OptIn(MiraiExperimentalApi::class)
    @Throws(Exception::class)
    fun process(
        message: String,
        html: String,
        avatar: String,
        name: String,
        time: String,
        event: GroupMessageEvent
    ): Message {
        val contact: String =
            "{\"app\":\"com.tencent.structmsg\",\"desc\":\"新闻\",\"view\":\"news\",\"ver\":\"0.0.0.1\",\"prompt\":\"Github更新通知\",\"appID\":\"\",\"sourceName\":\"\",\"actionData\":\"\",\"actionData_A\":\"\",\"sourceUrl\":\"\",\"meta\":{\"news\":{\"app_type\":1,\"appid\":\"100951776\",\"desc\":\"${message}\",\"jumpUrl\":\"${html}\",\"preview\":\"${avatar}\",\"tag\":\"哔哩哔哩\",\"title\":\"${name}推送了\"}},\"config\":{\"autosize\":true,\"ctime\":${Date().time},\"forward\":true,\"type\":\"normal\"},\"text\":\"\",\"extraApps\":[],\"sourceAd\":\"\",\"extra\":\"{\\\"app_type\\\":1,\\\"appid\\\":100951776,\\\"uin\\\":895018766}\"}"
        return RichMessage.share(url = html, title = name + "推送了代码", content = message + "\n${time}", coverUrl = avatar)
//        return LightApp(contact)
    }
}



