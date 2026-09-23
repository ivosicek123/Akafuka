package com.whitecorp.bitcoinwallet.data.repository

import com.whitecorp.bitcoinwallet.api.BitcoinAPIService
import com.whitecorp.bitcoinwallet.api.TransactionResponse
import com.whitecorp.bitcoinwallet.security.KeyManager
import org.bitcoinj.core.Address
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.params.MainNetParams
import org.bitcoinj.params.TestNet3Params
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Bitcoin Repository
 * Handles all Bitcoin wallet operations including key management and transactions
 * GitHub Account: @ivosicek123
 * ORCID ID: 0009-0007-4808-8653
 */
@Singleton
class BitcoinRepository @Inject constructor(
    private val bitcoinAPIService: BitcoinAPIService,
    private val keyManager: KeyManager
) {
    
    private val networkParameters: NetworkParameters = MainNetParams.get()
    
    /**
     * Generate a new Bitcoin key pair
     */
    fun generateNewKeyPair(): Pair<String, String> {
        val key = ECKey()
        val privateKeyHex = key.privateKeyAsHex
        val address = key.toAddress(networkParameters).toBase58()
        
        // Store private key securely
        keyManager.storePrivateKey(address, privateKeyHex)
        
        return Pair(address, key.publicKeyAsHex)
    }
    
    /**
     * Get address balance from blockchain
     */
    suspend fun getAddressBalance(address: String): Result<Long> = try {
        val balanceString = bitcoinAPIService.getAddressBalance(address)
        val balance = balanceString.toLongOrNull() ?: 0L
        Result.success(balance)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    /**
     * Get total received by address
     */
    suspend fun getTotalReceivedByAddress(address: String): Result<Long> = try {
        val totalString = bitcoinAPIService.getTotalReceivedByAddress(address)
        val total = totalString.toLongOrNull() ?: 0L
        Result.success(total)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    /**
     * Broadcast transaction to the network
     */
    suspend fun broadcastTransaction(
        txHex: String,
        blockcypherToken: String
    ): Result<TransactionResponse> = try {
        val request = com.whitecorp.bitcoinwallet.api.BroadcastTransactionRequest(tx = txHex)
        val response = bitcoinAPIService.broadcastTransaction(request, blockcypherToken)
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    /**
     * Get transaction details
     */
    suspend fun getTransaction(
        txHash: String,
        blockcypherToken: String
    ): Result<TransactionResponse> = try {
        val response = bitcoinAPIService.getTransaction(txHash, blockcypherToken)
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }
    
    /**
     * Retrieve private key for signing
     */
    fun retrievePrivateKey(address: String): String? {
        return keyManager.retrievePrivateKey(address)
    }
    
    /**
     * Delete wallet (clear all keys)
     */
    fun deleteWallet(address: String) {
        keyManager.deletePrivateKey(address)
    }
    
    /**
     * Check if address exists in wallet
     */
    fun addressExists(address: String): Boolean {
        return keyManager.keyExists(address)
    }
}
