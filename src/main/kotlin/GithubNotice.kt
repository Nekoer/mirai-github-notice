package com.hcyacg


import com.hcyacg.GithubTask.Companion.all
import com.hcyacg.command.Github

import com.hcyacg.github.RateLimit
import com.hcyacg.initial.Configurations
import com.hcyacg.initial.Configurations.Companion.init


import net.mamoe.mirai.console.command.CommandManager

import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.utils.info


object GithubNotice : KotlinPlugin(
    JvmPluginDescription(
        id = "com.hcyacg.github-notice",
        name = "github更新通知",
        version = "1.3",
    ) {
        author("Nekoer")
        info("""github更新通知""")
    }
) {



    override fun onEnable() {
        CommandManager.registerCommand(Github(),true)
        logger.info { "github更新通知 loaded" }
        GlobalEventChannel.subscribeAlways<GroupMessageEvent> { event ->

            if (event.message.content.indexOf("/github rate_limit")>= 0){
                RateLimit().getRateLimit(event)
            }

        }
    }





    /**
     * 初始化配置文件 以及每次开启时读取配置文件
     */
    override fun PluginComponentStorage.onLoad() {
        init()

    }




}



