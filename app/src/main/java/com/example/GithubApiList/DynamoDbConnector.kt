package com.example.GithubApiList

import android.content.Context
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DynamoDbConnector(application: Context) {
    object DynamoDBHelper {
        const val COGNITO_IDP_ID = "eu-central-1:bb36451b-571a-47c8-8394-8867f6630db6"
        val COGNITO_IDP_REGION = Regions.US_EAST_2  //allotted region
        const val TABLE_NAME = "Players"    //your table name
    }
    private val credentialsProvider =
        CognitoCachingCredentialsProvider(
            application.applicationContext, DynamoDBHelper.COGNITO_IDP_ID,
            DynamoDBHelper.COGNITO_IDP_REGION
        )

    private val dbClient =
        AmazonDynamoDBClient(credentialsProvider).apply {
            /**
             * Don't forget to mention the region for database client. If not, it defaults to US_EAST_1
             */
            setRegion(Region.getRegion(DynamoDBHelper.COGNITO_IDP_REGION))
        }

    private lateinit var table: Table

    private suspend fun getTable(): Table {
        if (::table.isInitialized)
            return table
        return suspendCoroutine { continuation ->
            table = Table.loadTable(dbClient, DynamoDBHelper.TABLE_NAME)
            continuation.resume(table)
        }
    }

}