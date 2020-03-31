package com.freewillsolutions.True.selfdcare.datasource.remote

import kotlinx.coroutines.Deferred
import com.freewillsolutions.True.selfdcare.datasource.Result
import com.freewillsolutions.True.selfdcare.utility.ERR_CODE_NO_INTERNET_CONNECTION
import com.freewillsolutions.True.selfdcare.utility.ERR_CODE_NO_STATUS_CODE
import com.freewillsolutions.True.selfdcare.utility.ERR_CODE_SOCKET_EXCEPTION
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.jozzee.android.core.connection.NetworkMonitor
import com.jozzee.android.core.text.isJsonFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.BufferedSource
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.SocketException
import java.net.SocketTimeoutException
import java.nio.charset.Charset
import javax.net.ssl.HttpsURLConnection

open class RemoteDataSource {
    suspend fun <T> request(job: Deferred<Result<T>>): Result<T> = withContext(Dispatchers.IO) {
        if (NetworkMonitor.isConnected.not()) {
            return@withContext Result.error<T>(ERR_CODE_NO_INTERNET_CONNECTION, "No Internet Connection")
        }
        return@withContext try {
            job.await().apply {
                statusCode = HttpURLConnection.HTTP_OK
            }
    }catch (e:HttpException){
            e.printStackTrace()
            Result.error<T>(e.code(),getHttpExceptionMessage(e))
        }catch (e: HttpException) {
            e.printStackTrace()
            Result.error<T>(e.code(), getHttpExceptionMessage(e))
        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
            Result.error<T>(HttpsURLConnection.HTTP_CLIENT_TIMEOUT, e.message)
        } catch (e: SocketException) {
            e.printStackTrace()
            Result.error<T>(ERR_CODE_SOCKET_EXCEPTION, e.message)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            Result.error<T>(ERR_CODE_NO_STATUS_CODE, throwable.message)
        }


    }

    fun getHttpExceptionMessage(exception: HttpException): String {
        if (exception.response()?.errorBody()?.contentType() != null) {
            exception.response()?.errorBody()?.also { errorBody ->

                val source: BufferedSource = errorBody.source()
                source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
                val charset: Charset = errorBody.contentType()!!.charset(Charset.forName("UTF-8"))!!
                val stringBody = source.buffer.clone().readString(charset)

                if (stringBody.isJsonFormat()) {
                    val jsonObject = Gson().fromJson(stringBody, JsonElement::class.java).asJsonObject
                    if (jsonObject.has("message")) {
                        return jsonObject.get("messgae").asString
                    }
                }
            }
        }
        return exception.message()
    }
    }