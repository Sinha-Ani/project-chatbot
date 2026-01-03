# 🤖 YouTube ChatBot

A sophisticated chatbot application built with Spring Boot that automatically replies to YouTube live stream messages using the YouTube Data API v3.

## ✨ Features

- **Automated Chat Responses**: Automatically respond to live stream chat messages
- **OAuth2 Authentication**: Secure authentication with Google YouTube API
- **Spring Boot Integration**: Built on Spring Boot 3.x for robust enterprise-grade development
- **Real-time Processing**: Handle live chat messages in real-time
- **Customizable Responses**: Easily configure bot behavior and responses

## 🛠️ Technologies Used

- **Java 17** - Programming language
- **Spring Boot 3.5.9** - Application framework
- **Google YouTube Data API v3** - YouTube integration
- **Google API Client Libraries** - OAuth2 and HTTP client
- **Maven** - Dependency management and build tool

## 📋 Prerequisites

Before running this application, make sure you have:

- **Java 17** or higher installed
- **Maven 3.6+** for building the project
- **Google Cloud Console** account with YouTube Data API enabled
- **OAuth 2.0 credentials** from Google Cloud Console

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/project-chatbot.git
cd project-chatbot
```

### 2. Google API Setup

1. Go to the [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select an existing one
3. Enable the **YouTube Data API v3**
4. Go to **Credentials** → **Create Credentials** → **OAuth 2.0 Client IDs**
5. Download the `client_secret.json` file
6. Place the `client_secret.json` file in the `src/main/resources/` directory

### 3. Build the Application

```bash
# Using Maven wrapper (recommended)
./mvnw clean install

# Or using system Maven
mvn clean install
```

### 4. Run the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or using system Maven
mvn spring-boot:run
```

## ⚙️ Configuration

### Application Properties

The application uses Spring Boot's default configuration. You can customize settings in `src/main/resources/application.yaml`:

```yaml
# Add your custom configurations here
spring:
  application:
    name: YouTube ChatBot

# YouTube API Configuration
youtube:
  application:
    name: "YouTubeBot"
```

### OAuth2 Scopes

The application currently requests the following YouTube API scopes:
- `https://www.googleapis.com/auth/youtube.force-ssl` - Full access to YouTube account

## 📁 Project Structure

```
project-chatbot/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── project/
│   │   │           └── chatbot/
│   │   │               ├── ChatbotApplication.java    # Main Spring Boot application
│   │   │               └── bot/
│   │   │                   └── YoutubeBot.java        # YouTube bot logic
│   │   │           └── services/
│   │   │               └── YouTubeAuthService.java    # YouTube API authentication
│   │   └── resources/
│   │       ├── application.yaml                      # Application configuration
│   │       └── client_secret.json                    # Google API credentials (add manually)
│   └── test/
│       └── java/
│           └── com/
│               └── project/
│                   └── chatbot/
│                       └── ChatbotApplicationTests.java
├── .gitignore
├── pom.xml                                           # Maven configuration
├── README.md                                         # This file
└── mvnw & mvnw.cmd                                   # Maven wrapper scripts
```

## 🔧 Usage

1. **Start the Application**: Run the Spring Boot application as described above
2. **OAuth2 Flow**: On first run, you'll be prompted to authorize the application with your Google account
3. **Bot Activation**: The bot will automatically start monitoring and responding to chat messages
4. **Monitor Logs**: Check the console output for bot activity and responses

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines

- Follow Java coding standards
- Add unit tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting PR

## 📝 API Documentation

The application integrates with YouTube Data API v3. For detailed API documentation, visit:
- [YouTube Data API v3 Documentation](https://developers.google.com/youtube/v3/docs)

## 🐛 Troubleshooting

### Common Issues

1. **OAuth2 Authentication Failed**
   - Ensure `client_secret.json` is in the correct location
   - Verify your Google Cloud Console credentials are valid
   - Check that YouTube Data API is enabled

2. **Build Failures**
   - Ensure Java 17 is installed and JAVA_HOME is set
   - Clear Maven cache: `mvn clean`
   - Update dependencies: `mvn dependency:resolve`

3. **API Quota Exceeded**
   - Check your Google Cloud Console quota limits
   - Implement rate limiting in your bot logic

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot) - The framework powering this application
- [Google YouTube API](https://developers.google.com/youtube/v3) - For YouTube integration capabilities
- [Google API Client Libraries](https://github.com/googleapis/google-api-java-client) - For seamless API integration

## 📞 Support

If you have any questions or need help:

- Open an issue on GitHub
- Check the [YouTube API documentation](https://developers.google.com/youtube/v3/docs)
- Review Spring Boot [documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)

---

**Made with ❤️ for the YouTube community**
