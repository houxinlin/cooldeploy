package com.hxl.cooldeploy.git.event

class PushEvent {
    var ref: String? = null
    var before: String? = null
    var after: String? = null
    var repository: RepositoryDTO? = null
    var pusher: PusherDTO? = null
    var sender: SenderDTO? = null
    var isCreated = false
    var isDeleted = false
    var isForced = false
    var base_ref: Any? = null
    var compare: String? = null
    var commits: List<CommitsDTO>? = null
    var head_commit: HeadCommitDTO? = null

    class RepositoryDTO {
        var id = 0
        var node_id: String? = null
        var name: String? = null
        var full_name: String? = null
        var isPrivateX = false
        var owner: OwnerDTO? = null
        var html_url: String? = null
        var description: Any? = null
        var isFork = false
        var url: String? = null
        var forks_url: String? = null
        var keys_url: String? = null
        var collaborators_url: String? = null
        var teams_url: String? = null
        var hooks_url: String? = null
        var issue_events_url: String? = null
        var events_url: String? = null
        var assignees_url: String? = null
        var branches_url: String? = null
        var tags_url: String? = null
        var blobs_url: String? = null
        var git_tags_url: String? = null
        var git_refs_url: String? = null
        var trees_url: String? = null
        var statuses_url: String? = null
        var languages_url: String? = null
        var stargazers_url: String? = null
        var contributors_url: String? = null
        var subscribers_url: String? = null
        var subscription_url: String? = null
        var commits_url: String? = null
        var git_commits_url: String? = null
        var comments_url: String? = null
        var issue_comment_url: String? = null
        var contents_url: String? = null
        var compare_url: String? = null
        var merges_url: String? = null
        var archive_url: String? = null
        var downloads_url: String? = null
        var issues_url: String? = null
        var pulls_url: String? = null
        var milestones_url: String? = null
        var notifications_url: String? = null
        var labels_url: String? = null
        var releases_url: String? = null
        var deployments_url: String? = null
        var created_at = 0
        var updated_at: String? = null
        var pushed_at = 0
        var git_url: String? = null
        var ssh_url: String? = null
        var clone_url: String? = null
        var svn_url: String? = null
        var homepage: Any? = null
        var size = 0
        var stargazers_count = 0
        var watchers_count = 0
        var language: Any? = null
        var isHas_issues = false
        var isHas_projects = false
        var isHas_downloads = false
        var isHas_wiki = false
        var isHas_pages = false
        var forks_count = 0
        var mirror_url: Any? = null
        var isArchived = false
        var isDisabled = false
        var open_issues_count = 0
        var license: Any? = null
        var isAllow_forking = false
        var isIs_template = false
            private set
        var topics: List<*>? = null
        var visibility: String? = null
        var forks = 0
        var open_issues = 0
        var watchers = 0
        var default_branch: String? = null
        var stargazers = 0
        var master_branch: String? = null
        fun setIs_template(is_template: Boolean) {
            isIs_template = is_template
        }

        class OwnerDTO {
            var name: String? = null
            var email: String? = null
            var login: String? = null
            var id = 0
            var node_id: String? = null
            var avatar_url: String? = null
            var gravatar_id: String? = null
            var url: String? = null
            var html_url: String? = null
            var followers_url: String? = null
            var following_url: String? = null
            var gists_url: String? = null
            var starred_url: String? = null
            var subscriptions_url: String? = null
            var organizations_url: String? = null
            var repos_url: String? = null
            var events_url: String? = null
            var received_events_url: String? = null
            var type: String? = null
            var isSite_admin = false
        }
    }

    class PusherDTO {
        var name: String? = null
        var email: String? = null
    }

    class SenderDTO {
        var login: String? = null
        var id = 0
        var node_id: String? = null
        var avatar_url: String? = null
        var gravatar_id: String? = null
        var url: String? = null
        var html_url: String? = null
        var followers_url: String? = null
        var following_url: String? = null
        var gists_url: String? = null
        var starred_url: String? = null
        var subscriptions_url: String? = null
        var organizations_url: String? = null
        var repos_url: String? = null
        var events_url: String? = null
        var received_events_url: String? = null
        var type: String? = null
        var isSite_admin = false
    }

    class HeadCommitDTO {
        var id: String? = null
        var tree_id: String? = null
        var isDistinct = false
        var message: String? = null
        var timestamp: String? = null
        var url: String? = null
        var author: AuthorDTO? = null
        var committer: CommitterDTO? = null
        var added: List<String>? = null
        var removed: List<*>? = null
        var modified: List<*>? = null

        class AuthorDTO {
            var name: String? = null
            var email: String? = null
            var username: String? = null
        }

        class CommitterDTO {
            var name: String? = null
            var email: String? = null
            var username: String? = null
        }
    }

    class CommitsDTO {
        var id: String? = null
        var tree_id: String? = null
        var isDistinct = false
        var message: String? = null
        var timestamp: String? = null
        var url: String? = null
        var author: AuthorDTO? = null
        var committer: CommitterDTO? = null
        var added: List<String>? = null
        var removed: List<*>? = null
        var modified: List<*>? = null

        class AuthorDTO {
            var name: String? = null
            var email: String? = null
            var username: String? = null
        }

        class CommitterDTO {
            var name: String? = null
            var email: String? = null
            var username: String? = null
        }
    }
}