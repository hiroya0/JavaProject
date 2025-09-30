# UPGRADE TO JAVA 21

This document summarizes the manual upgrade steps performed to move this project to Java 21 (LTS).

What I changed

- Updated `pom.xml` to set `<java.version>21</java.version>`.
- Added `maven-compiler-plugin` (3.11.0) with `<release>21</release>` to ensure compilation target.

Local verification steps

1. Install Temurin 21 (Homebrew):

```bash
brew install --cask temurin@21
```

2. Set `JAVA_HOME` to JDK 21 for your shell session:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v21)
java -version
```

3. Build and run tests:

```bash
cd /Users/hiroya/Downloads/demo
./mvnw -DskipTests=false clean test
```

CI example (GitHub Actions)

Create `.github/workflows/java-ci.yml` with the following minimal example to run on JDK 21:

```yaml
name: Java CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
      - name: Build and test
        run: ./mvnw -DskipTests=false clean test
```

Notes and warnings

- I installed Temurin 21 and ran `./mvnw clean test` locally; tests passed on JDK 21 for this demo project.
- Some runtime/test warnings were observed (Mockito inline-mock-maker self-attaching and Java agent warnings). Consider configuring Mockito as an agent if inline mocking is required under future JDK restrictions.
- The automated Copilot upgrade plan tool was not run because it requires a Copilot Pro plan.

Next steps

- Add CI workflow to ensure all branches/builds use Java 21.
- Optionally run OpenRewrite recipes to catch and fix API incompatibilities automatically.
- Consider updating project README to note Java 21 requirement.

Contact

If you'd like, I can add the GitHub Actions workflow file and commit the `pom.xml` and `UPGRADE_TO_JAVA21.md` changes.
