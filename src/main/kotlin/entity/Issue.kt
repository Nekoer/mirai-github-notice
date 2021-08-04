package entity

import com.alibaba.fastjson.annotation.JSONField
import java.math.BigInteger

data class Issue(

	@JSONField(name="Issue")
	val issue: List<IssueItem?>? = null
) {
	override fun toString(): String {
		return "Issue(issue=$issue)"
	}
}

data class PullRequest(

	@JSONField(name="patch_url")
	val patchUrl: String? = null,

	@JSONField(name="html_url")
	val htmlUrl: String? = null,

	@JSONField(name="diff_url")
	val diffUrl: String? = null,

	@JSONField(name="url")
	val url: String? = null
) {
	override fun toString(): String {
		return "PullRequest(patchUrl=$patchUrl, htmlUrl=$htmlUrl, diffUrl=$diffUrl, url=$url)"
	}
}

data class IssueItem(

	@JSONField(name="assignees")
	val assignees: List<Any?>? = null,

	@JSONField(name="created_at")
	val createdAt: String? = null,

	@JSONField(name="title")
	val title: String? = null,

	@JSONField(name="body")
	val body: String? = null,

	@JSONField(name="labels_url")
	val labelsUrl: String? = null,

	@JSONField(name="author_association")
	val authorAssociation: String? = null,

	@JSONField(name="number")
	val number: Int? = null,

	@JSONField(name="updated_at")
	val updatedAt: String? = null,

	@JSONField(name="performed_via_github_app")
	val performedViaGithubApp: Any? = null,

	@JSONField(name="comments_url")
	val commentsUrl: String? = null,

	@JSONField(name="active_lock_reason")
	val activeLockReason: Any? = null,

	@JSONField(name="repository_url")
	val repositoryUrl: String? = null,

	@JSONField(name="id")
	val id: Long? = null,

	@JSONField(name="state")
	val state: String? = null,

	@JSONField(name="locked")
	val locked: Boolean? = null,

	@JSONField(name="comments")
	val comments: Int? = null,

	@JSONField(name="closed_at")
	val closedAt: Any? = null,

	@JSONField(name="url")
	val url: String? = null,

	@JSONField(name="labels")
	val labels: List<LabelsItem?>? = null,

	@JSONField(name="milestone")
	val milestone: Milestone? = null,

	@JSONField(name="events_url")
	val eventsUrl: String? = null,

	@JSONField(name="html_url")
	val htmlUrl: String? = null,

	@JSONField(name="assignee")
	val assignee: Any? = null,

	@JSONField(name="user")
	val user: User? = null,

	@JSONField(name="node_id")
	val nodeId: String? = null,

	@JSONField(name="pull_request")
	val pullRequest: PullRequest? = null
) {
	override fun toString(): String {
		return "IssueItem(assignees=$assignees, createdAt=$createdAt, title=$title, body=$body, labelsUrl=$labelsUrl, authorAssociation=$authorAssociation, number=$number, updatedAt=$updatedAt, performedViaGithubApp=$performedViaGithubApp, commentsUrl=$commentsUrl, activeLockReason=$activeLockReason, repositoryUrl=$repositoryUrl, id=$id, state=$state, locked=$locked, comments=$comments, closedAt=$closedAt, url=$url, labels=$labels, milestone=$milestone, eventsUrl=$eventsUrl, htmlUrl=$htmlUrl, assignee=$assignee, user=$user, nodeId=$nodeId, pullRequest=$pullRequest)"
	}
}

data class Milestone(

	@JSONField(name="creator")
	val creator: Creator? = null,

	@JSONField(name="closed_at")
	val closedAt: Any? = null,

	@JSONField(name="description")
	val description: String? = null,

	@JSONField(name="created_at")
	val createdAt: String? = null,

	@JSONField(name="title")
	val title: String? = null,

	@JSONField(name="closed_issues")
	val closedIssues: Int? = null,

	@JSONField(name="url")
	val url: String? = null,

	@JSONField(name="due_on")
	val dueOn: Any? = null,

	@JSONField(name="labels_url")
	val labelsUrl: String? = null,

	@JSONField(name="number")
	val number: Int? = null,

	@JSONField(name="updated_at")
	val updatedAt: String? = null,

	@JSONField(name="html_url")
	val htmlUrl: String? = null,

	@JSONField(name="id")
	val id: Long? = null,

	@JSONField(name="state")
	val state: String? = null,

	@JSONField(name="open_issues")
	val openIssues: Int? = null,

	@JSONField(name="node_id")
	val nodeId: String? = null
) {
	override fun toString(): String {
		return "Milestone(creator=$creator, closedAt=$closedAt, description=$description, createdAt=$createdAt, title=$title, closedIssues=$closedIssues, url=$url, dueOn=$dueOn, labelsUrl=$labelsUrl, number=$number, updatedAt=$updatedAt, htmlUrl=$htmlUrl, id=$id, state=$state, openIssues=$openIssues, nodeId=$nodeId)"
	}
}

