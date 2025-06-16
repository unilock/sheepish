# Sheepish

## Building

1. Create a folder "libs" in the project root directory (next to "gradle", "src", etc.)
2. Download Sinytra Connector: https://modrinth.com/mod/connector/version/2.0.0-beta.8+1.21.1
3. Open or extract the Sinytra Connector JAR as a ZIP file
4. Navigate to `<SinytraConnector.jar>/META-INF/jarjar`
5. Copy "org.sinytra.connector-2.0.0-beta.8+1.21.1-mod.jar" and paste into the "libs" folder created in step 1
6. Open a command prompt or terminal window in the project root directory and execute `./gradlew build`
