package com.hcyacg.github

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.hcyacg.GithubTask
import com.hcyacg.utils.CardUtil
import com.hcyacg.utils.HttpUtil
import entity.IssueItem
import entity.PullItem
import net.mamoe.mirai.Bot
import net.mamoe.mirai.utils.MiraiLogger
import okhttp3.Headers
import okhttp3.RequestBody
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.*

class Pulls {

    val logger: MiraiLogger = MiraiLogger.create("Bot")
    private val headers = Headers.Builder().add("Accept","application/vnd.github.v3+json").add("Authorization","token ${GithubTask.token}")
    private val requestBody: RequestBody? = null

    suspend fun checkPullsUpdate(
        projects: Any?
    ){
        val bots = Bot.instances
        var time: String? = null
        var myDate : Date? =null
        try{
            if (!RateLimits().isResidue()){
                headers.removeAll("Authorization")
            }
            val data = HttpUtil.request(
                method = HttpUtil.Companion.Method.GET,
                uri = "https://api.github.com/repos/$projects/pulls?state=open",
                body = requestBody,
                headers = headers.build(),
                logger = logger
            )

            if (null == data || JSONArray.parseArray(data).size < 1){
                return
            }

            val pullItem = JSONObject.parseObject(JSONArray.parseArray(data)[0].toString(), PullItem::class.java)

            if (null == GithubTask.pullItem[projects.toString()]?.nodeId){
                GithubTask.pullItem[projects.toString()] = pullItem
                return
            }

            if (GithubTask.pullItem[projects.toString()]!!.nodeId == pullItem.nodeId){
                return
            }
            GithubTask.pullItem[projects.toString()] = pullItem

            if (null != pullItem.createdAt){
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
                myDate = dateFormat.parse(pullItem.createdAt.toString().replace("Z", "+0000"))
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                time = sdf.format(Date(myDate.time.toString().toLong()))

            }

            if (myDate != null) {
                if (Date().time-(5 *60 * 1000) > myDate.time){
                    return
                }
            }

            for (e in GithubTask.groups) {
                for (bot in bots){
                    bot.getGroup(e.toString().toLong())?.sendMessage(
                        CardUtil().process(
                            message = pullItem.body.toString(),
                            html = pullItem.htmlUrl.toString(),
                            avatar = pullItem.user!!.avatarUrl.toString(),
                            time = time.toString(),
                            name = pullItem.user.login.toString()+ "发起了合并请求"
                        )
                    )
                }
            }

            for (u in GithubTask.users) {
                for (bot in bots){
                    bot.getStranger(u.toString().toLong())?.sendMessage(
                        CardUtil().process(
                            message = pullItem.body.toString(),
                            html = pullItem.htmlUrl.toString(),
                            avatar = pullItem.user!!.avatarUrl.toString(),
                            time = time.toString(),
                            name = pullItem.user.login.toString()+ "发起了合并请求"
                        )
                    )
                }
            }
        }catch (e: SocketTimeoutException){
            logger.warning("请求超时")
            return
        }catch (e: ConnectException){
            GithubTask.logger.warning("无法连接到api.github.com")
            return
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}