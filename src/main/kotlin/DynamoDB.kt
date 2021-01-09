import com.amazonaws.AmazonServiceException
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.*


fun main() {

    val request = CreateTableRequest()
        .withAttributeDefinitions(
            AttributeDefinition(
                "Name", ScalarAttributeType.S
            )
        )
        .withKeySchema(KeySchemaElement("Name", KeyType.HASH))
        .withProvisionedThroughput(ProvisionedThroughput(5, 5))
        .withTableName("Client")

    val ddb = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.SA_EAST_1).build()

    try {
        val result = ddb.createTable(request)
        println(result.tableDescription.tableName)
    } catch (e: AmazonServiceException) {
        System.err.println(e.errorMessage)
        System.exit(1)
    }
}

class DynamoDB {
}