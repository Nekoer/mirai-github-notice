package com.hcyacg.entity

import java.io.Serializable

data class Branch(
    var name: String,
    var sha: String,
    var url: String,
    var protected: Boolean
): Serializable {
    override fun toString(): String {
        return "Branch(name='$name', sha='$sha', url='$url', protected=$protected)"
    }
}

