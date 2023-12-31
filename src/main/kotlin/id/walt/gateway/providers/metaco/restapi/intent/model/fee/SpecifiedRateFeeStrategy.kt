package id.walt.gateway.providers.metaco.restapi.intent.model.fee

import kotlinx.serialization.Serializable

@Serializable
data class SpecifiedRateFeeStrategy(
    val gasPrice: String,
) : FeeStrategy("SpecifiedRate")