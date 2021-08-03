package com.hcyacg


import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject


import com.hcyacg.github.Commits
import kotlinx.coroutines.*
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.utils.MiraiLogger
import java.util.*

class GithubTask {
    companion object {
        var switch: Boolean = false
        var logger: MiraiLogger = MiraiLogger.create("Bot")
        var sha = HashMap<String, String>()
        var num: Int = 0
        var projectJson: JSONObject = JSONObject.parseObject("{}")
        var token: String? = null
        var groups: JSONArray = JSONArray.parseArray("[]")
        var users: JSONArray = JSONArray.parseArray("[]")
        var admin: JSONArray = JSONArray.parseArray("[]")
        var project: JSONArray = JSONArray.parseArray("[]")
//        var event : GroupMessageEvent? = null
    }
    /**
     * 开启推送通知,循环仓库
     */
    private var time = Timer()
    var timerTask: TimerTask? = null


    @OptIn(DelicateCoroutinesApi::class)
    suspend fun openTask() {
        logger.info("Github推送通知已开启")
        try{
            time.purge()
            time.schedule(object : TimerTask() {
                override fun run() {
                    for ((index, e) in project.withIndex()) {
                        val j: JSONObject = JSONObject.parseObject(e.toString())
                        runBlocking{
                                Commits().checkUpdate(
                                    projects = j["name"],
                                    branch = j["branch"],
                                    index = index
                                )
                        }
                    }
                    if (!switch) {
                        this.cancel()
                        logger.info("Github推送通知已关闭")
                    }
                }
            }, Date(), 5000)
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

}