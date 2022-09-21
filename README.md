## Testing

Run from command line:

    ./gradlew run

### Linux

Regular build:

    ./gradlew packageUberJarForCurrentOs && java -jar build/compose/jars/Test-linux-x64-1.0.0.jar

Minified build:

    ./gradlew minify --info && java -jar build/compose/jars/Test-linux-x64-1.0.0.min.jar

### Windows

Regular build:

    ./gradlew packageUberJarForCurrentOs && java -jar build/compose/jars/Test-windows-x64-1.0.0.jar

Minified build:

    ./gradlew minify --info && java -jar build/compose/jars/Test-windows-x64-1.0.0.min.jar
