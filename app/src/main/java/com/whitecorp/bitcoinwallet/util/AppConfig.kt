package com.whitecorp.bitcoinwallet.util

/**
 * ELEANOR Application Metadata
 * Version: 1.0.0
 * GitHub Account: @ivosicek123
 * ORCID ID: 0009-0007-4808-8653
 */
object AppConfig {
    const val APP_NAME = "ELEANOR"
    const val APP_VERSION = "1.0.0"
    const val GITHUB_ACCOUNT = "ivosicek123"
    const val ORCID_ID = "0009-0007-4808-8653"
    const val REPOSITORY_URL = "https://github.com/ivosicek123/bitcoin-wallet-android"
    
    // Bitcoin Network Configuration
    const val BITCOIN_NETWORK = "mainnet" // or "testnet"
    const val BIP32_PATH = "m/44'/0'/0'/0/0" // BIP44 path for Bitcoin
    
    // Banking Configuration
    const val BANK_NAME = "Partners banka"
    const val BANK_ACCOUNT = "9999913120"
    
    // API Configuration
    const val BLOCKCHAIN_API_BASE_URL = "https://blockchain.info/"
    const val BLOCKCYPHER_API_BASE_URL = "https://api.blockcypher.com/v1/btc/main/"
    const val BANKING_API_BASE_URL = "https://api.partners-banka.cz/"
    const val MEMPOOL_API_BASE_URL = "https://mempool.space/api/"
    
    // Security Configuration
    const val KEY_ALIAS_PREFIX = "bitcoin_wallet_"
    const val ENCRYPTION_ALGORITHM = "AES/GCM/NoPadding"
    const val KEY_SIZE = 256 // bits
    
    // Transaction Configuration
    const val MIN_CONFIRMATIONS = 1
    const val TARGET_CONFIRMATIONS = 6
    
    // Withdrawal Configuration
    const val MIN_WITHDRAWAL_BTC = 0.001
    const val MAX_WITHDRAWAL_BTC = 10.0
    const val WITHDRAWAL_FEE_PERCENTAGE = 1.5
    
    // Timeouts
    const val API_TIMEOUT_SECONDS = 30L
    const val SESSION_TIMEOUT_MINUTES = 15L
}
