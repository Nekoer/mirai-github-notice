package com.hcyacg.github

import com.alibaba.fastjson.JSONObject
import com.hcyacg.GithubNotice
import com.hcyacg.GithubTask
import com.hcyacg.GithubTask.Companion.all
import com.hcyacg.GithubTask.Companion.branches
import com.hcyacg.GithubTask.Companion.groups
import com.hcyacg.GithubTask.Companion.logger
import com.hcyacg.GithubTask.Companion.num
import com.hcyacg.GithubTask.Companion.project
import com.hcyacg.GithubTask.Companion.sha
import com.hcyacg.GithubTask.Companion.token
import com.hcyacg.GithubTask.Companion.users
import com.hcyacg.utils.CardUtil


import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.getGroupOrNull
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.console.util.ContactUtils.getFriendOrGroup
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.MessageEvent
import net.mamoe.mirai.message.data.LightApp
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.data.RichMessage
import net.mamoe.mirai.utils.MiraiExperimentalApi
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.closeQuietly
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Commits {

    private val client = OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
        .readTimeout(60000, TimeUnit.MILLISECONDS)

    /**
     * 检查github推送更新
     */
    @OptIn(ConsoleExperimentalApi::class)
    suspend fun checkCommitUpdate(
        projects: Any?,
        branch: Any?,
    ) {
        var name: Any? = null
        var time: Any? = null
        var html: Any? = null
        var avatar: Any? = null
        var message: Any? = null
        var stA: String? = null
        var response: Response? = null
//        logger.warning("${projects.toString()} => ${branch.toString()}")
        val bots = Bot.instances
        try {
            val request = if (RateLimits().isResidue()){
                Request.Builder()
                    .url("https://api.github.com/repos/${projects.toString()}/commits/${branch.toString()}")
                    .addHeader("Authorization", "token $token")
                    .addHeader("Accept", "application/vnd.github.v3+json").build()
            }else{
                Request.Builder()
                    .url("https://api.github.com/repos/${projects.toString()}/commits/${branch.toString()}")
                    .addHeader("Accept", "application/vnd.github.v3+json").build()
            }

            response = client.build().newCall(request).execute()

            if (response.isSuccessful) {
                stA = response.body?.string()
            }

            val jsonObject: JSONObject? = JSONObject.parseObject(stA)

            if (null != jsonObject) {
                if (sha["${projects}/$branch"].contentEquals(jsonObject["sha"].toString())) {
                    return
                }
                if (null == sha["${projects}/$branch"]){
                    sha["${projects}/$branch"] = jsonObject["sha"].toString()
                    return
                }
//                logger.warning("${sha["${projects}/$branch"]} => $sha1")

                sha["${projects}/$branch"] = jsonObject["sha"].toString()


                val commit: Any? = jsonObject["commit"]
                val committer: Any? = JSONObject.parseObject(commit.toString())["committer"]
                name = JSONObject.parseObject(committer.toString())["name"]
                val date: Any? = JSONObject.parseObject(committer.toString())["date"]

                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
                val myDate: Date = dateFormat.parse(date.toString().replace("Z", "+0000"))
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                time = sdf.format(Date(java.lang.String.valueOf(myDate.time).toLong()))

                message = JSONObject.parseObject(commit.toString())["message"]
                html = jsonObject["html_url"]

                val committers: Any? = jsonObject["committer"]
                avatar = JSONObject.parseObject(committers.toString())["avatar_url"]

                    for (e in groups) {
                        for (bot in bots){
                            bot.getGroup(e.toString().toLong())?.sendMessage(
                                CardUtil().process(
                                    message = message.toString(),
                                    html = html.toString(),
                                    avatar = avatar.toString(),
                                    time = time.toString(),
                                    name = name.toString()+ "为${projects.toString()}推送了代码",
                                    event = bot.getFriendOrGroup(e.toString().toLong())
                                )
                            )
                        }
                    }

                    for (u in users) {
                        for (bot in bots){
                            bot.getStranger(u.toString().toLong())?.sendMessage(
                                CardUtil().process(
                                    message = message.toString(),
                                    html = html.toString(),
                                    avatar = avatar.toString(),
                                    time = time.toString(),
                                    name = name.toString()+ "为${projects.toString()}推送了代码",
                                    event = bot.getFriendOrGroup(u.toString().toLong())
                                )
                            )
                        }
                    }
            }
        }catch (e: SocketTimeoutException){
            logger.warning("请求超时")
            return
        }catch (e: ConnectException){
            logger.warning("无法连接到api.github.com")
            return
        } catch (e: Exception) {
            e.printStackTrace()
        }finally {
            response?.close()
        }
    }


}