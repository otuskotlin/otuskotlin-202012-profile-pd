package dsl

import dsl.models.UserPermissionsModel

class PermissionsConfig {
    private val _permissions = mutableSetOf<UserPermissionsModel>()
    val permissions: Set<UserPermissionsModel>
        get() = _permissions.toSet()

    fun add(permission: UserPermissionsModel) = _permissions.add(permission)

    fun add(permissionStr: String) = add(UserPermissionsModel.valueOf(permissionStr))

    operator fun String.unaryPlus() = add(this)

    operator fun UserPermissionsModel.unaryPlus() = add(this)

}
