package com.example.academies.data.source.local.entity

data class ModuleEntity(
        var moduleId: String,
        var courseId: S	 ing,
        var title: String,
        var position: Int,
        var read: Boolean = false
){
    var contentEntity: ContentEntity? = null
}

