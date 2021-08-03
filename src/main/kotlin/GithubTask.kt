package com.hcyacg

import com.alibaba.fastjson.JSONObject
import com.hcyacg.config.Config
import com.hcyacg.config.Config.logger
import com.hcyacg.config.Config.project
import com.hcyacg.github.Commits.Companion.checkUpdate
import kotlinx.coroutines.*
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.utils.MiraiLogger
import java.util.*

class GithubTask {
    companion object {
        /**
         * 开启推送通知,循环仓库
         */
        private var time = Timer()
        var timerTask: TimerTask? = null

        @OptIn(DelicateCoroutinesApi::class)
        fun openTask() {
            logger.info("Github推送通知已开启")
            time.purge()

            time.schedule(object : TimerTask() {
                override fun run() {
                    for ((index, e) in project.withIndex()) {
                        val j: JSONObject = JSONObject.parseObject(e.toString())
                        suspend fun myFunc() = coroutineScope {
                            launch {
                                checkUpdate(
                                    projects = j["name"],
                                    branch = j["branch"],
                                    index = index
                                )
                            }
                        }
                    }
                    if (!Config.flag) {
                        this.cancel()
                        logger.info("Github推送通知已关闭")
                    }
                }
            }, Date(), 5000)
        }
    }
}