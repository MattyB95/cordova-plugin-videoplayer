# Run unit tests
test:
    cd tests/android && ./gradlew test

# Clean test build artifacts then run tests (forces full rebuild)
test-clean:
    cd tests/android && ./gradlew clean test

# Clean test build artifacts only
clean:
    cd tests/android && ./gradlew clean

# Open the HTML test report in the browser
report: test
    open tests/android/build/reports/tests/testDebugUnitTest/index.html

# Run Android lint on the test module
lint:
    cd tests/android && ./gradlew lint

# Run lint and tests — useful as a pre-commit/CI check
check: lint test

# List all resolved dependencies for the test module
deps:
    cd tests/android && ./gradlew dependencies --configuration debugUnitTestRuntimeClasspath

# Validate plugin.xml is well-formed XML
validate:
    xmllint --noout plugin.xml && echo "plugin.xml is valid"
