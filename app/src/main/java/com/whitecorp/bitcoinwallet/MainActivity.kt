package com.whitecorp.bitcoinwallet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.whitecorp.bitcoinwallet.databinding.ActivityMainBinding
import com.whitecorp.bitcoinwallet.security.BiometricAuthenticator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ELEANOR Bitcoin Wallet Application
 * Version: 1.0.0
 * GitHub Account: @ivosicek123
 * ORCID ID: 0009-0007-4808-8653
 * 
 * Main activity entry point for the Bitcoin wallet application
 * Handles biometric authentication and navigation
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    
    @Inject
    lateinit var biometricAuthenticator: BiometricAuthenticator
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        
        initializeApp()
    }
    
    /**
     * Initialize the application with biometric authentication
     */
    private fun initializeApp() {
        lifecycleScope.launch {
            if (biometricAuthenticator.isBiometricAvailable()) {
                biometricAuthenticator.authenticateWithBiometric(
                    this@MainActivity,
                    title = "ELEANOR Wallet",
                    subtitle = "Authenticate to access your Bitcoin wallet",
                    negativeButtonText = "Cancel"
                )
                
                // Observe authentication result
                biometricAuthenticator.authenticationResult.observe(this@MainActivity) { result ->
                    when (result) {
                        is BiometricAuthResult.Success -> {
                            Toast.makeText(
                                this@MainActivity,
                                "Authentication successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            loadWalletScreen()
                        }
                        is BiometricAuthResult.Error -> {
                            Toast.makeText(
                                this@MainActivity,
                                "Error: ${result.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        is BiometricAuthResult.Failed -> {
                            Toast.makeText(
                                this@MainActivity,
                                result.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            } else {
                loadWalletScreen()
            }
        }
    }
    
    /**
     * Load the wallet screen after authentication
     */
    private fun loadWalletScreen() {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.navigate(R.id.walletFragment)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }
}

// Result sealed class for biometric authentication
sealed class BiometricAuthResult {
    object Success : BiometricAuthResult()
    data class Error(val message: String) : BiometricAuthResult()
    data class Failed(val message: String) : BiometricAuthResult()
}