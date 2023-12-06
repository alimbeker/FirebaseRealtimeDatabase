package com.example.firebaserealtimedatabase

class UserWrapper : FRDWrapper<User>() {
    override fun getTableName(): String {
        return "users"
    }

    override fun getClassType(): Class<User> {
        return User::class.java
    }
}
