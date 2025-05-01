package com.example.authentication_app.repository

import com.example.authentication_app.data.remote.ApiService
import com.example.authentication_app.data.remote.LoginRequest
import com.example.authentication_app.util.Resource
import org.json.JSONObject
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(email: String, password: String): Resource<String> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                val token = response.body()?.token ?: ""
                Resource.Success(token)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = try {
                    JSONObject(errorBody ?: "").getString("error")
                } catch (e: Exception) {
                    "خطأ غير معروف"
                }
                Resource.Error(errorMessage)
            }
        } catch (e: Exception) {
            Resource.Error("فشل الاتصال بالسيرفر: ${e.message}")
        }
    }
}
