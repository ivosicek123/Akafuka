package com.whitecorp.bitcoinwallet.data.repository

import com.whitecorp.bitcoinwallet.api.BankingAPIService
import com.whitecorp.bitcoinwallet.api.WithdrawalRequest
import com.whitecorp.bitcoinwallet.api.WithdrawalResponse
import com.whitecorp.bitcoinwallet.api.WithdrawalStatusResponse
import com.whitecorp.bitcoinwallet.api.ExchangeRateResponse
import com.whitecorp.bitcoinwallet.security.KeyManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Banking Repository
 * Handles all banking operations including withdrawals and account management
 * GitHub Account: @ivosicek123
 * ORCID ID: 0009-0007-4808-8653
 */
@Singleton
class BankingRepository @Inject constructor(
    private val bankingAPIService: BankingAPIService,
    private val keyManager: KeyManager
) {
    
    /**
     * Create a withdrawal request to Partners banka
     */
    suspend fun createWithdrawal(
        bitcoinAddress: String,
        amountBtc: Double,
        bankAccount: String,
        bankCode: String,
        accountHolderName: String,
        apiKey: String,
        apiSecret: String
    ): Result<WithdrawalResponse> = try {
        val authorization = "Bearer $apiSecret"
        val request = WithdrawalRequest(
            bitcoinAddress = bitcoinAddress,
            amountBtc = amountBtc,
            bankAccount = bankAccount,
            bankCode = bankCode,
            accountHolderName = accountHolderName
        )
        
        val response = bankingAPIService.createWithdrawal(
            authorization = authorization,
            apiKey = apiKey,
            request = request
        )
        
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    /**
     * Get withdrawal status
     */
    suspend fun getWithdrawalStatus(
        withdrawalId: String,
        apiKey: String,
        apiSecret: String
    ): Result<WithdrawalStatusResponse> = try {
        val authorization = "Bearer $apiSecret"
        val response = bankingAPIService.getWithdrawalStatus(
            withdrawalId = withdrawalId,
            authorization = authorization,
            apiKey = apiKey
        )
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    /**
     * Get current BTC to CZK exchange rate
     */
    suspend fun getExchangeRate(): Result<ExchangeRateResponse> = try {
        val response = bankingAPIService.getExchangeRate()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    /**
     * Store API credentials securely
     */
    fun storeApiCredentials(apiKey: String, apiSecret: String) {
        keyManager.storePrivateKey("banking_api_key", apiKey)
        keyManager.storePrivateKey("banking_api_secret", apiSecret)
    }
    
    /**
     * Retrieve API credentials securely
     */
    fun retrieveApiCredentials(): Pair<String?, String?> {
        val apiKey = keyManager.retrievePrivateKey("banking_api_key")
        val apiSecret = keyManager.retrievePrivateKey("banking_api_secret")
        return Pair(apiKey, apiSecret)
    }
    
    /**
     * Clear stored API credentials
     */
    fun clearApiCredentials() {
        keyManager.deletePrivateKey("banking_api_key")
        keyManager.deletePrivateKey("banking_api_secret")
    }
}
