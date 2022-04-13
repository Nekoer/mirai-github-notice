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

        val toExternalResource = ImageUtil.getImage(avatar).toByteArray().toExternalResource()
        val imageId: String = toExternalResource.uploadAsImage(event).imageId
        withContext(Dispatchers.IO) {
            toExternalResource.close()
        }


        val builder = Image.Builder.newBuilder(imageId)
        builder.height = 50
        builder.width = 50

        builder.type = ImageType.PNG

        val image = builder.build();


        return image.plus(name).plus("\n")
            .plus("时间：$time").plus("\n")
            .plus("介绍：$message").plus("\n")
            .plus("网址：$html").plus("\n")
    }

}