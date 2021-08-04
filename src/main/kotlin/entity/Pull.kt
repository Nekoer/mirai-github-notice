package entity

import com.alibaba.fastjson.annotation.JSONField

data class Pull(

	@JSONField(name="Pull")
	val pull: List<PullItem?>? = null
)

data class Comments(

	@JSONField(name="href")
	val href: String? = null
)

data class Commits(

	@JSONField(name="href")
	val href: String? = null
)

data class Issues(

	@JSONField(name="href")
	val href: String? = null
)

data class RequestedReviewersItem(
	val any: Any? = null
)

data class Links(

	@JSONField(name="comments")
	val comments: Comments? = null,

	@JSONField(name="issue")
	val issue: Issues? = null,

	@JSONField(name="self")
	val self: Self? = null,

	@JSONField(name="review_comments")
	val reviewComments: ReviewComments? = null,

	@JSONField(name="commits")
	val commits: Commits? = null,

	@JSONField(name="statuses")
	val statuses: Statuses? = null,

	@JSONField(name="html")
	val html: Html? = null,

	@JSONField(name="review_comment")
	val reviewComment: ReviewComment? = null
)

data class Html(

	@JSONField(name="href")
	val href: String? = null
)

data class Repo(

	@JSONField(name="stargazers_count")
	val stargazersCount: Int? = null,

	@JSONField(name="pushed_at")
	val pushedAt: String? = null,

	@JSONField(name="subscription_url")
	val subscriptionUrl: String? = null,

	@JSONField(name="language")
	val language: String? = null,

	@JSONField(name="branches_url")
	val branchesUrl: String? = null,

	@JSONField(name="issue_comment_url")
	val issueCommentUrl: String? = null,

	@JSONField(name="labels_url")
	val labelsUrl: String? = null,

	@JSONField(name="subscribers_url")
	val subscribersUrl: String? = null,

	@JSONField(name="releases_url")
	val releasesUrl: String? = null,

	@JSONField(name="svn_url")
	val svnUrl: String? = null,

	@JSONField(name="id")
	val id: Long? = null,

	@JSONField(name="forks")
	val forks: Int? = null,

	@JSONField(name="archive_url")
	val archiveUrl: String? = null,

	@JSONField(name="git_refs_url")
	val gitRefsUrl: String? = null,

	@JSONField(name="forks_url")
	val forksUrl: String? = null,

	@JSONField(name="statuses_url")
	val statusesUrl: String? = null,

	@JSONField(name="ssh_url")
	val sshUrl: String? = null,

	@JSONField(name="license")
	val license: License? = null,

	@JSONField(name="full_name")
	val fullName: String? = null,

	@JSONField(name="size")
	val size: Int? = null,

	@JSONField(name="languages_url")
	val languagesUrl: String? = null,

	@JSONField(name="html_url")
	val htmlUrl: String? = null,

	@JSONField(name="collaborators_url")
	val collaboratorsUrl: String? = null,

	@JSONField(name="clone_url")
	val cloneUrl: String? = null,

	@JSONField(name="name")
	val name: String? = null,

	@JSONField(name="pulls_url")
	val pullsUrl: String? = null,

	@JSONField(name="default_branch")
	val defaultBranch: String? = null,

	@JSONField(name="hooks_url")
	val hooksUrl: String? = null,

	@JSONField(name="trees_url")
	val treesUrl: String? = null,

	@JSONField(name="tags_url")
	val tagsUrl: String? = null,

	@JSONField(name="private")
	val jsonMemberPrivate: Boolean? = null,

	@JSONField(name="contributors_url")
	val contributorsUrl: String? = null,

	@JSONField(name="has_downloads")
	val hasDownloads: Boolean? = null,

	@JSONField(name="notifications_url")
	val notificationsUrl: String? = null,

	@JSONField(name="open_issues_count")
	val openIssuesCount: Int? = null,

	@JSONField(name="description")
	val description: String? = null,

	@JSONField(name="created_at")
	val createdAt: String? = null,

	@JSONField(name="watchers")
	val watchers: Int? = null,

	@JSONField(name="keys_url")
	val keysUrl: String? = null,

	@JSONField(name="deployments_url")
	val deploymentsUrl: String? = null,

	@JSONField(name="has_projects")
	val hasProjects: Boolean? = null,

	@JSONField(name="archived")
	val archived: Boolean? = null,

	@JSONField(name="has_wiki")
	val hasWiki: Boolean? = null,

	@JSONField(name="updated_at")
	val updatedAt: String? = null,

	@JSONField(name="comments_url")
	val commentsUrl: String? = null,

	@JSONField(name="stargazers_url")
	val stargazersUrl: String? = null,

	@JSONField(name="disabled")
	val disabled: Boolean? = null,

	@JSONField(name="git_url")
	val gitUrl: String? = null,

	@JSONField(name="has_pages")
	val hasPages: Boolean? = null,

	@JSONField(name="owner")
	val owner: Owner? = null,

	@JSONField(name="commits_url")
	val commitsUrl: String? = null,

	@JSONField(name="compare_url")
	val compareUrl: String? = null,

	@JSONField(name="git_commits_url")
	val gitCommitsUrl: String? = null,

	@JSONField(name="blobs_url")
	val blobsUrl: String? = null,

	@JSONField(name="git_tags_url")
	val gitTagsUrl: String? = null,

	@JSONField(name="merges_url")
	val mergesUrl: String? = null,

	@JSONField(name="downloads_url")
	val downloadsUrl: String? = null,

	@JSONField(name="has_issues")
	val hasIssues: Boolean? = null,

	@JSONField(name="url")
	val url: String? = null,

	@JSONField(name="contents_url")
	val contentsUrl: String? = null,

	@JSONField(name="mirror_url")
	val mirrorUrl: Any? = null,

	@JSONField(name="milestones_url")
	val milestonesUrl: String? = null,

	@JSONField(name="teams_url")
	val teamsUrl: String? = null,

	@JSONField(name="fork")
	val fork: Boolean? = null,

	@JSONField(name="issues_url")
	val issuesUrl: String? = null,

	@JSONField(name="events_url")
	val eventsUrl: String? = null,

	@JSONField(name="issue_events_url")
	val issueEventsUrl: String? = null,

	@JSONField(name="assignees_url")
	val assigneesUrl: String? = null,

	@JSONField(name="open_issues")
	val openIssues: Int? = null,

	@JSONField(name="watchers_count")
	val watchersCount: Int? = null,

	@JSONField(name="node_id")
	val nodeId: String? = null,

	@JSONField(name="homepage")
	val homepage: String? = null,

	@JSONField(name="forks_count")
	val forksCount: Int? = null
)



