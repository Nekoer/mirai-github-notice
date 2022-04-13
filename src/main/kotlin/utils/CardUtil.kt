package com.hcyacg.utils

import net.mamoe.mirai.message.data.LightApp
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.RichMessage
import net.mamoe.mirai.utils.MiraiExperimentalApi
import java.util.*

class CardUtil {

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
    ): Message {
        val contact: String =
            "{\"app\":\"com.tencent.structmsg\",\"desc\":\"新闻\",\"view\":\"news\",\"ver\":\"0.0.0.1\",\"prompt\":\"Github更新通知\",\"appID\":\"\",\"sourceName\":\"\",\"actionData\":\"\",\"actionData_A\":\"\",\"sourceUrl\":\"\",\"meta\":{\"news\":{\"app_type\":1,\"appid\":\"100951776\",\"desc\":\"${message}\",\"jumpUrl\":\"${html}\",\"preview\":\"${avatar}\",\"tag\":\"哔哩哔哩\",\"title\":\"${name}推送了\"}},\"config\":{\"autosize\":true,\"ctime\":${Date().time},\"forward\":true,\"type\":\"normal\"},\"text\":\"\",\"extraApps\":[],\"sourceAd\":\"\",\"extra\":\"{\\\"app_type\\\":1,\\\"appid\\\":100951776,\\\"uin\\\":895018766}\"}"

//        return RichMessage.share(url = html, title = name, content = message + "\n${time}", coverUrl = avatar)
        return LightApp(contact)
    }

}