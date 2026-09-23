package com.whitecorp.bitcoinwallet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whitecorp.bitcoinwallet.api.WithdrawalResponse
import com.whitecorp.bitcoinwallet.data.repository.BankingRepository
import com.whitecorp.bitcoinwallet.util.AppConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Withdrawal ViewModel
 * Manages Bitcoin to banking withdrawal operations
 * GitHub Account: @ivosicek123
 * ORCID ID: 0009-0007-4808-8653
 */
@HiltViewModel
class WithdrawalViewModel @Inject constructor(
    private val bankingRepository: BankingRepository
) : ViewModel() {
    
    private val _withdrawalResponse = MutableLiveData<WithdrawalResponse?>()
    val withdrawalResponse: LiveData<WithdrawalResponse?> = _withdrawalResponse
    
    private val _exchangeRate = MutableLiveData<Double>()
    val exchangeRate: LiveData<Double> = _exchangeRate
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage
    
    private val _successMessage = MutableLiveData<String?>()
    val successMessage: LiveData<String?> = _successMessage
    
    /**
     * Load current BTC to CZK exchange rate
     */
    fun loadExchangeRate() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                bankingRepository.getExchangeRate().onSuccess { response ->
                    _exchangeRate.value = response.rate
                    _errorMessage.value = null
                }.onFailure { error ->
                    _errorMessage.value = "Failed to load exchange rate: ${error.message}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Create withdrawal request to Partners banka
     */
    fun createWithdrawal(
        bitcoinAddress: String,
        amountBtc: Double,
        bankAccount: String = AppConfig.BANK_ACCOUNT,
        bankCode: String = "0100", // Partners banka code
        accountHolderName: String,
        apiKey: String,
        apiSecret: String
    ) {
        viewModelScope.launch {
            try {
                // Validate amount
                if (amountBtc < AppConfig.MIN_WITHDRAWAL_BTC || amountBtc > AppConfig.MAX_WITHDRAWAL_BTC) {
                    _errorMessage.value = "Amount must be between ${AppConfig.MIN_WITHDRAWAL_BTC} and ${AppConfig.MAX_WITHDRAWAL_BTC} BTC"
                    return@launch
                }
                
                _isLoading.value = true
                bankingRepository.createWithdrawal(
                    bitcoinAddress = bitcoinAddress,
                    amountBtc = amountBtc,
                    bankAccount = bankAccount,
                    bankCode = bankCode,
                    accountHolderName = accountHolderName,
                    apiKey = apiKey,
                    apiSecret = apiSecret
                ).onSuccess { response ->
                    _withdrawalResponse.value = response
                    _successMessage.value = "Withdrawal initiated: ${response.withdrawalId}"
                    _errorMessage.value = null
                }.onFailure { error ->
                    _errorMessage.value = "Withdrawal failed: ${error.message}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Calculate CZK amount from BTC
     */
    fun calculateCZKAmount(btcAmount: Double, rate: Double): Double {
        return btcAmount * rate
    }
    
    /**
     * Calculate withdrawal fee
     */
    fun calculateFee(amountCzk: Double): Double {
        return amountCzk * (AppConfig.WITHDRAWAL_FEE_PERCENTAGE / 100)
    }
    
    /**
     * Clear messages
     */
    fun clearMessages() {
        _errorMessage.value = null
        _successMessage.value = null
    }
}
