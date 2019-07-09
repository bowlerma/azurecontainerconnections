# azurecontainerconnections
Tests Azure ephemeral port limits.

Creates connections against a SQL Azure database until either the specified number of connections is reached, or a connection cannot be created.

## Usage
```
java -jar azureconntest-1.0-SNAPSHOT.jar <sql azure instance url> <db name> <username> <password> <number of connections to create> <sleep time between creating each connection in milliseconds>
```

For example:

```
java -jar azureconntest-1.0-SNAPSHOT.jar dudetest.database.windows.net dudetest mhrdev1 Pass1234 1000 500
```
