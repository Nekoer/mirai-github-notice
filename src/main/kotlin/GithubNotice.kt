package com.hcyacg


import com.hcyacg.command.Commands
import com.hcyacg.initial.Configuration.Companion.init

import net.mamoe.mirai.console.command.CommandManager

import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent

import net.mamoe.mirai.utils.info
import okhttp3.*

import java.util.concurrent.TimeUnit


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
    val client = OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
    .readTimeout(60000, TimeUnit.MILLISECONDS)


    override fun onEnable() {
        CommandManager.registerCommand(Commands,true)

        logger.info { "Plugin loaded" }
        GlobalEventChannel.subscribeAlways<GroupMessageEvent> { event ->
        }
    }





    /**
     * 初始化配置文件 以及每次开启时读取配置文件
     */
    override fun PluginComponentStorage.onLoad() {
        init()
    }




}



