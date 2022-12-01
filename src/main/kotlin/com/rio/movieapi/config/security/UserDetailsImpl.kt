package com.rio.movieapi.config.security

import com.rio.movieapi.data.entity.UserData
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.util.StringUtils


class UserDetailsImpl(
    private val username: String = "",
    private val email: String = "",
    private val name: String = "",
    private val password: String = "",
    private val roles: String = ""
) : UserDetails {

    public fun build(data: UserData?): UserDetailsImpl {
        return UserDetailsImpl(
            data?.email ?: "",
            data?.email ?: "",
            data?.name ?: "",
            data?.token ?: "",
            data?.uid ?: "")
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities: MutableList<GrantedAuthority> = ArrayList()
        if (StringUtils.hasText(roles)) {
            val splits = roles.replace(" ".toRegex(), "").split(",".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            for (string in splits) {
                authorities.add(SimpleGrantedAuthority(string))
            }
        }
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}