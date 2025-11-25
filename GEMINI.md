# Project Context: Tycho Example

## Overview
This project demonstrates the usage of the **Maven Tycho** build system for building Eclipse plug-ins, Eclipse Rich Client Platform (RCP) applications, and OSGi-based applications. It serves as a reference for various Tycho capabilities including pomless builds, P2 update sites, product builds, and target platform definitions.

## Key Technologies
*   **Java:** (Version 21 is used in CI, targeting Java 17+)
*   **Maven:** Build tool (Version 3.9.8+ recommended)
*   **Eclipse Tycho:** Maven extensions for building Eclipse artifacts (Version 4.0.9)
*   **Eclipse RCP:** The underlying platform for the application.

## Directory Structure
*   `bundles/`: Contains the source code for the OSGi bundles (plug-ins).
*   `features/`: Contains the Eclipse Feature definitions.
*   `releng/`: Release engineering artifacts.
    *   `com.vogella.tycho.product/`: Defines the product build.
    *   `target-platform/`: Contains the `.target` definition file used to resolve dependencies.
*   `com.vogella.tycho.fatjar/`: Example of a fat jar build.
*   `tests/`: (Inferred) Various test bundles are present (e.g., `com.vogella.tycho.rcp.tests`).

## Building and Running

### Prerequisites
*   JDK 17 or higher
*   Maven 3.9.0 or higher

### Build Commands
Run the following command from the project root:

**Full Build (skipping UI tests):**
```bash
mvn clean verify -Dskip.ui-tests=true
```

**Full Build (including integration tests):**
```bash
mvn clean verify
```

### Build Artifacts
After a successful build, artifacts are typically found in the `target/` directories of the respective modules, particularly within `releng/com.vogella.tycho.product/target/` for the final product.

## Development Conventions
*   **Tycho Version:** Defined in the root `pom.xml` (`<tycho.version>`).
*   **Target Platform:** The build resolves dependencies against the target platform defined in `releng/target-platform`.
*   **Pomless Build:** The project utilizes Tycho's pomless build feature, reducing the need for `pom.xml` files in every bundle.
