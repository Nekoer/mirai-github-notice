package com.hcyacg.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import net.mamoe.mirai.utils.MiraiExperimentalApi
import java.util.*

class CardUtil {

    /**
     * 返回卡片
     */
    @Throws(Exception::class)
    suspend fun process(
        message: String,
        html: String,
        avatar: String,
        name: String,
        time: String,
        event: Contact
    ): Message {
//        val contact: String =
//            "{\"app\":\"com.tencent.structmsg\",\"desc\":\"新闻\",\"view\":\"news\",\"ver\":\"0.0.0.1\",\"prompt\":\"Github更新通知\",\"appID\":\"\",\"sourceName\":\"\",\"actionData\":\"\",\"actionData_A\":\"\",\"sourceUrl\":\"\",\"meta\":{\"news\":{\"app_type\":1,\"appid\":\"100951776\",\"desc\":\"${message}\",\"jumpUrl\":\"${html}\",\"preview\":\"${avatar}\",\"tag\":\"哔哩哔哩\",\"title\":\"${name}推送了\"}},\"config\":{\"autosize\":true,\"ctime\":${Date().time},\"forward\":true,\"type\":\"normal\"},\"text\":\"\",\"extraApps\":[],\"sourceAd\":\"\",\"extra\":\"{\\\"app_type\\\":1,\\\"appid\\\":100951776,\\\"uin\\\":895018766}\"}"

        val toExternalResource = ImageUtil.getImage(avatar).toByteArray().toExternalResource()
        val imageId: String = toExternalResource.uploadAsImage(event).imageId
        withContext(Dispatchers.IO) {
            toExternalResource.close()
        }
//        val message = PlainText("")
//        return RichMessage.share(url = html, title = name, content = message + "\n${time}", coverUrl = avatar)
        return Image(imageId).plus(name).plus("\n")
            .plus("时间：$time").plus("\n")
            .plus("介绍：$message").plus("\n")
            .plus("网址：$html").plus("\n")
    }

}