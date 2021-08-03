package com.hcyacg.command

import com.hcyacg.GithubNotice
import com.hcyacg.GithubTask.Companion.openTask

import com.hcyacg.config.Config.flag
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand

object Commands : CompositeCommand(
    GithubNotice,
    "github",
    description = "github"
) {
    @SubCommand("start")
    suspend fun CommandSender.start() { // 通过 /manage mute <target> <duration> 调用
        flag = true
        openTask()
    }

    @SubCommand("stop")
    suspend fun CommandSender.stop() { // 通过 /manage mute <target> <duration> 调用
        flag = false
    }

    @SubCommand("reload")
    suspend fun CommandSender.reload() { // 通过 /manage mute <target> <duration> 调用
        reload()
    }

}