data class License(

	@JSONField(name="name")
	val name: String? = null,

	@JSONField(name="spdx_id")
	val spdxId: String? = null,

	@JSONField(name="key")
	val key: String? = null,

	@JSONField(name="url")
	val url: String? = null,

	@JSONField(name="node_id")
	val nodeId: String? = null
)

data class Head(

	@JSONField(name="ref")
	val ref: String? = null,

	@JSONField(name="repo")
	val repo: Repo? = null,

	@JSONField(name="label")
	val label: String? = null,

	@JSONField(name="sha")
	val sha: String? = null,

	@JSONField(name="user")
	val user: User? = null
)

data class ReviewComment(

	@JSONField(name="href")
	val href: String? = null
)

data class Statuses(

	@JSONField(name="href")
	val href: String? = null
)

data class PullItem(

	@JSONField(name="issue_url")
	val issueUrl: String? = null,

	@JSONField(name="_links")
	val links: Links? = null,

	@JSONField(name="diff_url")
	val diffUrl: String? = null,

	@JSONField(name="created_at")
	val createdAt: String? = null,

	@JSONField(name="assignees")
	val assignees: List<Any?>? = null,

	@JSONField(name="requested_reviewers")
	val requestedReviewers: List<RequestedReviewersItem?>? = null,

	@JSONField(name="title")
	val title: String? = null,

	@JSONField(name="body")
	val body: String? = null,

	@JSONField(name="requested_teams")
	val requestedTeams: List<Any?>? = null,

	@JSONField(name="head")
	val head: Head? = null,

	@JSONField(name="author_association")
	val authorAssociation: String? = null,

	@JSONField(name="number")
	val number: Int? = null,

	@JSONField(name="patch_url")
	val patchUrl: String? = null,

	@JSONField(name="updated_at")
	val updatedAt: String? = null,

	@JSONField(name="draft")
	val draft: Boolean? = null,

	@JSONField(name="merge_commit_sha")
	val mergeCommitSha: String? = null,

	@JSONField(name="comments_url")
	val commentsUrl: String? = null,

	@JSONField(name="review_comment_url")
	val reviewCommentUrl: String? = null,

	@JSONField(name="active_lock_reason")
	val activeLockReason: Any? = null,

	@JSONField(name="id")
	val id: Long? = null,

	@JSONField(name="state")
	val state: String? = null,

	@JSONField(name="locked")
	val locked: Boolean? = null,

	@JSONField(name="commits_url")
	val commitsUrl: String? = null,

	@JSONField(name="closed_at")
	val closedAt: Any? = null,

	@JSONField(name="statuses_url")
	val statusesUrl: String? = null,

	@JSONField(name="merged_at")
	val mergedAt: Any? = null,

	@JSONField(name="auto_merge")
	val autoMerge: Any? = null,

	@JSONField(name="url")
	val url: String? = null,

	@JSONField(name="labels")
	val labels: List<Any?>? = null,

	@JSONField(name="milestone")
	val milestone: Milestone? = null,

	@JSONField(name="html_url")
	val htmlUrl: String? = null,

	@JSONField(name="review_comments_url")
	val reviewCommentsUrl: String? = null,

	@JSONField(name="assignee")
	val assignee: Any? = null,

	@JSONField(name="user")
	val user: User? = null,

	@JSONField(name="node_id")
	val nodeId: String? = null,

	@JSONField(name="base")
	val base: Base? = null
)

data class Base(

	@JSONField(name="ref")
	val ref: String? = null,

	@JSONField(name="repo")
	val repo: Repo? = null,

	@JSONField(name="label")
	val label: String? = null,

	@JSONField(name="sha")
	val sha: String? = null,

	@JSONField(name="user")
	val user: User? = null
)

data class Self(

	@JSONField(name="href")
	val href: String? = null
)

data class Owner(

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
)

data class ReviewComments(

	@JSONField(name="href")
	val href: String? = null
)
