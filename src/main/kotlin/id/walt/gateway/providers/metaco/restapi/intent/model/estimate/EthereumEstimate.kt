package id.walt.gateway.providers.metaco.restapi.intent.model.estimate

import kotlinx.serialization.Serializable

@Serializable
data class EthereumEstimate(
    val defaultedToBlockGasLimit: Boolean,
    val gas: String,
) : Estimate("Ethereum")