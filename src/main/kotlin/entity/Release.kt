package entity

import com.alibaba.fastjson.annotation.JSONField

data class Release(

	@JSONField(name="author")
	val author: Author? = null,

	@JSONField(name="tag_name")
	val tagName: String? = null,

	@JSONField(name="created_at")
	val createdAt: String? = null,

	@JSONField(name="body")
	val body: String? = null,

	@JSONField(name="url")
	val url: String? = null,

	@JSONField(name="assets_url")
	val assetsUrl: String? = null,

	@JSONField(name="assets")
	val assets: List<Assets?>? = null,

	@JSONField(name="prerelease")
	val prerelease: Boolean? = null,

	@JSONField(name="html_url")
	val htmlUrl: String? = null,

	@JSONField(name="target_commitish")
	val targetCommitish: String? = null,

	@JSONField(name="draft")
	val draft: Boolean? = null,

	@JSONField(name="zipball_url")
	val zipballUrl: String? = null,

	@JSONField(name="name")
	val name: String? = null,

	@JSONField(name="upload_url")
	val uploadUrl: String? = null,

	@JSONField(name="id")
	val id: Int? = null,

	@JSONField(name="published_at")
	val publishedAt: String? = null,

	@JSONField(name="tarball_url")
	val tarballUrl: String? = null,

	@JSONField(name="node_id")
	val nodeId: String? = null
) {
	override fun toString(): String {
		return "Release(author=$author, tagName=$tagName, createdAt=$createdAt, body=$body, url=$url, assetsUrl=$assetsUrl, assets=$assets, prerelease=$prerelease, htmlUrl=$htmlUrl, targetCommitish=$targetCommitish, draft=$draft, zipballUrl=$zipballUrl, name=$name, uploadUrl=$uploadUrl, id=$id, publishedAt=$publishedAt, tarballUrl=$tarballUrl, nodeId=$nodeId)"
	}
}

data class Author(
	@JSONField(name = "avatar_url")
	val avatarUrl: String? = null,
	@JSONField(name = "events_url")
	val eventsUrl: String? = null,
	@JSONField(name = "followers_url")
	val followersUrl: String? = null,
	@JSONField(name = "following_url")
	val followingUrl: String? = null,
	@JSONField(name = "gists_url")
	val gistsUrl: String? = null,
	@JSONField(name = "gravatar_id")
	val gravatarId: String? = null,
	@JSONField(name = "html_url")
	val htmlUrl: String? = null,
	@JSONField(name = "id")
	val id: Int? = null,
	@JSONField(name = "login")
	val login: String? = null,
	@JSONField(name = "node_id")
	val nodeId: String? = null,
	@JSONField(name = "organizations_url")
	val organizationsUrl: String? = null,
	@JSONField(name = "received_events_url")
	val receivedEventsUrl: String? = null,
	@JSONField(name = "repos_url")
	val reposUrl: String? = null,
	@JSONField(name = "site_admin")
	val siteAdmin: Boolean? = null,
	@JSONField(name = "starred_url")
	val starredUrl: String? = null,
	@JSONField(name = "subscriptions_url")
	val subscriptionsUrl: String? = null,
	@JSONField(name = "type")
	val type: String? = null,
	@JSONField(name = "url")
	val url: String? = null
) {
	override fun toString(): String {
		return "Author(avatarUrl='$avatarUrl', eventsUrl='$eventsUrl', followersUrl='$followersUrl', followingUrl='$followingUrl', gistsUrl='$gistsUrl', gravatarId='$gravatarId', htmlUrl='$htmlUrl', id=$id, login='$login', nodeId='$nodeId', organizationsUrl='$organizationsUrl', receivedEventsUrl='$receivedEventsUrl', reposUrl='$reposUrl', siteAdmin=$siteAdmin, starredUrl='$starredUrl', subscriptionsUrl='$subscriptionsUrl', type='$type', url='$url')"
	}
}


data class Assets(
    @JSONField(name = "browser_download_url")
    val browserDownloadUrl: String? = null,
    @JSONField(name = "content_type")
    val contentType: String? = null,
    @JSONField(name = "created_at")
    val createdAt: String? = null,
    @JSONField(name = "download_count")
    val downloadCount: Int? = null,
    @JSONField(name = "id")
    val id: Int? = null,
    @JSONField(name = "label")
    val label: Any? = null,
    @JSONField(name = "name")
    val name: String? = null,
    @JSONField(name = "node_id")
    val nodeId: String? = null,
    @JSONField(name = "size")
    val size: Int? = null,
    @JSONField(name = "state")
    val state: String? = null,
    @JSONField(name = "updated_at")
    val updatedAt: String? = null,
    @JSONField(name = "uploader")
    val uploader: Uploader? = null,
    @JSONField(name = "url")
    val url: String? = null
) {
	override fun toString(): String {
		return "Assets(browserDownloadUrl='$browserDownloadUrl', contentType='$contentType', createdAt='$createdAt', downloadCount=$downloadCount, id=$id, label=$label, name='$name', nodeId='$nodeId', size=$size, state='$state', updatedAt='$updatedAt', uploader=$uploader, url='$url')"
	}
}

data class Uploader(
	@JSONField(name = "avatar_url")
	val avatarUrl: String? = null,
	@JSONField(name = "events_url")
	val eventsUrl: String? = null,
	@JSONField(name = "followers_url")
	val followersUrl: String? = null,
	@JSONField(name = "following_url")
	val followingUrl: String? = null,
	@JSONField(name = "gists_url")
	val gistsUrl: String? = null,
	@JSONField(name = "gravatar_id")
	val gravatarId: String? = null,
	@JSONField(name = "html_url")
	val htmlUrl: String? = null,
	@JSONField(name = "id")
	val id: Int? = null,
	@JSONField(name = "login")
	val login: String? = null,
	@JSONField(name = "node_id")
	val nodeId: String? = null,
	@JSONField(name = "organizations_url")
	val organizationsUrl: String? = null,
	@JSONField(name = "received_events_url")
	val receivedEventsUrl: String? = null,
	@JSONField(name = "repos_url")
	val reposUrl: String? = null,
	@JSONField(name = "site_admin")
	val siteAdmin: Boolean? = null,
	@JSONField(name = "starred_url")
	val starredUrl: String? = null,
	@JSONField(name = "subscriptions_url")
	val subscriptionsUrl: String? = null,
	@JSONField(name = "type")
	val type: String? = null,
	@JSONField(name = "url")
	val url: String? = null
) {
	override fun toString(): String {
		return "Uploader(avatarUrl='$avatarUrl', eventsUrl='$eventsUrl', followersUrl='$followersUrl', followingUrl='$followingUrl', gistsUrl='$gistsUrl', gravatarId='$gravatarId', htmlUrl='$htmlUrl', id=$id, login='$login', nodeId='$nodeId', organizationsUrl='$organizationsUrl', receivedEventsUrl='$receivedEventsUrl', reposUrl='$reposUrl', siteAdmin=$siteAdmin, starredUrl='$starredUrl', subscriptionsUrl='$subscriptionsUrl', type='$type', url='$url')"
	}
}