data class User(

	@JSONField(name="gists_url")
	val gistsUrl: String? = null,

	@JSONField(name="repos_url")
	val reposUrl: String? = null,

	@JSONField(name="following_url")
	val followingUrl: String? = null,

	@JSONField(name="starred_url")
	val starredUrl: String? = null,

	@JSONField(name="login")
	val login: String? = null,

	@JSONField(name="followers_url")
	val followersUrl: String? = null,

	@JSONField(name="type")
	val type: String? = null,

	@JSONField(name="url")
	val url: String? = null,

	@JSONField(name="subscriptions_url")
	val subscriptionsUrl: String? = null,

	@JSONField(name="received_events_url")
	val receivedEventsUrl: String? = null,

	@JSONField(name="avatar_url")
	val avatarUrl: String? = null,

	@JSONField(name="events_url")
	val eventsUrl: String? = null,

	@JSONField(name="html_url")
	val htmlUrl: String? = null,

	@JSONField(name="site_admin")
	val siteAdmin: Boolean? = null,

	@JSONField(name="id")
	val id: Long? = null,

	@JSONField(name="gravatar_id")
	val gravatarId: String? = null,

	@JSONField(name="node_id")
	val nodeId: String? = null,

	@JSONField(name="organizations_url")
	val organizationsUrl: String? = null
) {
	override fun toString(): String {
		return "User(gistsUrl=$gistsUrl, reposUrl=$reposUrl, followingUrl=$followingUrl, starredUrl=$starredUrl, login=$login, followersUrl=$followersUrl, type=$type, url=$url, subscriptionsUrl=$subscriptionsUrl, receivedEventsUrl=$receivedEventsUrl, avatarUrl=$avatarUrl, eventsUrl=$eventsUrl, htmlUrl=$htmlUrl, siteAdmin=$siteAdmin, id=$id, gravatarId=$gravatarId, nodeId=$nodeId, organizationsUrl=$organizationsUrl)"
	}
}

data class LabelsItem(

	@JSONField(name="default")
	val jsonMemberDefault: Boolean? = null,

	@JSONField(name="color")
	val color: String? = null,

	@JSONField(name="name")
	val name: String? = null,

	@JSONField(name="description")
	val description: String? = null,

	@JSONField(name="id")
	val id: Long? = null,

	@JSONField(name="url")
	val url: String? = null,

	@JSONField(name="node_id")
	val nodeId: String? = null
) {
	override fun toString(): String {
		return "LabelsItem(jsonMemberDefault=$jsonMemberDefault, color=$color, name=$name, description=$description, id=$id, url=$url, nodeId=$nodeId)"
	}
}

data class Creator(

	@JSONField(name="gists_url")
	val gistsUrl: String? = null,

	@JSONField(name="repos_url")
	val reposUrl: String? = null,

	@JSONField(name="following_url")
	val followingUrl: String? = null,

	@JSONField(name="starred_url")
	val starredUrl: String? = null,

	@JSONField(name="login")
	val login: String? = null,

	@JSONField(name="followers_url")
	val followersUrl: String? = null,

	@JSONField(name="type")
	val type: String? = null,

	@JSONField(name="url")
	val url: String? = null,

	@JSONField(name="subscriptions_url")
	val subscriptionsUrl: String? = null,

	@JSONField(name="received_events_url")
	val receivedEventsUrl: String? = null,

	@JSONField(name="avatar_url")
	val avatarUrl: String? = null,

	@JSONField(name="events_url")
	val eventsUrl: String? = null,

	@JSONField(name="html_url")
	val htmlUrl: String? = null,

	@JSONField(name="site_admin")
	val siteAdmin: Boolean? = null,

	@JSONField(name="id")
	val id: Long? = null,

	@JSONField(name="gravatar_id")
	val gravatarId: String? = null,

	@JSONField(name="node_id")
	val nodeId: String? = null,

	@JSONField(name="organizations_url")
	val organizationsUrl: String? = null
) {
	override fun toString(): String {
		return "Creator(gistsUrl=$gistsUrl, reposUrl=$reposUrl, followingUrl=$followingUrl, starredUrl=$starredUrl, login=$login, followersUrl=$followersUrl, type=$type, url=$url, subscriptionsUrl=$subscriptionsUrl, receivedEventsUrl=$receivedEventsUrl, avatarUrl=$avatarUrl, eventsUrl=$eventsUrl, htmlUrl=$htmlUrl, siteAdmin=$siteAdmin, id=$id, gravatarId=$gravatarId, nodeId=$nodeId, organizationsUrl=$organizationsUrl)"
	}
}
