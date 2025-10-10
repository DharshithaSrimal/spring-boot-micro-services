#!/bin/bash

echo "=== Installing Maven and Java 17 on Ubuntu 22.04 ==="

# Install Java 17
install_java17() {
    echo "Installing Java 17..."
    
    # Update package index
    sudo apt update
    
    # Install OpenJDK 17
    sudo apt install -y openjdk-17-jdk
    
    # Set Java 17 as default (if multiple versions exist)
    sudo update-alternatives --set java /usr/lib/jvm/java-17-openjdk-amd64/bin/java
    sudo update-alternatives --set javac /usr/lib/jvm/java-17-openjdk-amd64/bin/javac
    
    # Set JAVA_HOME
    echo "export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64" | sudo tee -a /etc/environment
    export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
    
    echo "Java 17 installation completed!"
    java -version
}

# Method 1: Install from Ubuntu repositories (Recommended for most users)
install_maven_apt() {
    echo "Installing Maven from Ubuntu repositories..."
    
    # Update package index
    sudo apt update
    
    # Install Maven
    sudo apt install -y maven
    
    # Verify installation
    mvn -version
    
    echo "Maven installation from apt completed!"
}

# Method 2: Install specific version manually
install_maven_manual() {
    echo "Installing Maven manually (latest version)..."
    
    # Update system
    sudo apt update
    
    # Set Maven version
    MAVEN_VERSION="3.9.5"
    
    # Download Maven
    cd /tmp
    wget https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz
    
    # Extract Maven
    tar -xzf apache-maven-${MAVEN_VERSION}-bin.tar.gz
    
    # Move to /opt directory
    sudo mv apache-maven-${MAVEN_VERSION} /opt/maven
    
    # Create symbolic link for easier updates
    sudo ln -sf /opt/maven /opt/maven-current
    
    # Set up environment variables
    sudo tee /etc/profile.d/maven.sh > /dev/null <<EOF
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export M2_HOME=/opt/maven-current
export MAVEN_HOME=/opt/maven-current
export PATH=\$M2_HOME/bin:\$PATH
EOF
    
    # Make it executable
    sudo chmod +x /etc/profile.d/maven.sh
    
    # Source the environment
    source /etc/profile.d/maven.sh
    
    # Clean up
    rm /tmp/apache-maven-${MAVEN_VERSION}-bin.tar.gz
    
    echo "Manual Maven installation completed!"
    echo "Please run 'source /etc/profile.d/maven.sh' or log out and back in"
}

# Check if Java 17 is installed
check_java() {
    if java -version 2>&1 | grep -q "openjdk version \"17"; then
        echo "Java 17 is already installed:"
        java -version
        return 0
    else
        echo "Java 17 is not installed or different version found."
        echo "Installing OpenJDK 17..."
        install_java17
        return 0
    fi
}

# Main installation function
main() {
    echo "Choose installation method:"
    echo "1. Install from Ubuntu repositories (recommended)"
    echo "2. Install manually (latest version)"
    echo ""
    read -p "Enter your choice (1 or 2): " choice
    
    # Check/Install Java 17 first
    check_java
    
    case $choice in
        1)
            install_maven_apt
            ;;
        2)
            install_maven_manual
            ;;
        *)
            echo "Invalid choice. Installing from repositories..."
            install_maven_apt
            ;;
    esac
    
    echo ""
    echo "=== Installation Summary ==="
    echo "Java version:"
    java -version
    echo ""
    echo "Maven version:"
    mvn -version
    echo ""
    echo "Maven installation completed successfully!"
}

# Run main function if script is executed directly
if [[ "${BASH_SOURCE[0]}" == "${0}" ]]; then
    main
fi