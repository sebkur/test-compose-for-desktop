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

## Native distributions

### Linux

Build Debian package:

    ./gradlew packageDeb

Install:

    dpkg -i build/compose/binaries/main/deb/test-project_1.0.0-1_amd64.deb

### Windows

Build MSI installer:

    .\gradlew packageMsi

Install:

    msiexec /i build\compose\binaries\main\msi\Test-1.0.0.msi
