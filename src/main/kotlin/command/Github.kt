package com.hcyacg.command

import com.hcyacg.GithubNotice
import com.hcyacg.GithubTask
import com.hcyacg.GithubTask.Companion.switch


import com.hcyacg.initial.Configuration
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CommandSenderOnMessage
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.MessageSource.Key.quote
import net.mamoe.mirai.utils.MiraiLogger

class Github : CompositeCommand(
    GithubNotice,
    "github",
    description = "github"
) {
    var logger: MiraiLogger = MiraiLogger.create("Bot")

    @SubCommand("start","启动")
    fun CommandSender.start() {
        switch = true
        GithubTask().openTask(this)
    }

    @SubCommand("stop","关闭")
    fun CommandSender.stop() {
        switch = false
        logger.info("请稍等片刻……")
    }

    @SubCommand("reload","重载")
    fun CommandSender.reload() {
//        Configuration.load()
//        GithubTask.num = 0
//        Configuration.logger.info("配置文件已重载")
    }

}