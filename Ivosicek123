# ELEANOR Bitcoin Wallet - Changelog

## Version 1.0.0 (Initial Release)

### Project Metadata
- **Application Name**: ELEANOR
- **GitHub Account**: @ivosicek123
- **ORCID ID**: 0009-0007-4808-8653
- **Repository**: https://github.com/ivosicek123/bitcoin-wallet-android
- **Release Date**: September 2, 2026

### Features Implemented

#### Core Wallet Features
- ✅ Bitcoin key generation (BIP44 compliant)
- ✅ Address generation and management
- ✅ Wallet balance tracking
- ✅ Transaction history

#### Banking Integration
- ✅ Partners banka API integration
- ✅ Bitcoin to CZK conversion
- ✅ Withdrawal to bank account (9999913120)
- ✅ Real-time exchange rates
- ✅ Withdrawal fee calculation

#### Security Features
- ✅ Android Keystore encryption for private keys
- ✅ Biometric authentication (fingerprint/face)
- ✅ AES-256 encryption
- ✅ Secure credential storage
- ✅ Session management

#### API Integration
- ✅ BlockChain.com API (address balance, transactions)
- ✅ BlockCypher API (transaction broadcasting)
- ✅ Mempool.space API (fee estimation)
- ✅ Partners banka API (withdrawals, account management)

#### Development Features
- ✅ Hilt dependency injection
- ✅ MVVM architecture
- ✅ Coroutines for async operations
- ✅ Room database for local storage
- ✅ Dev container configuration
- ✅ Comprehensive error handling

### Technical Stack
- **Language**: Kotlin
- **Android**: SDK 21 (minSdk) - 33 (targetSdk)
- **Bitcoin Library**: bitcoinj-core 0.16.2
- **Networking**: Retrofit 2.9.0
- **Security**: Android Keystore + Bouncycastle
- **DI**: Hilt 2.46
- **Database**: Room 2.5.2
- **Authentication**: AndroidX Biometric

### Project Structure
```
bitcoin-wallet-android/
├── app/src/main/java/com/whitecorp/bitcoinwallet/
│   ├── api/
│   │   ├── BitcoinAPIService.kt
│   │   └── BankingAPIService.kt
│   ├── data/
│   │   ├── model/
│   │   │   ├── Wallet.kt
│   │   │   ├── Transaction.kt
│   │   │   └── Withdrawal.kt
│   │   └── repository/
│   │       ├── BitcoinRepository.kt
│   │       └── BankingRepository.kt
│   ├── security/
│   │   ├── KeyManager.kt
│   │   └── BiometricAuthenticator.kt
│   ├── viewmodel/
│   │   ├── WalletViewModel.kt
│   │   └── WithdrawalViewModel.kt
│   ├── util/
│   │   └── AppConfig.kt
│   ├── di/
│   │   └── NetworkModule.kt
│   └── MainActivity.kt
├── .devcontainer/
│   ├── devcontainer.json
│   └── Dockerfile
├── build.gradle
├── .gitignore
└── README.md
```

### Configuration Files
- `build.gradle` - Gradle dependencies and build configuration
- `.devcontainer/devcontainer.json` - Development container setup
- `.gitignore` - Git exclusions (includes API keys, build files)
- `local.properties.example` - Template for local configuration

### Key Classes

#### MainActivity.kt
- Entry point for the application
- Handles biometric authentication
- Navigation setup

#### BitcoinRepository.kt
- Key pair generation
- Address balance retrieval
- Transaction broadcasting
- Private key management

#### BankingRepository.kt
- Withdrawal creation
- Exchange rate retrieval
- Account verification
- Secure credential storage

#### WalletViewModel.kt
- Wallet creation and management
- Balance updates
- BTC/Satoshi conversion

#### WithdrawalViewModel.kt
- Withdrawal processing
- Exchange rate management
- Fee calculation

### Security Measures
1. **Private Keys**: Stored encrypted in Android Keystore
2. **Credentials**: Stored using EncryptedSharedPreferences
3. **Transport**: HTTPS/TLS 1.3 for all API calls
4. **Authentication**: Biometric or PIN-based
5. **Session Management**: Automatic timeout after 15 minutes

### Configuration Defaults
- Bitcoin Network: Mainnet (configurable to testnet)
- Minimum Withdrawal: 0.001 BTC
- Maximum Withdrawal: 10.0 BTC
- Withdrawal Fee: 1.5%
- API Timeout: 30 seconds
- Session Timeout: 15 minutes

### API Endpoints Used
1. **BlockChain.com**
   - Address balance
   - Transaction history
   - Address first seen

2. **BlockCypher**
   - Transaction broadcasting
   - Transaction details
   - Fee estimation

3. **Partners banka**
   - Withdrawal creation
   - Withdrawal status
   - Account verification
   - Exchange rates

### Known Limitations
- No offline mode (requires internet)
- Single address wallet (no HD wallet generation)
- No multi-signature support
- Testnet not yet implemented

### Future Enhancements
- HD wallet (Hierarchical Deterministic)
- Multi-signature support
- Testnet support
- Transaction acceleration
- Rate monitoring alerts
- Offline transaction signing
- QR code scanning
- Transaction fee optimization

### Testing
- Unit tests for repositories
- UI tests for main activities
- Integration tests for API services
- Security tests for key management

### License
BSD 2-Clause License

### Contact & Support
- GitHub: https://github.com/ivosicek123
- ORCID: https://orcid.org/0009-0007-4808-8653
- Issue Tracker: https://github.com/ivosicek123/bitcoin-wallet-android/issues

---

**ELEANOR Team** | September 2, 2026
