package com.whitecorp.bitcoinwallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whitecorp.bitcoinwallet.data.repository.BitcoinRepository
import com.whitecorp.bitcoinwallet.util.AppConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Wallet ViewModel
 * Manages Bitcoin wallet state and operations
 * GitHub Account: @ivosicek123
 * ORCID ID: 0009-0007-4808-8653
 */
@HiltViewModel
class WalletViewModel @Inject constructor(
    private val bitcoinRepository: BitcoinRepository
) : ViewModel() {
    
    private val _walletAddress = MutableLiveData<String>()
    val walletAddress: LiveData<String> = _walletAddress
    
    private val _walletBalance = MutableLiveData<Long>()
    val walletBalance: LiveData<Long> = _walletBalance
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    
    /**
     * Create a new Bitcoin wallet
     */
    fun createNewWallet() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val (address, _) = bitcoinRepository.generateNewKeyPair()
                _walletAddress.value = address
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to create wallet: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Refresh wallet balance
     */
    fun refreshBalance(address: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                bitcoinRepository.getAddressBalance(address).onSuccess { balance ->
                    _walletBalance.value = balance
                    _errorMessage.value = null
                }.onFailure { error ->
                    _errorMessage.value = "Failed to fetch balance: ${error.message}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Convert satoshi to BTC
     */
    fun satoshiToBTC(satoshi: Long): Double {
        return satoshi / 100_000_000.0
    }
    
    /**
     * Convert BTC to satoshi
     */
    fun btcToSatoshi(btc: Double): Long {
        return (btc * 100_000_000).toLong()
    }
    
    /**
     * Clear error message
     */
    fun clearError() {
        _errorMessage.value = null
    }
}
