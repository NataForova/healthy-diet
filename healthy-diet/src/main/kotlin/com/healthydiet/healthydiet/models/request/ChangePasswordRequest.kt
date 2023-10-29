package com.healthydiet.healthydiet.models.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ChangePasswordRequest(val oldPassword: String,
                                 val newPassword: String)