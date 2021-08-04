package com.hcyacg.entity

import com.alibaba.fastjson.annotation.JSONField

data class RateLimit(

    @JSONField(name="rate")
    val rate: Rate? = null,

    @JSONField(name="resources")
    val resources: Resources? = null
) {
    override fun toString(): String {
        return "RateLimit(rate=$rate, resources=$resources)"
    }
}

data class Core(

    @JSONField(name="resource")
    val resource: String? = null,

    @JSONField(name="limit")
    val limit: Int? = null,

    @JSONField(name="reset")
    val reset: Int? = null,

    @JSONField(name="used")
    val used: Int? = null,

    @JSONField(name="remaining")
    val remaining: Int? = null
) {
    override fun toString(): String {
        return "Core(resource=$resource, limit=$limit, reset=$reset, used=$used, remaining=$remaining)"
    }
}

data class Rate(

    @JSONField(name="resource")
    val resource: String? = null,

    @JSONField(name="limit")
    val limit: Int? = null,

    @JSONField(name="reset")
    val reset: Int? = null,

    @JSONField(name="used")
    val used: Int? = null,

    @JSONField(name="remaining")
    val remaining: Int? = null
) {
    override fun toString(): String {
        return "Rate(resource=$resource, limit=$limit, reset=$reset, used=$used, remaining=$remaining)"
    }
}

data class IntegrationManifest(

    @JSONField(name="resource")
    val resource: String? = null,

    @JSONField(name="limit")
    val limit: Int? = null,

    @JSONField(name="reset")
    val reset: Int? = null,

    @JSONField(name="used")
    val used: Int? = null,

    @JSONField(name="remaining")
    val remaining: Int? = null
) {
    override fun toString(): String {
        return "IntegrationManifest(resource=$resource, limit=$limit, reset=$reset, used=$used, remaining=$remaining)"
    }
}

data class Resources(

    @JSONField(name="core")
    val core: Core? = null,

    @JSONField(name="search")
    val search: Search? = null,

    @JSONField(name="graphql")
    val graphql: Graphql? = null,

    @JSONField(name="integration_manifest")
    val integrationManifest: IntegrationManifest? = null
) {
    override fun toString(): String {
        return "Resources(core=$core, search=$search, graphql=$graphql, integrationManifest=$integrationManifest)"
    }
}

data class Graphql(

    @JSONField(name="resource")
    val resource: String? = null,

    @JSONField(name="limit")
    val limit: Int? = null,

    @JSONField(name="reset")
    val reset: Int? = null,

    @JSONField(name="used")
    val used: Int? = null,

    @JSONField(name="remaining")
    val remaining: Int? = null
) {
    override fun toString(): String {
        return "Graphql(resource=$resource, limit=$limit, reset=$reset, used=$used, remaining=$remaining)"
    }
}

data class Search(

    @JSONField(name="resource")
    val resource: String? = null,

    @JSONField(name="limit")
    val limit: Int? = null,

    @JSONField(name="reset")
    val reset: Int? = null,

    @JSONField(name="used")
    val used: Int? = null,

    @JSONField(name="remaining")
    val remaining: Int? = null
) {
    override fun toString(): String {
        return "Search(resource=$resource, limit=$limit, reset=$reset, used=$used, remaining=$remaining)"
    }
}