# ELEANOR - Bitcoin Wallet for Android

**ELEANOR** is a secure Bitcoin wallet application for Android developed by @ivosicek123 (ORCID: 0009-0007-4808-8653), with integrated banking withdrawal capabilities through Partners banka.

## 👤 Developer Information

- **GitHub Account**: [@ivosicek123](https://github.com/ivosicek123)
- **ORCID ID**: [0009-0007-4808-8653](https://orcid.org/0009-0007-4808-8653)
- **Repository**: [bitcoin-wallet-android](https://github.com/ivosicek123/bitcoin-wallet-android)

## 🚀 Features

### Bitcoin Wallet Management
- Generate and manage Bitcoin addresses
- Secure private key storage using Android Keystore
- View wallet balance and transaction history
- Send and receive Bitcoin
- Real-time balance updates

### Banking Integration
- Direct withdrawal to Partners banka account
- Bitcoin to CZK conversion with live rates
- Secure transaction processing
- Withdrawal history tracking
- Account verification

### Security
- Biometric authentication (fingerprint/face)
- AES-256 encryption for sensitive data
- Encrypted credential storage
- Session management with auto-timeout
- No private keys transmitted over network

### Developer-Friendly
- Clean architecture (MVVM pattern)
- Dependency injection with Hilt
- Coroutines for async operations
- Room database for persistence
- Comprehensive error handling

## 🔧 Tech Stack

- **Language**: Kotlin 1.8+
- **Android**: SDK 21 (minSdk) to 33 (targetSdk)
- **Bitcoin**: bitcoinj-core 0.16.2
- **Networking**: Retrofit 2.9.0 + OkHttp 4.11.0
- **Security**: Android Keystore + Bouncycastle
- **DI**: Hilt 2.46
- **Database**: Room 2.5.2
- **Auth**: AndroidX Biometric 1.1.0
- **Coroutines**: kotlinx-coroutines 1.7.1

## 📋 Prerequisites

- Android Studio (latest version)
- Android SDK 33 or higher
- Java 11 or higher
- Kotlin 1.8+
- Git

## 🔑 API Keys Required

Create a `local.properties` file with:

```properties
# BlockChain.com API
blockchain_api_key=YOUR_API_KEY

# BlockCypher API
blockcypher_token=YOUR_TOKEN

# Anycoin M.P.

# Bitcoin Network
bitcoin_network=mainnet
```

## 📦 Installation

### 1. Clone Repository

```bash
git clone https://github.com/ivosicek123/bitcoin-wallet-android.git
cd bitcoin-wallet-android
```

### 2. Configure API Keys

```bash
cp local.properties.example local.properties
# Edit local.properties with your API credentials
```

### 3. Build Project

```bash
./gradlew clean build
```

### 4. Run on Device/Emulator

```bash
./gradlew installDebug
# Or use Android Studio Run button
```

## 📁 Project Structure

```
bitcoin-wallet-android/
├── app/src/main/java/com/whitecorp/bitcoinwallet/
│   ├── api/                 # Retrofit API services
│   │   ├── BitcoinAPIService.kt
│   │   └── BankingAPIService.kt
│   ├── data/                # Data layer
│   │   ├── model/           # Entity models
│   │   └── repository/      # Data repositories
│   ├── security/            # Security utilities
│   │   ├── KeyManager.kt
│   │   └── BiometricAuthenticator.kt
│   ├── viewmodel/           # MVVM ViewModels
│   │   ├── WalletViewModel.kt
│   │   └── WithdrawalViewModel.kt
│   ├── util/                # Utilities
│   │   └── AppConfig.kt
│   ├── di/                  # Dependency injection
│   └── MainActivity.kt      # Entry point
├── .devcontainer/           # Dev container config
├── build.gradle             # Dependencies
├── .gitignore              # Git exclusions
├── CHANGELOG.md            # Version history
└── README.md               # This file
```

## 🔐 Security

### Private Key Management
- Keys are stored encrypted in Android Keystore
- Keys never leave the device
- All cryptographic operations use AES-256

### Authentication
- Biometric login (fingerprint/face recognition)
- PIN code backup authentication
- Automatic session timeout (15 minutes)

### API Security
- HTTPS/TLS 1.3 for all communications
- API key rotation support
- Request signing with HMAC
- No sensitive data in logs

## 💰 Banking Integration

### Partners banka
- **Bank Account**: 9999913120
- **Minimum Withdrawal**: 0.001 BTC
- **Maximum Withdrawal**: 10.0 BTC
- **Withdrawal Fee**: 1.5%
- **Exchange**: Live BTC to CZK rates

### Withdrawal Process
1. Select amount to withdraw
2. Verify conversion rate and fee
3. Confirm bank account details
4. Authenticate with biometric/PIN
5. Transaction processing
6. Receive notification on completion

## 🧪 Testing

### Run Tests

```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest

# Test coverage
./gradlew testDebugUnitTestCoverage
```

## 🐛 Troubleshooting

### App won't start
- Ensure Android API 21+ is available
- Clear build cache: `./gradlew clean`
- Invalidate Android Studio cache

### API errors
- Check internet connection
- Verify API keys in `local.properties`
- Check API rate limits
- Review server status pages

### Biometric not working
- Ensure device has biometric sensor
- Check Android version 6.0+
- Verify permissions in manifest
- Register fingerprint/face in Settings

### Transaction not broadcasting
- Check network connection
- Verify Bitcoin address format
- Ensure sufficient balance
- Check blockchain network status

## 📚 API Documentation

### Bitcoin APIs
- [BlockChain.com API](https://www.blockchain.com/api)
- [BlockCypher](https://www.blockcypher.com)
- [Mempool.space API](https://mempool.space/api)

### Banking
- Partners banka API (Contact banka for documentation)

## 🔄 Git Workflow

```bash
# Create feature branch
git checkout -b feature/your-feature

# Make changes and commit
git add .
git commit -m "Add your feature"

# Push to remote
git push origin feature/your-feature

# Create Pull Request on GitHub
```

## 📈 Version History

### v1.0.0 (Current)
- Initial release
- Bitcoin wallet functionality
- Banking integration
- Security features

See [CHANGELOG.md](CHANGELOG.md) for detailed history.

## 📄 License

BSD 2-Clause License - See [LICENSE](LICENSE) file

## ⚠️ Disclaimer

**SECURITY WARNING:**
- Always backup your seed phrase securely
- Never share private keys or seed phrases
- This application is provided as-is for educational purposes
- Use testnet before using with real Bitcoin
- The developer is not liable for lost funds

**BANKING WARNING:**
- Verify all bank account information before withdrawal
- Withdrawal processing depends on bank timelines
- Conversion rates are subject to market conditions
- Transaction fees apply - check current rates
- Partners banka terms and conditions apply

## 🤝 Support

For issues, questions, or contributions:

1. Check [GitHub Issues](https://github.com/ivosicek123/bitcoin-wallet-android/issues)
2. Create a new issue with detailed description
3. Submit pull requests for improvements
4. Contact via ORCID profile

## 📞 Contact

- **GitHub**: [@ivosicek123](https://github.com/ivosicek123)
- **ORCID**: [0009-0007-4808-8653](https://orcid.org/0009-0007-4808-8653)
- **Email**: Through GitHub profile

---

**Built with ❤️ by Faltýn corp. **

**Last Updated**: September 2, 2026

**GitHub Repository**: https://github.com/ivosicek123/bitcoin-wallet-android
