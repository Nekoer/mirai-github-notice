package com.hcyacg.config

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import net.mamoe.mirai.utils.MiraiLogger
import java.util.HashMap


/**
 * 配置文件中各键值对参数
 */
object Config {
    var sha = HashMap<String, String>()
    var num: Int = 0
    var projectJson: JSONObject = JSONObject.parseObject("{}")
    var token: String? = null
    var project: JSONArray = JSONArray.parseArray("[]")
    var flag: Boolean = false
    var groups: JSONArray = JSONArray.parseArray("[]")
    var admin: JSONArray = JSONArray.parseArray("[]")

    var logger: MiraiLogger = MiraiLogger.create("Bot")

}