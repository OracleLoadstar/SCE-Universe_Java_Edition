# GitHub Workflows

## Build and Release Workflow

### `build-and-release.yml`

This workflow automatically builds the Java application and creates a GitHub release whenever code is pushed to any branch.

#### Trigger
- Runs on every `push` to any branch

#### What it does
1. **Checkout**: Checks out the repository code
2. **Setup Java**: Installs JDK 17 (Temurin distribution)
3. **Compile**: Compiles all Java source files from `src/` to `bin/`
4. **Create JAR**: Packages compiled classes into `SCE-Universe.jar` with proper manifest
   - Main-Class: `cn.oraclestar.sce.App.App`
5. **Calculate Hash**: Computes SHA256 checksum of the JAR file
6. **Create Release**: Publishes a new GitHub release with:
   - Tag name: `release-{branch-name}-{short-commit-sha}`
   - Release name: `Release {branch-name}-{short-commit-sha}`
   - JAR file attached as an artifact
   - SHA256 hash included in the release description

#### Requirements
- Repository must have `contents: write` permission (enabled by default)
- Java source code must be in `src/` directory
- Main class must be `cn.oraclestar.sce.App.App`

#### Output
- GitHub release with the JAR file
- SHA256 hash for file verification
