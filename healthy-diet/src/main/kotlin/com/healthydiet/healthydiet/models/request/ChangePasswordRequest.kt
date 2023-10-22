package com.healthydiet.healthydiet.models.request

import com.fasterxml.jackson.annotation.JsonAlias

data class ChangePasswordRequest(@JsonAlias("current_password")val oldPassword: String,
                                 @JsonAlias("new_password")val newPassword: String)