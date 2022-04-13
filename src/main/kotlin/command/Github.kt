package com.hcyacg.command

import com.hcyacg.GithubNotice
import com.hcyacg.GithubTask
import com.hcyacg.GithubTask.Companion.switch
import com.hcyacg.initial.Configurations.Companion.overload


import net.mamoe.mirai.console.command.*
import net.mamoe.mirai.utils.MiraiLogger

class Github : CompositeCommand(
    GithubNotice,
    "github",
    description = "github"
) {
    var logger: MiraiLogger = MiraiLogger.Factory.create(Github::class, "Bot")

    @SubCommand("start","启动")
    @Description("开启监控")
    suspend fun CommandSender.start() {
        switch = true
        GithubTask().openTask()
    }

    @SubCommand("stop","关闭")
    @Description("停止监控")
    fun CommandSender.stop() {
        switch = false
        logger.info("请稍等片刻……")
    }

    @SubCommand("reload","重载")
    @Description("重载配置")
    fun CommandSender.reload() {
        overload()
    }



}